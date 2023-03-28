package eightqueens;

import java.util.Arrays;
import java.util.Iterator;

public class QueensBacktracking implements Iterator<int[]> {

  private int[] positions; // column position of each queen in a given row

  public QueensBacktracking(final int size) {
    // TODO arg validation
    positions = new int[size];
  }

  public boolean hasNext() {
    return solve(0);
  }

  public int[] next() {
    // QUESTION why do we have to return a copy of the array?
    final var result = Arrays.copyOf(positions, positions.length);
    bump();
    return result;
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
      if (QueensUtil.isSafe(positions, row, col)) {
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

  private void bump() {
    // bump past the current solution by moving the bottom queen 
    // one square to the right, wrapping around and propagating upward if necessary
    // (like a carry when incrementing a number)
    final var size = positions.length;
    for (var row = size - 1; row >= 0; row -= 1) {
      positions[row] += 1;
      if (positions[row] == size) {
        positions[row] = 0;
      } else {
        break;
      }
    }    
  }
}
