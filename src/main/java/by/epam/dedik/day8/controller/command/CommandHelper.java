package by.epam.dedik.day8.controller.command;

import by.epam.dedik.day8.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private Map<String, BookCommand> map = new HashMap<>();

    public CommandHelper() {
        map.put(CommandName.ADD_BOOK, new AddBookCommand());
        map.put(CommandName.REMOVE_BOOK, new RemoveBookCommand());
        map.put(CommandName.FIND_BY_NAME, new FindByNameCommand());
        map.put(CommandName.FIND_BY_AUTHOR, new FindByAuthorCommand());
        map.put(CommandName.FIND_BY_YEAR, new FindByYearCommand());
        map.put(CommandName.FIND_BY_NUMBER_PAGES, new FindByNumberPagesCommand());
        map.put(CommandName.SORT_BY_TAG, new SortByTagCommand());
    }

    public BookCommand getCommand(String name) {
        return map.get(name.toUpperCase());
    }
}
