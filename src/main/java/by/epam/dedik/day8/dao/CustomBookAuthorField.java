package by.epam.dedik.day8.dao;

public enum CustomBookAuthorField {
    ID("author.id"),
    NAME("author.name"),
    SURNAME("author.surname"),
    LAST_NAME("author.last_name");

    private String column;

    CustomBookAuthorField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
