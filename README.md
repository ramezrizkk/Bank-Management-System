# 🏦 Java Banking System

A console-based banking application built with Java that demonstrates core object-oriented programming principles, file I/O operations, and data persistence. This project simulates real-world banking operations including user authentication, account management, and financial transactions.

## ✨ Features

- **User Authentication** — Secure login system with unique ID and password verification
- **Account Management** — Create new accounts with auto-generated unique identifiers
- **Financial Operations** — Deposit, withdraw, and transfer money between accounts
- **Profile Customization** — Update username and password with verification
- **Data Persistence** — All user data stored and retrieved from local text files in CSV format
- **Input Validation** — Comprehensive error handling for invalid user inputs

## 🏗️ Architecture

### Project Structure
Main.java        → Entry point, menu navigation, and business logic
User.java        → Entity class defining user attributes and getters/setters
FileManager.java → Data access layer handling file operations
users.txt        → Auto-generated data file storing user records


### Technical Implementation
| Component | Technology | Purpose |
|-----------|------------|---------|
| Language | Java SE | Core application logic |
| Storage | Plain Text (CSV) | Persistent data storage |
| I/O Handling | BufferedReader/BufferedWriter | Efficient file operations |
| Architecture | Console-based OOP | Modular, maintainable design |

## 🔄 Core Workflows

### Authentication Flow
1. User enters ID and password
2. System validates credentials against stored data
3. Upon success, user gains access to banking menu
4. Failed attempts return to main menu with error message

### Transaction Processing
- **Deposits** — Validated for positive amounts, immediately update balance
- **Withdrawals** — Checked against available balance and positive amount validation
- **Transfers** — Cross-account transactions with recipient lookup and atomic updates

### Data Management
- All modifications trigger immediate file updates
- Atomic write operations prevent data corruption
- Sequential ID generation ensures unique account identifiers

## 🛡️ Security Features

- Password verification required for sensitive operations (username/password changes)
- Input sanitization prevents crashes from malformed data
- Balance validation prevents overdrafts and invalid transactions
- Account deletion requires explicit confirmation

## 📊 Code Statistics

- **3 Java Classes** — Clean separation of concerns
- **8 Banking Operations** — Comprehensive financial functionality
- **File-Based Persistence** — No external database dependencies

## 🔮 Potential Enhancements

- Database migration (SQL-based storage)
- GUI implementation (JavaFX or Swing)
- Transaction history logging
- Password encryption (bcrypt/SHA-256)
- Interest calculation algorithms
- Multi-currency support

## 🎥 Demo
A demonstration video explaining the project is available here (in arabic):
https://youtu.be/io8NvdFP2YA?si=n39-dJ99HVGlRSyB
