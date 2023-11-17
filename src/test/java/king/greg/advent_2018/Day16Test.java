package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

public class Day16Test {

	@Test
	public void test() throws FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day16/Test1.txt").getPath());
		Day16 day16 = new Day16();
		day16.processInput(fileReader);
	}

	@Test
	public void testSolution() throws FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day16/inputPart1.txt").getPath());
		Day16 day16 = new Day16();
		day16.processInput(fileReader);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day16/inputPart2.txt").getPath());
		Day16 day16 = new Day16();
		day16.processInput2(fileReader);
	}
	
}
