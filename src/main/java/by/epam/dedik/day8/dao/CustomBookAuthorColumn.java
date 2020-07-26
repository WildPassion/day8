package by.epam.dedik.day8.dao;

public enum CustomBookAuthorColumn {
    ID("id"),
    NAME("name"),
    SURNAME("surname"),
    LAST_NAME("last_name");

    private String column;

    CustomBookAuthorColumn(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
