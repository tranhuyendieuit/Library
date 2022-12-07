package library.service;

import java.util.List;

import library.model.Author;
import library.repository.AuthorRepository;

public class AuthorService {

	private AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {

		this.authorRepository = authorRepository;
	}

	public List<Author> findAll() {

		return authorRepository.findAll();
	}

}
