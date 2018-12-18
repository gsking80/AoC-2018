package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day16 {

	int[] registers = new int[4];
	String[] opcodes = new String[] { "muli", "seti", "bani", "gtri", "gtrr", "eqrr", "addi", "gtir", "eqir", "mulr",
			"addr", "borr", "bori", "eqri", "banr", "setr" };

	Day16() {
		Arrays.fill(registers, 0);
	}

	Day16(int[] registersToSet) {
		setRegisters(registersToSet);
	}

	public void setRegisters(final int[] registersToSet) {
		registers = Arrays.copyOf(registersToSet, 4);
	}

	public int[] getRegisters() {
		return registers;
	}

	public void processInput(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			int[] before = null;
			int[] after = null;
			int[] instruction = null;

			final Map<String, boolean[]> successes = new HashMap<String, boolean[]>();
			for (String opcode : opcodes) {
				successes.put(opcode, new boolean[] { true, true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true });
			}

			int match3OrMore = 0;

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else if (lineJustFetched.length() == 0) {
					continue;
				} else if (lineJustFetched.charAt(0) == 'B') { // Before
					String[] registerStrings = lineJustFetched
							.substring(lineJustFetched.indexOf('[') + 1, lineJustFetched.indexOf(']')).split(",");
					before = new int[4];
					for (int i = 0; i < 4; i++) {
						before[i] = Integer.valueOf(registerStrings[i].trim());
					}
				} else if (lineJustFetched.charAt(0) == 'A') { // After
					String[] registerStrings = lineJustFetched
							.substring(lineJustFetched.indexOf('[') + 1, lineJustFetched.indexOf(']')).split(",");
					after = new int[4];
					for (int i = 0; i < 4; i++) {
						after[i] = Integer.valueOf(registerStrings[i].trim());
					}
				} else { // Code to run
					String[] instructionStrings = lineJustFetched.split(" ");
					instruction = new int[4];
					for (int i = 0; i < 4; i++) {
						instruction[i] = Integer.valueOf(instructionStrings[i].trim());
					}
				}

				if (null == before || null == instruction || null == after) {
					continue;
				}
				int match = 0;
				for (String opcode : opcodes) {
					setRegisters(before);
					Day16.class.getMethod(opcode, int.class, int.class, int.class).invoke(this, instruction[1],
							instruction[2], instruction[3]);
					// method.invoke(new Day16(before), instruction[1], instruction[2],
					// instruction[3]);
					if (Arrays.equals(getRegisters(), after)) {
						match++;
					} else {
						boolean[] results = successes.get(opcode);
						results[instruction[0]] = false;
						successes.put(opcode, results);
					}
				}
				if (match >= 3) {
					match3OrMore++;
				}
//				System.out.println(successes);
				before = null;
				instruction = null;
				after = null;

			}
			System.out.println(match3OrMore);
			for (Map.Entry<String, boolean[]> entry : successes.entrySet()) {
				System.out.print(entry.getKey() + " - ");
				for (int i = 0; i < 16; i++) {
					if (entry.getValue()[i]) {
						System.out.print(i + ",");
					}
				}
				System.out.print('\n');

			}
		} catch (IOException | IllegalArgumentException | NoSuchMethodException | SecurityException
				| IllegalAccessException | InvocationTargetException ioe) {

		}
	}

	public void processInput2(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else if (lineJustFetched.length() == 0) {
					continue;
				} else { // Code to run
					String[] instructionStrings = lineJustFetched.split(" ");
					final int[] instruction = new int[4];
					for (int i = 0; i < 4; i++) {
						instruction[i] = Integer.valueOf(instructionStrings[i].trim());
					}

					Day16.class.getMethod(opcodes[instruction[0]], int.class, int.class, int.class).invoke(this,
							instruction[1], instruction[2], instruction[3]);

				}
			}
			System.out.println(registers[0]);
		} catch (IOException | IllegalArgumentException | NoSuchMethodException | SecurityException
				| IllegalAccessException | InvocationTargetException ioe) {

		}
	}

	public void addr(int a, int b, int c) {
		registers[c] = registers[a] + registers[b];
	}

	public void addi(int a, int b, int c) {
		registers[c] = registers[a] + b;
	}

	public void mulr(int a, int b, int c) {
		registers[c] = registers[a] * registers[b];
	}

	public void muli(int a, int b, int c) {
		registers[c] = registers[a] * b;
	}

	public void banr(int a, int b, int c) {
		registers[c] = registers[a] & registers[b];
	}

	public void bani(int a, int b, int c) {
		registers[c] = registers[a] & b;
	}

	public void borr(int a, int b, int c) {
		registers[c] = registers[a] | registers[b];
	}

	public void bori(int a, int b, int c) {
		registers[c] = registers[a] | b;
	}

	public void setr(int a, int b, int c) {
		registers[c] = registers[a];
	}

	public void seti(int a, int b, int c) {
		registers[c] = a;
	}

	public void gtir(int a, int b, int c) {
		registers[c] = a > registers[b] ? 1 : 0;
	}

	public void gtri(int a, int b, int c) {
		registers[c] = registers[a] > b ? 1 : 0;
	}

	public void gtrr(int a, int b, int c) {
		registers[c] = registers[a] > registers[b] ? 1 : 0;
	}

	public void eqir(int a, int b, int c) {
		registers[c] = a == registers[b] ? 1 : 0;
	}

	public void eqri(int a, int b, int c) {
		registers[c] = registers[a] == b ? 1 : 0;
	}

	public void eqrr(int a, int b, int c) {
		registers[c] = registers[a] == registers[b] ? 1 : 0;
	}

}
