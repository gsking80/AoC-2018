package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day19 {

	int[] registers = new int[6];
	int instructionPointer;
	List<String> program = new ArrayList<String>();

	Day19() {
		Arrays.fill(registers, 0);
		instructionPointer = 0;
	}

	Day19(int register0) {
		Arrays.fill(registers, 0);
		registers[0] = register0;
		instructionPointer = 0;
	}

	public void processInput(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);

			while (true) {
				final String lineJustFetched = buf.readLine();
				if (null == lineJustFetched) {
					break;
				} else if (lineJustFetched.length() == 0) {
					continue;
				} else { // Program
					String[] instructionStrings = lineJustFetched.split(" ");
					if (instructionStrings.length == 2) {
						instructionPointer = Integer.valueOf(instructionStrings[1]);
					} else {
						program.add(lineJustFetched);
					}
				}
			}
			executeProgram();
		} catch (IOException | IllegalArgumentException | SecurityException ioe) {

		}
	}

	private void executeProgram() {
		while (registers[instructionPointer] < program.size()) {
			final String instruction = program.get(registers[instructionPointer]);
			System.out.print("ip=" + registers[instructionPointer] + printRegisters() + instruction);

			String[] instructionString = instruction.split(" ");
			try {
				Day19.class.getMethod(instructionString[0], int.class, int.class, int.class).invoke(this,
						Integer.valueOf(instructionString[1].trim()), Integer.valueOf(instructionString[2].trim()),
						Integer.valueOf(instructionString[3].trim()));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(printRegisters());
			registers[instructionPointer]++;
		}
		System.out.println(printRegisters());
	}

	public int actualProgram(final int input) {
		// Sum of factors of input
		int answer = 0;
		for (int i = 1; i <= input; i++) {
			if (input % i == 0) {
				answer += i;
			}
		}
		return answer;
	}

	private String printRegisters() {
		return " [" + registers[0] + ", " + registers[1] + ", " + registers[2] + ", " + registers[3] + ", "
				+ registers[4] + ", " + registers[5] + "] ";
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
