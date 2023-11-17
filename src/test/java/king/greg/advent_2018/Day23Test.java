package king.greg.advent_2018;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day23Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/Test1a.txt").getPath());
		Day23 day23 = new Day23(fileReader);
		Assertions.assertThat(day23.nanobotsWithinRange(day23.findStrongest())).isEqualTo(7);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/input.txt").getPath());
		Day23 day23 = new Day23(fileReader);
		Assertions.assertThat(day23.nanobotsWithinRange(day23.findStrongest())).isEqualTo(780);
	}
	
	@Test
	public void test2a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/Test2a.txt").getPath());
		Day23 day23 = new Day23(fileReader);
		Assertions.assertThat(day23.distanceToOptimalPosition()).isEqualTo(36);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day23/input.txt").getPath());
		Day23 day23 = new Day23(fileReader);
		Assertions.assertThat(day23.distanceToOptimalPosition()).isEqualTo(110841112);
	}

}
