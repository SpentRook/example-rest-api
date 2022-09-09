package co.edu.unisabana.usuario.service.library;

import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.port.DeleteBookPort;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.springframework.stereotype.Service;

@Service
public class DeleterBookLibrary {

    private final SearchBookPort searchBookPort;
    private final DeleteBookPort deleteBookPort;

    public DeleterBookLibrary(SearchBookPort searchBookPort, DeleteBookPort deleteBookPort) {
        this.searchBookPort = searchBookPort;
        this.deleteBookPort = deleteBookPort;
    }

    public int deleteBook(Book book) {
        boolean exists = searchBookPort.validateExistsBook(book.getName());
        if (exists) {
            deleteBookPort.deleteBook(book.getName());
            return 1;
        } else {
            return 2;
        }
    }
}


