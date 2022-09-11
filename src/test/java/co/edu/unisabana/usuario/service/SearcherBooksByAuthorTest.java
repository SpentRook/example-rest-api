package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import co.edu.unisabana.usuario.service.library.SearcherBooksByAuthor;
import co.edu.unisabana.usuario.service.library.model.Book;
import co.edu.unisabana.usuario.service.library.model.CategoryBook;
import co.edu.unisabana.usuario.service.library.port.SearchBookPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class SearcherBooksByAuthorTest {

    @InjectMocks
    private SearcherBooksByAuthor service;

    @Mock
    private SearchBookPort searchBookPort;

    private Book book;

    @BeforeEach
    public void setUp(){
        CategoryBook category = CategoryBook.fromString("digital");
        book = new Book("Libro A", 2022, "Jonathan B", false, category);
    }

    @Test
    public void Given_existing_books_for_author_When_searchBooksByAuthor_return_list_of_books(){
        ArrayList<BookEntity> result = service.searchBooksByAuthor("Libro A");
        Mockito.verify(searchBookPort).searchBooksByAuthor("Libro A");
        Assertions.assertEquals(1, result.size());
    }
}
