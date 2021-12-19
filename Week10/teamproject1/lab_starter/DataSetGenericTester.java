package lab_starter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import experimentFX.SortMethods;

class DataSetGenericTester {


	@Test
	public void testSort0() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = { new C1(3), new C1(4), new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		List<C1> expect = new ArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), new C1(3), new C1(4), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		List<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort1() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = { new C1(1), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		List<C1> expect = new ArrayList<>();
		final C1[] EXPECTED = { new C1(1), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		List<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort2() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = { new C1(2), new C1(1), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		List<C1> expect = new ArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		List<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort3() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = { new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		List<C1> expect = new ArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		List<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort4() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = {};
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		List<C1> expect = new ArrayList<>();
		final C1[] EXPECTED = {};
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		List<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}
	
	//Author Austin Jenkins
	@Test
	public void testSortOriginal() {
		DataSetGenericMergeSort<C1> ds = new DataSetGenericMergeSort<>();
		DataSetGenericMergeSort<C1> og = new DataSetGenericMergeSort<>();
		final C1[] SORT_ME = { new C1(3), new C1(4), new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> og.add(e));
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));
		
		List<C1> newList = ds.sort();

		assertEquals(ds, og);
	}

	class C1 implements Measurable {
		int iv;
		C1(int v) {
			iv = v;
		}
		@Override
		public int getMeasure() {
			return iv;
		}
		
		public String toString() {
			return "C1: " + iv;
		}		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			C1 other = (C1) obj;
			if (iv != other.iv)
				return false;
			return true;
		}
	}
}


