package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day13 {

	char[][] track;
	boolean crash = false;
	int crashX;
	int crashY;
	List<Cart> carts = new ArrayList<Cart>();

	public void loadTracksAndCarts(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			final List<char[]> trackList = new ArrayList<char[]>();
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					trackList.add(lineJustFetched.toCharArray());
				}
			}
			track = new char[trackList.size()][];
			track = trackList.toArray(track);
			printTrack();
			findCarts();
			printTrack();
		} catch (IOException ioe) {

		}
	}

	public void findCarts() {
		for (int y = 0; y < track.length; y++) {
			for (int x = 0; x < track[y].length; x++) {

				switch (track[y][x]) {
				case 'v':
					carts.add(new Cart(x, y, 'v'));
					track[y][x] = '|';
					break;
				case '^':
					carts.add(new Cart(x, y, '^'));
					track[y][x] = '|';
					break;
				case '<':
					carts.add(new Cart(x, y, '<'));
					track[y][x] = '-';
					break;
				case '>':
					carts.add(new Cart(x, y, '>'));
					track[y][x] = '-';
					break;
				}
			}
		}
	}

	public int[] findFirstCrash() {

		try {
			while (!crash) {
				moveCarts();
			}
		} catch (final Exception e) {

		}
		return new int[] { crashX, crashY };
	}
	
	public int[] findFinalCart() throws Exception {
		while (carts.size()>1) {
			moveCarts();
		}
		return new int[] { carts.get(0).getxLoc(), carts.get(0).getyLoc()};
	}

	public void moveCarts() throws Exception {
		Collections.sort(carts);
		for (Cart cart : carts) {
			if (!cart.isCrashed()) {
				cart.move();
			}
		}
		final List<Cart> liveCarts = new ArrayList<Cart>();
		for (Cart cart : carts) {
			if (!cart.isCrashed()) {
				liveCarts.add(cart);
			}
		}
		carts = liveCarts;

	}

	public void printTrack() {
		for (int y = 0; y < track.length; y++) {
			for (int x = 0; x < track[y].length; x++) {
				System.out.print(track[y][x]);
			}
			System.out.print('\n');
		}
	}

	class Cart implements Comparable<Cart> {
		public int getxLoc() {
			return xLoc;
		}

		public void setxLoc(int xLoc) {
			this.xLoc = xLoc;
		}

		public int getyLoc() {
			return yLoc;
		}

		public void setyLoc(int yLoc) {
			this.yLoc = yLoc;
		}

		public char getDirection() {
			return direction;
		}

		public void setDirection(char direction) {
			this.direction = direction;
		}

		public int getNextTurn() {
			return nextTurn;
		}

		public void setNextTurn(int nextTurn) {
			this.nextTurn = nextTurn;
		}

		int xLoc;
		int yLoc;
		char direction;
		int nextTurn;
		boolean crashed;

		Cart(int xLoc, int yLoc, char direction) {
			this.xLoc = xLoc;
			this.yLoc = yLoc;
			this.direction = direction;
			nextTurn = 0;
			crashed = false;
		}

		public boolean isCrashed() {
			return crashed;
		}

		public void setCrashed(boolean crashed) {
			this.crashed = crashed;
		}

		public void move() throws Exception {
			switch (direction) {
			case 'v':
				yLoc += 1;
				break;
			case '^':
				yLoc -= 1;
				break;
			case '<':
				xLoc -= 1;
				break;
			case '>':
			default:
				xLoc += 1;
				break;
			}

			// look for crash
			for (final Cart cart : carts) {
				if (cart.getxLoc() == xLoc && cart.getyLoc() == yLoc && !this.equals(cart) && !cart.isCrashed()) {
//					crash = true;
//					crashX = xLoc;
//					crashY = yLoc;
					System.out.println("CRASH! x=" + xLoc + ", y=" + yLoc);
//					throw new Exception();
					cart.setCrashed(true);
					crashed = true;
				}
			}

			switch (track[yLoc][xLoc]) {
			case '\\':
				switch (direction) {
				case 'v':
					direction = '>';
					break;
				case '^':
					direction = '<';
					break;
				case '<':
					direction = '^';
					break;
				case '>':
				default:
					direction = 'v';
					break;
				}
				break;
			case '/':
				switch (direction) {
				case 'v':
					direction = '<';
					break;
				case '^':
					direction = '>';
					break;
				case '<':
					direction = 'v';
					break;
				case '>':
				default:
					direction = '^';
					break;
				}
				break;
			case '+':
				switch (nextTurn % 3) {
				case 0:
					// turn left
					switch (direction) {
					case 'v':
						direction = '>';
						break;
					case '^':
						direction = '<';
						break;
					case '<':
						direction = 'v';
						break;
					case '>':
					default:
						direction = '^';
						break;
					}
					break;
				case 1:
					// go straight
					break;
				case 2:
					// turn right
					switch (direction) {
					case 'v':
						direction = '<';
						break;
					case '^':
						direction = '>';
						break;
					case '<':
						direction = '^';
						break;
					case '>':
					default:
						direction = 'v';
						break;
					}
					break;
				}
				nextTurn++;
				break;
			default:
				// do nothing
				break;
			}

		}

		@Override
		public int compareTo(Cart arg0) {
			return Comparator.comparing(Cart::getyLoc).thenComparing(Cart::getxLoc).compare(this, arg0);
		}
	}

}
