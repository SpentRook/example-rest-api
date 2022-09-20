package co.edu.unisabana.usuario.service.library;

import co.edu.unisabana.usuario.exception.NoContentException;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearcherBooksByAuthor {
    private final SearchBookPort searchBookPort;

    public SearcherBooksByAuthor(SearchBookPort searchBookPort) {
        this.searchBookPort = searchBookPort;
    }

    public ArrayList<BookEntity> searchBooksByAuthor(String author){
        ArrayList<BookEntity> books = searchBookPort.searchBooksByAuthor(author);
        if(books.isEmpty()){
            throw new NoContentException("No hay libros de ese autor");
        }
            return books;
    }
}
