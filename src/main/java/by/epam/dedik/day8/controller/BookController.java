package by.epam.dedik.day8.controller;

import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.controller.command.CommandHelper;

import java.util.Map;

public class BookController {
    public void doSomething(Map<String, Object> request, BookResponse response) {
        CommandHelper commandHelper = new CommandHelper();
        Object o = request.get(Params.COMMAND);
        if (o != null && o.getClass() == String.class) {
            BookCommand command = commandHelper.getCommand(String.valueOf(o));
            command.execute(request, response);
        }
    }
}
