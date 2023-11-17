package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day25Test {

	@Test
	public void test1a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/Test1a.txt").getPath());
		Day25 day25 = new Day25(fileReader);
		Assertions.assertThat(day25.constellationsCount()).isEqualTo(2);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/Test1b.txt").getPath());
		Day25 day25 = new Day25(fileReader);
		Assertions.assertThat(day25.constellationsCount()).isEqualTo(4);
	}
	
	@Test
	public void test1c() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/Test1c.txt").getPath());
		Day25 day25 = new Day25(fileReader);
		Assertions.assertThat(day25.constellationsCount()).isEqualTo(3);
	}
	
	@Test
	public void test1d() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/Test1d.txt").getPath());
		Day25 day25 = new Day25(fileReader);
		Assertions.assertThat(day25.constellationsCount()).isEqualTo(8);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day25/input.txt").getPath());
		Day25 day25 = new Day25(fileReader);
		Assertions.assertThat(day25.constellationsCount()).isEqualTo(388);
	}

}
