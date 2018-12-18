package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day12 {

	String initialState;
	Map<Integer, Character> pots = new HashMap<Integer, Character>();
	final Map<String, Character> plans = new HashMap<String, Character>();
	Integer leftmostPot;
	Integer rightmostPot;
	boolean override = false;
	long overrideScore;

	public void processInput(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else if (lineJustFetched.isEmpty()) {
					// skip
				} else if (lineJustFetched.charAt(0) == 'i') {
					// initialState
					final String[] strings = lineJustFetched.split(":");
					initialState = strings[1].trim();
					for (int i = 0; i < initialState.length(); i++) {
						pots.put(i, initialState.charAt(i));
					}
					leftmostPot = 0;
					rightmostPot = initialState.length() - 1;
				} else {
					final String[] plan = lineJustFetched.split("=>");
					plans.put(plan[0].trim(), plan[1].trim().charAt(0));
				}
			}
		} catch (IOException ioe) {

		}
	}

	public void incrementGenerations(long generations) {
		printPots();
		long lastScore = scorePots();
		long lastDiff = 0;
		int sameDiffCount = 0;
		for (long generation = 1; generation <= generations; generation++) {
			incrementGeneration();
			printPots();
			long newScore = scorePots();
//			System.out.println("Generation: " + generation + ", Score: " + newScore + " Difference: " + (newScore - lastScore) + " leftmostPot: " + leftmostPot);

			long diff = newScore - lastScore;
			if (diff == lastDiff) {
				sameDiffCount++;
			} else {
				lastDiff = diff;
				sameDiffCount = 0;
			}
			lastScore = newScore;
			if (sameDiffCount >= 100) {
				// we found a pattern, get out now!
				long generationsToGo = generations - generation;
				long finalScore = lastScore + (diff * generationsToGo);
				overrideScore = finalScore;
				override = true;
				return;
			}
		}
	}

	public void incrementGeneration() {
		final Map<Integer, Character> nextGeneration = new HashMap<Integer, Character>();

		Integer nextLeftmost = leftmostPot;
		Integer nextRightmost = rightmostPot;

		String toCheck = ".....";
		for (int pot = leftmostPot - 2; pot < rightmostPot + 2; pot++) {
			toCheck = toCheck.substring(1) + (null == pots.get(pot + 2) ? '.' : pots.get(pot + 2));

			Character nextPotValue = plans.get(toCheck);
			if (nextPotValue == null) {
				nextPotValue = '.';
			}

			// what if we have to expand out?
			if (nextPotValue == '#') {
				if (pot < nextLeftmost) {
					nextLeftmost = pot;
				} else if (pot > nextRightmost) {
					nextRightmost = pot;
				}
				nextGeneration.put(pot, nextPotValue);
			}

			// what if we need to contract?
			if (nextPotValue == '.') {
				if (pot == nextLeftmost) {
					nextLeftmost++;
				} else if (pot == nextRightmost) {
					nextRightmost--;
				}
			}

			
		}
		leftmostPot = nextLeftmost;
		rightmostPot = nextRightmost;
		pots = nextGeneration;
	}

	public long scorePots() {
		if (override) {
			return overrideScore;
		}
		int score = 0;
		for (final Integer potID : pots.keySet()) {
			if (pots.get(potID) == '#') {
				score += potID;
			}
		}
		return score;
	}
	
	public void printPots() {
		for (int potID = -2; potID <= rightmostPot; potID++) {
			Character pot = pots.get(potID);
			System.out.print(null == pot ? '.' : pot);
		}
		System.out.print('\n');
	}

}
