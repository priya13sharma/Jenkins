package com.nagarro.service;

import java.util.List;
import com.nagarro.entities.Library;

/**
 * Interface for basic library data modification methods'declaration
 * 
 * @author priyasharma
 *
 */
public interface LibraryService {

	/**
	 * To get the list of all librarian
	 * 
	 * @return list of librarian
	 */
	List<Library> getLibraryList();

	/**
	 * To update a particular librarian data
	 * 
	 * @param library
	 * @return
	 */
	static Library updateLibrary(Library library) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * To add a new librarian
	 * 
	 * @param librarian
	 */
	void uploadLibrary(Library librarian);

	/**
	 * To delete a particular librarian
	 * 
	 * @param bookCode
	 * @return
	 */
	boolean deleteLibrary(Long bookCode);

	/**
	 * To fetch the details of a particular librarian by id
	 * 
	 * @param bookCode
	 * @return
	 */
	Library getLibraryById(Long bookCode);
}
