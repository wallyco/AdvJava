package project_start.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import project_start.collection.DataSetGeneric;
import project_start.hr.mgmt.Executive;
import project_start.hr.mgmt.ExecutiveOnlyScreen;
import project_start.hr.mgmt.Manager;
import project_start.hr.mgmt.ManagerOnlyScreen;
import project_start.hr.person.Employee;
import project_start.hr.person.EmployeeOnlyScreen;
import project_start.tools.Screener;

class PayrollTest {

	@Test
	void testAddEmployee() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		Employee em2 = dsg.get(0);
		assertEquals(em.getEmployeeName(), em2.getEmployeeName());
	}

	@Test
	void testAddExecutive() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		Employee em2 = dsg.get(1);
		assertEquals(ma.getEmployeeName(), em2.getEmployeeName());
	}

	@Test
	void testAddManager() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		Employee em2 = dsg.get(2);
		assertEquals(ex.getEmployeeName(), em2.getEmployeeName());
	}

	@Test
	void testDisplayEverybody() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		ArrayList<Employee> everyone = new ArrayList<>();
		everyone.add(em);
		everyone.add(ma);
		everyone.add(ex);
		List<Employee> dsgRet = dsg.getList();
		assertEquals(everyone, dsgRet);
	}

	@Test
	void testDisplayHighestSalary() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(444, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		Employee rich = dsg.getMax();
		assertEquals(ex.getEmployeeName(), rich.getEmployeeName());
		assertEquals(ex.getSalary(), rich.getSalary(), .0001);
	}

	@Test
	void testDisplayOnlyEmployees() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		List<Employee> emp = dsg.getList(new EmScreen());
		assertEquals(em.getEmployeeName(), emp.get(0).getEmployeeName());
	}

	@Test
	void testDisplayOnlyExecutives() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		List<Employee> emp = dsg.getList(new ExScreen());
		assertEquals(ex.getEmployeeName(), emp.get(0).getEmployeeName());
	}

	@Test
	void testDisplayOnlyManagers() {
		DataSetGeneric<Employee> dsg = new DataSetGeneric<>();
		Employee em = new Employee(44, "addedEmp", 1000);
		Employee ma = new Manager(55, "addedManager", 2000, "dept");
		Employee ex = new Executive(66, "addedEx", 3000, "division", .3);
		dsg.add(em);
		dsg.add(ma);
		dsg.add(ex);
		List<Employee> emp = dsg.getList(new MaScreen());
		assertEquals(ma.getEmployeeName(), emp.get(0).getEmployeeName());
	}

	class ExScreen implements Screener<Employee> {

		@Override
		public boolean test(Employee objToTest) {
			if ((objToTest instanceof Executive))
				return true;
			return false;
		}
	}

	class MaScreen implements Screener<Employee> {

		@Override
		public boolean test(Employee objToTest) {
			if ((objToTest instanceof Manager && !(objToTest instanceof Executive)))
				return true;
			return false;
		}
	}

	class EmScreen implements Screener<Employee> {

		@Override
		public boolean test(Employee objToTest) {
			if (!(objToTest instanceof Manager))
				return true;
			return false;
		}
	}

	@Test
	public void TestGetList() {
		Employee emp = new Employee(22, "Wally", 31000);
		Manager mgr = new Manager(33, "Heather", 40000, "HR");
		Executive exec = new Executive(44, "Mike", 60000, "HR", .2);
		DataSetGeneric<Employee> empList = new DataSetGeneric<>();
		empList.add(emp);
		empList.add(mgr);
		empList.add(exec);
		ArrayList<Employee> testList = (ArrayList<Employee>) empList.getList();
		// Since the entire list is being retrieved, empList and testList should be
		// equal
		assertEquals(empList, testList);
	}

	@Test
	public void TestScreenerGetListEmployeeOnly() {
		// Setting up the DataSetGeneric datastore
		Employee emp = new Employee(22, "Wally", 31000);
		Manager mgr = new Manager(33, "Heather", 40000, "HR");
		Executive exec = new Executive(44, "Mike", 60000, "HR", .2);
		DataSetGeneric<Employee> empList = new DataSetGeneric<>();
		empList.add(emp);
		empList.add(mgr);
		empList.add(exec);

		// Creating the test list to compare to getList(Screener)
		DataSetGeneric<Employee> testlist = new DataSetGeneric<>();
		testlist.add(emp);

		// Performing the getList(Screener) function
		EmployeeOnlyScreen e = new EmployeeOnlyScreen();
		ArrayList<Employee> screened = (ArrayList<Employee>) empList.getList(e);

		assertEquals(testlist, screened);
	}

	@Test
	public void TestScreenerGetListManagerOnly() {
		// Setting up the DataSetGeneric datastore
		Employee emp = new Employee(22, "Wally", 31000);
		Manager mgr = new Manager(33, "Heather", 40000, "HR");
		Executive exec = new Executive(44, "Mike", 60000, "HR", .2);
		DataSetGeneric<Employee> empList = new DataSetGeneric<>();
		empList.add(emp);
		empList.add(mgr);
		empList.add(exec);

		// Creating the test list to compare to getList(Screener)
		DataSetGeneric<Employee> testlist = new DataSetGeneric<>();
		testlist.add(mgr);

		// Performing the getList(Screener) function
		ManagerOnlyScreen e = new ManagerOnlyScreen();
		ArrayList<Employee> screened = (ArrayList<Employee>) empList.getList(e);

		assertEquals(testlist, screened);
	}

	@Test
	public void TestScreenerGetListExecutiveOnly() {
		// Setting up the DataSetGeneric datastore
		Employee emp = new Employee(22, "Wally", 31000);
		Manager mgr = new Manager(33, "Heather", 40000, "HR");
		Executive exec = new Executive(44, "Mike", 60000, "HR", .2);
		DataSetGeneric<Employee> empList = new DataSetGeneric<>();
		empList.add(emp);
		empList.add(mgr);
		empList.add(exec);

		// Creating the test list to compare to getList(Screener)
		DataSetGeneric<Employee> testlist = new DataSetGeneric<>();
		testlist.add(exec);

		// Performing the getList(Screener) function
		ExecutiveOnlyScreen e = new ExecutiveOnlyScreen();
		ArrayList<Employee> screened = (ArrayList<Employee>) empList.getList(e);

		assertEquals(testlist, screened);
	}

}
