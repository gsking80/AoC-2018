package king.greg.advent_2018;

public class Day11 {

	public static int powerLevel(final int x, final int y, final int serial) {
		final int rackID = x + 10;
		int powerLevel = rackID * y;
		powerLevel += serial;
		powerLevel = powerLevel * rackID;
		final int hundreds = ((powerLevel / 100) % 10);
		powerLevel = hundreds - 5;
		return powerLevel;
	}

	int[][] grid = new int[300][300];

	public void generatePower(final int powerLevel) {
		for (int x = 0; x < 300; x++) {
			for (int y = 0; y < 300; y++) {
				grid[x][y] = powerLevel(x + 1, y + 1, powerLevel);
			}
		}
	}

	public int[] findMaxPower(final int squareSize) {
		int[] coordinate = new int[3];
		coordinate[2] = 0;
		for (int x = 0; x < 300 - squareSize; x++) {
			for (int y = 0; y < 300 - squareSize; y++) {
				final int blockPower = blockPower(x, y, squareSize);
				if (blockPower > coordinate[2]) {
					coordinate[0] = x + 2;
					coordinate[1] = y + 2;
					coordinate[2] = blockPower;
//					System.out.println(x+","+y+" - " + blockPower);
				}
			}
		}
		return coordinate;
	}

	public int[] findMaxPowerSquare() {
		int[] coordinate = new int[4];
		coordinate[3] = 0;
		for (int size = 1; size <= 300; size++) {
			System.out.println("Size " + size);
			final int[] testCoordinate = findMaxPower(size);
			if (testCoordinate[2] > coordinate[3]) {
				coordinate[0] = testCoordinate[0];
				coordinate[1] = testCoordinate[1];
				coordinate[2] = size;
				coordinate[3] = testCoordinate[2];
			}
		}
		return coordinate;
	}

	private int blockPower(final int x, final int y, final int squareSize) {
		int totalPower = 0;
		for (int xX = x; xX < x + squareSize; xX++) {
			for (int yY = y; yY < y + squareSize; yY++) {
				totalPower += grid[xX + 1][yY + 1];
			}
		}
		return totalPower;
	}

}
