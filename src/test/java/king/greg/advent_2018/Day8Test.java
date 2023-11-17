package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day8Test {

	@Test
	public void test() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day8/Test1.txt").getPath());
		final Day8 day8 = new Day8();
		day8.createTree(fileReader);
		Assertions.assertThat(day8.getMetaDataSum()).isEqualTo(138);
	}
	
	@Test
	public void testSolution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day8/input.txt").getPath());
		final Day8 day8 = new Day8();
		day8.createTree(fileReader);
		Assertions.assertThat(day8.getMetaDataSum()).isEqualTo(42146);
	}
	
	@Test
	public void test2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day8/Test1.txt").getPath());
		final Day8 day8 = new Day8();
		day8.createTree(fileReader);
		Assertions.assertThat(day8.getValue()).isEqualTo(66);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day8/input.txt").getPath());
		final Day8 day8 = new Day8();
		day8.createTree(fileReader);
		Assertions.assertThat(day8.getValue()).isEqualTo(26753);
	}

}
