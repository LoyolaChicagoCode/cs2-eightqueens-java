package eightqueens;

import java.util.LinkedList;
import java.util.Arrays;

public class Main {

  private static final int DEFAULT_SIZE = 4;
  
  public static void main(final String[] args) {
    final var size = args.length == 1 ? Integer.parseInt(args[0]) : DEFAULT_SIZE;
    final var queens = new Queens(size);
    var count = 0;

    final var results = new LinkedList<int[]>();
    queens.generateAndCheck(0, results);
    System.out.printf("%d-queens generate-and-check results:\n", size);
    for (final var r : results) {
      System.out.println(Arrays.toString(r));
    }
    System.out.printf("%d results out of %d possible arrangements\n", results.size(), Math.round(Math.pow(size, size)));
    
    System.out.println();
    
    System.out.printf("%d-queens direct backtracking results:\n", size);
    queens.reset();
    while (queens.hasNext()) {
      count += 1;
      final var r = queens.next();
      System.out.println(Arrays.toString(r)); // TODO print in nicer 2d format
    }
    System.out.printf("%d results\n", count);
  }
}
