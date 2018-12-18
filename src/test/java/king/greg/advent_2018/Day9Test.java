package king.greg.advent_2018;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day9Test {

	@Test
	public void test1() {
		Assertions.assertThat(Day9.playGame(9, 25)).isEqualTo(32);
	}
	
	@Test
	public void test1a() {
		Assertions.assertThat(Day9.playGame(10, 1618)).isEqualTo(8317);
	}
	
	@Test
	public void test1b() {
		Assertions.assertThat(Day9.playGame(13, 7999)).isEqualTo(146373);
	}
	
	@Test
	public void test1c() {
		Assertions.assertThat(Day9.playGame(17, 1104)).isEqualTo(2764);
	}
	
	@Test
	public void test1d() {
		Assertions.assertThat(Day9.playGame(21, 6111)).isEqualTo(54718);
	}
	
	@Test
	public void test1e() {
		Assertions.assertThat(Day9.playGame(30, 5807)).isEqualTo(37305);
	}

	@Test
	public void testSolution1() {
		Assertions.assertThat(Day9.playGame(471, 72026)).isEqualTo(390093);
	}
	
	@Test
	public void testSolution2() {
		Assertions.assertThat(Day9.playGame(471, 7202600)).isEqualTo(3150377341L);
	}
	
}
