package king.greg.advent_2018;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.LinkedList;

public class Day5 {

	public static int react(final String polymer) {
		final LinkedList<Character> reacted = new LinkedList<Character>();
		for (int i = 0; i < polymer.length(); i++) {
			final Character unit = polymer.charAt(i);
			if (reacted.isEmpty()) {
				reacted.addLast(unit);
			} else {
				if (abs(reacted.getLast() - unit) == 32) {
					reacted.removeLast();
				} else {
					reacted.addLast(unit);
				}
			}
		}
		return reacted.size();
	}

	public static int shortestAdjusted(final String polymer) {
		int shortest = Integer.MAX_VALUE;
		for (int i = 0; i < 26; i++) {
			String adjustedPolymer = "";
			for (int j = 0; j < polymer.length(); j++) {
				final Character unit = polymer.charAt(j);
				if (!(unit == 'a' + i || unit == 'A' + i)) {
					adjustedPolymer += unit;
				}
			}
			shortest = min(shortest, react(adjustedPolymer));
		}
		return shortest;
	}
}
