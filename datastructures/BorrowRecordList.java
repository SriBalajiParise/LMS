package datastructures;

import models.BorrowRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom Linked List implementation for storing borrow records
 */
public class BorrowRecordList {
    private Node<BorrowRecord> head;
    private int size;

    public BorrowRecordList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Add a borrow record to the list
     */
    public void add(BorrowRecord record) {
        Node<BorrowRecord> newNode = new Node<>(record);
        if (head == null) {
            head = newNode;
        } else {
            Node<BorrowRecord> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Get all borrow records
     */
    public List<BorrowRecord> getAllRecords() {
        List<BorrowRecord> records = new ArrayList<>();
        Node<BorrowRecord> current = head;
        while (current != null) {
            records.add(current.data);
            current = current.next;
        }
        return records;
    }

    /**
     * Get all borrow records for a specific user
     */
    public List<BorrowRecord> getRecordsByUser(String userId) {
        List<BorrowRecord> records = new ArrayList<>();
        Node<BorrowRecord> current = head;
        while (current != null) {
            if (current.data.getUserId().equals(userId)) {
                records.add(current.data);
            }
            current = current.next;
        }
        return records;
    }

    /**
     * Get all borrow records for a specific book
     */
    public List<BorrowRecord> getRecordsByBook(String bookId) {
        List<BorrowRecord> records = new ArrayList<>();
        Node<BorrowRecord> current = head;
        while (current != null) {
            if (current.data.getBookId().equals(bookId)) {
                records.add(current.data);
            }
            current = current.next;
        }
        return records;
    }

    /**
     * Get the number of records
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
}
