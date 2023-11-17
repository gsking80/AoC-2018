package king.greg.advent_2018;

import java.awt.Point;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day22Test {

	@Test
	public void test1() {
		final Day22 day22 = new Day22(510, new Point(10,10));
		Assertions.assertThat(day22.calculateRiskSmallestRectangle()).isEqualTo(114);
	}

	@Test
	public void testSolution1() {
		final Day22 day22 = new Day22(3066, new Point(13,726));
		Assertions.assertThat(day22.calculateRiskSmallestRectangle()).isEqualTo(10115);
	}
	
	@Test
	public void test2() {
		final Day22 day22 = new Day22(510, new Point(10,10));
		Assertions.assertThat(day22.calculateFastestTime()).isEqualTo(45);
	}
	
	@Test
	public void testSolution2() {
		final Day22 day22 = new Day22(3066, new Point(13,726));
		Assertions.assertThat(day22.calculateFastestTime()).isEqualTo(990);
	}
}
