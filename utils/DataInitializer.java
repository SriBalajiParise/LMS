package utils;

import services.BookService;
import services.UserService;

/**
 * Utility class to initialize sample data
 */
public class DataInitializer {
    
    /**
     * Initialize sample books
     */
    public static void initializeSampleBooks(BookService bookService) {
        bookService.addBook("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", "Fiction");
        bookService.addBook("To Kill a Mockingbird", "Harper Lee", "978-0061120084", "Fiction");
        bookService.addBook("1984", "George Orwell", "978-0451524935", "Science Fiction");
        bookService.addBook("Clean Code", "Robert C. Martin", "978-0132350884", "Programming");
        bookService.addBook("Design Patterns", "Gang of Four", "978-0201633612", "Programming");
        bookService.addBook("The Hobbit", "J.R.R. Tolkien", "978-0547928227", "Fantasy");
        bookService.addBook("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "978-0439708180", "Fantasy");
        bookService.addBook("Introduction to Algorithms", "Thomas H. Cormen", "978-0262033848", "Computer Science");
    }

    /**
     * Initialize sample users
     */
    public static void initializeSampleUsers(UserService userService) {
        userService.addUser("John Doe", "john.doe@email.com", "555-0101");
        userService.addUser("Jane Smith", "jane.smith@email.com", "555-0102");
        userService.addUser("Bob Johnson", "bob.johnson@email.com", "555-0103");
        userService.addUser("Alice Williams", "alice.williams@email.com", "555-0104");
        userService.addUser("Charlie Brown", "charlie.brown@email.com", "555-0105");
    }
}
