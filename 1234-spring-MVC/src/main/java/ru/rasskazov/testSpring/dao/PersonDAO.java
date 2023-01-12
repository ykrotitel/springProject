package ru.rasskazov.testSpring.dao;

import org.springframework.stereotype.Component;
import ru.rasskazov.testSpring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(0, "Nick"));
        people.add(new Person(1, "Daniel"));
        people.add(new Person(2, "Kirill"));
        people.add(new Person(3, "Mate"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null );
    }
}

