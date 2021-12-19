package project_start.hr.mgmt;

import project_start.collection.Measurable;
import project_start.tools.Screener;

public class ManagerOnlyScreen<E extends Measurable> implements Screener<E>{

	@Override
	public boolean test(E obj) {
		if(obj instanceof Manager) return true;
		return false;
	}
	
}
