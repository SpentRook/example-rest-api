package co.edu.unisabana.usuario.service.library.port;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;

import java.util.ArrayList;

public interface SearchBookPort {

    boolean validateExistsBook(String nameBook);

    ArrayList<BookEntity> searchBooksByAuthor(String author);
}
