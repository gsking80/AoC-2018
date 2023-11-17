package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day4Test {

	@Test
	public void test() throws IOException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day4/Test1.txt").getPath());
		final BufferedReader buf = new BufferedReader(fileReader);
		final Day4 day4 = new Day4();
		while(true) {
			final String lineJustFetched = buf.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				day4.addRecord(lineJustFetched);
			}
		}
		buf.close();
		day4.sortRecords();
		day4.parseRecords();
		Assertions.assertThat(day4.solution()).isEqualTo(240);
		Assertions.assertThat(day4.solution2()).isEqualTo(4455);
	}
	
	
	@Test
	public void testSolution() throws IOException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day4/input.txt").getPath());
		final BufferedReader buf = new BufferedReader(fileReader);
		final Day4 day4 = new Day4();
		while(true) {
			final String lineJustFetched = buf.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				day4.addRecord(lineJustFetched);
			}
		}
		buf.close();
		day4.sortRecords();
		day4.parseRecords();
		Assertions.assertThat(day4.solution()).isEqualTo(104764);
		Assertions.assertThat(day4.solution2()).isEqualTo(128617);
	}

}
