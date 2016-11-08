package jp.agri_note.recyclerviewreversesample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PeopleRepository {

    final Random random = new Random(12345L);
    final List<Person> people = new ArrayList<>();
    {
        people.addAll(Arrays.asList(
                new Male("Bob", 1),
                new Female("Alice", 3),
                new Male("Jonie", 2),
                new Female("Christina", 4),
                new Male("Jonie", 6),
                new Female("Alice", 7),
                new Male("Bob", 5),
                new Female("Christina", 8)
        ));
    }

    public List<Person> values() {
        return people;
    }

    public Male newMale() {
        return new Male("Michael", random.nextInt(60));
    }

    public Female newFemale() {
        return new Female("Jenna", random.nextInt(60));
    }
}
