package lecture.d_methodReferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MethodReferencesTest {

//	You use lambda expressions to create anonymous methods. Sometimes, 
//	however, a lambda expression does nothing but call an existing method. 
//	In those cases, it's often clearer to refer to the existing method by name. 
//	Method references enable you to do this; they are compact, easy-to-read lambda 
//	expressions for methods that already have a name.

	public static void main(String... args) {
		List<Person> roster = Person.createRoster();
//		for (Person p : roster) {
//			p.printPerson();
//		}
		Person[] rosterAsArrayAge = roster.toArray(new Person[roster.size()]);
		Person[] rosterAsArrayName = roster.toArray(new Person[roster.size()]);
		
		class PersonAgeComparator implements Comparator<Person> {
			public int compare(Person a, Person b) {
				return a.getBirthday().compareTo(b.getBirthday());
			}
		}

		// Without method reference
		Arrays.sort(rosterAsArrayAge, new PersonAgeComparator());

		// With lambda expression
		Arrays.sort(rosterAsArrayAge, (Person a, Person b) -> {
			return a.getBirthday().compareTo(b.getBirthday());
		});

		// With method reference
		Arrays.sort(rosterAsArrayAge, Person::compareByAge);

		// Reference to an instance method of a particular object
		class ComparisonProvider {
			public int compareByName(Person a, Person b) {
				return a.getName().compareTo(b.getName());
			}

			public int compareByAge(Person a, Person b) {
				return a.getBirthday().compareTo(b.getBirthday());
			}
		}
		ComparisonProvider myComparisonProvider = new ComparisonProvider();
		Arrays.sort(rosterAsArrayAge, myComparisonProvider::compareByAge);
		Arrays.sort(rosterAsArrayName, myComparisonProvider::compareByName);

		System.out.println("People by Age");
		for (Person p : rosterAsArrayAge) {
			p.printPerson();
		}
		System.out.println("\n\nPeople by Name");
		for (Person p : rosterAsArrayName) {
			p.printPerson();
		}
	}
}