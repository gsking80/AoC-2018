package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Day10 {

	final LinkedList<Point> points = new LinkedList<Point>();

	public class Point {
		@Override
		public String toString() {
			return "Point [position= <" + positionX + ", " + positionY + ">, velocity= <" + velocityX + ", " + velocityY
					+ ">]";
		}

		public int getPositionX() {
			return positionX;
		}

		public void setPositionX(int positionX) {
			this.positionX = positionX;
		}

		public int getPositionY() {
			return positionY;
		}

		public void setPositionY(int positionY) {
			this.positionY = positionY;
		}

		private int positionX;
		private int positionY;
		private int velocityX;
		private int velocityY;

		public Point(final String input) {
			final String[] inputArray = input.split("[<,>]");
			positionX = Integer.parseInt(inputArray[1].trim());
			positionY = Integer.parseInt(inputArray[2].trim());
			velocityX = Integer.parseInt(inputArray[4].trim());
			velocityY = Integer.parseInt(inputArray[5].trim());
		}

		public void update(final int steps) {
			positionX += velocityX * steps;
			positionY += velocityY * steps;
		}
	}

	public void loadPoints(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			while (true) {
				String lineJustFetched;

				lineJustFetched = buf.readLine();

				if (null == lineJustFetched) {
					break;
				} else {
					points.add(new Point(lineJustFetched));
				}
			}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatePoints(final int steps) {
		for (Point point : points) {
			point.update(steps);
		}
	}

	public void findMessage() {
		long lastPointArea = pointArea();
		int waitTime = 0;
		while(true) {
			updatePoints(1);
			long nextPointArea = pointArea();
			if (nextPointArea < lastPointArea) {
				lastPointArea = nextPointArea;
				waitTime++;
			} else {
				updatePoints(-1);
				break;
			}
		}
		System.out.println("Waited " + waitTime + " seconds.");
		plotPoints();
	}
	
	public void plotPoints() {
		Integer minX = points.stream().mapToInt(v -> v.getPositionX()).min().orElseThrow(NoSuchElementException::new);
		Integer maxX = points.stream().mapToInt(v -> v.getPositionX()).max().orElseThrow(NoSuchElementException::new);
		Integer minY = points.stream().mapToInt(v -> v.getPositionY()).min().orElseThrow(NoSuchElementException::new);
		Integer maxY = points.stream().mapToInt(v -> v.getPositionY()).max().orElseThrow(NoSuchElementException::new);
		
		Character[][] map = new Character[(maxX-minX) + 1][(maxY-minY) + 1];
		for (Point point: points) {
			map[point.getPositionX()-minX][point.getPositionY()-minY] = '#';
		}
		for (int y = minY; y < maxY + 1; y++) {
			for (int x = minX; x < maxX + 1; x++) {
				System.out.print(map[x - minX][y - minY] == null ? '.' : map[x - minX][y - minY]);
			}
			System.out.print('\n');
		}
	}

	public long pointArea() {

		Integer minY = points.stream().mapToInt(v -> v.getPositionY()).min().orElseThrow(NoSuchElementException::new);
		Integer maxY = points.stream().mapToInt(v -> v.getPositionY()).max().orElseThrow(NoSuchElementException::new);

		return (maxY - minY);

	}

}
