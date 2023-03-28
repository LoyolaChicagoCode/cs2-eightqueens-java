package eightqueens;

public class QueensUtil {

  /**
   * Determine whether it is safe to place a new queen on the current row at the
   * given column.
   */
  public static boolean isSafe(final int[] positions, final int newRow, final int newCol) {
    for (var row = 0; row < newRow; row += 1) {
      final var col = positions[row];
      if (col == newCol || Math.abs(col - newCol) == newRow - row) {
        return false;
      }
    }
    return true;
  }
}
