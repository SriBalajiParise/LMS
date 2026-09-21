package services;

import models.Book;
import models.BorrowRecord;
import datastructures.BorrowRecordList;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing borrowing operations
 */
public class BorrowService {
    private BorrowRecordList borrowRecords;
    private BookService bookService;
    private UserService userService;
    private int recordCounter;

    public BorrowService(BookService bookService, UserService userService) {
        this.borrowRecords = new BorrowRecordList();
        this.bookService = bookService;
        this.userService = userService;
        this.recordCounter = 1;
    }

    /**
     * Borrow a book
     */
    public String borrowBook(String userId, String bookId) {
        // Validate user exists
        if (!userService.userExists(userId)) {
            return null;
        }

        // Validate book exists
        Book book = bookService.getBook(bookId);
        if (book == null) {
            return null;
        }

        // Check if book is available
        if (!book.isAvailable()) {
            return null;
        }

        // Create borrow record
        String recordId = "R" + String.format("%04d", recordCounter++);
        BorrowRecord record = new BorrowRecord(recordId, userId, bookId);
        borrowRecords.add(record);
        
        // Mark book as unavailable
        book.setAvailable(false);

        return recordId;
    }

    /**
     * Return a book
     */
    public boolean returnBook(String bookId) {
        List<BorrowRecord> bookRecords = borrowRecords.getRecordsByBook(bookId);
        
        // Find active borrow record
        BorrowRecord activeRecord = null;
        for (BorrowRecord record : bookRecords) {
            if (!record.isReturned()) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            return false;
        }

        // Mark as returned
        activeRecord.returnBook();
        
        // Mark book as available
        Book book = bookService.getBook(bookId);
        if (book != null) {
            book.setAvailable(true);
        }

        return true;
    }

    /**
     * Get borrow history for a user
     */
    public List<BorrowRecord> getUserBorrowHistory(String userId) {
        return borrowRecords.getRecordsByUser(userId);
    }

    /**
     * Get borrow history for a book
     */
    public List<BorrowRecord> getBookBorrowHistory(String bookId) {
        return borrowRecords.getRecordsByBook(bookId);
    }

    /**
     * Get all borrow records
     */
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecords.getAllRecords();
    }

    /**
     * Get all overdue records
     */
    public List<BorrowRecord> getOverdueRecords() {
        List<BorrowRecord> overdueRecords = new ArrayList<>();
        for (BorrowRecord record : borrowRecords.getAllRecords()) {
            if (record.isOverdue()) {
                overdueRecords.add(record);
            }
        }
        return overdueRecords;
    }

    /**
     * Get active borrow record for a book
     */
    public BorrowRecord getActiveRecordForBook(String bookId) {
        List<BorrowRecord> bookRecords = borrowRecords.getRecordsByBook(bookId);
        for (BorrowRecord record : bookRecords) {
            if (!record.isReturned()) {
                return record;
            }
        }
        return null;
    }

    /**
     * Get total number of borrow records
     */
    public int getTotalRecords() {
        return borrowRecords.getSize();
    }

    /**
     * Check if user has active borrows
     */
    public boolean hasActiveBorrows(String userId) {
        List<BorrowRecord> userRecords = borrowRecords.getRecordsByUser(userId);
        for (BorrowRecord record : userRecords) {
            if (!record.isReturned()) {
                return true;
            }
        }
        return false;
    }
}
