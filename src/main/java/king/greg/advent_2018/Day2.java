package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

	public static int checksum(final FileReader fileReader) {
		int checksum = 0;
		int twos = 0;
		int threes = 0;
		
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			
			
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					break;
				} else {
					final Map<Character, Integer> charMap = new HashMap<Character, Integer>();
					for (int x = 0; x < lineJustFetched.length(); x++) {
						if (charMap.containsKey(lineJustFetched.charAt(x))) {
							charMap.put(lineJustFetched.charAt(x), charMap.get(lineJustFetched.charAt(x)) + 1);
						} else {
							charMap.put(lineJustFetched.charAt(x), 1);
						}
					}
					boolean twoNeeded = true;
					boolean threeNeeded = true;
					for (final Integer count: charMap.values()) {
						if (twoNeeded && count.equals(2)) {
							twos++;
							twoNeeded = false;
						} else if (threeNeeded && count.equals(3)) {
							threes++;
							threeNeeded = false;
						}
					}
				}
			}
		} catch (IOException ioe) {
			
		}
		checksum = twos * threes;
		return checksum;
		
	}
	
	public static String boxKey(final FileReader fileReader) {
		final List<String> boxes = new ArrayList<String>();
		
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			
			
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					break;
				} else {
					boxes.add(lineJustFetched);
				}
			}
		} catch (IOException ioe) {
			
		}
		
		for (int i = 0; i < boxes.size() -1; i++) {
			
			for (int j = i+1; j < boxes.size(); j++) {
				
				int difference = 0;
				String word = "";
				for (int x = 0; x < boxes.get(i).length(); x++) {
					if (boxes.get(i).charAt(x) != boxes.get(j).charAt(x)) {
						difference++;
					} else {
						word = word.concat(Character.toString(boxes.get(i).charAt(x)));
					}
					if (difference > 1) {
						break;
					}
				}
				if (difference == 1) { // we have winners!
					return word;
				}
			}
		}
		
		return "";
	}
	
}
