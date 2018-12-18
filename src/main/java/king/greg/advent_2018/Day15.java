package king.greg.advent_2018;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

public class Day15 {

	char[][] cave;
	List<Actor> actors = new ArrayList<Actor>();
	boolean gameOver = false;
	int rounds = 0;

	public int runGame() {
		while (!gameOver) {
			round();
		}
		System.out.println("Rounds: " + rounds);
		int remainingHP = 0;
		for (Actor actor: actors) {
			if (!actor.isDead()) {
				remainingHP += actor.getHitPoints();
			}
		}
		System.out.println("Remaining HP: " + remainingHP);
		return rounds * remainingHP;
	}

	public void round() {
		// Reading order
		printCave();
		Collections.sort(actors);
		System.out.println(actors);
		for (Actor actor : actors) {
			if (actor.isDead()) {
				continue;
			}
			if (actor.hasTargets()) {
				if (!actor.hasAdjacentTargets()) {
					actor.move();
					printCave();
				}
				if (actor.hasAdjacentTargets()) {
					actor.attack();
				}
			} else {
				System.out.println("Game over!");
				gameOver = true;
				return;
			}
//			printCave();
		}
		actors.removeIf(actor -> actor.isDead());
		rounds++;
	}

	public void readFile(final FileReader fileReader) {
		readFile(fileReader, 3);
	}
	
	public void readFile(final FileReader fileReader, final int elfPower) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			final List<char[]> caveList = new ArrayList<char[]>();
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					caveList.add(lineJustFetched.toCharArray());
				}
			}
			cave = new char[caveList.size()][];
			cave = caveList.toArray(cave);
			printCave();
			findActors(elfPower);
