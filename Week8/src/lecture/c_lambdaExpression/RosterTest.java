package lecture.c_lambdaExpression;

import java.util.List;

public class RosterTest {

	public static void main(String... args) {

		List<Person> roster = Person.createRoster();

//      -- Simple print to view the data
//		for (Person p : roster) {
//			p.printPerson();
//		}

// Approach 1: Create Methods that Search for Persons that Match One Characteristic

		System.out.println("Persons older than 20:");
		RosterMethods.printPersonsOlderThan(roster, 20);
		System.out.println();

// Approach 2: Create More Generalized Search Methods

		System.out.println("Persons between the ages of 14 and 30:");
		RosterMethods.printPersonsWithinAgeRange(roster, 14, 30);
		System.out.println();

// Approach 3: Specify Search Criteria Code in a Local Class

		System.out.println("Persons who are eligible for Selective Service:");
		class CheckPersonEligibleForSelectiveService implements RosterMethods.CheckPerson {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
			}
		}
		RosterMethods.printPersons(roster, new CheckPersonEligibleForSelectiveService());
		System.out.println();

// Approach 4: Specify Search Criteria Code in an Anonymous Class

		System.out.println("Persons who are eligible for Selective Service " + "(anonymous class):");
		RosterMethods.printPersons(roster, new RosterMethods.CheckPerson() {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25;
			}
		});
		System.out.println();

// Approach 5: Specify Search Criteria Code with a Lambda Expression

		System.out.println("Persons who are eligible for Selective Service " + "(lambda expression):");
		RosterMethods.printPersons(roster,
				(Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
		System.out.println();

// Approach 6: Use Standard Functional Interfaces with Lambda Expressions

		System.out.println("Persons who are eligible for Selective Service " + "(with Predicate parameter):");
		RosterMethods.printPersonsWithPredicate(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
		System.out.println();

// Approach 7: Use Lambda Expressions Throughout Your Application

		System.out.println(
				"Persons who are eligible for Selective Service " + "(with Predicate and Consumer parameters):");

		RosterMethods.processPersons(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.printPerson());
		System.out.println();

// Approach 7, second example

		System.out.println("Persons who are eligible for Selective Service "
				+ "(with Predicate, Function, and Consumer parameters):");

		RosterMethods.processPersonsWithFunction(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.getEmailAddress(),
				email -> System.out.println(email));
		System.out.println();

// Approach 8: Use Generic More Extensively

		System.out.println("Persons who are eligible for Selective Service " + "(generic version):");
		RosterMethods.processElements(roster,
				p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25, p -> p.getEmailAddress(),
				email -> System.out.println(email));
		System.out.println();

// Approach 9: Use Bulk Data Operations That Accept Lambda Expressions as Parameters

		System.out.println("Persons who are eligible for Selective Service " + "(with bulk data operations):");
		roster.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25)
				.map(p -> p.getEmailAddress()).forEach(email -> System.out.println(email));
	}
}