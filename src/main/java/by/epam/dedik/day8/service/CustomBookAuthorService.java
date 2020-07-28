package by.epam.dedik.day8.service;

import by.epam.dedik.day8.entity.CustomBookAuthor;

import java.util.Optional;

public interface CustomBookAuthorService {
    boolean addAuthor(CustomBookAuthor author) throws ServiceException;

    boolean deleteAuthor(CustomBookAuthor author) throws ServiceException;

    boolean updateAuthor(CustomBookAuthor oldAuthor, CustomBookAuthor newAuthor) throws ServiceException;

    Optional<CustomBookAuthor> findAuthor(CustomBookAuthor author) throws ServiceException;
}
