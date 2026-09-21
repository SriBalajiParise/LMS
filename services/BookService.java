package services;

import models.Book;
import datastructures.BookBST;
import utils.SortingUtils;

import java.util.*;

/**
 * Service class for managing book operations
 */
public class BookService {
    private HashMap<String, Book> bookHashMap;
    private BookBST bookBST;
    private int bookCounter;

    public BookService() {
        this.bookHashMap = new HashMap<>();
        this.bookBST = new BookBST();
        this.bookCounter = 1;
    }

    /**
     * Add a new book to the library
     */
    public String addBook(String title, String author, String isbn, String category) {
        String bookId = "B" + String.format("%04d", bookCounter++);
        Book book = new Book(bookId, title, author, isbn, category);
        bookHashMap.put(bookId, book);
        bookBST.insert(book);
        return bookId;
    }

    /**
     * Get a book by ID
     */
    public Book getBook(String bookId) {
        return bookHashMap.get(bookId);
    }

    /**
     * Update book details
     */
    public boolean updateBook(String bookId, String field, String newValue) {
        Book book = bookHashMap.get(bookId);
        if (book == null) return false;

        switch (field.toLowerCase()) {
            case "title":
                book.setTitle(newValue);
                break;
            case "author":
                book.setAuthor(newValue);
                break;
            case "category":
                book.setCategory(newValue);
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     * Delete a book from the library
     */
    public boolean deleteBook(String bookId) {
        Book book = bookHashMap.get(bookId);
        if (book == null) return false;
        if (!book.isAvailable()) return false; // Cannot delete borrowed books

        bookHashMap.remove(bookId);
        return true;
    }

    /**
     * Search for books by various criteria
     */
    public List<Book> searchBooks(String searchTerm) {
        Set<Book> resultSet = new HashSet<>();

        // Search by ID (exact match)
        if (bookHashMap.containsKey(searchTerm)) {
            resultSet.add(bookHashMap.get(searchTerm));
        }

        // Search by title using BST
        resultSet.addAll(bookBST.searchByTitle(searchTerm));

        // Search by author, category, or ISBN
        for (Book book : bookHashMap.values()) {
            if (book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getCategory().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getIsbn().contains(searchTerm)) {
                resultSet.add(book);
            }
        }

        List<Book> results = new ArrayList<>(resultSet);
        return SortingUtils.mergeSort(results);
    }

    /**
     * Get all books sorted by title
     */
    public List<Book> getAllBooksSorted() {
        return bookBST.getAllBooksSorted();
    }

    /**
     * Get all books (unsorted)
     */
    public Collection<Book> getAllBooks() {
        return bookHashMap.values();
    }

    /**
     * Get total number of books
     */
    public int getTotalBooks() {
        return bookHashMap.size();
    }

    /**
     * Get number of available books
     */
    public int getAvailableBooks() {
        int count = 0;
        for (Book book : bookHashMap.values()) {
            if (book.isAvailable()) count++;
        }
        return count;
    }

    /**
     * Get number of borrowed books
     */
    public int getBorrowedBooks() {
        return getTotalBooks() - getAvailableBooks();
    }

    /**
     * Check if a book exists
     */
    public boolean bookExists(String bookId) {
        return bookHashMap.containsKey(bookId);
    }
}
