package datastructures;

import models.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Binary Search Tree implementation for efficient book searching
 */
public class BookBST {
    private BSTNode root;

    public BookBST() {
        this.root = null;
    }

    /**
     * Insert a book into the BST
     */
    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private BSTNode insertRec(BSTNode root, Book book) {
        if (root == null) {
            return new BSTNode(book);
        }
        
        if (book.getTitle().compareToIgnoreCase(root.book.getTitle()) < 0) {
            root.left = insertRec(root.left, book);
        } else {
            root.right = insertRec(root.right, book);
        }
        
        return root;
    }

    /**
     * Search for books by title prefix or substring
     */
    public List<Book> searchByTitle(String titlePrefix) {
        List<Book> results = new ArrayList<>();
        searchByTitleRec(root, titlePrefix.toLowerCase(), results);
        return results;
    }

    private void searchByTitleRec(BSTNode node, String titlePrefix, List<Book> results) {
        if (node == null) return;
        
        searchByTitleRec(node.left, titlePrefix, results);
        
        if (node.book.getTitle().toLowerCase().contains(titlePrefix)) {
            results.add(node.book);
        }
        
        searchByTitleRec(node.right, titlePrefix, results);
    }

    /**
     * Get all books sorted by title
     */
    public List<Book> getAllBooksSorted() {
        List<Book> books = new ArrayList<>();
        inorderTraversal(root, books);
        return books;
    }

    private void inorderTraversal(BSTNode node, List<Book> books) {
        if (node == null) return;
        inorderTraversal(node.left, books);
        books.add(node.book);
        inorderTraversal(node.right, books);
    }

    /**
     * Check if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the total number of books in the tree
     */
    public int getSize() {
        return countNodes(root);
    }

    private int countNodes(BSTNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
