# Library Management System 📚

A comprehensive Java-based Library Management System demonstrating the use of various data structures and algorithms.

## 🎯 Project Overview

Developed: April 2024 - May 2024

This system efficiently manages book records, borrowing history, and user details using optimized data structures including:
- **HashMap** - O(1) lookup for books and users
- **Binary Search Tree** - Efficient searching by book title
- **Linked List** - Borrowing history management
- **Merge Sort** - Sorting search results

## 📁 Project Structure

```
Library-Management-System/
├── Main.java                          # Entry point
├── controllers/
│   └── LibraryController.java         # Main application controller
├── services/
│   ├── BookService.java               # Book management operations
│   ├── UserService.java               # User management operations
│   └── BorrowService.java             # Borrowing operations
├── models/
│   ├── Book.java                      # Book entity
│   ├── User.java                      # User entity
│   └── BorrowRecord.java              # Borrow record entity
├── datastructures/
│   ├── BookBST.java                   # Binary Search Tree implementation
│   ├── BSTNode.java                   # BST Node
│   ├── BorrowRecordList.java          # Custom Linked List
│   └── Node.java                      # Linked List Node
├── ui/
│   └── MenuDisplay.java               # UI display utilities
└── utils/
    ├── SortingUtils.java              # Sorting algorithms (Merge Sort, Quick Sort)
    └── DataInitializer.java           # Sample data initialization
```

## ✨ Features

### Book Management
- ✅ Add new books
- ✅ Update book details (title, author, category)
- ✅ Delete books
- ✅ Search books by title, author, category, ISBN, or ID
- ✅ Display all books sorted by title

### User Management
- ✅ Register new users
- ✅ Update user information
- ✅ Delete users (with validation)
- ✅ Display all users

### Borrowing System
- ✅ Borrow books
- ✅ Return books
- ✅ Track borrowing history
- ✅ View overdue books
- ✅ 14-day loan period with automatic due date calculation

### Reports & Statistics
- ✅ Library statistics dashboard
- ✅ User-specific borrowing history
- ✅ All borrow records
- ✅ Overdue tracking

## 🚀 How to Compile and Run

### Option 1: Compile All Files
```bash
# Compile all Java files
javac Main.java models/*.java services/*.java controllers/*.java datastructures/*.java ui/*.java utils/*.java

# Run the application
java Main
```

### Option 2: Using Package Structure
```bash
# Compile with package structure
javac -d bin Main.java models/*.java services/*.java controllers/*.java datastructures/*.java ui/*.java utils/*.java

# Run from bin directory
cd bin
java Main
```

### Option 3: One-liner Compilation
```bash
# Compile and run
javac Main.java && java Main
```

## 🎮 Usage

Once the application starts, you'll see an interactive menu with 15 options:

### Book Management (1-5)
1. Add New Book
2. Update Book
3. Delete Book
4. Search Books
5. Display All Books

### User Management (6-9)
6. Add New User
7. Update User
8. Delete User
9. Display All Users

### Borrowing Management (10-14)
10. Borrow Book
11. Return Book
12. View User Borrow History
13. View All Borrow Records
14. View Overdue Books

### Reports (15)
15. Display Statistics

### Exit (0)
0. Exit Application

## 📊 Data Structures Used

### 1. HashMap
- **Purpose**: O(1) book and user lookup by ID
- **Files**: `BookService.java`, `UserService.java`
- **Complexity**: O(1) for insert, search, delete

### 2. Binary Search Tree (BST)
- **Purpose**: Efficient title-based searching
- **Files**: `BookBST.java`, `BSTNode.java`
- **Complexity**: O(log n) average for search, O(n) worst case

### 3. Linked List
- **Purpose**: Sequential access to borrowing history
- **Files**: `BorrowRecordList.java`, `Node.java`
- **Complexity**: O(n) for search, O(1) for append

### 4. Sorting Algorithms
- **Merge Sort**: Used for search results
- **Quick Sort**: Alternative implementation provided
- **Files**: `SortingUtils.java`
- **Complexity**: O(n log n) average case

## 📝 Sample Data

The system comes pre-loaded with sample data:

### Books (8 books)
- The Great Gatsby
- To Kill a Mockingbird
- 1984
- Clean Code
- Design Patterns
- The Hobbit
- Harry Potter and the Philosopher's Stone
- Introduction to Algorithms

### Users (5 users)
- John Doe
- Jane Smith
- Bob Johnson
- Alice Williams
- Charlie Brown

## 🔧 Design Patterns

- **Service Layer Pattern**: Separation of business logic
- **MVC Pattern**: Model-View-Controller architecture
- **Repository Pattern**: Data access abstraction

## 🎓 Educational Value

This project demonstrates:
- Object-Oriented Programming principles
- Data structure implementation and usage
- Algorithm optimization
- Clean code practices
- Package organization
- Separation of concerns

## 📄 License

This project is for educational purposes.

## 👨‍💻 Author

Your Name
- April 2024 - May 2024

---

**Note**: This is a console-based application. For production use, consider adding:
- Database integration
- GUI interface
- Authentication system
- File persistence
- Error logging
- Unit tests
