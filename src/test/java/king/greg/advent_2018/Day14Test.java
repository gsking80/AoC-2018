package king.greg.advent_2018;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day14Test {

	@Test
	public void test1a() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getScores(9)).isEqualTo("5158916779");
	}

	@Test
	public void test1b() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getScores(5)).isEqualTo("0124515891");
	}
	
	@Test
	public void test1c() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getScores(18)).isEqualTo("9251071085");
	}
	
	@Test
	public void test1d() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getScores(2018)).isEqualTo("5941429882");
	}
	
	@Test
	public void testSolution1() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getScores(513401)).isEqualTo("5371393113");
	}
	
	@Test
	public void test2a() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getRecipesBefore("51589")).isEqualTo(9);
	}
	
	@Test
	public void test2b() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getRecipesBefore("01245")).isEqualTo(5);
	}
	
	@Test
	public void test2c() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getRecipesBefore("92510")).isEqualTo(18);
	}
	
	@Test
	public void test2d() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getRecipesBefore("59414")).isEqualTo(2018);
	}
	
	@Test
	public void testSolution2() {
		final Day14 day14 = new Day14();
		Assertions.assertThat(day14.getRecipesBefore("513401")).isEqualTo(20286858);
	}
}
