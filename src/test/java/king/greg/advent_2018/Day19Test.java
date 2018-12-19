package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day19Test {

	@Test
	public void test() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/Test1a.txt").getPath());
		final Day19 day19 = new Day19();
		day19.processInput(fileReader);
	}
	
	@Test
	public void Solution() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day19/input.txt").getPath());
		final Day19 day19 = new Day19();
		day19.processInput(fileReader);
	}
	
	@Test
	public void Solution2() throws FileNotFoundException {
		final Day19 day19 = new Day19(1);
		Assertions.assertThat(day19.actualProgram(10551287)).isEqualTo(10708992);
	}

}
