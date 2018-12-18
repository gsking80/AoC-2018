package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day6Test {

	@Test
	public void test() throws FileNotFoundException {
		final Day6 day6 = new Day6();
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day6/Test1.txt").getPath());
		day6.loadCoordinates(fileReader);
		day6.fillMap();
		Assertions.assertThat(day6.calculateLargestFiniteArea()).isEqualTo(17);
	}
	
	@Test
	public void testSolution() throws FileNotFoundException {
		final Day6 day6 = new Day6();
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day6/input.txt").getPath());
		day6.loadCoordinates(fileReader);
		day6.fillMap();
		Assertions.assertThat(day6.calculateLargestFiniteArea()).isEqualTo(3969);
	}
	
	@Test
	public void test2() throws Exception {
		final Day6 day6 = new Day6();
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day6/Test1.txt").getPath());
		day6.loadCoordinates(fileReader);
		day6.fillSafeMap(32);
		Assertions.assertThat(day6.calculateSafeArea()).isEqualTo(16);
	}
	
	@Test
	public void testSolution2() throws Exception {
		final Day6 day6 = new Day6();
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day6/input.txt").getPath());
		day6.loadCoordinates(fileReader);
		day6.fillSafeMap(10000);
		Assertions.assertThat(day6.calculateSafeArea()).isEqualTo(42123);
	}
	
	@Test
	public void testBug2() throws Exception {
		final Day6 day6 = new Day6();
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day6/bug.txt").getPath());
		day6.loadCoordinates(fileReader);
		day6.fillSafeMap(10000);
		Assertions.assertThat(day6.calculateSafeArea()).isEqualTo(46667);
	}

}
