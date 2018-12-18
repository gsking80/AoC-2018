package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day7 {

	final Map<Character, Set<Character>> prerequisites = new HashMap<Character, Set<Character>>();
	final List<Character> steps = new ArrayList<Character>();

	public void populateSteps(final FileReader fileReader) {

		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else {
					final Character preReq = lineJustFetched.charAt(5);
					final Character step = lineJustFetched.charAt(36);
					if (prerequisites.containsKey(step)) {
						prerequisites.get(step).add(preReq);
					} else {
						final Set<Character> preReqs = new HashSet<Character>();
						preReqs.add(preReq);
						prerequisites.put(step, preReqs);
					}
					if (!steps.contains(step)) {
						steps.add(step);
					}
					if (!steps.contains(preReq)) {
						steps.add(preReq);
					}

				}
			}
			steps.sort(Comparator.comparing(record -> record.toString()));
			System.out.println(prerequisites);
			System.out.println(steps);
		} catch (IOException ioe) {

		}
	}

	public String calcStepOrder() {
		String order = new String();
		while (!steps.isEmpty()) {
			for (Iterator<Character> iterator = steps.iterator(); iterator.hasNext();) {
				boolean canPerform = true;
				final Character step = iterator.next();
				final Set<Character> preReqs = prerequisites.get(step);
				if (!(null == preReqs)) {
					for (Character preReq : preReqs) {
						if (steps.contains(preReq)) {
							canPerform = false;
						}
					}
				}
				if (canPerform) {
					order += step;
					steps.remove(step);
					break;
				}
			}

		}
		return order;
	}
	
	class Worker {
		Character currentStep = ' ';
		int secondsLeft = 0;
		int workerID;
		
		Worker(final int ID) {
			workerID = ID;
		}

		@Override
		public String toString() {
			return "Worker [workerID=" + workerID + " currentStep=" + currentStep + ", secondsLeft=" + secondsLeft  
					+ "]";
		}
		
		
	}
	
	public int calcCompletionTime(final int numWorkers, final int timeOffset) {
		int seconds = 0;
		String order = new String();
		final Worker[] workers = new Worker[numWorkers];
		for (int i = 0; i < numWorkers; i++) {
			workers[i] = new Worker(i);
		}
		final Set<Character> wip = new HashSet<Character>();
		while (!steps.isEmpty() || !wip.isEmpty()) {
			System.out.println("Starting second: " + seconds);
			for (final Worker worker: workers) {
				if (worker.currentStep == ' ') {
					System.out.println("worker available");
					for (Iterator<Character> iterator = steps.iterator(); iterator.hasNext();) {
						boolean canPerform = true;
						final Character step = iterator.next();
						final Set<Character> preReqs = prerequisites.get(step);
						if (!(null == preReqs)) {
							for (Character preReq: preReqs) {
								if (steps.contains(preReq) || wip.contains(preReq)) {
									canPerform = false;
								}
							}
						}
						if (canPerform) {
							worker.currentStep = step;
							worker.secondsLeft = step + timeOffset - 64;
							steps.remove(step);
							wip.add(step);
							break;
						}
					}
				}
			}
			// increment time
			seconds++;
			System.out.println("Working...");
			for (final Worker worker:workers) {
				System.out.println(worker);
				if (worker.secondsLeft > 0) {
					worker.secondsLeft--;
				}
				if (worker.currentStep != ' ' && worker.secondsLeft == 0) {
					wip.remove(worker.currentStep);
					order += worker.currentStep;
					worker.currentStep = ' ';
				}
			}
			System.out.println("Done= " + order);
		}
		return seconds;
	}

}
