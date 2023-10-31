package ed.api_config_server;

import java.util.Collection;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRestController {
	private HashOperations<String, Integer, Book> hashOperations;
	
	public BookRestController(RedisTemplate<String, Book> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}
	
	@PostMapping("/book")
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		hashOperations.put("Book", book.getBookId(), book);
		return new ResponseEntity<>("book saved", HttpStatus.CREATED);
	}
	
	@GetMapping("/book/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
		Book book = hashOperations.get("Book", bookId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@GetMapping("/books")
	public ResponseEntity<Collection<Book>> getAllBooks() {
		Map<Integer, Book> map = hashOperations.entries("Book");
		Collection<Book> bookCollection = map.values();
		return new ResponseEntity<>(bookCollection, HttpStatus.OK);
	}
}
