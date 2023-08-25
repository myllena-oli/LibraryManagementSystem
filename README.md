# Library Management System

The Library Management System is a simple application for managing book information in a library. The primary goal behind developing this Java application was to acquire hands-on experience in working with PostgreSQL and Docker.
It provides the following functionalities:

- List all books in the library.
- Retrieve information about a specific book.
- Create a new book entry.
- Update details of an existing book.
- Delete a book from the library.

## Requirements

Make sure you have the following tools installed in your development environment:

- Java 11
- PostgreSQL  (You should have a PostgreSQL database up and running. Make sure to adjust the database connection settings in the code to match your setup.)
- Docker (Optional, for containerization)
- Git (optional, for cloning the repository)

## Usage

1. When you run the application, you will be presented with a menu where you can choose an option by entering the corresponding number.

2. Options:

- 1 - List all books in the library.
- 2 - Retrieve information about a specific book by entering its ID.
- 3 - Create a new book entry by providing the book's name, author, and release date.
- 4 - Update details of an existing book by entering its ID and providing new information (you can leave fields blank to keep the current values).
- 5 - Delete a book from the library by entering its ID.
- 0 - Exit the application.

The application will guide you through the chosen action, and you can interact with it using the provided options.

