package experimentFX;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

// The weird stuff--unused variables, etc, are attempts to keep
// the Java compiler and runtime honest.  They are incredibly efficient,
// which might further skew an already flawed test.
// author - J. Parks
//
public class SortMethods {
	
	protected static void tweakJit() {
		// try calling everything to optimize the bytecode
		methA(1, 1);
		methB(1, 1);
		methC(1);
		methD(1);
		methE(1,1);
	}

	// binary search
	protected static double methA(int numElements, int numSearches) {
		long[]  elements = new long[numElements];
		for (int i = 0; i < numElements; i++) {
			elements[i] = ((long) i) * 3;
		}
		Random rand = new Random();
		double totMillis = 0;
		for (int i = 0; i < numSearches; i++) {
			int key = rand.nextInt(numElements * 3);
			long startTime = System.currentTimeMillis();
			double d = methF(elements, key);
			long stopTime = System.currentTimeMillis();
			d = d + 1;
			long elapsedTime = stopTime - startTime;
			totMillis += elapsedTime;
		}
		// System.out.println("bin search times: " + numSearches);
		double elapsedSecs = (double) totMillis / 1000;
		return elapsedSecs;
	}
	
	// linear search
	protected static double methB(int numElements, int numSearches) {
		long[]  elements = new long[numElements];
		for (int i = 0; i < numElements; i++) {
			elements[i] = ((long) i) * 3;
		}
		Random rand = new Random();
		double totMillis = 0;
		for (int i = 0; i < numSearches; i++) {
			int key = rand.nextInt(numElements * 3);
			long startTime = System.currentTimeMillis();
			long val = methG(elements, key);
			long stopTime = System.currentTimeMillis();
			val = val + 1;
			long elapsedTime = stopTime - startTime;
			totMillis += elapsedTime;
		}
		double elapsedSecs = (double) totMillis / 1000;
		return elapsedSecs;
	}

	// selection sort
	protected static double methC(int numElements) {
		double[] elements = new double[numElements];
		Random rand = new Random();
		for (int i = 0; i < numElements; i++) {
			elements[i] = rand.nextDouble();
		}
		long startTime = System.currentTimeMillis();
		methH(elements);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		double elapsedSecs = (double) elapsedTime / 1000;
		return elapsedSecs;
	}

	// Arrays.sort (some recursive algorithm)
	protected static double methD(int numElements) {
		double[] elements = new double[numElements];
		Random rand = new Random();
		for (int i = 0; i < numElements; i++) {
			elements[i] = rand.nextDouble();
		}
		long startTime = System.currentTimeMillis();
		Arrays.sort(elements);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		double elapsedSecs = (double) elapsedTime / 1000;
		return elapsedSecs;
	}
	
	// search hash set
	protected static double methE(int numElements, int numSearches) {
		HashSet<Long>  elements = new HashSet<>();
		for (int i = 0; i < numElements; i++) {
			elements.add(((long) i) * 3);
		}
		Random rand = new Random();
		double totMillis = 0;
		for (int i = 0; i < numSearches; i++) {
			long key = rand.nextInt(numElements * 3);
			long startTime = System.currentTimeMillis();
			boolean b = elements.contains(key);
			long stopTime = System.currentTimeMillis();
			b = !b;
			long elapsedTime = stopTime - startTime;
			totMillis += elapsedTime;
		}
		double elapsedSecs = (double) totMillis / 1000;
		return elapsedSecs;
	}
	
	  /** Use binary search to find the key in the list */
	protected static int methF(long[] list, long key) {
	    int low = 0;
	    int high = list.length - 1;

	    while (high >= low) {
	      int mid = (low + high) / 2;
	      if (key < list[mid])
	        high = mid - 1;
	      else if (key == list[mid])
	        return mid;
	      else
	        low = mid + 1;
	    }

	    return -low - 1; // Now high < low
	  }
	  
	  /** The method for finding a key in the list */
	protected static int methG(long[] list, long key) {
	    for (int i = 0; i < list.length; i++) {
	      if (key == list[i])
	        return i;
	    }
	    return -1;
	  }

	  /** The method for sorting the numbers */
	protected static void methH(double[] list) {
	    for (int i = 0; i < list.length - 1; i++) {
	      // Find the minimum in the list[i..list.length-1]
	      double currentMin = list[i];
	      int currentMinIndex = i;

	      for (int j = i + 1; j < list.length; j++) {
	        if (currentMin > list[j]) {
	          currentMin = list[j];
	          currentMinIndex = j;
	        }
	      }
	      // Swap list[i] with list[currentMinIndex] if necessary;
	      if (currentMinIndex != i) {
	        list[currentMinIndex] = list[i];
	        list[i] = currentMin;
	      }
	    }
	  }
}