//			printCave();
		} catch (IOException ioe) {

		}
	}

	public void printCave() {
//		for (int y = 0; y < cave.length; y++) {
//			for (int x = 0; x < cave[y].length; x++) {
//				System.out.print(cave[y][x]);
//			}
//			System.out.print('\n');
//		}
	}

	public void findActors(final int elfPower) {
		for (int y = 0; y < cave.length; y++) {
			for (int x = 0; x < cave[y].length; x++) {

				switch (cave[y][x]) {
				case 'E':
					actors.add(new Actor(x, y, 'E', elfPower));
					break;
				case 'G':
					actors.add(new Actor(x, y, 'G'));
					break;
				}
			}
		}
	}

	class Actor implements Comparable<Actor> {

		@Override
		public String toString() {
			return "Actor [x=" + x + ", y=" + y + ", type=" + type + ", hp=" + hitPoints + "]";
		}

		private int x;
		private int y;
		private char type;
		private int hitPoints;
		private int attackPower;
		private boolean dead;

		public boolean isDead() {
			return dead;
		}

		public void setDead(boolean dead) {
			this.dead = dead;
		}

		public int getHitPoints() {
			return hitPoints;
		}

		public void setHitPoints(int hitPoints) {
			this.hitPoints = hitPoints;
		}

		public int getAttackPower() {
			return attackPower;
		}

		public void setAttackPower(int attackPower) {
			this.attackPower = attackPower;
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

		public char getType() {
			return type;
		}

		public void setType(char type) {
			this.type = type;
		}

		Actor(int x, int y, char type) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.hitPoints = 200;
			this.attackPower = 3;
			this.dead = false;
		}
		
		Actor(int x, int y, char type, int attackPower) {
			this.x = x;
			this.y = y;
			this.type = type;
			this.hitPoints = 200;
			this.attackPower = attackPower;
			this.dead = false;
		}

		@Override
		public int compareTo(Actor o) {
			return Comparator.comparing(Actor::getY).thenComparing(Actor::getX).compare(this, o);
		}

		public boolean hasAdjacentTargets() {
			for (Actor actor : actors) {
				if (canAttack(actor)) {
					return true;
				}
			}
			return false;
		}

		public boolean canAttack(final Actor actor) {
			if (actor.isDead() || actor.getType() == type) {
				return false;
			}
			final double distance = Math.abs(actor.getX() - getX()) + Math.abs(actor.getY() - getY());
			return distance <= 1;
		}

		public boolean hasTargets() {
			for (Actor actor : actors) {
				if (actor.getType() != this.getType() && !actor.isDead()) {
					return true;
				}
			}
			return false;
		}

		public boolean attack() {
			Actor target = null;
			for (Actor potentialTarget : actors) {
				if (canAttack(potentialTarget)) {
					if (null == target) {
						target = potentialTarget;
					} else if (potentialTarget.getHitPoints() < target.getHitPoints()) {
						target = potentialTarget;
					} else if (potentialTarget.getHitPoints() == target.getHitPoints()
							&& (potentialTarget.compareTo(target) < 0)) {
						target = potentialTarget;
					}
				}
			}
			return target.takeDamage(attackPower);
		}

		public boolean takeDamage(final int attackPower) {
			this.hitPoints -= attackPower;
			System.out.println("Hit target! " + this.x + "," + this.y + "   New HP: " + this.hitPoints);
			if (hitPoints <= 0) {
				printCave();
				System.out.println("Man down!");
				// This is the part 2 code.  Comment it out to get back to Part 1.
				if (this.type == 'E') {
					throw new RuntimeException("Elf power not high enough!");
				}
				cave[y][x] = '.';
				dead = true;
				printCave();
			}
			return true;
		}

		public boolean move() {
			final Point target = getNextStep();
			System.out.println(this.toString() + target);
			if (null != target) {
				cave[target.y][target.x] = type;
				cave[y][x] = '.';
				x = target.x;
				y = target.y;
			}
			return true;
		}

		public Point getNextStep() {
			final Set<Point> potentialDestinations = new HashSet<Point>();

			for (Actor potentialTarget : actors) {
				if (potentialTarget.getType() != this.getType()  && !potentialTarget.isDead()) {
					if (cave[potentialTarget.getY() - 1][potentialTarget.getX()] == '.') {
						potentialDestinations.add(new Point(potentialTarget.getX(), potentialTarget.getY() - 1));
					}
					if (cave[potentialTarget.getY() + 1][potentialTarget.getX()] == '.') {
						potentialDestinations.add(new Point(potentialTarget.getX(), potentialTarget.getY() + 1));
					}
					if (cave[potentialTarget.getY()][potentialTarget.getX() - 1] == '.') {
						potentialDestinations.add(new Point(potentialTarget.getX() - 1, potentialTarget.getY()));
					}
					if (cave[potentialTarget.getY()][potentialTarget.getX() + 1] == '.') {
						potentialDestinations.add(new Point(potentialTarget.getX() + 1, potentialTarget.getY()));
					}
				}
			}

			Set<List<Node>> potentialPaths = new HashSet<List<Node>>();
			for (Point target : potentialDestinations) {
				potentialPaths.add(aStar(new Node(new Point(this.getX(), this.getY())), new Node(target)));
			}

			// figure out which node to head to
			int shortestPath = Integer.MAX_VALUE;
			Point currentTarget = null;
			Point nextStep = null;
			for (List<Node> path : potentialPaths) {
				if (path.isEmpty()) {
					continue;
				}
				if (path.size() == shortestPath) {
					Point potentialTarget = path.get(path.size() - 1).getPoint();
					if ((potentialTarget.getY() < currentTarget.getY())
							|| (potentialTarget.getY() == currentTarget.getY()
									&& potentialTarget.getX() < currentTarget.getX())) {
						currentTarget = potentialTarget;
						nextStep = path.get(0).getPoint();
					}

				} else if (path.size() < shortestPath) {
					currentTarget = path.get(path.size() - 1).getPoint();
					nextStep = path.get(0).getPoint();
					shortestPath = path.size();
				}
			}
			return nextStep;
		}

		public List<Node> aStar(final Node start, final Node goal) {
//			System.out.println("Start: " + start + " end: " + goal);
			// Setup for A*
			Map<Node, Node> parentMap = new HashMap<Node, Node>();
			Set<Node> visited = new HashSet<Node>();
			Map<Node, Integer> distances = new HashMap<Node, Integer>();

			Queue<Node> priorityQueue = initQueue();

			// enque start node with distance 0
			start.setDistanceToStart(0);
			start.setPredictedDistance(start.estimateDistanceTo(goal));
			distances.put(start, 0);
			priorityQueue.add(start);
			Node current = null;

			while (!priorityQueue.isEmpty()) {
				current = priorityQueue.remove();

				if (!visited.contains(current)) {
					visited.add(current);
					if (current.equals(goal)) {
						return reconstructPath(start, goal, parentMap);
					}
					// iterate over the neighbors
					for (Node neighbor : current.findNeighbors()) {
						if (!visited.contains(neighbor)) {
							// new Node
							int predictedDistanceFromNeighbor = neighbor.estimateDistanceTo(goal);
							int totalDistance = current.getDistanceToStart() + 1 + predictedDistanceFromNeighbor;
							if (distances.get(neighbor) == null || totalDistance < distances.get(neighbor)) {
								distances.put(neighbor, totalDistance);
								neighbor.setDistanceToStart(current.getDistanceToStart() + 1);
								neighbor.setTotalDistance(totalDistance);
								neighbor.setPredictedDistance(predictedDistanceFromNeighbor);
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

		private PriorityQueue<Node> initQueue() {
			return new PriorityQueue<>(10, new Comparator<Node>() {

				@Override
				public int compare(Node arg0, Node arg1) {
					// Had to change this to distanceToStart instead of totalDistance.  I believe this makes it more Dijkstra than A*
					return Comparator.comparing(Node::getDistanceToStart).thenComparing(Node::getY).thenComparing(Node::getX).compare(arg0, arg1);
				}
				
			});
		}

		private List<Node> reconstructPath(Node start, Node goal, Map<Node, Node> parentMap) {
			LinkedList<Node> path = new LinkedList<Node>();
			Node currNode = goal;
			while (!currNode.equals(start)) {
				path.addFirst(currNode);
				currNode = parentMap.get(currNode);
			}
			// I don't really need to know start, I already have it.
			// path.addFirst(start);
//			System.out.println("Possible path: " + path);
			return path;
		}

	}

	class Node {

		@Override
		public String toString() {
			return "Node [point=" + point + ", distanceToStart=" + distanceToStart + ", predictedDistance="
					+ predictedDistance + "]";
		}

		@Override
		public int hashCode() {
			return point.hashCode();
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
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}

		public int estimateDistanceTo(Node target) {
			return (int) (Math.abs(this.getPoint().getX() - target.getPoint().getX())
					+ Math.abs(this.getPoint().getY() - target.getPoint().getY()));
		}

		final List<Node> findNeighbors() {
//			System.out.println("Neighbors of node: " + this);
			final List<Node> neighbors = new ArrayList<Node>();
			if (cave[(int) getPoint().getY() - 1][(int) getPoint().getX()] == '.') {
				neighbors.add(new Node(new Point((int) getPoint().getX(), (int) getPoint().getY() - 1)));
			}
//			printCave();
//			System.out.println(((int) getPoint().getY()) + "," + ((int) getPoint().getX() - 1));
//			System.out.println(" - " + cave[(int) getPoint().getY()][(int) getPoint().getX() - 1] );
			if (cave[(int) getPoint().getY()][(int) getPoint().getX() - 1] == '.') {
				neighbors.add(new Node(new Point((int) getPoint().getX() - 1, (int) getPoint().getY())));
			}
			if (cave[(int) getPoint().getY()][(int) getPoint().getX() + 1] == '.') {
				neighbors.add(new Node(new Point((int) getPoint().getX() + 1, (int) getPoint().getY())));
			}
			if (cave[(int) getPoint().getY() + 1][(int) getPoint().getX()] == '.') {
				neighbors.add(new Node(new Point((int) getPoint().getX(), (int) getPoint().getY() + 1)));
			}
			return neighbors;
		}

		private Point point;
		private int distanceToStart;
		private int predictedDistance;
		private int totalDistance;
		
		public int getX() {
			return point.x;
		}
		
		public int getY() {
			return point.y;
		}

		public int getTotalDistance() {
			return totalDistance;
		}

		public void setTotalDistance(int totalDistance) {
			this.totalDistance = totalDistance;
		}

		public int getPredictedDistance() {
			return predictedDistance;
		}

		public void setPredictedDistance(int predictedDistance) {
			this.predictedDistance = predictedDistance;
		}

		public Point getPoint() {
			return point;
		}

		public void setPoint(Point point) {
			this.point = point;
		}

		public int getDistanceToStart() {
			return distanceToStart;
		}

		public void setDistanceToStart(int distanceToStart) {
			this.distanceToStart = distanceToStart;
		}

		Node(final Point point) {
			this.point = point;
		}

		private Day15 getOuterType() {
			return Day15.this;
		}

	}

}
