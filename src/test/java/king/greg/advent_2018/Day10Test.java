package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.junit.Test;

public class Day10Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/Test1.txt").getPath());
		final Day10 day10 = new Day10();
		day10.loadPoints(fileReader);
		day10.findMessage();
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day10/input.txt").getPath());
		final Day10 day10 = new Day10();
		day10.loadPoints(fileReader);
		day10.findMessage();
	}

}
