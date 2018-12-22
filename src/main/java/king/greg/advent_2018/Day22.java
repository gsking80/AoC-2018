package king.greg.advent_2018;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Day22 {

	final Map<Point, Integer> caveMap = new HashMap<Point, Integer>();
	final int depth;
	final Point target;

	Day22(final int depth, final Point target) {
		this.depth = depth;
		this.target = target;
		caveMap.put(target, 0);
	}

	public int calculateRiskSmallestRectangle() {
		int risk = 0;
		for (int x = 0; x <= target.x; x++) {
			for (int y = 0; y <= target.y; y++) {
				risk += erosionLevel(getGeologicIndex(new Point(x, y))) % 3;
			}
		}

		return risk;
	}

	public int getGeologicIndex(final Point region) {
		Integer geologicIndex = caveMap.get(region);
		if (null == geologicIndex) {
			if (region.y == 0) {
				if (region.x == 0) {
					geologicIndex = 0;
				} else {
					geologicIndex = region.x * 16807;
				}
			} else if (region.x == 0) {
				geologicIndex = region.y * 48271;
			} else {
				geologicIndex = erosionLevel(getGeologicIndex(new Point(region.x - 1, region.y)))
						* erosionLevel(getGeologicIndex(new Point(region.x, region.y - 1)));
			}
			caveMap.put(region, geologicIndex);
		}

		return geologicIndex;
	}

	public int erosionLevel(final int geologicIndex) {
		return (geologicIndex + depth) % 20183;
	}

	public int calculateFastestTime() {
		final List<Node> fastestPath = aStar(new Node(new Point(0, 0), 'T'), new Node(target, 'T'));
		printPath(fastestPath);
		int time = fastestPath.get(fastestPath.size()-1).getTimeToStart();
		return time;
	}
	
	public void printPath(final List<Node> path) {
		for (final Node node: path) {
			System.out.println(node);
		}
	}

	class Node {
		@Override
		public String toString() {
			return "Node [region=" + region + ", equippedTool=" + equippedTool + ", TimeToStart=" + TimeToStart
					+ ", predictedTimeToTarget=" + predictedTimeToTarget + ", totalTime=" + totalTime + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + equippedTool;
			result = prime * result + ((region == null) ? 0 : region.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (equippedTool != other.equippedTool)
				return false;
			if (region == null) {
				if (other.region != null)
					return false;
			} else if (!region.equals(other.region))
				return false;
			return true;
		}

		Point region;
		Character equippedTool;

		Node(Point region, Character equippedTool) {
			this.region = region;
			this.equippedTool = equippedTool;
		}

		private int TimeToStart;
		private int predictedTimeToTarget;
		private int totalTime;

		public int getTimeToStart() {
			return TimeToStart;
		}

		public void setTimeToStart(int timeToStart) {
			TimeToStart = timeToStart;
		}

		public int getPredictedTimeToTarget() {
			return predictedTimeToTarget;
		}

		public void setPredictedTimeToTarget(int predictedTimeToTarget) {
			this.predictedTimeToTarget = predictedTimeToTarget;
		}

		public int getTotalTime() {
			return totalTime;
		}

		public void setTotalTime(int totalTime) {
			this.totalTime = totalTime;
		}

		public int estimateTimeToTarget() {
			return (int) (Math.abs(region.x - target.x) + Math.abs(region.y - target.y));
		}

		private Day22 getOuterType() {
			return Day22.this;
		}

		public List<Node> findNeighbors() {
			final List<Node> neighbors = new ArrayList<Node>();
			// first change equipment.  Just get them all, we'll toss out the dupes
			switch (erosionLevel(getGeologicIndex(region)) % 3) {
			case 0: //rocky
				neighbors.add(new Node(region, 'T'));
				neighbors.add(new Node(region, 'C'));
				break;
			case 1: // wet
				neighbors.add(new Node(region, 'N'));
				neighbors.add(new Node(region, 'C'));
				break;
			case 2: // narrow
				neighbors.add(new Node(region, 'N'));
				neighbors.add(new Node(region, 'T'));
				break;
			}
			// now check reachable neighbors
			if (region.x > 0) {
				final Point neighborRegion = new Point(region.x - 1, region.y);
				if (canReach(neighborRegion)) {
					neighbors.add(new Node(neighborRegion, equippedTool));
				}
			}
			if (region.y > 0) {
				final Point neighborRegion = new Point(region.x, region.y-1);
				if (canReach(neighborRegion)) {
					neighbors.add(new Node(neighborRegion, equippedTool));
				}
			}
			final Point neighborRegionEast = new Point(region.x+1, region.y);
			if (canReach(neighborRegionEast)) {
				neighbors.add(new Node(neighborRegionEast, equippedTool));
			}
			final Point neighborRegionSouth = new Point(region.x, region.y+1);
			if (canReach(neighborRegionSouth)) {
				neighbors.add(new Node(neighborRegionSouth, equippedTool));
			}
			return neighbors;
		}
		
		private boolean canReach(final Point neighborRegion) {
			boolean canReach = false;
			final int terrain = erosionLevel(getGeologicIndex(neighborRegion)) % 3;
			switch(terrain) {
			case 0: //rocky
				if (equippedTool == 'C' || equippedTool == 'T') {
					canReach = true;
				}
				break;
			case 1: // wet
				if (equippedTool == 'C' || equippedTool == 'N') {
					canReach = true;
				}
				break;
			case 2: // narrow
				if (equippedTool == 'N' || equippedTool == 'T') {
					canReach = true;
				}
				break;
			}
			return canReach;
		}

		public int timeToNeighbor(final Node neighbor) {
			if (region == neighbor.region && equippedTool != neighbor.equippedTool) {
				return 7;
			} else if (region.x == neighbor.region.x && Math.abs(region.y - neighbor.region.y) == 1) {
				return 1;
			} else if (Math.abs(region.x - neighbor.region.x) == 1) {
				return 1;
			}
			throw new RuntimeException("These aren't neighbors! " + this + neighbor);
		}
	}

	private PriorityQueue<Node> initQueue() {
		return new PriorityQueue<>(10, new Comparator<Node>() {

			@Override
			public int compare(Node arg0, Node arg1) {
				return Comparator.comparing(Node::getTotalTime).compare(arg0, arg1);
			}

		});
	}

	public List<Node> aStar(final Node mouth, Node target) {

		// Setup for A*
		Map<Node, Node> parentMap = new HashMap<Node, Node>();
		Set<Node> visited = new HashSet<Node>();
		Map<Node, Integer> times = new HashMap<Node, Integer>();

		Queue<Node> priorityQueue = initQueue();

		// enque start node with distance 0
		mouth.setTimeToStart(0);
		mouth.setPredictedTimeToTarget(mouth.estimateTimeToTarget());
		times.put(mouth, 0);
		priorityQueue.add(mouth);
		Node current = null;

		while (!priorityQueue.isEmpty()) {
			current = priorityQueue.remove();

			if (!visited.contains(current)) {
				visited.add(current);
				if (current.equals(target)) {
					return reconstructPath(mouth, current, parentMap);
				}
				// iterate over the neighbors
				for (Node neighbor : current.findNeighbors()) {
					if (!visited.contains(neighbor)) {
						// new Node
						int predictedTimeFromNeighbor = neighbor.estimateTimeToTarget();
						int totalTime = current.getTimeToStart() + current.timeToNeighbor(neighbor)
								+ predictedTimeFromNeighbor;
						if (times.get(neighbor) == null || totalTime < times.get(neighbor)) {
							times.put(neighbor, totalTime);
							neighbor.setTimeToStart(current.getTimeToStart() + current.timeToNeighbor(neighbor));
							neighbor.setTotalTime(totalTime);
							neighbor.setPredictedTimeToTarget(predictedTimeFromNeighbor);
							parentMap.put(neighbor, current);
							priorityQueue.add(neighbor);
						}
					}
				}
			}
		}

		// goal unreachable
		return Collections.emptyList();
	}

	private List<Node> reconstructPath(Node mouth, Node target, Map<Node, Node> parentMap) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node currNode = target;
		while (!currNode.equals(mouth)) {
			path.addFirst(currNode);
			currNode = parentMap.get(currNode);
		}
		path.addFirst(mouth);
		return path;
	}

}
