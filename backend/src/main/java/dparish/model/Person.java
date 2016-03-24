package dparish.model;

/**
 * @author dparish
 */
public class Person {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }
}
