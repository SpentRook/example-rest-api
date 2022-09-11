package co.edu.unisabana.usuario.dto;

import co.edu.unisabana.usuario.repository.dao.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse {
    private ArrayList<BookEntity> data;

}
