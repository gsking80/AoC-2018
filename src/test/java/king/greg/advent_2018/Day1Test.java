package king.greg.advent_2018;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

public class Day1Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day1/Test1.txt").getPath());
		assertThat(Day1.frequency(0, fileReader)).isEqualTo(3);
	}
	
	@Test
	public void testSolution() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day1/input.txt").getPath());
		assertThat(Day1.frequency(0, fileReader)).isEqualTo(474);
	}
	
	@Test
	public void testRepeat1() throws Exception {
		final FileInputStream fileStream = new FileInputStream(getClass().getClassLoader().getResource("Day1/TestRepeat1.txt").getPath());
		assertThat(Day1.frequencyRepeat(0, fileStream)).isEqualTo(0);
	}
	
	@Test
	public void testRepeat2() throws Exception {
		final FileInputStream fileStream = new FileInputStream(getClass().getClassLoader().getResource("Day1/TestRepeat2.txt").getPath());
		assertThat(Day1.frequencyRepeat(0, fileStream)).isEqualTo(10);
	}
	
	@Test
	public void testRepeat3() throws Exception {
		final FileInputStream fileStream = new FileInputStream(getClass().getClassLoader().getResource("Day1/TestRepeat3.txt").getPath());
		assertThat(Day1.frequencyRepeat(0, fileStream)).isEqualTo(5);
	}
	
	@Test
	public void testRepeatSolution() throws Exception {
		final FileInputStream fileStream = new FileInputStream(getClass().getClassLoader().getResource("Day1/input.txt").getPath());
		assertThat(Day1.frequencyRepeat(0, fileStream)).isEqualTo(137041);
	}

}
