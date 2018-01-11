package ml.ledv.library.cli.utils;

import ml.ledv.library.db.entity.impl.BookEntity;

public class Printer {

    public static void printBooks(final BookEntity book) {
        System.out.println();
        System.out.println("******************************************************");
        System.out.println("Id:        " + book.getId());
        System.out.println("Book name: " + book.getName());
        System.out.println("******************************************************");
    }
}
