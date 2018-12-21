package king.greg.advent_2018;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Day20 {

	Map<Point, Room> map = new HashMap<Point, Room>();

	class Room {
		@Override
		public String toString() {
			return "Room [north=" + north + ", south=" + south + ", east=" + east + ", west=" + west + "]";
		}
		boolean north = false;
		boolean south = false;
		boolean east = false;
		boolean west = false;
	}

	Day20(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					Point start = new Point(0, 0);
					map.put(start, new Room());
					path(lineJustFetched, new Point(0, 0), 0);
//					System.out.println(map);
//					printMap();
				}
			}
		} catch (IOException ioe) {

		}
	}
	
	public void printMap() {
		final int minX = map.keySet().stream().min(Comparator.comparing(Point::getX)).get().x;
		final int maxX = map.keySet().stream().max(Comparator.comparing(Point::getX)).get().x;
		final int minY = map.keySet().stream().min(Comparator.comparing(Point::getY)).get().y;
		final int maxY = map.keySet().stream().max(Comparator.comparing(Point::getY)).get().y;
		for(int y = maxY; y >= minY; y--) {
			StringBuilder top = new StringBuilder();
			StringBuilder middle = new StringBuilder();
			StringBuilder bottom = new StringBuilder();
			for(int x = minX; x <= maxX; x++) {
				final Room room = map.get(new Point(x,y));
				if (x == minX) {
					top.append("#");
					middle.append(null == room ? "?" : room.west ? "|" : "#");
					bottom.append("#");
				}
				top.append(null == room ? "??" : room.north ? "-#" : "##");
				middle.append((x ==0 && y == 0) ? "X" : ".");
				middle.append(null == room ? "?" : room.east ? "|" : "#");
				bottom.append(null == room ? "??" : room.south ? "-#" : "##");
			}
			if (y == maxY) {
				System.out.println(top.toString());
			}
			System.out.println(middle.toString());
			System.out.println(bottom.toString());
		}
	}

	public int getMaxDistance(final Point startPoint) {
		final Queue<Point> pointQueue = new LinkedList<Point>();
		final Set<Point> visitedPoints = new HashSet<Point>();
		final Map<Point, Integer> distances = new HashMap<Point,Integer>();
		
		distances.put(startPoint, 0);
		pointQueue.add(startPoint);
		Point current = null;
		
		while(!pointQueue.isEmpty()) {
			current = pointQueue.remove();
			
			if (!visitedPoints.contains(current)) {
				visitedPoints.add(current);
				int distance = distances.get(current);
				final Room currentRoom = map.get(current);
				if(currentRoom.north) {
					final Point newPoint = new Point(current.x,current.y+1);
					if (null == distances.get(newPoint) || distances.get(newPoint) > distance + 1) {
						distances.put(newPoint, distance + 1);
						pointQueue.add(newPoint);
					}
				}
				if(currentRoom.east) {
					final Point newPoint = new Point(current.x+1,current.y);
					if (null == distances.get(newPoint) || distances.get(newPoint) > distance + 1) {
						distances.put(newPoint, distance + 1);
						pointQueue.add(newPoint);
					}
				}
				if(currentRoom.west) {
					final Point newPoint = new Point(current.x-1,current.y);
					if (null == distances.get(newPoint) || distances.get(newPoint) > distance + 1) {
						distances.put(newPoint, distance + 1);
						pointQueue.add(newPoint);
					}
				}
				if(currentRoom.south) {
					final Point newPoint = new Point(current.x,current.y-1);
					if (null == distances.get(newPoint) || distances.get(newPoint) > distance + 1) {
						distances.put(newPoint, distance + 1);
						pointQueue.add(newPoint);
					}
				}
			}
		}
		
//		return distances.values().stream().max(Comparator.naturalOrder()).get();
		return (int) distances.values().stream().filter(s -> s >= 1000).count();
	}

	public int path(final String pathRegex, final Point startPoint, final int offset) {
		final List<Point> endPoints = new ArrayList<Point>();
		Point currentPoint = new Point(startPoint);
		for (int i = offset; i < pathRegex.length(); i++) {
			Point newPoint = new Point(currentPoint);
			Room currentRoom = map.get(currentPoint);
//			System.out.print(pathRegex.charAt(i));
			switch (pathRegex.charAt(i)) {
			case 'N':
				newPoint.y++;
				currentRoom.north = true;
				getRoom(newPoint).south = true;
				currentPoint = newPoint;
				break;
			case 'E':
				newPoint.x++;
				currentRoom.east = true;
				getRoom(newPoint).west = true;
				currentPoint = newPoint;
				break;
			case 'W':
				newPoint.x--;
				currentRoom.west = true;
				getRoom(newPoint).east = true;
				currentPoint = newPoint;
				break;
			case 'S':
				newPoint.y--;
				currentRoom.south = true;
				getRoom(newPoint).north = true;
				currentPoint = newPoint;
				break;
			case '(':
				i = path(pathRegex, currentPoint, i+1);
//				currentPoint = newPoint;
				break;
//				return 0;
			case '|':
				endPoints.add(currentPoint);
				currentPoint = startPoint;
				break;
			case ')':
//				for (Point endpoint: endPoints) {
//					path(pathRegex,endpoint, i+1);
//				}
				return i;
//				break;
			}
		}
		return 0;
	}
	
	private Room getRoom(final Point point) {
		Room room = map.get(point);
		if (null == room) {
			room = new Room();
			map.put(point, room);
		}
		return room;
	}

}
