package king.greg.advent_2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Day9 {

	
	public static int playGame2(final int players, final int lastMarble) {
		final List<Integer> marbles = new ArrayList<Integer>();
		marbles.add(0);
		int currentPlayer = 0;
		int currentMarble = 0;
		Integer scores[] = new Integer[players];
		for (int i = 0; i < players; i++) {
			scores[i] = 0;
		}
		for (int marbleToPlace = 1; marbleToPlace <= lastMarble; marbleToPlace++) {
			if (marbleToPlace % 1000 == 0) { 
				System.out.println(marbleToPlace);
			}
			
			if (marbleToPlace % 23 == 0) {
				scores[currentPlayer] += marbleToPlace;
				currentMarble -= 7;
				while(currentMarble < 0) {
					currentMarble += marbles.size();
				}
				
//				System.out.println("removing " + currentMarble);
				scores[currentPlayer] += marbles.remove(currentMarble);
			} else {
				currentMarble = (currentMarble + 2) % marbles.size();
				marbles.add(currentMarble, marbleToPlace);
			}
//			System.out.println((currentPlayer + 1) +" - " +marbles);
			currentPlayer = (currentPlayer + 1) % players;
		}
		
		return Collections.max(Arrays.asList(scores));
	}
	
	public static long playGame(final int players, final int lastMarble) {
		final LinkedList<Integer> marbles = new LinkedList<Integer>();
		marbles.add(0);
		ListIterator<Integer> currentMarble = marbles.listIterator();
		int currentPlayer = 0;
		long scores[] = new long[players];
		for (int i = 0; i < players; i++) {
			scores[i] = 0;
		}
		for (int marbleToPlace = 1; marbleToPlace <= lastMarble; marbleToPlace++) {
			if (marbleToPlace % 23 == 0) {
				scores[currentPlayer] += marbleToPlace;
				for (int i = 0; i < 8; i++) {
					if(!currentMarble.hasPrevious()) {
						currentMarble = marbles.listIterator(marbles.size());
					}
					currentMarble.previous();
				}
				
				if (!currentMarble.hasNext()) {
					currentMarble = marbles.listIterator();
				}
				scores[currentPlayer] += currentMarble.next();
				currentMarble.remove();
				if (!currentMarble.hasNext()) {
					currentMarble = marbles.listIterator();
				}
				currentMarble.next();
			} else {
					if(!currentMarble.hasNext()) {
						currentMarble = marbles.listIterator();
					}
					currentMarble.next();
				currentMarble.add(marbleToPlace);
			}
//			System.out.println((currentPlayer + 1) +" - " +marbles);
			currentPlayer = (currentPlayer + 1) % players;
		}
		long highScore = 0;
		for (int i = 0; i < players; i++) {
			if (scores[i] > highScore) {
				highScore = scores[i];
			}
		}
		return highScore;
	}
	
}
