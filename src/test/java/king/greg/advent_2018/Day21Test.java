package king.greg.advent_2018;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class Day21Test {

	@Test
	public void Solution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day21/input.txt").getPath());
		final Day21 day21 = new Day21(255);
		day21.processInput(fileReader);
	}

}
