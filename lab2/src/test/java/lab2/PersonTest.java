package lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.google.gson.Gson;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void equalsAndHashCodeContract() {
        EqualsVerifier.forClass(Person.class).verify();
    }

    @Test
    void jsonRoundTripGivesEqualObject() {
        Gson gson = new Gson();
        Person original = new Person("Reutskyi", "Artem", 19);

        Person restored = gson.fromJson(gson.toJson(original), Person.class);

        assertEquals(original, restored);
    }

    @Test
    void differentPeopleAreNotEqual() {
        Person a = new Person("Reutskyi", "Artem", 19);
        assertNotEquals(a, new Person("Reutskyi", "Artem", 20));
        assertNotEquals(a, new Person("Shevchenko", "Taras", 47));
        assertNotEquals(a, new Person("Testenko", "Test", 123));
    }
}
