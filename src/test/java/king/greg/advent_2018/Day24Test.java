package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day24Test {

	@Test
	public void test1a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(
				getClass().getClassLoader().getResource("Day24/Test1a.txt").getPath());
		Day24 day24 = new Day24(fileReader);
		Assertions.assertThat(day24.war()).isEqualTo(5216);
	}

	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(
				getClass().getClassLoader().getResource("Day24/input.txt").getPath());
		Day24 day24 = new Day24(fileReader);
		Assertions.assertThat(day24.war()).isEqualTo(33551);
	}

	@Test
	public void test2a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(
				getClass().getClassLoader().getResource("Day24/Test1a.txt").getPath());
		Day24 day24 = new Day24(fileReader);
		Assertions.assertThat(day24.war2(1570)).isEqualTo(51);
	}

	@Test
	public void testSolution2() throws FileNotFoundException {
		int units = -1;
		int boost = 0;
		while (units < 0) {
			boost++;
			final FileReader fileReader = new FileReader(
					getClass().getClassLoader().getResource("Day24/input.txt").getPath());
			Day24 day24 = new Day24(fileReader);
			units = day24.war2(boost);
		}
		Assertions.assertThat(units).isEqualTo(760);
	}

}
