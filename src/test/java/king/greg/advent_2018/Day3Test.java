package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day3Test {

	@Test
	public void test() throws IOException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day3/Test1.txt").getPath());
		final Day3 day3 = new Day3();
		final BufferedReader buf = new BufferedReader(fileReader);
		while(true) {
			final String lineJustFetched = buf.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				day3.claim(lineJustFetched);
			}
		}
		buf.close();
		Assertions.assertThat(day3.claimsGreaterThan(1)).isEqualTo(4);
		
		final BufferedReader buf2 = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("Day3/Test1.txt").getPath()));
		int claim = 0;
		while(true) {
			final String lineJustFetched = buf2.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				 claim += day3.checkClaim(lineJustFetched);
			}
		}
		buf2.close();
		Assertions.assertThat(claim).isEqualTo(3);
	}
	
	@Test
	public void testSolution() throws IOException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day3/input.txt").getPath());
		final Day3 day3 = new Day3();
		final BufferedReader buf = new BufferedReader(fileReader);
		while(true) {
			final String lineJustFetched = buf.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				day3.claim(lineJustFetched);
			}
		}
		buf.close();
		Assertions.assertThat(day3.claimsGreaterThan(1)).isEqualTo(107663);
		
		final BufferedReader buf2 = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("Day3/input.txt").getPath()));
		int claim = 0;
		while(true) {
			final String lineJustFetched = buf2.readLine();
			if(null == lineJustFetched) {
				break;
			} else {
				 claim += day3.checkClaim(lineJustFetched);
			}
		}
		buf2.close();
		Assertions.assertThat(claim).isEqualTo(1166);
	}

}
