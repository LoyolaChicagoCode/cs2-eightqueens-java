package eightqueens;

import java.util.Arrays;
import java.util.List;

public class QueensGenAndCheck {

  private int[] positions; // column position of each queen in a given row

  public QueensGenAndCheck(final int size) {
    // TODO arg validation
    positions = new int[size];
  }

  /**
   * Generates all possible arrangements of n queens on distinct rows
   * and adds only the safe ones to the results list.
   */
  public void generateAndCheck(final List<int[]> results) {
    generateAndCheckRec(0, results);
  }

  private void generateAndCheckRec(final int row, final List<int[]> results) {
    // TODO arg validation
    if (row < positions.length) {
      // recursively generate the next permutation
      // by placing queens row-by-row
      for (var col = 0; col < positions.length; col += 1) {
        positions[row] = col;
        generateAndCheckRec(row + 1, results);
      }
    } else {
      // when we get to the bottom row, then we have just finished 
      // a permutation and need to check whether it's a (safe) solution
      // QUESTION why do we have to make a copy of the array?
      if (allSafe()) {
        results.add(Arrays.copyOf(positions, positions.length));
      }
    }
  }

  private boolean allSafe() {
    for (var r = 0; r < positions.length; r += 1) {
      if (!QueensUtil.isSafe(positions, r, positions[r])) {
        return false;
      }
    }
    return true;
  }
}
