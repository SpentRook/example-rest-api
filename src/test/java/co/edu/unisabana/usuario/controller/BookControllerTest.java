package co.edu.unisabana.usuario.controller;

import co.edu.unisabana.usuario.AbstractTest;
import co.edu.unisabana.usuario.dto.BookDto;
import co.edu.unisabana.usuario.dto.BookListResponse;
import co.edu.unisabana.usuario.dto.BookReponse;
import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookControllerTest extends AbstractTest {

    private static final String PATH_REGISTER_BOOK = "/book/register";
    private static final String PATH_SEARCH_BOOKS = "/book/search?author=";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void Given_bookData_When_registerBook_Then_add_book_to_list() {
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Nuevo libro registrado", result.getBody().getData());
    }

    @Test
    public void Given_Book1_When_registerBook_Then_increase_quantity_in_one() {
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        ResponseEntity<BookReponse> result = restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        assertEquals("Cantidad actualizada", result.getBody().getData());

    }

    @Test
    public void Given_Pedro_as_author_When_searchBooksByAuthor_then_return_2_records(){
        BookDto dto = new BookDto("Book 1", 2003, "Pedro", "Commercial", "suave");
        BookDto dto2 = new BookDto("Book 2", 2003, "Pedro", "Commercial", "suave");
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto, BookReponse.class);
        restTemplate.postForEntity(PATH_REGISTER_BOOK, dto2, BookReponse.class);
        ResponseEntity<BookListResponse> result = restTemplate.getForEntity(PATH_SEARCH_BOOKS + "Pedro", BookListResponse.class);
        assertEquals(2, result.getBody().getData().size());
    }

}
