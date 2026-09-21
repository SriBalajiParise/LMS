import java.util.*;
import java.time.LocalDate;

class Book {
    String id, title, author, isbn, category;
    boolean available;
    
    Book(String id, String title, String author, String isbn, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available = true;
    }
}

class User {
    String id, name, email, phone;
    LocalDate joined;
    
    User(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.joined = LocalDate.now();
    }
}

class BorrowRecord {
    String recordId, userId, bookId;
    LocalDate borrowed, due, returned;
    
    BorrowRecord(String recordId, String userId, String bookId) {
        this.recordId = recordId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowed = LocalDate.now();
        this.due = borrowed.plusDays(14);
    }
    
    boolean isOverdue() {
        return returned == null && LocalDate.now().isAfter(due);
    }
}

class TreeNode {
    Book book;
    TreeNode left, right;
    
    TreeNode(Book book) {
        this.book = book;
    }
}

class LinkedNode {
    BorrowRecord data;
    LinkedNode next;
    
    LinkedNode(BorrowRecord data) {
        this.data = data;
    }
}

public class LibrarySystem {
    HashMap<String, Book> books = new HashMap<>();
    HashMap<String, User> users = new HashMap<>();
    TreeNode root = null;
    LinkedNode recordsHead = null;
    int bookCount = 1, userCount = 1, recordCount = 1;
    Scanner sc = new Scanner(System.in);
    
    void addBook(String title, String author, String isbn, String category) {
        String id = "B" + String.format("%03d", bookCount++);
        Book book = new Book(id, title, author, isbn, category);
        books.put(id, book);
        root = insertTree(root, book);
        System.out.println("Book added: " + id);
    }
    
    TreeNode insertTree(TreeNode node, Book book) {
        if (node == null) return new TreeNode(book);
        if (book.title.compareToIgnoreCase(node.book.title) < 0)
            node.left = insertTree(node.left, book);
        else
            node.right = insertTree(node.right, book);
        return node;
    }
    
    void searchBooks(String term) {
        System.out.println("\n--- Search Results ---");
        List<Book> results = new ArrayList<>();
        
        if (books.containsKey(term)) results.add(books.get(term));
        
        searchTree(root, term.toLowerCase(), results);
        
        for (Book b : books.values()) {
            if ((b.author.toLowerCase().contains(term.toLowerCase()) ||
                 b.category.toLowerCase().contains(term.toLowerCase()) ||
                 b.isbn.contains(term)) && !results.contains(b)) {
                results.add(b);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No books found");
        } else {
            mergeSort(results, 0, results.size() - 1);
            for (Book b : results) {
                System.out.printf("%s | %s | %s | %s | %s\n", 
                    b.id, b.title, b.author, b.category, b.available ? "Available" : "Borrowed");
            }
        }
    }
    
    void searchTree(TreeNode node, String term, List<Book> results) {
        if (node == null) return;
        searchTree(node.left, term, results);
        if (node.book.title.toLowerCase().contains(term) && !results.contains(node.book))
            results.add(node.book);
        searchTree(node.right, term, results);
    }
    
    void mergeSort(List<Book> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }
    
    void merge(List<Book> list, int left, int mid, int right) {
        List<Book> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        
        while (i <= mid && j <= right) {
            if (list.get(i).title.compareToIgnoreCase(list.get(j).title) <= 0)
                temp.add(list.get(i++));
            else
                temp.add(list.get(j++));
        }
        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));
        
