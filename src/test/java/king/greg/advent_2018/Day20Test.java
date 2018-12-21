package king.greg.advent_2018;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class Day20Test {

	@Test
	public void test1a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Test1a.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(3);
	}
	
	@Test
	public void testMe1a() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Me1a.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(2);
	}
	
	@Test
	public void test1b() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Test1b.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(10);
	}
	
	@Test
	public void test1c() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Test1c.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(18);
	}
	
	@Test
	public void test1d() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/Test1d.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(23);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day20/input.txt").getPath());
		final Day20 day20 = new Day20(fileReader);
		Assertions.assertThat(day20.getMaxDistance(new Point(0,0))).isEqualTo(4360);
	}

}
