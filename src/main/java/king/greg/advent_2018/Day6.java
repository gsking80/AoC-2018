package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day6 {

	public class Coordinate {

		public Character getName() {
			return name;
		}

		public void setName(Character name) {
			this.name = name;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return "Coordinate [name=" + name + ", x=" + x + ", y=" + y + "]";
		}

		Character name;
		int x;
		int y;

		Coordinate(final Character name, final int x, final int y) {
			this.name = name;
			this.x = x;
			this.y = y;
		}

	}

	final List<Coordinate> centers = new ArrayList<Coordinate>();
	Character[][] map;
	int maxX = 0;
	int maxY = 0;

	public void loadCoordinates(final FileReader filereader) {
		
		try {
			final BufferedReader buf = new BufferedReader(filereader);
		
			Character label = 'A';
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					break;
				} else {
					final String[] coordinateArray = lineJustFetched.split(", ");
					int x = Integer.valueOf(coordinateArray[0]);
					int y = Integer.valueOf(coordinateArray[1]);
					centers.add(new Coordinate(label++,x,y));
					if (x > maxX) {
						maxX = x;
					}
					if (y > maxY) {
						maxY = y;
					}
				}
			}
			System.out.println(centers);
			maxX += 2;
			maxY += 2;
			map = new Character[maxX][maxY];
		} catch (IOException ioe) {
			
		}
	}
	
	public void fillMap() {
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				int distance = Integer.MAX_VALUE;
				Character closestCenter = ' ';
				int numClosest = 0;
				for (Coordinate center: centers) {
					final int trialDistance = Math.abs(x - center.getX()) + Math.abs(y - center.getY());
					if (trialDistance == distance) {
						numClosest++;
					} else if (trialDistance < distance) {
						distance = trialDistance;
						numClosest = 0;
						closestCenter = center.getName();
					}
				}
				if (numClosest > 0) {
					closestCenter = '.';
				}
				System.out.println(closestCenter + " closest to " + x + ","  + y);
				map[x][y] = closestCenter;
			}
		}
	}
	
	public void fillSafeMap(final int safeDistance) {
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				int totalDistance = 0;
				for (Coordinate center: centers) {
					totalDistance += Math.abs(x - center.getX()) + Math.abs(y - center.getY());
				}
				System.out.println(x + ","  + y + " totalDist= " + totalDistance);
				map[x][y] = totalDistance < safeDistance ? '#' : '.';
			}
		}
	}
	
	public int calculateSafeArea() throws Exception {
		int safeArea = 0;
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Character safe = map[x][y];
				System.out.print(safe);
				if (safe == '#') {
					if (x == 0 || y == 0 || x == maxX - 1 || y == maxY -1) {
						throw new Exception("MAP NOT BIG ENOUGH - " + x + ", " + y);
					} else {
						safeArea++;
					}
				}
			}
			System.out.println("");
		}
		return safeArea;
	}
	
	public int calculateLargestFiniteArea() {
		final Map<Character,Integer> areas = new HashMap<Character, Integer>();
		final Set<Character> infinites = new HashSet<Character>();
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				Character closest = map[x][y];
				if (x == 0 || y == 0 || x == maxX - 1 || y == maxY -1) {
					infinites.add(closest);
				} else {
					if (areas.containsKey(closest)) {
						areas.put(closest, areas.get(closest) + 1);
					} else {
						areas.put(closest, 1);
					}
				}
			}
		}
		int largestFiniteArea = 0;
		for (Map.Entry<Character, Integer> area :areas.entrySet()) {
			if (!infinites.contains(area.getKey())) {
				System.out.println(area.getKey() + " is " + area.getValue());
				if (area.getValue() > largestFiniteArea) {
					largestFiniteArea = area.getValue();
				}
			}
		}
		return largestFiniteArea;
	}
}
