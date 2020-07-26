package by.epam.dedik.day8.entity;

import java.util.StringJoiner;

public class CustomBookAuthor {
    private int id;
    private String name;
    private String surname;
    private String lastName;

    public CustomBookAuthor() {
    }

    public CustomBookAuthor(String name, String surname, String lastName) {
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
    }

    public CustomBookAuthor(int id, String name, String surname, String lastName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomBookAuthor that = (CustomBookAuthor) o;

        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        return lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomBookAuthor.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("lastName='" + lastName + "'")
                .toString();
    }
}
