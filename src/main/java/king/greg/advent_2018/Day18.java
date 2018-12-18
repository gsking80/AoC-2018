package king.greg.advent_2018;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 {

	Map<Point, Acre> woods = new HashMap<Point, Acre>();
	int maxX;
	int maxY;

	Day18(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			int y = 0;
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final char[] line = lineJustFetched.toCharArray();
					for (int x = 0; x < line.length; x++) {
						woods.put(new Point(x, y), new Acre(line[x]));
					}
					maxX = line.length;
				}
				y++;
			}
			maxY = y;
			printMap();
		} catch (IOException ioe) {

		}
	}

	public void calculateNewWoods() {
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				Point testPoint = new Point(x, y);
				Acre testAcre = woods.get(testPoint);
				int trees = 0;
				int lumberyards = 0;
				for (int xX = x - 1; xX <= x + 1; xX++) {
					for (int yY = y - 1; yY <= y + 1; yY++) {
						if (xX == x && yY == y) {
							continue;
						}
						Acre neighbor = woods.get(new Point(xX, yY));
						if (null == neighbor) {
							continue;
						} else if (neighbor.getCurrent() == '|') {
							trees++;
						} else if (neighbor.getCurrent() == '#') {
							lumberyards++;
						}
					}
				}
				switch (testAcre.getCurrent()) {
				case '.':
					if (trees >= 3) {
						testAcre.setNext('|');
					}
					break;
				case '|':
					if (lumberyards >= 3) {
						testAcre.setNext('#');
					}
					break;
				case '#':
					if (trees < 1 || lumberyards < 1) {
						testAcre.setNext('.');
					}
				}
			}
		}
	}

	public void updateWoods() {
		for (Acre acre : woods.values()) {
			acre.update();
		}
	}

	public int advanceWoods(final int minutes) {
		int oldScore = scoreWoods();
		for (int i = 1; i <= minutes; i++) {
			calculateNewWoods();
			updateWoods();
			// printMap();
			int score = scoreWoods();
			int difference = score - oldScore;
//			System.out.println("Minutes: " + i + ", score: " + score + ", difference: " + (score - oldScore));
			System.out.println(i + ", " + score + ", " + difference);
			oldScore = score;
		}
		return scoreWoods();
	}

	public int bigScore(final int minutes) {

		final int[] scoreArray = new int[] { 229680, 226135, 227160, 225164, 224237, 215380, 210000, 205114, 204336,
				196350, 198990, 197208, 200772, 199398, 202124, 198660, 202070, 200690, 206581, 206746, 213624, 214375,
				218544, 217408, 222534, 222662, 226914, 226914 };
		
		final int minutesToGo = minutes - 7910;
		return scoreArray[minutesToGo % scoreArray.length];
	}

	public int scoreWoods() {
		int trees = 0;
		int lumberyards = 0;
		for (Acre acre : woods.values()) {
			switch (acre.getCurrent()) {
			case '|':
				trees++;
				break;
			case '#':
				lumberyards++;
				break;
			}
		}
		return trees * lumberyards;
	}

	public void printMap() {
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX; x++) {
				System.out.print(woods.get(new Point(x, y)));
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}

	class Acre {
		char current;
		char next;

		Acre(final char current) {
			this.current = current;
			this.next = current;
		}

		@Override
		public String toString() {
			return Character.toString(current);
		}

		public char getCurrent() {
			return current;
		}

		public void setCurrent(char current) {
			this.current = current;
		}

		public char getNext() {
			return next;
		}

		public void setNext(char next) {
			this.next = next;
		}

		public void update() {
			current = next;
		}
	}
}
