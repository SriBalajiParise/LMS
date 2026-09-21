# Library Management System - Architecture Documentation

## 📐 Architecture Overview

This project follows a **layered architecture** pattern with clear separation of concerns.

```
┌─────────────────────────────────────────┐
│          Presentation Layer             │
│     (UI - User Interface)               │
│  - MenuDisplay.java                     │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Controller Layer               │
│  - LibraryController.java               │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Service Layer                  │
│  - BookService.java                     │
│  - UserService.java                     │
│  - BorrowService.java                   │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│          Model Layer                    │
│  - Book.java                            │
│  - User.java                            │
│  - BorrowRecord.java                    │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│      Data Structures Layer              │
│  - BookBST.java                         │
│  - BorrowRecordList.java                │
│  - BSTNode.java                         │
│  - Node.java                            │
└─────────────────────────────────────────┘
```

## 📦 Package Structure

### 1. **controllers/** - Application Flow Control
- `LibraryController.java` - Main controller handling all user interactions

### 2. **services/** - Business Logic
- `BookService.java` - Manages book operations (add, update, delete, search)
- `UserService.java` - Manages user operations (registration, updates)
- `BorrowService.java` - Handles borrowing and returning logic

### 3. **models/** - Data Entities
- `Book.java` - Book entity with properties and behaviors
- `User.java` - User/member entity
- `BorrowRecord.java` - Transaction record entity

### 4. **datastructures/** - Custom Data Structures
- `BookBST.java` - Binary Search Tree for efficient book searching
- `BSTNode.java` - BST node structure
- `BorrowRecordList.java` - Custom linked list for borrow records
- `Node.java` - Generic linked list node

### 5. **ui/** - User Interface Components
- `MenuDisplay.java` - Handles all console display formatting

### 6. **utils/** - Helper Utilities
- `SortingUtils.java` - Sorting algorithms (Merge Sort, Quick Sort)
- `DataInitializer.java` - Initializes sample data

## 🔄 Data Flow

### Example: Borrowing a Book

```
User Input (User ID, Book ID)
        ↓
LibraryController.handleBorrowBook()
        ↓
BorrowService.borrowBook(userId, bookId)
        ↓
    ┌───┴───┐
    ↓       ↓
UserService  BookService
.userExists  .getBook()
    ↓       ↓
    └───┬───┘
        ↓
Create BorrowRecord
        ↓
Add to BorrowRecordList (Linked List)
        ↓
Update Book availability
        ↓
Return success/failure to Controller
        ↓
MenuDisplay.displaySuccess() or displayError()
```

## 🎯 Design Patterns Used

### 1. **Service Layer Pattern**
- Separates business logic from presentation
- All operations go through service classes
- Example: `BookService`, `UserService`, `BorrowService`

### 2. **Model-View-Controller (MVC)**
- **Model**: `Book`, `User`, `BorrowRecord`
- **View**: `MenuDisplay` (console-based UI)
- **Controller**: `LibraryController`

### 3. **Singleton-like Data Storage**
- Each service maintains its own data store
- Services are instantiated once in the controller

### 4. **Strategy Pattern** (Implicit)
- Different sorting algorithms in `SortingUtils`
- Can switch between Merge Sort and Quick Sort

### 5. **Facade Pattern**
- `LibraryController` provides simplified interface
- Hides complex service interactions from user

## 📊 Data Structure Choices

### Why HashMap?
```java
HashMap<String, Book> bookHashMap
```
- **Use Case**: Store books with ID as key
- **Advantage**: O(1) lookup, insert, delete
- **Trade-off**: No ordering, extra memory

### Why Binary Search Tree?
```java
BookBST for title-based searching
```
- **Use Case**: Sorted book retrieval, prefix searching
- **Advantage**: O(log n) search, maintains sorted order
- **Trade-off**: Can become unbalanced

### Why Linked List?
```java
BorrowRecordList for transaction history
```
- **Use Case**: Sequential access to records
- **Advantage**: Dynamic size, easy insertion
- **Trade-off**: O(n) search time

## 🔍 Search Optimization

### Multi-Criteria Search Strategy
```
searchBooks(term) uses:
1. HashMap - Check exact ID match (O(1))
2. BST - Search by title (O(log n))
3. Linear scan - Author/Category (O(n))
4. Merge results and sort
```

## 🚀 Performance Characteristics

| Operation              | Data Structure | Complexity |
|------------------------|---------------|------------|
| Find book by ID        | HashMap       | O(1)       |
| Find book by title     | BST           | O(log n)   |
| List all books sorted  | BST           | O(n)       |
| Add book               | HashMap + BST | O(log n)   |
| Borrow history lookup  | Linked List   | O(n)       |
| Sort search results    | Merge Sort    | O(n log n) |

## 🧩 Class Relationships

### Dependencies

```
Main
 └── LibraryController
      ├── BookService
      │    ├── Book (model)
      │    └── BookBST (data structure)
      │
      ├── UserService
      │    └── User (model)
      │
      └── BorrowService
           ├── BorrowRecord (model)
           ├── BorrowRecordList (data structure)
           ├── BookService (dependency)
           └── UserService (dependency)
```

### Aggregation vs Composition

- **Composition**: `BookService` **owns** `BookBST`
- **Aggregation**: `BorrowService` **uses** `BookService` and `UserService`
- **Association**: `BorrowRecord` **references** Book and User by ID

## 🔐 Validation Rules

### Book Operations
- ✓ Cannot delete a borrowed book
- ✓ Book ID is auto-generated and unique
- ✓ ISBN format is not validated (future enhancement)

### User Operations
- ✓ Cannot delete user with active borrows
- ✓ User ID is auto-generated and unique
- ✓ Email format is not validated (future enhancement)

### Borrowing Operations
- ✓ Book must exist and be available
- ✓ User must exist
- ✓ Only one active borrow per book
- ✓ 14-day loan period
- ✓ Overdue detection on return

## 🎓 Object-Oriented Principles

### Encapsulation
- All fields are private
- Access through getters/setters
- Business logic hidden in services

### Abstraction
- Service interfaces hide implementation details
- Data structures abstract storage mechanisms

### Single Responsibility Principle
- Each class has one clear purpose
- BookService only handles books
- MenuDisplay only handles UI

### Open/Closed Principle
- Easy to add new sorting algorithms
- New services can be added without modifying existing ones

### Dependency Inversion
- Controller depends on services (abstractions)
- Not directly on data structures (implementations)

## 🔮 Future Enhancements

1. **Database Integration**: Replace in-memory storage with JDBC
2. **GUI**: Add JavaFX or Swing interface
3. **Authentication**: Add login system for users
4. **Fine System**: Calculate overdue fines
5. **Reservation System**: Allow book reservations
6. **Export/Import**: Save/load data from files
7. **Search Filters**: Advanced search with multiple criteria
8. **Analytics**: Borrowing trends and statistics
9. **Email Notifications**: Remind users of due dates
10. **Unit Tests**: Add JUnit test coverage

## 📚 Learning Outcomes

This project demonstrates understanding of:
- ✅ Custom data structure implementation
- ✅ Algorithm design and optimization
- ✅ Object-oriented design patterns
- ✅ Code organization and modularity
- ✅ Clean code practices
- ✅ Package management in Java
- ✅ Separation of concerns
- ✅ Business logic abstraction

---

**Last Updated**: May 2024  
**Version**: 1.0
