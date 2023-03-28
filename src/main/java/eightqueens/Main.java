package eightqueens;

import java.util.LinkedList;
import java.util.Arrays;

public class Main {

  private static final int DEFAULT_SIZE = 4;
  
  public static void main(final String[] args) {
    final var size = args.length == 1 ? Integer.parseInt(args[0]) : DEFAULT_SIZE;

    // QUESTION which of these solutions is more efficient?
    // TODO add option for measuring performance while suppressing output
    
    genAndCheck(size);
    System.out.println();
    backtrack(size);
  }

  private static void genAndCheck(final int size) {
    final var queens = new QueensGenAndCheck(size);
    final var results = new LinkedList<int[]>();
    queens.generateAndCheck(results);
    System.out.printf("%d-queens generate-and-check results:\n", size);
    for (final var r : results) {
      System.out.println(Arrays.toString(r));
    }
    System.out.printf("%d results out of %d possible arrangements\n", results.size(), Math.round(Math.pow(size, size)));
  }

  private static void backtrack(final int size) {
    final var queens = new QueensBacktracking(size);
    var count = 0;
    System.out.printf("%d-queens direct backtracking results:\n", size);
    while (queens.hasNext()) {
      count += 1;
      final var r = queens.next();
      System.out.println(Arrays.toString(r)); // TODO print in nicer 2d format
    }
    System.out.printf("%d results\n", count);
  }
}
