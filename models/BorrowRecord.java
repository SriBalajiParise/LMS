package models;

import java.time.LocalDate;

/**
 * BorrowRecord class to track borrowing history
 */
public class BorrowRecord {
    private String recordId;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public BorrowRecord(String recordId, String userId, String bookId) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14); // 2 weeks loan period
        this.returnDate = null;
        this.isReturned = false;
    }

    // Getters
    public String getRecordId() { return recordId; }
    public String getUserId() { return userId; }
    public String getBookId() { return bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return isReturned; }

    /**
     * Mark the book as returned
     */
    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.isReturned = true;
    }

    /**
     * Check if the book is overdue
     */
    public boolean isOverdue() {
        if (isReturned) return false;
        return LocalDate.now().isAfter(dueDate);
    }

    /**
     * Calculate days overdue
     */
    public long getDaysOverdue() {
        if (!isReturned || returnDate.isBefore(dueDate)) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    @Override
    public String toString() {
        String status = isReturned ? "Returned on " + returnDate : (isOverdue() ? "OVERDUE" : "Active");
        return String.format("Record: %-8s | User: %-8s | Book: %-8s | Borrowed: %s | Due: %s | Status: %s",
                recordId, userId, bookId, borrowDate, dueDate, status);
    }
}
