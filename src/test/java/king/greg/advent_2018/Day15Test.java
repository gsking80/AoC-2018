package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

public class Day15Test {

	@Ignore
	@Test
	public void testMovement() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/MoveTest.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		day15.runGame();
	}
	
	@Ignore
	@Test
	public void testMovement2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/MoveTest2.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		day15.runGame();
	}

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test1.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(27730);
	}
	
	@Test
	public void test2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test2.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(36334);
	}
	
	@Test
	public void test3() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test3.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(39514);
	}
	
	@Test
	public void test4() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test4.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(27755);
	}
	@Test
	public void test5() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test5.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(28944);
	}
	
	@Test
	public void test6() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Test6.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(18740);
	}
	
	@Ignore
	@Test
	public void testBug1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/Bug1.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(27730);
	}
	
	@Test
	public void testSolution() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/input.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader);
		Assertions.assertThat(day15.runGame()).isEqualTo(229950);
	}
	
	@Test
	public void testSolution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day15/input.txt").getPath());
		final Day15 day15 = new Day15();
		day15.readFile(fileReader, 13);
		Assertions.assertThat(day15.runGame()).isEqualTo(54360);
	}
	
}
