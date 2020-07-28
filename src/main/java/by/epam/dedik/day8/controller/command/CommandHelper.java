package by.epam.dedik.day8.controller.command;

import by.epam.dedik.day8.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private Map<String, BookCommand> map = new HashMap<>();

    public CommandHelper() {
        map.put(CommandName.ADD_BOOK, new AddBookCommand());
        map.put(CommandName.DELETE_BOOK, new DeleteBookCommand());
        map.put(CommandName.FIND_BY_FIELD, new FindByFieldCommand());
        map.put(CommandName.SORT_BY_TAG, new SortByTagCommand());
    }

    public BookCommand getCommand(String name) {
        return map.get(name.toUpperCase());
    }
}
