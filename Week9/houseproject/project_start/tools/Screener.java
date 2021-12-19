package project_start.tools;

import project_start.collection.Measurable;

public interface Screener<E extends Measurable> {
	boolean test(E obj);
}
