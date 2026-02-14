package com.ntr.hrank.rec;


import com.ntr.hrank.TreeNode;
import com.sun.source.tree.Tree;
import java.util.List;

public class RecursionAlgorithms {


  /**
   * n = (n - 1) * (n - 2) * (n - 3) *...(n - n)
   */
  static int factorial(int n) {
    if (n == 0) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  /**
   * // [1, 2, 7]
   * // call = 0:
   * //  -> base case = false
   * //  -> nums[0](v=1) + resolve_nums[1]
   * // call = 1:
   * //  -> base case = false
   * //  -> nums[1](v=2) + resolve_nums[2]
   * // call = 2:
   * //  -> base case = false
   * //  -> nums[2](v=7) + resolve_nums[3]
   * // call = 3:
   * //  -> base case = true => return 0;
   * // call = 2 resuming:
   * //  -> return 7 + 0 (call=3 returned val)
   * // call = 1 resuming:
   * //  -> return 2 + 7 (call=2 returned val)
   * // call = 0 resuming:
   * //  -> return 1 + 9 (call=1 returned val)
   * // ==> arraySum([1, 2, 7], 0) -> 10
   */
  static int arraySum(int[] nums, int i) {
    if (i >= nums.length) {
      return 0;
    }
    return nums[i] + arraySum(nums, i + 1);
  }


  /**
   * str = [e, m, i, l, y] -> [y, l, i, m, e]
   * <p>
   * call = 1: arguments: [str = [e, m, i, l, y], i = 0, j = 4]
   * -> base case skips
   * -> swap str[i=0] with str[j=4] => str = [y, m, i, l, e]
   * -> return resolve_call_2(i + 1(1), j - 1(3))
   * call = 2: arguments: [str = [y, m, i, l, e], i = 1, j = 3]
   * -> base case skips
   * -> swap str[i=1] with str[j=3] => str = [y, l, i, m, e]
   * -> return resolve_call_3(i + 1(2), j - 1(2))
   * call = 3: arguments: [str = [y, l, i, m, e], i = 2, j = 2]
   * -> base case returns str = [y, l, i, m, e]
   * call 2 resumes:
   * -> returns str = [y, l, i, m, e]
   * call 1 resumes:
   * -> returns str = [y, l, i, m, e]
   * ==> reverse([e, m, i, l, y], 0, 4) -> [y, l, i, m, e]
   */
  static char[] reverse(char[] str, int i, int j) {
    if (i >= j) {
      return str;
    }
    char c = str[i];
    str[i] = str[j];
    str[j] = c;
    return reverse(str, i + 1, j - 1);
  }

  static void printNumbers(int[] arr, int i) {
    if (i >= arr.length) {
      return;
    }
    System.out.println(arr[i]);
    printNumbers(arr, i + 1);
  }

  /**
   * iterative:
   * int max = nums[0];
   * <p>
   * for (int j = 0; j < nums.length; j++) {
   * if (max < nums[j]) {
   * max = nums[j];
   * }
   * }
   * return max;
   * <p>
   * [1, 3, 2]
   * call = 1:
   * -> base case skips
   * -> if current(nums[i]) > k => k = nums[i] (no update)
   * -> check if k = 1 < resolve_nums[1](rec call) and return larger one
   * call = 2:
   * -> base case skips
   * -> if current(nums[i]) > k => k = nums[i] (update -> k = 3)
   * -> check if k = 3 < resolve_nums[2](rec call) and return larger one
   * call = 3:
   * -> base case skips
   * * -> if current(nums[i]) > k => k = nums[i] (no update)
   * * -> check if k = 3 < resolve_nums[3](rec call) and return larger one
   * call = 4:
   * -> base case succeeds => return k(3)
   * call = 3 resumes:
   * -> returns k(3)
   * call = 2 resumes:
   * * -> returns k(3)
   * call = 1 resumes:
   * * -> returns k(3)
   */

  static long findMax(long[] nums, int i) {
    if (i == nums.length - 1) {
      return nums[i];
    }
    return Math.max(nums[i], findMax(nums, i + 1));
  }

  /**
   * [1, 2, 3, 2, 2], i = 0, x = 2
   * call 1: i = 0
   * -> base case = false
   * -> if x == current(2): false
   * -> resolve call 2
   * call 2: i = 1
   * -> base case = false
   * -> if x == current(2): true
   * -> add 1 to the resolution of call 3
   * call 3: i = 2
   * -> base case = false
   * -> if x == current(3): false
   * -> resolve call 4
   * call 4: i = 3
   * -> base case = false
   * -> if x == current(2): true
   * -> add 1 to the resolution of call 5
   * call 5: i = 4
   * -> base case = false
   * -> if x == current(2): true
   * -> add 1 to the resolution of call 6
   * call 6: i = 5
   * -> base case = true -> return 0
   * call 5 resuming: -> returns 1 + 0
   * call 4 resuming: -> returns 1 + 1
   * call 3 resuming: -> returns 0 + 2
   * call 2 resuming: -> returns 1 + 2
   * call 1 resuming: -> returns 3
   */
  static int countOccurrences(int[] nums, int i, int x) {
    // x = target val
    if (i == nums.length) {
      return 0;
    }
    if (x == nums[i]) {
      return 1 + countOccurrences(nums, i + 1, x);
    }
    return countOccurrences(nums, i + 1, x);
  }

  /**
   * Return true if the array is sorted in asc order.
   * [1, 2, 2, 7] → true
   * call 1: i = 1:
   * -> base case = false
   * -> if previous(1) is bigger than current(2): false
   * -> resolve call 2
   * call 2: i = 2:
   * -> base case = false
   * -> if prev(2) is bigger than current(2): false
   * resolve call 3
   * -> call 3: i = 3
   * -> if previous(2) is bigger than current(7): false
   * -> resolve call 4
   * call 4: i = 0:
   * -> base case = true -> returns true
   * call 3 resuming: returns true
   * call 2 resuming: returns true
   * call 1 resuming: returns true
   * ==> isSorted([1, 2, 2, 7]) -> true
   * <p>
   * ! can make this method private, add a public wrapper excluding the "i" argument to protect from
   * invalid input
   */
  static boolean isSorted(int[] nums, int i) {
    if (i >= nums.length) {
      return true;
    }
    if (nums[i - 1] > nums[i]) {
      return false;
    }
    return isSorted(nums, i + 1);
  }

  /**
   * Very similar to isSorted algorithm
   * [1, 2, 3], x = 2
   * call 1, i = 0:
   * -> base case: false
   * -> if x(2) == current(1): false
   * -> resolve call 2
   * call 2, i = 1:
   * -> base case: false
   * -> if x(2) == current(2): true
   * -> returns i
   * call 1 resumes: returns 1
   * ==> firstIndex([1, 2, 3], 0, 2) -> 1
   */
  static int firstIndex(int[] nums, int i, int x) {
    if (i >= nums.length) {
      return -1;
    }
    if (x == nums[i]) {
      return i;
    }
    return firstIndex(nums, i + 1, x);
  }


  /**
   * n = 13579
   * call 1, n = 13579:
   * -> base case: false
   * -> 1 + resolve_call_2 (n/10 = 1357)
   * call 2, n = 1357:
   * -> base case: false
   * -> 1 + resolve_call_3 (n/10 = 135)
   * call 3, n = 135:
   * -> base case: false
   * -> 1 + resolve_call_4(n/10 = 13)
   * call 4, n = 13:
   * -> base case: false
   * -> 1 + resolve_call_5(n/10 = 1)
   * call 5, n = 1:
   * -> base case: false
   * -> 1 + resolve_call_6(n/10 = 0)
   * call 6, n = 0:
   * -> base case: true -> returns 0
   * call 5 resumes -> returns 1 + 0
   * call 4 resumes -> 1 + 1 = 2
   * call 3 resumes -> 1 + 2 = 3
   * call 2 resumes -> 1 + 3 = 4
   * call 1 resumes -> 1 + 4 = 5
   * ==> countDigits(13579) -> 5
   */
  static int countDigits(int n) {
    if (n == 0) {
      return 0;
    }
    return 1 + countDigits(n / 10);
  }

  /**
   * while (n >= 0) {
   * x *= x;
   * n--;
   * }
   * return x;
   * <p>
   * power(2, 3) → 8
   * call 1: n = 3
   * -> base case: false
   * -> 2 * resolve_power(2, n-1(2))
   * call 2: n = 2
   * -> base case: false
   * -> 2 * resolve_power(2, n-1(1))
   * call 3: n = 1
   * -> base case: false
   * -> 2 * resolve_power(2, n-1(0))
   * call 4: n = 0
   * -> base case: true -> returns 1
   * call 3 resumes: 2 * 1 = 2
   * call 2 resumes: 2 * 2 = 4
   * call 1 resumes: 2 * 4 = 8
   * ==> power(2, 3) -> 8
   */
  static long power(long x, int n) {
    if (n == 0) {
      return 1;
    }
    return x * power(x, n - 1);
  }


  /**
   * int sum = 0;
   * while (n > 0) {
   * sum += n % 10;
   * n = n / 10;
   * }
   * return sum;
   */
  static int sumDigits(int n) {
    if (n == 0) {
      return 0;
    }
    return (n % 10) + sumDigits(n / 10);
  }

  static boolean isPalindrome(String s, int left, int right) {
    if (left >= right) {
      return true;
    }
    if (s.charAt(left) != s.charAt(right)) {
      return false;
    }
    return isPalindrome(s, left + 1, right - 1);
  }

  static long findMin(long[] nums, int i) {
    if (i == nums.length - 1) {
      return nums[i];
    }
    long nextVal = findMin(nums, i + 1);
    return Math.min(nums[i], nextVal);
  }

  /**
   * return a new string
   * str = "emily"
   * call 1:
   * -> base case: false
   * -> get last chr = 'y'
   * -> add 'y' at start of resolve_reverse(substring(0, 4 - 1 = 3))
   * call 2: str = "emil"
   * -> base case: false
   * -> get last chr = 'l'
   * -> add 'l' at start of resolve_reverse(substring(0, 3 - 1 = 2))
   * call 3: str = "emi"
   * -> base case: false
   * -> get last chr = 'i'
   * -> add 'i' at start of resolve_reverse(substring(0, 2 - 1 = 1))
   * call 4: str = "em"
   * -> base case: false
   * -> get last chr = 'm'
   * -> add 'm' at start of resolve_reverse(substring(0, 1 - 1 = 0))
   * call 5: str = "e"
   * -> base case: false
   * -> get last chr = 'e'
   * -> add 'e' at start of resolve_reverse(substring(0, 0 - 1 = -1))
   * call 6: str = ""
   * -> base case: true -> returns ""
   * call 5 resumes: returns e + ""
   * call 4 resumes: returns m + e
   * call 3 resumes: returns i + me
   * call 2 resumes: returns l + ime
   * call 1 resumes: returns y + lime
   * ==> reverse("emily") -> ylime
   */
  static String reverse(String str) {
    if (str.length() <= 1) {
      return str;
    }
    return str.charAt(str.length() - 1)
        + reverse(str.substring(0, str.length() - 1));
  }

  /**
   * bs(0, 5) -> [5, 10, 25, 50, 81] x = 25
   */
  public static int binarySearch(int[] nums, int x) {
    return binarySearch(nums, 0, nums.length - 1, x);
  }

  private static int binarySearch(int[] nums, int left, int right, int x) {
    // if no search space left
    if (left > right) {
      return -1;
    }
    int mid = left + (right - left) / 2;
    return x == nums[mid]
        ? mid
        : x > nums[mid]
            ? binarySearch(nums, mid + 1, right, x)
            : binarySearch(nums, left, mid - 1, x);
  }

  static int[] mergeSort(int[] nums) {
    int n = nums.length;
    if (n <= 1) {
      return nums;
    }
    // determine split
    int mid = n / 2;
    // Split into two halves with ranges:
    // left  = nums[0 .. mid)
    // right = nums[mid .. n)
    // for odd n, the right half will contain one extra element.
    int[] left = new int[mid];
    int[] right = new int[n - mid];
    // populate arrays
    System.arraycopy(nums, 0, left, 0, mid);
    System.arraycopy(nums, mid, right, 0, n - mid);

    return merge(mergeSort(left), mergeSort(right));
  }

  private static int[] merge(int[] left, int[] right) {
    int n = left.length + right.length;
    int[] merged = new int[n];

    // compare the smallest remaining element of each array
    int i = 0;
    int j = 0;
    int k = 0;
    // merge until 1 array is out of bounds
    while (j < right.length && i < left.length) {
      // if left smaller OR equal, append, go forward i
      // at each step, go forward k
      if (left[i] <= right[j]) {
        merged[k++] = left[i++];
      } else {
        // if right smaller, append, go forward j
        merged[k++] = right[j++];
      }
    }
    // right is out of bounds, append L
    if (j == right.length) {
      while (k < n) {
        merged[k] = left[i];
        i++;
        k++;
      }
    }
    // left is out of bounds, append right
    if (i == left.length) {
      while (k < n) {
        merged[k] = right[j];
        j++;
        k++;
      }
    }
    return merged;
  }
}
