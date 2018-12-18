package king.greg.advent_2018;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day11Test {

	@Test
	public void test1a() {
		Assertions.assertThat(Day11.powerLevel(3, 5, 8)).isEqualTo(4);
		Assertions.assertThat(Day11.powerLevel(122, 79, 57)).isEqualTo(-5);
		Assertions.assertThat(Day11.powerLevel(217, 196, 39)).isEqualTo(0);
		Assertions.assertThat(Day11.powerLevel(101, 153, 71)).isEqualTo(4);
	}
	
	@Test
	public void test1b() {
		final Day11 day11 = new Day11();
		day11.generatePower(18);
		Assertions.assertThat(day11.findMaxPower(3)).containsExactly(33,45,29);
	}
	
	@Test
	public void test1c() {
		final Day11 day11 = new Day11();
		day11.generatePower(42);
		Assertions.assertThat(day11.findMaxPower(3)).containsExactly(21,61,30);
	}

	@Test
	public void testSolution1() {
		final Day11 day11 = new Day11();
		day11.generatePower(7400);
		Assertions.assertThat(day11.findMaxPower(3)).containsExactly(34,72,29);
	}
	
	@Test
	public void test2a() {
		final Day11 day11 = new Day11();
		day11.generatePower(18);
		Assertions.assertThat(day11.findMaxPowerSquare()).containsExactly(90,269,16,113);
	}
	
	@Test
	public void testSolution2() {
		final Day11 day11 = new Day11();
		day11.generatePower(7400);
		Assertions.assertThat(day11.findMaxPowerSquare()).containsExactly(233,187,13,91);
	}
	
}
