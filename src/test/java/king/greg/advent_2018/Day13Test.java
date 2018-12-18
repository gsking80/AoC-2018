package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day13Test {

	@Test
	public void test1a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/Test1a.txt").getPath());
		final Day13 day13 = new Day13();
		day13.loadTracksAndCarts(fileReader);
		Assertions.assertThat(day13.findFirstCrash()).containsExactly(0,3);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/Test1b.txt").getPath());
		final Day13 day13 = new Day13();
		day13.loadTracksAndCarts(fileReader);
		Assertions.assertThat(day13.findFirstCrash()).containsExactly(7,3);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
		final Day13 day13 = new Day13();
		day13.loadTracksAndCarts(fileReader);
		Assertions.assertThat(day13.findFirstCrash()).containsExactly(63,103);
	}
	
	@Test
	public void test2a() throws Exception {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/Test2a.txt").getPath());
		final Day13 day13 = new Day13();
		day13.loadTracksAndCarts(fileReader);
		Assertions.assertThat(day13.findFinalCart()).containsExactly(6,4);
	}
	
	@Test
	public void testSolution2() throws Exception {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day13/input.txt").getPath());
		final Day13 day13 = new Day13();
		day13.loadTracksAndCarts(fileReader);
		Assertions.assertThat(day13.findFinalCart()).containsExactly(16,134);
	}

}
