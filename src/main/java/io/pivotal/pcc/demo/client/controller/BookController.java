package io.pivotal.pcc.demo.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.pcc.demo.client.domain.Book;
import io.pivotal.pcc.demo.client.service.BookService;

@RestController
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/")
	public String home() {
		return "Using Spring Data for Pivotal GemFire!";
	}

	@GetMapping("/ping")
	public String ping() {
		return "PONG";
	}

	@GetMapping("/books/{isbn}")
	public String getBook(@PathVariable String isbn) {
		long start = System.currentTimeMillis();
		Book book = bookService.getBook(isbn);
		long end = System.currentTimeMillis();
		return String
			.format("It took: %d millis to execute getBook: %s for ISBN: %s", (end - start), book.toString(), isbn);
	}

	@GetMapping("/bookspcc")
	public String getBooksPCC() {
		long start = System.currentTimeMillis();
		Iterable<Book> books = bookService.findAllFromPCC();
		long end = System.currentTimeMillis();

		String returnbooks = "";
		returnbooks = "PCC: It took: %d millis to execute getBooks:";
		for (Book book1 : books) {
			returnbooks = returnbooks+"ISBN: "+book1.getIsbn()+",books" +book1.toString()+"/r/n";
		}
		return returnbooks;
	}

	@GetMapping("/booksjpa")
	public String getBooksJpa() {
		long start = System.currentTimeMillis();
		Iterable<Book> books = bookService.findAllfromJPA();
		long end = System.currentTimeMillis();

		String returnbooks = "";
		returnbooks = "JPA: It took: %d millis to execute getBooks:";
		for (Book book1 : books) {
			returnbooks = returnbooks+"ISBN: "+book1.getIsbn()+",books" +book1.toString()+"/r/n";
		}
		return returnbooks;
	}
}
