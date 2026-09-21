# Quick Start Guide 🚀

## Running the Application

### Windows
```bash
# Double-click or run:
compile.bat
```

### Linux/Mac
```bash
# Make script executable (first time only):
chmod +x compile.sh

# Run:
./compile.sh
```

### Manual Compilation
```bash
# Compile
javac Main.java models/*.java services/*.java controllers/*.java datastructures/*.java ui/*.java utils/*.java

# Run
java Main
```

## 📋 Common Operations

### Sample Book IDs (Pre-loaded)
- `B0001` - The Great Gatsby
- `B0002` - To Kill a Mockingbird
- `B0003` - 1984
- `B0004` - Clean Code
- `B0005` - Design Patterns
- `B0006` - The Hobbit
- `B0007` - Harry Potter
- `B0008` - Introduction to Algorithms

### Sample User IDs (Pre-loaded)
- `U0001` - John Doe
- `U0002` - Jane Smith
- `U0003` - Bob Johnson
- `U0004` - Alice Williams
- `U0005` - Charlie Brown

## 🎯 Quick Test Workflow

### 1️⃣ Borrow a Book
```
Menu: 10
User ID: U0001
Book ID: B0001
```

### 2️⃣ Check User History
```
Menu: 12
User ID: U0001
```

### 3️⃣ Return the Book
```
Menu: 11
Book ID: B0001
```

### 4️⃣ Search for Books
```
Menu: 4
Search: Clean Code
```

### 5️⃣ View Statistics
```
Menu: 15
```

## 📝 Adding New Records

### Add a New Book
```
Menu: 1
Title: The Pragmatic Programmer
Author: Andy Hunt
ISBN: 978-0135957059
Category: Programming
```
**Result**: New Book ID will be auto-generated (e.g., B0009)

### Add a New User
```
Menu: 6
Name: Sarah Connor
Email: sarah.connor@email.com
Phone: 555-0106
```
**Result**: New User ID will be auto-generated (e.g., U0006)

## 🔍 Search Tips

### Search Capabilities
- **By Title**: Enter any part of the title
  - Example: "Harry" finds "Harry Potter..."
  
- **By Author**: Enter author name
  - Example: "Orwell" finds "1984"
  
- **By Category**: Enter category
  - Example: "Programming" finds all programming books
  
- **By ISBN**: Enter ISBN number
  - Example: "978-0743273565"
  
- **By Book ID**: Enter exact ID
  - Example: "B0001"

## ⚠️ Important Rules

### Cannot Delete
- ❌ Books that are currently borrowed
- ❌ Users who have unreturned books

### Loan Period
- 📅 14 days from borrow date
- ⏰ Returns after due date are marked as OVERDUE

### Book Availability
- ✅ Can only borrow available books
- 📚 Returned books become available immediately

## 🎨 Menu Navigation

```
┌─────────────────────────┐
│   MAIN MENU             │
├─────────────────────────┤
│ Books:       1-5        │
│ Users:       6-9        │
│ Borrowing:   10-14      │
│ Statistics:  15         │
│ Exit:        0          │
└─────────────────────────┘
```

## 📊 Understanding Output

### Book Display Format
```
ID: B0001    | Title: The Great Gatsby      | Author: F. Scott Fitzgerald | ISBN: 978-0743273565 | Category: Fiction    | Available: Yes
```

### User Display Format
```
ID: U0001    | Name: John Doe               | Email: john.doe@email.com   | Phone: 555-0101      | Member Since: 2024-04-15
```

### Borrow Record Format
```
Record: R0001    | User: U0001    | Book: B0001    | Borrowed: 2024-04-20 | Due: 2024-05-04 | Status: Active
```

## 🔥 Pro Tips

1. **View Statistics First**: Get overview of library state
   ```
   Menu → 15
   ```

2. **Search Before Adding**: Check if book already exists
   ```
   Menu → 4 → Enter title
   ```

3. **Check History**: See user's borrowing patterns
   ```
   Menu → 12 → Enter User ID
   ```

4. **Monitor Overdue**: Regularly check overdue books
   ```
   Menu → 14
   ```

5. **Update Before Delete**: Verify book isn't borrowed
   ```
   Menu → 5 (display all) → Check availability → Menu → 3 (delete)
   ```

## 🐛 Troubleshooting

### Compilation Errors
```bash
# Error: package does not exist
# Solution: Make sure you're in the project root directory

# Error: cannot find symbol
# Solution: Compile all files together (use compile script)
```

### Runtime Issues
```
# Invalid choice error
→ Enter only numbers from the menu

# Book not found
→ Use Menu 5 to see all valid Book IDs

# User not found
→ Use Menu 9 to see all valid User IDs

# Cannot borrow book
→ Check if book is available (Menu 5)
```

## 📱 Example Session

```
Welcome to Library Management System!

Menu → 15 (Statistics)
→ See: 8 books, 5 users, 0 transactions

Menu → 10 (Borrow Book)
→ User: U0001
→ Book: B0004
→ Success! Due: 2024-05-04

Menu → 12 (View History)
→ User: U0001
→ Shows: 1 record

Menu → 14 (Overdue Books)
→ Shows: None (if within 14 days)

Menu → 11 (Return Book)
→ Book: B0004
→ Success!

Menu → 0 (Exit)
→ Goodbye!
```

## 🎓 Learning Exercise Ideas

1. **Test Search Efficiency**: Add 100+ books, compare search times
2. **Stress Test**: Create multiple borrows, test tracking
3. **Edge Cases**: Try borrowing unavailable books
4. **Data Validation**: Test with empty inputs, special characters
5. **Algorithm Comparison**: Modify to use Quick Sort, compare performance

---

**Ready to Start?** Run `compile.bat` (Windows) or `./compile.sh` (Linux/Mac)