        for (i = 0; i < temp.size(); i++)
            list.set(left + i, temp.get(i));
    }
    
    void updateBook(String id) {
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        
        System.out.println("Current: " + book.title + " by " + book.author);
        System.out.print("Update (1)Title (2)Author (3)Category: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch(choice) {
            case 1: System.out.print("New title: "); book.title = sc.nextLine(); break;
            case 2: System.out.print("New author: "); book.author = sc.nextLine(); break;
            case 3: System.out.print("New category: "); book.category = sc.nextLine(); break;
        }
        System.out.println("Updated successfully");
    }
    
    void deleteBook(String id) {
        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found");
        } else if (!book.available) {
            System.out.println("Cannot delete - book is borrowed");
        } else {
            books.remove(id);
            System.out.println("Book deleted");
        }
    }
    
    void displayBooks() {
        System.out.println("\n--- All Books ---");
        List<Book> sorted = new ArrayList<>();
        inorder(root, sorted);
        for (Book b : sorted) {
            System.out.printf("%s | %-25s | %-20s | %s\n", 
                b.id, b.title, b.author, b.available ? "Available" : "Borrowed");
        }
        System.out.println("Total: " + books.size());
    }
    
    void inorder(TreeNode node, List<Book> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.book);
        inorder(node.right, list);
    }
    
    void addUser(String name, String email, String phone) {
        String id = "U" + String.format("%03d", userCount++);
        users.put(id, new User(id, name, email, phone));
        System.out.println("User registered: " + id);
    }
    
    void updateUser(String id) {
        User user = users.get(id);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        
        System.out.println("Current: " + user.name + " - " + user.email);
        System.out.print("Update (1)Name (2)Email (3)Phone: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch(choice) {
            case 1: System.out.print("New name: "); user.name = sc.nextLine(); break;
            case 2: System.out.print("New email: "); user.email = sc.nextLine(); break;
            case 3: System.out.print("New phone: "); user.phone = sc.nextLine(); break;
        }
        System.out.println("Updated successfully");
    }
    
    void deleteUser(String id) {
        if (!users.containsKey(id)) {
            System.out.println("User not found");
            return;
        }
        
        LinkedNode current = recordsHead;
        while (current != null) {
            if (current.data.userId.equals(id) && current.data.returned == null) {
                System.out.println("Cannot delete - user has unreturned books");
                return;
            }
            current = current.next;
        }
        
        users.remove(id);
        System.out.println("User deleted");
    }
    
    void displayUsers() {
        System.out.println("\n--- All Users ---");
        List<User> userList = new ArrayList<>(users.values());
        userList.sort(Comparator.comparing(u -> u.name));
        for (User u : userList) {
            System.out.printf("%s | %-20s | %-25s | %s\n", u.id, u.name, u.email, u.phone);
        }
        System.out.println("Total: " + users.size());
    }
    
    void borrowBook(String userId, String bookId) {
        if (!users.containsKey(userId)) {
            System.out.println("User not found");
            return;
        }
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (!book.available) {
            System.out.println("Book already borrowed");
            return;
        }
        
        String rid = "R" + String.format("%03d", recordCount++);
        BorrowRecord record = new BorrowRecord(rid, userId, bookId);
        
        LinkedNode newNode = new LinkedNode(record);
        if (recordsHead == null) {
            recordsHead = newNode;
        } else {
            LinkedNode temp = recordsHead;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        
        book.available = false;
        System.out.println("Book borrowed! Due date: " + record.due);
    }
    
    void returnBook(String bookId) {
        LinkedNode current = recordsHead;
        while (current != null) {
            if (current.data.bookId.equals(bookId) && current.data.returned == null) {
                current.data.returned = LocalDate.now();
                books.get(bookId).available = true;
                
                if (current.data.isOverdue()) {
                    long days = java.time.temporal.ChronoUnit.DAYS.between(
                        current.data.due, current.data.returned);
                    System.out.println("Book returned (OVERDUE by " + days + " days)");
                } else {
                    System.out.println("Book returned successfully");
                }
                return;
            }
            current = current.next;
        }
        System.out.println("No active borrow record found");
    }
    
    void viewHistory(String userId) {
        System.out.println("\n--- Borrow History for " + userId + " ---");
        LinkedNode current = recordsHead;
        boolean found = false;
        
        while (current != null) {
            if (current.data.userId.equals(userId)) {
                found = true;
                String status = current.data.returned != null ? 
                    "Returned" : (current.data.isOverdue() ? "OVERDUE" : "Active");
                System.out.printf("%s | Book: %s | Borrowed: %s | Due: %s | %s\n",
                    current.data.recordId, current.data.bookId, current.data.borrowed, 
                    current.data.due, status);
            }
            current = current.next;
        }
        
        if (!found) System.out.println("No records found");
    }
    
    void viewAllRecords() {
        System.out.println("\n--- All Borrow Records ---");
        LinkedNode current = recordsHead;
        int count = 0;
        
        while (current != null) {
            count++;
            String status = current.data.returned != null ? 
                "Returned" : (current.data.isOverdue() ? "OVERDUE" : "Active");
            System.out.printf("%s | User: %s | Book: %s | %s\n",
                current.data.recordId, current.data.userId, current.data.bookId, status);
            current = current.next;
        }
        System.out.println("Total records: " + count);
    }
    
    void viewOverdue() {
        System.out.println("\n--- Overdue Books ---");
        LinkedNode current = recordsHead;
        boolean found = false;
        
        while (current != null) {
            if (current.data.isOverdue()) {
                found = true;
                long days = java.time.temporal.ChronoUnit.DAYS.between(
                    current.data.due, LocalDate.now());
                System.out.printf("%s | User: %s | Book: %s | Overdue: %d days\n",
                    current.data.recordId, current.data.userId, current.data.bookId, days);
            }
            current = current.next;
        }
        
        if (!found) System.out.println("No overdue books");
    }
    
    void showStats() {
        int available = 0, borrowed = 0;
        for (Book b : books.values()) {
            if (b.available) available++;
            else borrowed++;
        }
        
        int totalRecords = 0;
        LinkedNode current = recordsHead;
        while (current != null) {
            totalRecords++;
            current = current.next;
        }
        
        System.out.println("\n--- Library Statistics ---");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available: " + available);
        System.out.println("Borrowed: " + borrowed);
        System.out.println("Total Users: " + users.size());
        System.out.println("Total Transactions: " + totalRecords);
    }
    
    void loadSampleData() {
        addBook("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", "Fiction");
        addBook("1984", "George Orwell", "978-0451524935", "Dystopian");
        addBook("Clean Code", "Robert Martin", "978-0132350884", "Programming");
        addBook("Design Patterns", "Gang of Four", "978-0201633612", "Programming");
        addBook("To Kill a Mockingbird", "Harper Lee", "978-0061120084", "Fiction");
        
        addUser("John Doe", "john@email.com", "555-0101");
        addUser("Jane Smith", "jane@email.com", "555-0102");
        addUser("Bob Wilson", "bob@email.com", "555-0103");
    }
    
    void run() {
        loadSampleData();
        
        while (true) {
            System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");
            System.out.println("BOOKS: 1.Add 2.Update 3.Delete 4.Search 5.Display");
            System.out.println("USERS: 6.Add 7.Update 8.Delete 9.Display");
            System.out.println("BORROW: 10.Borrow 11.Return 12.History 13.All Records 14.Overdue");
            System.out.println("OTHER: 15.Statistics 0.Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1:
                    System.out.print("Title: "); String t = sc.nextLine();
                    System.out.print("Author: "); String a = sc.nextLine();
                    System.out.print("ISBN: "); String i = sc.nextLine();
                    System.out.print("Category: "); String c = sc.nextLine();
                    addBook(t, a, i, c);
                    break;
                case 2:
                    System.out.print("Book ID: "); updateBook(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Book ID: "); deleteBook(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Search: "); searchBooks(sc.nextLine());
                    break;
                case 5:
                    displayBooks();
                    break;
                case 6:
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Email: "); String e = sc.nextLine();
                    System.out.print("Phone: "); String p = sc.nextLine();
                    addUser(n, e, p);
                    break;
                case 7:
                    System.out.print("User ID: "); updateUser(sc.nextLine());
                    break;
                case 8:
                    System.out.print("User ID: "); deleteUser(sc.nextLine());
                    break;
                case 9:
                    displayUsers();
                    break;
                case 10:
                    System.out.print("User ID: "); String uid = sc.nextLine();
                    System.out.print("Book ID: "); String bid = sc.nextLine();
                    borrowBook(uid, bid);
                    break;
                case 11:
                    System.out.print("Book ID: "); returnBook(sc.nextLine());
                    break;
                case 12:
                    System.out.print("User ID: "); viewHistory(sc.nextLine());
                    break;
                case 13:
                    viewAllRecords();
                    break;
                case 14:
                    viewOverdue();
                    break;
                case 15:
                    showStats();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
    public static void main(String[] args) {
        new LibrarySystem().run();
    }
}
