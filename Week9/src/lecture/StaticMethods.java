package lecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StaticMethods {
	static int count = 0;

	public static List<Integer> createList(int c) {
		count = c;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++)
			list.add(i);
		return list;
	}

	public static long getTestTime(Collection<Integer> c) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
			c.contains((int) (Math.random() * 2 * count));
		return System.currentTimeMillis() - startTime;
	}

	public static long getRemoveTime(Collection<Integer> c) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < count; i++)
			c.remove(i);
		return System.currentTimeMillis() - startTime;
	}
}
