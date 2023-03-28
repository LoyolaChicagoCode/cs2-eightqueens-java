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
   * Generates all possible constellations of n queens on distinct rows
   * and adds only the safe ones to the results list.
   */
  public void generateAndCheck(final int row, final List<int[]> results) {
    // TODO arg validation
    if (row == positions.length) {
      var safe = true;
      for (var r = 0; r < positions.length; r += 1) {
        if (!isSafe(r, positions[r])) {
          safe = false;
          break;
        }
      }
      if (safe) {
        results.add(Arrays.copyOf(positions, positions.length));
      }
      return;
    }
    for (var col = 0; col < positions.length; col += 1) {
      positions[row] = col;
      generateAndCheck(row + 1, results);
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
    if (row == positions.length) {
      return true;
    }
    // look for next available safe column starting with current one;
    // effectively, this means we're starting with the current arrangement for the entire board
    for (var col = positions[row]; col < positions.length; col += 1) {
      if (isSafe(row, col)) {
        positions[row] = col;
        if (solve(row + 1)) {
          return true;
        }
      }
    }
    // couldn't place queen on this row
    positions[row] = 0;
    return false;
  }

  public boolean hasNext() {
    return solve(0);
  }

  public int[] next() {
    final var size = positions.length;
    final var result = Arrays.copyOf(positions, positions.length);
    // move the bottom queen one square to the right, 
    // wrapping around and propagating upward if necessary
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
