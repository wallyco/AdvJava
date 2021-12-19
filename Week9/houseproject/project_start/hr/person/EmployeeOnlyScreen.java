package project_start.hr.person;

import project_start.collection.Measurable;
import project_start.hr.mgmt.Executive;
import project_start.hr.mgmt.Manager;
import project_start.tools.Screener;

public class EmployeeOnlyScreen<E extends Measurable> implements Screener<E> {

	@Override
	public boolean test(E obj) {
		if(obj instanceof Executive)return false;
		if(obj instanceof Manager)return false;
		return true;
	}

}
