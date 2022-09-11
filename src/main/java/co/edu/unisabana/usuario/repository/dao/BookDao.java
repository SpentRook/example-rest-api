package co.edu.unisabana.usuario.repository.dao;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.port.AddBookPort;
import co.edu.unisabana.usuario.service.library.port.DeleteBookPort;
import co.edu.unisabana.usuario.service.library.port.RegisterBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class BookDao implements SearchBookPort, RegisterBookPort, AddBookPort, DeleteBookPort {

    public static List<BookEntity> listBooks = new ArrayList<>();

    @Override
    public boolean validateExistsBook(String nameBook) {
        AtomicBoolean exists = new AtomicBoolean(false);
        listBooks.forEach(book -> {
            if (book.getName().equals(nameBook)) {
                exists.set(true);
            }
        });
        return exists.get();
    }

    @Override
    public ArrayList<BookEntity> searchBooksByAuthor(String author) {
        ArrayList<BookEntity> matchingBooks = new ArrayList<>();
        listBooks.forEach(book -> {
            if(book.getAuthor().equals(author)){
                matchingBooks.add(book);
            }
        });
        return matchingBooks;
    }


    @Override
    public void registerBook(Book newBook) {
        BookEntity bookEntity = BookEntity.fromModel(newBook);
        bookEntity.setId(listBooks.size() + 1);
        listBooks.add(bookEntity);
    }

    @Override
    public boolean addBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name)) {
                if (book.getQuantity() > 15) {
                    throw new IllegalArgumentException("No es posible agregar más unidades del libro");
                }
                book.setQuantity(book.getQuantity() + 1);
                return true;
            }

        }
        throw new IllegalArgumentException("No existe libro para actualizar");



    }

    @Override
    public boolean deleteBook(String name) {
        for (BookEntity book : listBooks) {
            if (book.getName().equals(name)) {
                listBooks.remove(book);
                return true;
            }
        }
        throw new IllegalArgumentException("No existe libre para eliminar");
    }
}
