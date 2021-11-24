package com.nagarro.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.entities.Library;
import com.nagarro.repository.LibraryRepository;

/**
 * Class containing methods for modification of Library data
 * 
 * @author priyasharma
 *
 */
@RestController
@RequestMapping("/restapi")
public class LibrarianController {

	@Autowired
	private LibraryRepository libraryRepository;

	/**
	 * Method to get data of all librarian
	 * 
	 * @return List of Librarian
	 */
	@GetMapping("/librarian")
	public List<Library> getLibrarian() {
		return libraryRepository.findAll();
	}

	/**
	 * Method to get data of a particular library(by using id)
	 * 
	 * @param id
	 * @return
	 * 
	 */
	@GetMapping(path = "/librarian/{id}")
	public ResponseEntity<? extends Object> getLibraryById(@PathVariable(name = "id") Long id) {
		Optional<Library> library = null;

		try {
			library= libraryRepository.findById(id);

		} catch (Exception e) {
			return ResponseEntity.of(Optional.of(library));
		}
		if (library != null) {
			return ResponseEntity.ok().body(library);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to enter data of a new library in the database
	 * 
	 * @param library
	 * @return
	 */
	@PostMapping("/librarian")
	public Library createLibrary(@RequestBody Library library) {
		return libraryRepository.save(library);
	}

	/**
	 * Method to get data of a particular librarian by id for modification of his/her
	 * data
	 * 
	 * @param id
	 * @param libraryDetails
	 * @return
	 */
	@PutMapping("/librarain/{id}")
	public Library updateLibrary(@PathVariable(name = "id") Long id, @RequestBody Library libraryDetails) {
		Library existingLibrary = libraryRepository.getById(id);
		existingLibrary.setBookName(libraryDetails.getBookName());
		existingLibrary.setAuthor(libraryDetails.getAuthor());
		existingLibrary.setEmail(libraryDetails.getEmail());
		existingLibrary.setDataadded(libraryDetails.getDataadded());
		return libraryRepository.save(existingLibrary);
	}

	/**
	 * Method to delete a particularlibrarian data
	 * 
	 * @param id
	 * @return true if the delete operation is done successfully
	 */
	@DeleteMapping("/librarian/{id}")
	public boolean deleteLibrary(@PathVariable(name = "id") Long id) {
		libraryRepository.deleteById(id);
		return true;
	}

}

