package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day17Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/Test1a.txt").getPath());
		final Day17 day17 = new Day17(fileReader);
		day17.start();
		Assertions.assertThat(day17.totalWater()).isEqualTo(57);
		Assertions.assertThat(day17.restWater()).isEqualTo(29);
	}

	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day17/input.txt").getPath());
		final Day17 day17 = new Day17(fileReader);
		day17.start();
		Assertions.assertThat(day17.totalWater()).isEqualTo(33362);
		Assertions.assertThat(day17.restWater()).isEqualTo(27801);
	}
	
}
