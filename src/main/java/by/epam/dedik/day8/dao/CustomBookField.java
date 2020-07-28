package by.epam.dedik.day8.dao;

public enum CustomBookField {
    ID("custom_book.id"),
    NAME("custom_book.name"),
    YEAR("custom_book.year"),
    NUMBER_PAGES("custom_book.number_pages");

    private String column;

    CustomBookField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
