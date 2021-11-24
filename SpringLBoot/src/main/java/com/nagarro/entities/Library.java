package com.nagarro.entities;

/**
 * Entity class for employee's details
 * 
 * @author priyasharma
 *
 */
public class Library {

	private Long bookCode;
	private String bookName;
	private String author;
	private String email;
	private String dataadded;

	public Library(Long bookCode, String bookName, String author,String email, String dataadded) {
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.email=email;
		this.dataadded = dataadded;
	}

	

	public Library() {
	}

	
	public Long getBookCode() {
		return bookCode;
	}

	public void setBookCode(Long bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataadded() {
		return dataadded;
	}

	public void setDataadded(String dataadded) {
		this.dataadded = dataadded;
	}

	@Override
	public String toString() {
		return "Library [bookCode=" + bookCode + ", bookName=" + bookName + ", author=" + author
				+ ",email=" + email+",  dataadded=" + dataadded + "]";
	}

}
