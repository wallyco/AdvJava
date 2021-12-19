package lab_start;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ComparatorTreeTester {

    public static String[] words = {"cat", "apple", "banana", "gargle", "door", "elephant", "frito"};
    public static String[] orderedWords = {"apple", "banana", "cat", "door", "elephant", "frito", "gargle"};
    public static String[] reversedWords = {"banana", "gargle","apple", "frito", "door", "cat", "elephant"};
    public static Integer[] integers = {6, 3, 9, 1, 8, 2, 4, 7, 5};
    public static Integer[] orderedIntegers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static Integer[] reversedIntegers = {9, 8, 7, 6, 5, 4, 3, 2, 1};
    public static Integer[] evenOddIntegers = {1, 3, 2, 5, 4, 7, 6, 9, 8};

    // Tests natural order with Strings if no Comparator is supplied.
    @Test
    public void testNaturalOrderString() {
        ComparatorTree<String> comparatorTree = new ComparatorTree<>(words);
        Iterator<String> iterator = comparatorTree.iterator();
        for (String s : orderedWords) {
            assertEquals(iterator.next(), s);
        }
    }

    // Tests using a comparator to order String elements by their reversed value.
    @Test
    public void testReversedOrderString() {
        ComparatorTree<String> comparatorTree = new ComparatorTree<>(words, (o1, o2) -> {
            StringBuilder reverse1 = new StringBuilder(o1).reverse();
            StringBuilder reverse2 = new StringBuilder(o2).reverse();
            return reverse1.toString().compareTo(reverse2.toString());
        });
        Iterator<String> iterator = comparatorTree.iterator();
        for (String s : reversedWords) {
            assertEquals(iterator.next(), s);
        }
    }

    // Tests natural order with Integers if no Comparator is supplied.
    @Test
    public void testNaturalOrderInteger() {
        ComparatorTree<Integer> comparatorTree = new ComparatorTree<>(integers);
        Iterator<Integer> iterator = comparatorTree.iterator();
        for (Integer i : orderedIntegers) {
            assertEquals(iterator.next(), i);
        }
    }

    // Tests reverse order with Integers using a comparator.
    @Test
    public void testReverseOrderInteger() {
        ComparatorTree<Integer> comparatorTree = new ComparatorTree<>(integers, ((o1, o2) -> {
            int neg1 = o1 * -1;
            int neg2 = o2 * -1;
            return Integer.compare(neg1, neg2);
        }));
        Iterator<Integer> iterator = comparatorTree.iterator();
        for (Integer i : reversedIntegers) {
            assertEquals(iterator.next(), i);
        }
    }

    // Tests Integer orders using a comparator. Odd numbers are subtracted by one; even added one;
    @Test
    public void testEvenOddInteger() {
        ComparatorTree<Integer> comparatorTree = new ComparatorTree<>(integers, ((o1, o2) -> {
            Integer[] integers = {o1, o2};
            int[] results = new int[2];
            for (int i = 0; i < integers.length; i++) {
                if (integers[i] % 2 == 0) {
                    results[i] = integers[i] + 1;
                } else {
                    results[i] = integers[i] - 1;
                }
            }
            return Integer.compare(results[0], results[1]);
        }));
        Iterator<Integer> iterator = comparatorTree.iterator();
        for (Integer i : evenOddIntegers) {
            assertEquals(iterator.next(), i);
        }
    }
}


