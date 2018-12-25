package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import king.greg.advent_2018.Day15.Node;

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
		return findOptimalPosition();
	}

	public int findOptimalPosition() {
		final int minX = nanobots.stream().min(Comparator.comparingInt(bot -> bot.x)).get().x;
		final int maxX = nanobots.stream().max(Comparator.comparingInt(bot -> bot.x)).get().x;
		final int minY = nanobots.stream().min(Comparator.comparingInt(bot -> bot.y)).get().y;
		final int maxY = nanobots.stream().max(Comparator.comparingInt(bot -> bot.y)).get().y;
		final int minZ = nanobots.stream().min(Comparator.comparingInt(bot -> bot.z)).get().z;
		final int maxZ = nanobots.stream().max(Comparator.comparingInt(bot -> bot.z)).get().z;

		List<Integer> edges = new ArrayList<Integer>();
		edges.add(maxX - minX);
		edges.add(maxY - minY);
		edges.add(maxZ - minZ);

		int biggestEdge = Collections.max(edges);
		int edgePower = 0;
		while (biggestEdge > 0) {
			biggestEdge >>= 1;
			edgePower++;
		}
		final SearchCube initialCube = new SearchCube((int) Math.pow(2, edgePower), nanobots.size(), minX, minY, minZ);
		Queue<SearchCube> priorityQueue = initQueue();
		priorityQueue.add(initialCube);

		SearchCube current = null;
		while (!priorityQueue.isEmpty()) {
			current = priorityQueue.remove();
			if (current.edgeSize == 1) {
				return current.getDistanceFromOrigin();
			}
			int newEdge = current.getEdgeSize() / 2;
			for (int x = current.x; x < current.x + current.edgeSize; x += newEdge) {
				for (int y = current.y; y < current.y + current.edgeSize; y += newEdge) {
					for (int z = current.z; z < current.z + current.edgeSize; z += newEdge) {
						final int newBotsInRange = botsInRange(x,y,z, newEdge - 1);
//						System.out.println(x + "," + y + "," + z);
//						System.out.println(newBotsInRange + " -- " + x + "," + y + ","
//								+ x + " -- " + newEdge);
						priorityQueue.add(new SearchCube(newEdge, newBotsInRange, x,y,z));
					}
				}
			}
		}

		return -1;
	}

	public int botsInRange(final int x, final int y, final int z, final int edgeSize) {
		int inRange = 0;
		for (final Nanobot bot : nanobots) {
			int distance = 0;
			if (bot.x < x) {
				distance += x - bot.x;
			}
			if (bot.x > x + edgeSize) {
				distance += bot.x - (x + edgeSize);
			}
			if (bot.y < y) {
				distance += y - bot.y;
			}
			if (bot.y > y + edgeSize) {
				distance += bot.y - (y + edgeSize);
			}
			if (bot.z < z) {
				distance += z - bot.z;
			}
			if (bot.z > z + edgeSize) {
				distance += bot.z - (z + edgeSize);
			}
			if (distance <= bot.signalRadius) {
				inRange++;
			}
		}
		return inRange;
	}

	class SearchCube {
		int x;
		int y;
		int z;
		int edgeSize;
		int botsInRange;

		SearchCube(int edgeSize, int botsInRange, int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.edgeSize = edgeSize;
			this.botsInRange = botsInRange;
		}

		public int getEdgeSize() {
			return edgeSize;
		}

		public void setEdgeSize(int edgeSize) {
			this.edgeSize = edgeSize;
		}

		public int getBotsInRange() {
			return botsInRange;
		}

		public void setBotsInRange(int botsInRange) {
			this.botsInRange = botsInRange;
		}

		public int getDistanceFromOrigin() {
			return Math.abs(x) + Math.abs(y) + Math.abs(z);
		}

	}

	private PriorityQueue<SearchCube> initQueue() {
		return new PriorityQueue<>(10, new Comparator<SearchCube>() {

			@Override
			public int compare(SearchCube arg0, SearchCube arg1) {
				return Comparator.comparing(SearchCube::getBotsInRange, Comparator.reverseOrder())
						.thenComparing(SearchCube::getDistanceFromOrigin).thenComparing(SearchCube::getEdgeSize)
						.compare(arg0, arg1);
			}

		});
	}

	public int manhattanDistance(final int[] a, final int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) + Math.abs(a[2] - b[2]);
	}

}
