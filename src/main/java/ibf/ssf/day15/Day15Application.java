package ibf.ssf.day15;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf.ssf.day15.model.Person;
import ibf.ssf.day15.repository.PersonRepo;
import ibf.ssf.day15.repository.TestRepo;
import ibf.ssf.day15.service.PersonService;
import ibf.ssf.day15.utils.Util;

@SpringBootApplication
public class Day15Application implements CommandLineRunner {

	@Autowired
	TestRepo testRepo;


	@Autowired
	PersonService personService; // Already autowires personrepo

	public static void main(String[] args) {
		SpringApplication.run(Day15Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// testRepo.storeValueData("name", "serra");
		// String name = testRepo.retrieveValueData("name");
		// System.out.println("From name key in Redis: " + name);

		// testRepo.storeValueData("email", "beckykok@gmail.com");
		// String email = testRepo.retrieveValueData("email");
		// System.out.println("From email key in Redis: " + email);

		// // testRepo.addToList("cart", "apple");
		// // testRepo.addToList("cart", "oranges");
		// // testRepo.addToList("cart", "grapes");
		// List<String> fruits = testRepo.getList("cart");
		// System.out.println(fruits);

		Person p = new Person(1, "Becky", 20000);
		personService.addPerson(Util.KEY_PERSON, p);
		p = new Person(2, "Serra", 10000);
		personService.addPerson(Util.KEY_PERSON, p);
		p = new Person(3, "Marcus", 10500);
		personService.addPerson(Util.KEY_PERSON, p);

		List<Person> persons = personService.getPersonList(Util.KEY_PERSON);
		System.out.println(persons);

		
		Boolean isDeleted = personService.deletePerson(Util.KEY_PERSON, p);
		System.out.println(isDeleted);
	}

}
