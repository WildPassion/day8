package by.epam.dedik.day8.dao;

public enum  CustomBookColumn {
    ID("id"),
    NAME("name"),
    YEAR("year"),
    NUMBER_PAGES("number_pages");

    private String column;

    CustomBookColumn(String column) {
        this.column = column;
    }


    public String getColumn() {
        return column;
    }
}
