package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Day25 {

	final LinkedList<int[]> points = new LinkedList<int[]>();

	public Day25(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final String[] inputStrings = lineJustFetched.split(",");
					points.add(new int[] { Integer.valueOf(inputStrings[0]), Integer.valueOf(inputStrings[1]),
							Integer.valueOf(inputStrings[2]), Integer.valueOf(inputStrings[3]) });
				}
			}
		} catch (IOException ioe) {

		}
	}

	public int constellationsCount() {
		int constellations = 0;
		while (!points.isEmpty()) {
			final LinkedList<int[]> currentConstellation = new LinkedList<int[]>();
			currentConstellation.add(points.removeFirst());
			constellations++;
			while (!currentConstellation.isEmpty()) {
				final int[] currentPoint = currentConstellation.removeFirst();
				final Iterator<int[]> pointsIter = points.iterator();
				while (pointsIter.hasNext()) {
					final int[] testPoint = pointsIter.next();
					if (manhattanDistance(currentPoint, testPoint) <=3) {
						currentConstellation.add(testPoint);
						pointsIter.remove();
					}
				}
			}

		}
		return constellations;
	}

	public int manhattanDistance(final int[] a, final int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]) + Math.abs(a[3] - b[3]);
	}

}
