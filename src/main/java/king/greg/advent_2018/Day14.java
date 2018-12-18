package king.greg.advent_2018;

import java.util.Vector;

public class Day14 {

	int elfTensRecipe = 0;
	int elfOnesRecipe = 1;
	
	final Vector<Integer> recipeScores = new Vector<Integer>();
	
	public String getScores (final int location) {
		recipeScores.add(3);
		recipeScores.add(7);
		while (recipeScores.size() < location + 10) {
//			System.out.println(recipeScores.size() + " " + elfTensRecipe + " " + elfOnesRecipe);
			final int elfTensRecipeScore = recipeScores.get(elfTensRecipe);
			final int elfOnesRecipeScore = recipeScores.get(elfOnesRecipe);
			final int newRecipe = elfTensRecipeScore + elfOnesRecipeScore;
			if (newRecipe > 9) {
				recipeScores.add(newRecipe / 10);
			}
			recipeScores.add(newRecipe % 10);
			elfTensRecipe += elfTensRecipeScore + 1;
			elfOnesRecipe += elfOnesRecipeScore + 1;
			if (elfTensRecipe >= recipeScores.size()) {
				elfTensRecipe = elfTensRecipe % recipeScores.size();
			}
			if (elfOnesRecipe >= recipeScores.size()) {
				elfOnesRecipe = elfOnesRecipe % recipeScores.size();
			}
		}
		String scores = "";
		for (int i = 0; i < 10; i++) {
			scores += recipeScores.get(location + i);
		}
		return scores;
	}
	
	public int getRecipesBefore (final String scores) {
		recipeScores.add(3);
		recipeScores.add(7);
		String currentScores = "37";
		int recipes = 2;
		while (true) {
//			System.out.println(recipeScores.size() + " " + elfTensRecipe + " " + elfOnesRecipe);
			final int elfTensRecipeScore = recipeScores.get(elfTensRecipe);
			final int elfOnesRecipeScore = recipeScores.get(elfOnesRecipe);
			final int newRecipe = elfTensRecipeScore + elfOnesRecipeScore;
			if (newRecipe > 9) {
				recipeScores.add(newRecipe / 10);
				recipes++;
				currentScores += newRecipe / 10;
				if (scores.length() < currentScores.length()) {
					currentScores = currentScores.substring(1);
				}
				if (scores.equals(currentScores)) {
					return recipes - currentScores.length();
				}
					
			}
			recipeScores.add(newRecipe % 10);
			recipes++;
			currentScores += newRecipe % 10;
			if (scores.length() < currentScores.length()) {
				currentScores = currentScores.substring(1);
			}
			if (scores.equals(currentScores)) {
				return recipes - currentScores.length();
			}
			elfTensRecipe += elfTensRecipeScore + 1;
			elfOnesRecipe += elfOnesRecipeScore + 1;
			if (elfTensRecipe >= recipeScores.size()) {
				elfTensRecipe = elfTensRecipe % recipeScores.size();
			}
			if (elfOnesRecipe >= recipeScores.size()) {
				elfOnesRecipe = elfOnesRecipe % recipeScores.size();
			}
		}
	}

}
