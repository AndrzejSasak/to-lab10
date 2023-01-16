package com.endriu.tohosted;

import com.endriu.tohosted.controller.BookController;
import com.endriu.tohosted.entity.Book;
import com.endriu.tohosted.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBooks_whenCalled_shouldRetrieveCorrectData() throws Exception {
        List<Book> bookList = new ArrayList<>();
        Book book = Book.builder()
                .name("name")
                .author("author")
                .year("2001")
                .build();
        bookList.add(book);
        when(bookService.getAllBooks()).thenReturn(bookList);

        String url = "http://localhost:8080/bookservice/books";
        String expected = "[{\"name\":\"name\",\"author\":\"author\",\"year\":\"2001\"}]";


        mockMvc.perform(get(url))
                .andExpect(content().string(containsString(expected)))
                .andExpect(status().isOk());
    }

    @Test
    void addBook_givenCorrectData_shouldAddBookCorrectly() throws Exception {
        Book book = Book.builder()
                .name("newName")
                .author("newAuthor")
                .year("2001")
                .build();

        String url = "http://localhost:8080/bookservice";

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    void addBook_givenBadData_shouldReturnBadRequest() throws Exception {
        String url = "http://localhost:8080/bookservice";

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("badcontent"))
                .andExpect(status().isBadRequest());
    }


}
