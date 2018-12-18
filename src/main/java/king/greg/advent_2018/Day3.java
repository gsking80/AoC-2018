package king.greg.advent_2018;

public class Day3 {
	
	int[][] fabric = new int[1000][1000];
	
	public void claim(String claimString) {
		final String[] claimArray = claimString.split(" ");
		final int leftSide = Integer.valueOf(claimArray[2].substring(0, claimArray[2].indexOf(',')));
		final int topSide = Integer.valueOf(claimArray[2].substring(claimArray[2].indexOf(',') + 1, claimArray[2].indexOf(':')));
		final int width = Integer.valueOf(claimArray[3].substring(0, claimArray[3].indexOf('x')));
		final int height = Integer.valueOf(claimArray[3].substring(claimArray[3].indexOf('x') + 1, claimArray[3].length()));
		for (int i = leftSide; i < leftSide + width; i++) {
			for (int j = topSide; j < topSide + height; j++) {
				fabric[i][j]++;
			}
		}
	}
	
	public int claimsGreaterThan(final int x) {
		int excessClaims = 0;
				for (int i = 0; i < 1000; i++) {
					for (int j = 0; j < 1000; j++) {
						if (fabric[i][j] > x) {
							excessClaims++;
						}
					}
				}
		return excessClaims;
	}
	
	public int checkClaim(final String claimString) {
		final String[] claimArray = claimString.split(" ");
		final int leftSide = Integer.valueOf(claimArray[2].substring(0, claimArray[2].indexOf(',')));
		final int topSide = Integer.valueOf(claimArray[2].substring(claimArray[2].indexOf(',') + 1, claimArray[2].indexOf(':')));
		final int width = Integer.valueOf(claimArray[3].substring(0, claimArray[3].indexOf('x')));
		final int height = Integer.valueOf(claimArray[3].substring(claimArray[3].indexOf('x') + 1, claimArray[3].length()));
		for (int i = leftSide; i < leftSide + width; i++) {
			for (int j = topSide; j < topSide + height; j++) {
				if (fabric[i][j] > 1) {
					return 0;
				}
			}
		}
		return Integer.valueOf(claimArray[0].replaceAll("#", ""));
	}

}
