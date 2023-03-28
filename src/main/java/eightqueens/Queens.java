package eightqueens;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Queens implements Iterator<int[]> {

  private int[] positions; // column position of each queen in a given row

  public Queens(final int size) {
    // TODO arg validation
    positions = new int[size];
  }

  /**
   * Generates all possible arrangements of n queens on distinct rows
   * and adds only the safe ones to the results list.
   */
  public void generateAndCheck(final int row, final List<int[]> results) {
    // TODO arg validation
    if (row < positions.length) {
      // recursively generate the next permutation
      // by placing queens row-by-row
      for (var col = 0; col < positions.length; col += 1) {
        positions[row] = col;
        generateAndCheck(row + 1, results);
      }
    } else {
      // when we get to the bottom row, then we have just finished 
      // a permutation and need to check whether it's a (safe) solution
      for (var r = 0; r < positions.length; r += 1) {
        if (!isSafe(r, positions[r])) {
          return;
        }
      }
      // QUESTION why do we have to make a copy of the array?
      results.add(Arrays.copyOf(positions, positions.length));
    }
  }

  /**
   * Determine whether it is safe to place a new queen on the current row at the
   * given column.
   */
  private boolean isSafe(final int newRow, final int newCol) {
    for (var row = 0; row < newRow; row += 1) {
      final var col = positions[row];
      if (col == newCol || col - newCol == row - newRow || newCol - col == row - newRow) {
        return false;
      }
    }
    return true;
  }

  public void reset() {
    for (var r = 0; r < positions.length; r += 1) {
      positions[r] = 0;
    }
  }

  /** Uses recursive backtracking directly to solve the n-queens problem. */
  private boolean solve(final int row) {
    // if we've safely placed all queens, we have a(nother) solution
    if (row == positions.length) {
      return true;
    }
    // look for next available safe column starting with current one;
    // effectively, this means we're starting just one step past the current solution
    // (see also next() below)
    for (var col = positions[row]; col < positions.length; col += 1) {
      if (isSafe(row, col)) {
        positions[row] = col;
        if (solve(row + 1)) {
          return true;
        }
      }
    }
    // QUESTION why do we need to reset this to 0?
    positions[row] = 0;
    // couldn't safely place queen on this row
    return false;
  }

  public boolean hasNext() {
    return solve(0);
  }

  public int[] next() {
    final var size = positions.length;
    // QUESTION why do we have to make a copy of the array?
    final var result = Arrays.copyOf(positions, positions.length);
    // bump past the current solution by moving the bottom queen 
    // one square to the right, wrapping around and propagating upward if necessary
    // (like a carry when incrementing a number)
    for (var row = size - 1; row >= 0; row -= 1) {
      positions[row] += 1;
      if (positions[row] == size) {
        positions[row] = 0;
      } else {
        break;
      }
    }
    return result;
  }
}
