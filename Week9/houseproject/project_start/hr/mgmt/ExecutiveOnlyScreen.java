package project_start.hr.mgmt;

import project_start.collection.Measurable;
import project_start.tools.Screener;

public class ExecutiveOnlyScreen<E extends Measurable> implements Screener<E>{

	@Override
	public boolean test(E obj) {
		if(obj instanceof Executive) return true;
		return false;
	}

}
