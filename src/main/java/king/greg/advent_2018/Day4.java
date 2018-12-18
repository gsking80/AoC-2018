package king.greg.advent_2018;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Day4 {
	
	public class Record {
		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public String getActivity() {
			return activity;
		}

		public void setActivity(String activity) {
			this.activity = activity;
		}

		LocalDateTime timestamp;
		String activity;
		
		Record(LocalDateTime a, String b) {
			timestamp = a;
			activity = b;
		}
		
		@Override
		public String toString() {
			return "--" + timestamp + "--" + activity + "--";
		}
	}
	
	List<Record> records = new ArrayList<Record>();
	final Map<Integer, int[]> guardDuty = new HashMap<Integer, int[]>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public void addRecord(final String input) {
		final String dateString = input.substring(1,input.indexOf(']'));
		final String activity = input.substring(input.indexOf(']') + 2);
//		System.out.println("--" + dateString + "--" + activity + "--");
		records.add(new Record(LocalDateTime.parse(dateString, formatter), activity));
	}
	
	public void sortRecords() {
		records.sort(Comparator.comparing(record -> record.getTimestamp()));
//		for (Record record: records) {
//			System.out.println(record);
//		}
	}
	
	public void parseRecords() {
		
		int currentGuard = -1;
		int lastMinute = -1;
		boolean isAwake = true;
		for (Record record: records) {
			System.out.println(record);
			final String[] activity = record.getActivity().split(" ");
			switch(activity[0]) {
			case "Guard":
				if (!isAwake) {
					for (int i = lastMinute; i < 60; i++) {
						guardDuty.get(currentGuard)[i]++;
					}
				}
				currentGuard = Integer.parseInt(activity[1].substring(1));
				isAwake = true;
				lastMinute = record.getTimestamp().getHour() == 0 ? record.getTimestamp().getMinute() : 0;
				if (!guardDuty.containsKey(currentGuard)) {
					guardDuty.put(currentGuard, new int[60]);
				}
				break;
			case "wakes":
				if (isAwake) {
					System.out.println("********OOOOPS - " + record);
				}
				final int currentMinute = record.getTimestamp().getMinute();
				for (int i = lastMinute; i < currentMinute; i++) {
					guardDuty.get(currentGuard)[i]++;
				}
				lastMinute = currentMinute;
				isAwake = true;
				break;
			case "falls":
				if (!isAwake) {
					System.out.println("**********OOOOPS - " + record);
				}
				final int sleepMinute = record.getTimestamp().getHour() == 0 ? record.getTimestamp().getMinute() : 60;
				lastMinute = sleepMinute;
				isAwake = false;
				break;
			default:
				System.out.println("**********MISSED - " + record);
				
			}
		}

	}
	
	public int solution() {
		int sleepyGuard= -1;
		int sleepyMinutes = 0;
		int sleepiestMinute = -1;
		for (Map.Entry<Integer, int[]> entry : guardDuty.entrySet()) {
			final int sleepTime = IntStream.of(entry.getValue()).sum();
			System.out.println("Guard " + entry.getKey() + " slept for " + sleepTime);
			if ( sleepTime > sleepyMinutes) {
				sleepyGuard = entry.getKey();
				sleepyMinutes = sleepTime;
				sleepiestMinute = findMaxIndex(entry.getValue());
			}
			
		}
		System.out.println("sleepyGuard: " + sleepyGuard + " - sleepiestMinute: " + sleepiestMinute);
		return sleepyGuard * sleepiestMinute;
	}
	
	public int solution2() {
		int sleepyGuard= -1;
		int sleepyTimes = 0;
		int sleepiestMinute = -1;
		for (Map.Entry<Integer, int[]> entry : guardDuty.entrySet()) {
			final int maxMinute = findMaxIndex(entry.getValue());
			final int times = entry.getValue()[maxMinute];
			System.out.println("Guard " + entry.getKey() + " slept " + times + " during minute " + maxMinute);
			if ( times > sleepyTimes) {
				sleepyGuard = entry.getKey();
				sleepyTimes = times;
				sleepiestMinute = maxMinute;
			}
			
		}
		System.out.println("sleepyGuard: " + sleepyGuard + " - sleepiestMinute: " + sleepiestMinute);
		return sleepyGuard * sleepiestMinute;
	}
		
	int findMaxIndex(int [] arr) { 
	     int max = arr[0]; 
	     int maxIdx = 0; 
	     for(int i = 1; i < arr.length; i++) { 
	          if(arr[i] > max) { 
	             max = arr[i]; 
	             maxIdx = i; 
	          } 
	     } 
	     return maxIdx; 
	}
	
}
