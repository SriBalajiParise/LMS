package datastructures;

import models.Book;

/**
 * Binary Search Tree Node for organizing books
 */
public class BSTNode {
    public Book book;
    public BSTNode left;
    public BSTNode right;

    public BSTNode(Book book) {
        this.book = book;
        this.left = null;
        this.right = null;
    }
}
