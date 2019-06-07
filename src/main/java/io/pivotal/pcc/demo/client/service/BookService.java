package io.pivotal.pcc.demo.client.service;

import java.util.Optional;
import java.util.Random;

import com.github.javafaker.Faker;

import io.pivotal.pcc.demo.client.repositories.jpa.JPABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.pivotal.pcc.demo.client.domain.Book;
import io.pivotal.pcc.demo.client.repositories.pcc.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	@Autowired
	@Qualifier("JPABookRepository")
	JPABookRepository jPABookRepository;

	private final Faker faker;

	public BookService(BookRepository bookRepository, Faker faker) {
		this.bookRepository = bookRepository;
		this.faker = faker;
	}

	private void sleep(int bound) {

		try {
			Thread.sleep(new Random().nextInt(bound));
		}
		catch (InterruptedException cause) {
			cause.printStackTrace();
		}
	}

	@Cacheable("Books")
	public Book getBook(String isbn) {

		sleep(1500);

		Optional<Book> optionalBook = jPABookRepository.findById(isbn);
		Book book = null;
		if (optionalBook.isPresent()) {
			book = optionalBook.get();
			book.setGenre(book.getGenre()+":JPA");
		}else{
			com.github.javafaker.Book fakerBook = faker.book();
			book = new Book(isbn, fakerBook.title(), fakerBook.author(), fakerBook.genre());

			jPABookRepository.save(book);
		}

		return book;
	}

	@CachePut(cacheNames = "Books", key = "#root.args[0].isbn")
	public Book addBook(Book book) {
		return book;
	}

	public Iterable<Book> findAllFromPCC() {
		return bookRepository.findAll();
	}

	public Iterable<Book> findAllfromJPA() {
		return jPABookRepository.findAll();
	}
}
