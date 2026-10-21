package library;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

public class Library {

    private final List<Book> books = new ArrayList<>();

    private final List<Copy> copies = new ArrayList<>();

    private final List<Member> members = new ArrayList<>();

    private final List<Loan> loans = new ArrayList<>();

    // Register a book

    public void addBook(Book book) {

        books.add(book);

    }

    // Add a copy of a book

    public void addCopy(String isbn, String inventoryNumber) {

        Book book = findBook(isbn);

        if (book == null) {

            throw new IllegalArgumentException("Unknown ISBN: " + isbn);

        }

        copies.add(new Copy(inventoryNumber, book));

    }

    // Register a member

    public void registerMember(Member member) {

        members.add(member);

    }

    // Find a book by ISBN

    public Book findBook(String isbn) {

        for (Book book : books) {

            if (book.getIsbn().equals(isbn)) {

                return book;

            }

        }

        return null;

    }

    // Find a copy by inventory number

    public Copy findCopy(String inventoryNumber) {

        for (Copy copy : copies) {

            if (copy.getInventoryNumber().equals(inventoryNumber)) {

                return copy;

            }

        }

        return null;

    }

    // Find a member by number

    private Member findMember(String memberNumber) {

        for (Member member : members) {

            if (member.getNumber().equals(memberNumber)) {

                return member;

            }

        }

        return null;

    }

    // Lend a copy to a member

    public Loan lend(String memberNumber,

                     String inventoryNumber,

                     LocalDate today) {

        Member member = findMember(memberNumber);

        Copy copy = findCopy(inventoryNumber);

        if (member == null) {

            throw new IllegalArgumentException(

                    "Unknown member: " + memberNumber);

        }

        if (copy == null) {

            throw new IllegalArgumentException(

                    "Unknown copy: " + inventoryNumber);

        }

        if (!copy.isAvailable()) {

            throw new IllegalStateException(

                    "Copy is not available");

        }

        int activeLoans = 0;

        for (Loan loan : loans) {

            if (loan.getMember().equals(member) && loan.isOpen()) {

                activeLoans++;

            }

        }

        if (activeLoans >= 5) {

            throw new IllegalStateException(

                    "Member cannot have more than 5 active loans");

        }

        Loan loan = new Loan(member, copy, today);

        loans.add(loan);

        copy.setAvailable(false);

        return loan;

    }

    // Return a copy

    public void returnCopy(String inventoryNumber, LocalDate today) {

        Copy copy = findCopy(inventoryNumber);

        if (copy == null) {

            throw new IllegalArgumentException(

                    "Unknown copy: " + inventoryNumber);

        }

        if (copy.isAvailable()) {

            throw new IllegalStateException(

                    "Copy is not currently borrowed");

        }

        for (Loan loan : loans) {

            if (loan.getCopy().equals(copy) && loan.isOpen()) {

                loan.setReturnDate(today);

                copy.setAvailable(true);

                return;

            }

        }

        throw new IllegalStateException("No open loan found");

    }

    // Search books by title, ignoring case

    public List<Book> searchByTitle(String keyword) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {

            if (book.getTitle().toLowerCase()

                    .contains(keyword.toLowerCase())) {

                result.add(book);

            }

        }

        return result;

    }

}