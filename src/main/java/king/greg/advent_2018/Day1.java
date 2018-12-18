package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Day1 {

	public static int frequency(final int start, final FileReader fileReader) {
		int frequency = start;
		
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			
			
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					break;
				} else {
					frequency += Integer.parseInt(lineJustFetched);
				}
			}
		} catch (IOException ioe) {
			
		}
		
		return frequency;
	}
	
	public static int frequencyRepeat(final int start, final FileInputStream fileStream) throws Exception {

		int frequency = start;
		
		final Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		
		frequencyMap.put(frequency, 1);
		
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(fileStream));
			
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					fileStream.getChannel().position(0);
					buf = new BufferedReader(new InputStreamReader(fileStream));
				} else {
					frequency += Integer.parseInt(lineJustFetched);
					if (frequencyMap.containsKey(frequency)) {
						return frequency;
					} else {
						frequencyMap.put(frequency, 1);
					}
				}
			}
		} catch (IOException ioe) {
			
		}
		
		throw new Exception("Whoops!");
	
	}
	
}
