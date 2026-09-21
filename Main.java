import controllers.LibraryController;

/**
 * Main class to start the Library Management System
 * 
 * This system demonstrates the use of various data structures:
 * - HashMap for O(1) book and user lookups
 * - Binary Search Tree for efficient title-based searching
 * - Linked List for borrowing history management
 * - Merge Sort for sorting search results
 * 
 * @author Your Name
 * @version 1.0
 * @since April 2024
 */
public class Main {
    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        controller.run();
    }
}
