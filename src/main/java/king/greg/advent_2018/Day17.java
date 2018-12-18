package king.greg.advent_2018;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day17 {

	final Set<Point> input = new HashSet<Point>();
	int minX = Integer.MAX_VALUE;
	int maxX = Integer.MIN_VALUE;
	int minY = Integer.MAX_VALUE;
	int maxY = Integer.MIN_VALUE;
	char[][] map;
	final Queue<Point> pourPoints = initQueue();
	final Set<Point> water = new HashSet<Point>();

	Day17(final FileReader fileReader) {
		loadInput(fileReader);
	}

	private PriorityQueue<Point> initQueue() {
		return new PriorityQueue<>(10, new Comparator<Point>() {

			@Override
			public int compare(Point arg0, Point arg1) {
				return Comparator.comparing(Point::getY).thenComparing(Point::getX).compare(arg0, arg1);
			}

		});
	}

	public int totalWater() {
		int totalWater = 0;
		for (int x = minX - 1; x <= maxX +1; x++) {
			for (int y = minY; y <= maxY; y++) {
				if ((map[y][x - minX + 1] == '|') || (map[y][x - minX + 1] == '~')) {
					totalWater++;
				}
			}
		}
		return totalWater;
	}
	
	public int restWater() {
		int totalWater = 0;
		for (int x = minX - 1; x <= maxX +1; x++) {
			for (int y = minY; y <= maxY; y++) {
				if (map[y][x - minX + 1] == '~') {
					totalWater++;
				}
			}
		}
		return totalWater;
	}
	
	public void start() {
		Point spring = new Point(500 - minX + 1, 0);
		pourPoints.add(spring);
		while (!pourPoints.isEmpty()) {
			Point currentFlow = pourPoints.remove();
//			System.out.println("Evaluating point: " + currentFlow);
			if (currentFlow.y + 1 > maxY) { // reached the bottom here
				continue;
			} else if (map[currentFlow.y + 1][currentFlow.x] == '|') { // water already below.
				continue;
			} else if (map[currentFlow.y + 1][currentFlow.x] == '.') { // sand below!
				map[currentFlow.y + 1][currentFlow.x] = '|';
				pourPoints.add(new Point(currentFlow.x, currentFlow.y + 1));
			} else if ((map[currentFlow.y][currentFlow.x - 1] == '.')
					&& (map[currentFlow.y][currentFlow.x + 1] == '.')) { // sand to both sides!
				map[currentFlow.y][currentFlow.x - 1] = '|';
				pourPoints.add(new Point(currentFlow.x - 1, currentFlow.y));
				map[currentFlow.y][currentFlow.x + 1] = '|';
				pourPoints.add(new Point(currentFlow.x + 1, currentFlow.y));
			} else if (map[currentFlow.y][currentFlow.x - 1] == '.') { // sand to the left!
				map[currentFlow.y][currentFlow.x - 1] = '|';
				pourPoints.add(new Point(currentFlow.x - 1, currentFlow.y));
			} else if (map[currentFlow.y][currentFlow.x + 1] == '.') { // sand to the right!
				map[currentFlow.y][currentFlow.x + 1] = '|';
				pourPoints.add(new Point(currentFlow.x + 1, currentFlow.y));
			} else if (map[currentFlow.y][currentFlow.x + 1] == '|') { // check back to the right
				pourPoints.add(new Point(currentFlow.x + 1, currentFlow.y));
			} else if (map[currentFlow.y][currentFlow.x + 1] == '#') { // time to fill up?
				while (map[currentFlow.y][currentFlow.x] == '|') {
					currentFlow.x--;
				}
				if (map[currentFlow.y][currentFlow.x] == '#') {
					currentFlow.x++;
					while (map[currentFlow.y][currentFlow.x] == '|') {
						map[currentFlow.y][currentFlow.x] = '~';
						if (map[currentFlow.y - 1][currentFlow.x] == '|') { // here's our new flow point
							pourPoints.add(new Point(currentFlow.x, currentFlow.y - 1));
						}
						currentFlow.x++;
					}
				}
			}
		}
//		printMap();
	}

	private void loadInput(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final String[] lineCoordinates = lineJustFetched.split("=|, ");
//					System.out.println(Arrays.toString(lineCoordinates));
					if (lineCoordinates[0].equals("x")) {
						loadLine(lineCoordinates[1], lineCoordinates[3]);
					} else {
						loadLine(lineCoordinates[3], lineCoordinates[1]);
					}
				}
			}
		} catch (IOException ioe) {

		}
//		System.out.println(input);
		map = new char[maxY + 1][(maxX - minX) + 3];
		for (char[] row : map) {
			Arrays.fill(row, '.');
		}
		// Special Characters
		map[0][500 - minX + 1] = '+';
		for (Point clay : input) {
			map[clay.y][clay.x - minX + 1] = '#';
		}
//		printMap();
	}

	private void loadLine(final String xCoords, final String yCoords) {
		int xLower;
		int xUpper;
		int yLower;
		int yUpper;

		if (xCoords.contains("..")) {
			final String[] xLine = xCoords.split("\\.\\.");
			xLower = Integer.valueOf(xLine[0]);
			xUpper = Integer.valueOf(xLine[1]);
		} else {
			xLower = Integer.valueOf(xCoords);
			xUpper = xLower;
		}
		if (yCoords.contains("..")) {
			final String[] yLine = yCoords.split("\\.\\.");
			yLower = Integer.valueOf(yLine[0]);
			yUpper = Integer.valueOf(yLine[1]);
		} else {
			yLower = Integer.valueOf(yCoords);
			yUpper = yLower;
		}
		for (int x = xLower; x <= xUpper; x++) {
			for (int y = yLower; y <= yUpper; y++) {
				input.add(new Point(x, y));
			}
		}
		if (xLower < minX) {
			minX = xLower;
		}
		if (xUpper > maxX) {
			maxX = xUpper;
		}
		if (yLower < minY) {
			minY = yLower;
		}
		if (yUpper > maxY) {
			maxY = yUpper;
		}
	}

	public void printMap() {
		for (int y = 0; y < map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				System.out.print(map[y][x]);
			}
			System.out.print('\n');
		}
	}

}
