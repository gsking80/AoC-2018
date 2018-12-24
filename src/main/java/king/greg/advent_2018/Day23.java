package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day23 {

	final Set<Nanobot> nanobots = new HashSet<Nanobot>();
	final Map<Integer[], Integer> ranges = new HashMap<Integer[], Integer>();

	class Nanobot {
		final int x;
		final int y;
		final int z;
		final int signalRadius;

		Nanobot(final int x, final int y, final int z, final int signalRadius) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.signalRadius = signalRadius;
		}
	}

	Day23(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final String[] inputStrings = lineJustFetched.split("pos=<|,|>, r=");
					nanobots.add(new Nanobot(Integer.valueOf(inputStrings[1]), Integer.valueOf(inputStrings[2]),
							Integer.valueOf(inputStrings[3]), Integer.valueOf(inputStrings[4])));
				}
			}
		} catch (IOException ioe) {

		}
	}

	public Nanobot findStrongest() {
		Nanobot strongest = null;
		for (Nanobot test : nanobots) {
			if (null == strongest || test.signalRadius > strongest.signalRadius) {
				strongest = test;
			}
		}
		return strongest;
	}

	public int nanobotsWithinRange(final Nanobot botPrime) {
		int count = 0;
		for (Nanobot test : nanobots) {
			if ((Math.abs(botPrime.x - test.x) + Math.abs(botPrime.y - test.y)
					+ Math.abs(botPrime.z - test.z)) <= botPrime.signalRadius) {
				count++;
			}
		}
		return count;
	}

	public int inRangeOfNanobots(final int[] coordinate) {
		int count = 0;
		for (Nanobot test : nanobots) {
			if ((Math.abs(coordinate[0] - test.x) + Math.abs(coordinate[1] - test.y)
					+ Math.abs(coordinate[2] - test.z)) <= test.signalRadius) {
				count++;
			}
		}
		return count;
	}

	public int distanceToOptimalPosition() {
		final int[] coordinate = findOptimalPosition();
		return Math.abs(coordinate[0])+Math.abs(coordinate[1])+Math.abs(coordinate[2]);
	}
	
	public void fillMap() {
		final Set<int[]> counted = new HashSet<int[]>();
		for (Nanobot bot: nanobots) {
			
		}
	}

	public int[] findOptimalPosition() {
		final int minX = nanobots.stream().min(Comparator.comparingInt(bot -> bot.x)).get().x;
		final int maxX = nanobots.stream().max(Comparator.comparingInt(bot -> bot.x)).get().x;
		final int minY = nanobots.stream().min(Comparator.comparingInt(bot -> bot.y)).get().y;
		final int maxY = nanobots.stream().max(Comparator.comparingInt(bot -> bot.y)).get().y;
		final int minZ = nanobots.stream().min(Comparator.comparingInt(bot -> bot.z)).get().z;
		final int maxZ = nanobots.stream().max(Comparator.comparingInt(bot -> bot.z)).get().z;

		int count = 0;
		final int[] coordinate = new int[3];
		for (int x = minX; x <= maxX; x++) {
			System.out.println("++++++ "+ x);
			for (int y = minY; y <= maxY; y++) {
				System.out.println("--- "+ y);
				for (int z = minZ; z <= maxZ; z++) {
					System.out.println(maxZ - z);
						int testCount = inRangeOfNanobots(new int[] { x, y, z });
						if (testCount > count) {
							count = testCount;
							coordinate[0] = x;
							coordinate[1] = y;
							coordinate[2] = z;
						}
				}
			}
		}
		return coordinate;
	}

}
