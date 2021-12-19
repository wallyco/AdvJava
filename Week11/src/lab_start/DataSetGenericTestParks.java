package lab_start;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


public class DataSetGenericTestParks {

	@Test
	public void testGetMin0() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		assertEquals(null, ds.getMin());
	}

	@Test
	public void testGetMax0() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		assertEquals(null, ds.getMax());
	}

	@Test
	public void testGetMin1() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(1));
		assertEquals(1, ds.getMin().getMeasure());
	}

	@Test
	public void testGetMax1() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(1));
		assertEquals(1, ds.getMax().getMeasure());
	}

	@Test
	public void testGetMin2() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(1));
		ds.add(new C1(2));
		assertEquals(1, ds.getMin().getMeasure());
	}

	@Test
	public void testGetMax2() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(1));
		ds.add(new C1(2));
		assertEquals(2, ds.getMax().getMeasure());
	}

	@Test
	public void testGetMin3() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(2));
		ds.add(new C1(1));
		assertEquals(1, ds.getMin().getMeasure());
	}

	@Test
	public void testGetMax3() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		ds.add(new C1(2));
		ds.add(new C1(1));
		assertEquals(2, ds.getMax().getMeasure());
	}

	@Test
	public void testSort0() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = { new C1(3), new C1(4), new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), new C1(3), new C1(4), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort5() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = { new C1(3), new C1(4), new C1(0), new C1(9), new C1(8), new C1(6), new C1(5), new C1(7),
				new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = { new C1(0), new C1(1), new C1(2), new C1(3), new C1(4), new C1(5), new C1(6), new C1(7),
				new C1(8), new C1(9), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort1() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = { new C1(1), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = { new C1(1), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort2() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = { new C1(2), new C1(1), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort3() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = { new C1(1), new C1(2), };
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = { new C1(1), new C1(2), };
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
	}

	@Test
	public void testSort4() {
		DataSetGeneric<C1> ds = new DataSetGeneric<>();
		final C1[] SORT_ME = {};
		Arrays.stream(SORT_ME).forEach(e -> ds.add(e));

		MyList<C1> expect = new MyArrayList<>();
		final C1[] EXPECTED = {};
		Arrays.stream(EXPECTED).forEach(e -> expect.add(e));

		MyList<C1> actual = ds.sort();
		assertEquals(expect, actual);
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

		private DataSetGenericTestParks getOuterType() {
			return DataSetGenericTestParks.this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			C1 other = (C1) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (iv != other.iv)
				return false;
			return true;
		}

	}
}
