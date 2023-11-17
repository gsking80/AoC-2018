package king.greg.advent_2018;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Day7Test {

	@Test
	public void test1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day7/Test1.txt").getPath());
		final Day7 day7 = new Day7();
		day7.populateSteps(fileReader);
		Assertions.assertThat(day7.calcStepOrder()).isEqualTo("CABDFE");
	}

	
	@Test
	public void solution1() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day7/input.txt").getPath());
		final Day7 day7 = new Day7();
		day7.populateSteps(fileReader);
		Assertions.assertThat(day7.calcStepOrder()).isEqualTo("GKCNPTVHIRYDUJMSXFBQLOAEWZ");
	}
	
	@Test
	public void test2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day7/Test1.txt").getPath());
		final Day7 day7 = new Day7();
		day7.populateSteps(fileReader);
		Assertions.assertThat(day7.calcCompletionTime(2,0)).isEqualTo(15);
	}
	
	@Test
	public void solution2() throws FileNotFoundException {
		final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day7/input.txt").getPath());
		final Day7 day7 = new Day7();
		day7.populateSteps(fileReader);
		Assertions.assertThat(day7.calcCompletionTime(5,60)).isEqualTo(1265);
	}
	
}
