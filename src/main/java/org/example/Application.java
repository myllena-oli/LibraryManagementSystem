package org.example;

import org.example.model.Book;
import org.example.service.BookService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Application {
    private BookService bookService;
    private Scanner reader;

    public Application() {
        bookService = new BookService();
        reader = new Scanner(System.in);

    }


    public void run() {
        try {

            while (true) {
                displayMenu();
                String input = reader.nextLine();
                try {
                    switch (input) {
                        case "1":
                            listBooks();
                            break;
                        case "2":
                            getBookById();

                            break;
                        case "3":
                            createBook();
                            break;
                        case "4":
                            updateBook();
                            break;
                        case "5":
                            deleteBook();
                            break;
                        case "0":
                            System.out.println("Saindo da aplicação.");
                            System.exit(0);
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um valor válido.");
                    reader.nextLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Listar livros");
        System.out.println("2 - Buscar livro por ID");
        System.out.println("3 - Criar novo livro");
        System.out.println("4 - Atualizar livro");
        System.out.println("5 - Excluir livro");
        System.out.println("0 - Sair");
    }

    private void listBooks() {
        List<Book> books = bookService.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("Não há livros cadastrados.");
        } else {
            System.out.println("Lista de livros:");
            for (Book book : books) {
                System.out.println(book.getId() + " - " + book.getName() + " by " + book.getAuthor());
            }
        }
    }

    private void getBookById() {
        try {
            System.out.print("Digite o ID do livro: ");
            Long bookId = Long.parseLong(reader.nextLine());
            Book book = bookService.getBookById(bookId);

            if (book != null) {
                System.out.println("Informações do livro:");
                System.out.println("ID: " + book.getId());
                System.out.println("Nome: " + book.getName());
                System.out.println("Autor: " + book.getAuthor());
                System.out.println("Data de Lançamento: " + book.getReleaseDate());
            } else {
                System.out.println("Livro não encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de livro inválido. Insira um número válido.");
        }
    }

    private void createBook() throws IOException {
        System.out.print("Digite o nome do livro: ");
        String name = reader.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = reader.nextLine();
        System.out.print("Digite a data de lançamento (yyyy-MM-dd): ");
        String releaseDateStr = reader.nextLine();
        try {
            LocalDate releaseDate = LocalDate.parse(releaseDateStr);

            bookService.createBook(name, author, releaseDate);

            System.out.println("Livro criado com sucesso.");
        } catch (Exception e) {
            System.out.println("Data de lançamento inválida. Use o formato yyyy-MM-dd.");
        }
    }

    private void updateBook() throws IOException {
        try {
            System.out.print("Digite o ID do livro que deseja atualizar: ");
        Long bookId = reader.nextLong();
        reader.nextLine();
        Book existingBook = bookService.getBookById(bookId);


        if (existingBook == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do livro (deixe em branco para manter o atual): ");
        String name = reader.nextLine();
        System.out.print("Digite o novo autor do livro (deixe em branco para manter o atual): ");
        String author = reader.nextLine();
        System.out.print("Digite a nova data de lançamento (yyyy-MM-dd) (deixe em branco para manter a atual): ");
        String releaseDateStr = reader.nextLine();

        LocalDate releaseDate = (releaseDateStr.isEmpty()) ? existingBook.getReleaseDate() : LocalDate.parse(releaseDateStr);

        bookService.updateBook(existingBook.getId(), name, author, releaseDate);

        System.out.println("Livro atualizado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("ID de livro inválido. Insira um número válido.");
        } catch (Exception e) {
            System.out.println("Data de lançamento inválida. Use o formato yyyy-MM-dd.");
        }
    }

    private void deleteBook() {
        try {

            System.out.print("Digite o ID do livro que deseja excluir: ");
        Long bookId = Long.parseLong(reader.nextLine());
        Book existingBook = bookService.getBookById(bookId);

        if (existingBook == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        bookService.deleteBook(bookId);

        System.out.println("Livro excluído com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("ID de livro inválido. Insira um número válido.");
        }
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
