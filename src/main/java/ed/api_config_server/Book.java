package ed.api_config_server;

import java.io.Serializable;

public class Book implements Serializable {
	
	private Integer bookId;
	private String bookName;
	
	public Book() {}
	
	public Book(Integer bookId, String bookName) {
		this.bookId = bookId;
		this.bookName = bookName;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
