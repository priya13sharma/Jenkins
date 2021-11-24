package com.nagarro.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nagarro.entities.Library;

/**
 * Library Service Implementation class
 * 
 * @author priyasharma
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Library> getLibraryList() {
		ResponseEntity<List<Library>> response = restTemplate.exchange("http://localhost:8084/restapi/librarian/",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Library>>() {
				});
		List<Library> library= response.getBody();
		return library;
	}

	public Library updateLibrary(Library library) {

		restTemplate.put("http://localhost:8084/restapi/librarian/" + library.getBookCode(), library);
		return library;

	}

	@Override
	public void uploadLibrary(Library librarian) {

		restTemplate.postForObject("http://localhost:8084/restapi/librarian", librarian, Library.class);

	}

	@Override
	public boolean deleteLibrary(Long bookCode) {

		ResponseEntity<Boolean> deletedLib = restTemplate.exchange(
				"http://localhost:8084/restapi/librarian/" + bookCode, HttpMethod.DELETE, null,
				new ParameterizedTypeReference<Boolean>() {
				});
		if (deletedLib.getBody()) {
			return true;
		}
		return false;
	}

	@Override
	public Library getLibraryById(Long bookCode) {

		ResponseEntity<Library> lib = this.restTemplate.exchange(
				"http://localhost:8084/restapi/librarian/" + bookCode, HttpMethod.GET, null,
				new ParameterizedTypeReference<Library>() {
				});

		Library library= lib.getBody();
		System.out.println(library);

		return library;
	}

}

