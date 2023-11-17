package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day18Test {

	@Test
	public void test() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/Test1a.txt").getPath());
		final Day18 day18 = new Day18(fileReader);
		Assertions.assertThat(day18.advanceWoods(10)).isEqualTo(1147);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
		final Day18 day18 = new Day18(fileReader);
		Assertions.assertThat(day18.advanceWoods(10)).isEqualTo(603098);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day18/input.txt").getPath());
		final Day18 day18 = new Day18(fileReader);
		Assertions.assertThat(day18.bigScore(1000000000)).isEqualTo(210000);
	}

}
