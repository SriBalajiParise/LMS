package ui;

/**
 * Utility class for displaying menus and messages
 */
public class MenuDisplay {
    
    /**
     * Display welcome message
     */
    public static void displayWelcome() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO LIBRARY MANAGEMENT SYSTEM  ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    /**
     * Display main menu
     */
    public static void displayMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║   LIBRARY MANAGEMENT SYSTEM - MAIN MENU   ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│  BOOK MANAGEMENT                          │");
        System.out.println("│  1.  Add New Book                         │");
        System.out.println("│  2.  Update Book                          │");
        System.out.println("│  3.  Delete Book                          │");
        System.out.println("│  4.  Search Books                         │");
        System.out.println("│  5.  Display All Books                    │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│  USER MANAGEMENT                          │");
        System.out.println("│  6.  Add New User                         │");
        System.out.println("│  7.  Update User                          │");
        System.out.println("│  8.  Delete User                          │");
        System.out.println("│  9.  Display All Users                    │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│  BORROWING MANAGEMENT                     │");
        System.out.println("│  10. Borrow Book                          │");
        System.out.println("│  11. Return Book                          │");
        System.out.println("│  12. View User Borrow History             │");
        System.out.println("│  13. View All Borrow Records              │");
        System.out.println("│  14. View Overdue Books                   │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│  REPORTS & STATISTICS                     │");
        System.out.println("│  15. Display Statistics                   │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│  0.  Exit                                 │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.print("\nEnter your choice: ");
    }

    /**
     * Display exit message
     */
    public static void displayExit() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║   Thank you for using Library Management  ║");
        System.out.println("║              System. Goodbye!             ║");
        System.out.println("╚═══════════════════════════════════════════╝\n");
    }

    /**
     * Display section header
     */
    public static void displaySectionHeader(String title) {
        int padding = (43 - title.length()) / 2;
        String spaces = " ".repeat(padding);
        System.out.println("\n========== " + title + " ==========");
    }

    /**
     * Display success message
     */
    public static void displaySuccess(String message) {
        System.out.println("✓ " + message);
    }

    /**
     * Display error message
     */
    public static void displayError(String message) {
        System.out.println("✗ " + message);
    }

    /**
     * Display warning message
     */
    public static void displayWarning(String message) {
        System.out.println("⚠ " + message);
    }
}
