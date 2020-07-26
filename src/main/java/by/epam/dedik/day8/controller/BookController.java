package by.epam.dedik.day8.controller;

import by.epam.dedik.day8.controller.command.BookCommand;
import by.epam.dedik.day8.controller.command.CommandHelper;

public class BookController {
    public void doSomething(BookRequest request, BookResponse response) {
        CommandHelper commandHelper = new CommandHelper();
        BookCommand command = commandHelper.getCommand(request.getParameter(Params.COMMAND));

        command.execute(request, response);
    }
}
