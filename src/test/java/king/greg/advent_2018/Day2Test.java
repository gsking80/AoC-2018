package king.greg.advent_2018;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import org.junit.Test;


public class Day2Test {

	@Test
	public void test1() throws FileNotFoundException, URISyntaxException {


			final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day2/Sample.txt").getPath());
			assertThat(Day2.checksum(fileReader)).isEqualTo(12);
	}
	
	@Test
	public void testInput() throws FileNotFoundException, URISyntaxException {

			final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day2/input.txt").getPath());
			assertThat(Day2.checksum(fileReader)).isEqualTo(6474);
	}

	@Test
	public void testKeys1() throws FileNotFoundException, URISyntaxException {


			final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day2/SampleBoxes.txt").getPath());
			assertThat(Day2.boxKey(fileReader)).isEqualTo("fgij");
	}
	
	@Test
	public void testKeysInput() throws FileNotFoundException, URISyntaxException {


			final FileReader fileReader = new FileReader(getClass().getClassLoader().getResource("Day2/input.txt").getPath());
			assertThat(Day2.boxKey(fileReader)).isEqualTo("mxhwoglxgeauywfkztndcvjqr");
	}
	
	
}
