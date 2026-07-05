package com.ntr.hrank.benchmarks;

import static com.ntr.hrank.ds.DSAlgorithms.binarySearchIterative;
import static com.ntr.hrank.rec.RecursionAlgorithms.binarySearch;

import com.ntr.hrank.ds.DSAlgorithms;
import com.ntr.hrank.rec.RecursionAlgorithms;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class BinarySearchBenchmark {

  private int[] arr;
  private int target = 77777;

  @Setup
  public void setup() {
    int n = Integer.MAX_VALUE / 2;
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = i + 1;
    }
  }

  /**
   * Each recursive call has to:
   * - Push a new frame onto the call stack
   * - Save registers and return address
   * - Pop it all back on return
   */
  @Benchmark
  public int recursive() {
    return binarySearch(arr, target);
  }

  @Benchmark
  public int iterative() {
    return binarySearchIterative(arr, target);
  }

}
