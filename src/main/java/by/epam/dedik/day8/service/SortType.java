package by.epam.dedik.day8.service;

import by.epam.dedik.day8.entity.CustomBook;

import java.util.Comparator;

public enum SortType {
    NAME((first, second) -> first.getName().toLowerCase()
            .compareTo(second.getName().toLowerCase())),
    AUTHOR((first, second) -> first.getAuthors().get(0).toLowerCase()
            .compareTo(second.getAuthors().get(0).toLowerCase())),
    YEAR((first, second) -> Integer.compare(first.getYear(), second.getYear())),
    NUMBER_PAGES((first, second) -> Integer.compare(first.getNumberPages(), second.getNumberPages()));

    private Comparator<CustomBook> comparator;

    SortType(Comparator<CustomBook> comparator) {
        this.comparator = comparator;
    }

    public Comparator<CustomBook> getComparator() {
        return comparator;
    }
}
