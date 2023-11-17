package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day5Test {

	@Test
	public void test() {
		Assertions.assertThat(Day5.react("dabAcCaCBAcCcaDA")).isEqualTo(10);
		Assertions.assertThat(Day5.shortestAdjusted("dabAcCaCBAcCcaDA")).isEqualTo(4);
	}
	
	@Test
	public void testSolution() throws IOException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day5/input.txt").getPath());
//		int temp;
//		while ((temp = fileReader.read()) != -1) {
//			System.out.println((char)temp);
//		}
		final BufferedReader buf = new BufferedReader(fileReader);
//		System.out.println("Test [" + buf.readLine() + "]");
//		Assertions.assertThat(Day5.react(buf.readLine())).isEqualTo(9348);
		Assertions.assertThat(Day5.shortestAdjusted(buf.readLine())).isEqualTo(4996);
		buf.close();
	}

}
