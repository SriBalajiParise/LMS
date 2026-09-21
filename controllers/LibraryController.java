package controllers;

import models.Book;
import models.User;
import models.BorrowRecord;
import services.BookService;
import services.UserService;
import services.BorrowService;
import ui.MenuDisplay;
import utils.DataInitializer;

import java.util.List;
import java.util.Scanner;

/**
 * Main controller class for handling user interactions
 */
public class LibraryController {
    private BookService bookService;
    private UserService userService;
    private BorrowService borrowService;
    private Scanner scanner;

    public LibraryController() {
        this.bookService = new BookService();
        this.userService = new UserService();
        this.borrowService = new BorrowService(bookService, userService);
        this.scanner = new Scanner(System.in);
        
        // Initialize sample data
        DataInitializer.initializeSampleBooks(bookService);
        DataInitializer.initializeSampleUsers(userService);
    }

    /**
     * Start the application
     */
    public void run() {
        MenuDisplay.displayWelcome();
        
        while (true) {
            MenuDisplay.displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: handleAddBook(); break;
                case 2: handleUpdateBook(); break;
                case 3: handleDeleteBook(); break;
                case 4: handleSearchBooks(); break;
                case 5: handleDisplayAllBooks(); break;
                case 6: handleAddUser(); break;
                case 7: handleUpdateUser(); break;
                case 8: handleDeleteUser(); break;
                case 9: handleDisplayAllUsers(); break;
                case 10: handleBorrowBook(); break;
                case 11: handleReturnBook(); break;
                case 12: handleViewUserBorrowHistory(); break;
                case 13: handleViewAllBorrowRecords(); break;
                case 14: handleViewOverdueBooks(); break;
                case 15: handleDisplayStatistics(); break;
                case 0:
                    MenuDisplay.displayExit();
                    scanner.close();
                    return;
                default:
                    MenuDisplay.displayError("Invalid choice! Please try again.");
            }
        }
    }

    // ==================== BOOK HANDLERS ====================
    
    private void handleAddBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        String bookId = bookService.addBook(title, author, isbn, category);
        MenuDisplay.displaySuccess("Book added successfully! Book ID: " + bookId);
    }

    private void handleUpdateBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        
        Book book = bookService.getBook(bookId);
        if (book == null) {
            MenuDisplay.displayError("Book not found with ID: " + bookId);
            return;
        }

        System.out.println("\nCurrent Book Details:");
        System.out.println(book);
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Category");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        String field = "";
        String prompt = "";
        
        switch (choice) {
            case 1: field = "title"; prompt = "Enter new title: "; break;
            case 2: field = "author"; prompt = "Enter new author: "; break;
            case 3: field = "category"; prompt = "Enter new category: "; break;
            default:
                MenuDisplay.displayError("Invalid choice!");
                return;
        }

        System.out.print(prompt);
        String newValue = scanner.nextLine();
        
        if (bookService.updateBook(bookId, field, newValue)) {
            MenuDisplay.displaySuccess(field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully!");
        } else {
            MenuDisplay.displayError("Failed to update book.");
        }
    }

    private void handleDeleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();
        
        if (bookService.deleteBook(bookId)) {
            MenuDisplay.displaySuccess("Book deleted successfully!");
        } else {
            MenuDisplay.displayError("Cannot delete book! Either book not found or currently borrowed.");
        }
    }

    private void handleSearchBooks() {
        System.out.print("Enter search term (title/author/category/ISBN): ");
        String searchTerm = scanner.nextLine();
        
        List<Book> results = bookService.searchBooks(searchTerm);
        
        MenuDisplay.displaySectionHeader("Search Results");
        if (results.isEmpty()) {
            System.out.println("No books found matching: " + searchTerm);
        } else {
            System.out.println("Found " + results.size() + " book(s):\n");
            for (Book book : results) {
                System.out.println(book);
            }
        }
        System.out.println("====================================\n");
    }

    private void handleDisplayAllBooks() {
        List<Book> books = bookService.getAllBooksSorted();
        
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        MenuDisplay.displaySectionHeader("All Books (Sorted by Title)");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("Total Books: " + bookService.getTotalBooks());
        System.out.println("================================================\n");
    }

    // ==================== USER HANDLERS ====================
    
    private void handleAddUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        
        String userId = userService.addUser(name, email, phone);
        MenuDisplay.displaySuccess("User added successfully! User ID: " + userId);
    }

    private void handleUpdateUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        User user = userService.getUser(userId);
        if (user == null) {
            MenuDisplay.displayError("User not found with ID: " + userId);
            return;
        }

        System.out.println("\nCurrent User Details:");
        System.out.println(user);
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Phone");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        String field = "";
        String prompt = "";
        
        switch (choice) {
            case 1: field = "name"; prompt = "Enter new name: "; break;
            case 2: field = "email"; prompt = "Enter new email: "; break;
            case 3: field = "phone"; prompt = "Enter new phone: "; break;
            default:
                MenuDisplay.displayError("Invalid choice!");
                return;
        }

        System.out.print(prompt);
        String newValue = scanner.nextLine();
        
        if (userService.updateUser(userId, field, newValue)) {
            MenuDisplay.displaySuccess(field.substring(0, 1).toUpperCase() + field.substring(1) + " updated successfully!");
        } else {
            MenuDisplay.displayError("Failed to update user.");
        }
    }

    private void handleDeleteUser() {
        System.out.print("Enter User ID to delete: ");
        String userId = scanner.nextLine();
        
        if (borrowService.hasActiveBorrows(userId)) {
            MenuDisplay.displayError("Cannot delete user! User has unreturned books.");
            return;
        }
        
        if (userService.deleteUser(userId)) {
            MenuDisplay.displaySuccess("User deleted successfully!");
        } else {
            MenuDisplay.displayError("User not found with ID: " + userId);
        }
    }

    private void handleDisplayAllUsers() {
        List<User> users = userService.getAllUsersSorted();
        
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        MenuDisplay.displaySectionHeader("All Users");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("Total Users: " + userService.getTotalUsers());
        System.out.println("===============================\n");
    }

    // ==================== BORROWING HANDLERS ====================
    
    private void handleBorrowBook() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        
        String recordId = borrowService.borrowBook(userId, bookId);
        
        if (recordId != null) {
            BorrowRecord record = borrowService.getActiveRecordForBook(bookId);
            MenuDisplay.displaySuccess("Book borrowed successfully!");
            System.out.println("Record ID: " + recordId);
            System.out.println("Due Date: " + record.getDueDate());
        } else {
            MenuDisplay.displayError("Failed to borrow book! Check if user/book exists and book is available.");
        }
    }

    private void handleReturnBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        
        BorrowRecord activeRecord = borrowService.getActiveRecordForBook(bookId);
        
        if (borrowService.returnBook(bookId)) {
            MenuDisplay.displaySuccess("Book returned successfully!");
            if (activeRecord.getDaysOverdue() > 0) {
                MenuDisplay.displayWarning("Book was " + activeRecord.getDaysOverdue() + " day(s) overdue.");
            }
        } else {
            MenuDisplay.displayError("No active borrow record found for this book.");
        }
    }

    private void handleViewUserBorrowHistory() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        
        List<BorrowRecord> records = borrowService.getUserBorrowHistory(userId);
        
        if (records.isEmpty()) {
            System.out.println("No borrow history found for user: " + userId);
            return;
        }

        MenuDisplay.displaySectionHeader("Borrow History for User " + userId);
        for (BorrowRecord record : records) {
            System.out.println(record);
        }
        System.out.println("==========================================================\n");
    }

    private void handleViewAllBorrowRecords() {
        List<BorrowRecord> records = borrowService.getAllBorrowRecords();
        
        if (records.isEmpty()) {
            System.out.println("No borrow records found.");
            return;
        }

        MenuDisplay.displaySectionHeader("All Borrow Records");
        for (BorrowRecord record : records) {
            System.out.println(record);
        }
        System.out.println("Total Records: " + borrowService.getTotalRecords());
        System.out.println("========================================\n");
    }

    private void handleViewOverdueBooks() {
        List<BorrowRecord> overdueRecords = borrowService.getOverdueRecords();
        
        if (overdueRecords.isEmpty()) {
            System.out.println("No overdue books.");
            return;
        }

        MenuDisplay.displaySectionHeader("Overdue Books");
        for (BorrowRecord record : overdueRecords) {
            System.out.println(record);
        }
        System.out.println("===================================\n");
    }

    // ==================== STATISTICS HANDLER ====================
    
    private void handleDisplayStatistics() {
        MenuDisplay.displaySectionHeader("Library Statistics");
        System.out.println("Total Books:        " + bookService.getTotalBooks());
        System.out.println("Available Books:    " + bookService.getAvailableBooks());
        System.out.println("Borrowed Books:     " + bookService.getBorrowedBooks());
        System.out.println("Total Users:        " + userService.getTotalUsers());
        System.out.println("Total Transactions: " + borrowService.getTotalRecords());
        System.out.println("========================================\n");
    }
}
