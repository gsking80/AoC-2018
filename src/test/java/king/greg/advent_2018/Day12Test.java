package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day12Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/Test1.txt").getPath());
		final Day12 day12 = new Day12();
		day12.processInput(fileReader);
		day12.incrementGenerations(20);
		Assertions.assertThat(day12.scorePots()).isEqualTo(325);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/input.txt").getPath());
		final Day12 day12 = new Day12();
		day12.processInput(fileReader);
		day12.incrementGenerations(20);
		Assertions.assertThat(day12.scorePots()).isEqualTo(3798);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day12/input.txt").getPath());
		final Day12 day12 = new Day12();
		day12.processInput(fileReader);
		day12.incrementGenerations(50000000000L);
		Assertions.assertThat(day12.scorePots()).isEqualTo(3900000002212L);
	}

}
