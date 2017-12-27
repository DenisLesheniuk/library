package ml.ledv.library.cli.service;

import ml.ledv.library.cli.impl.CLIImpl;
import ml.ledv.library.cli.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Initializer {

    private BookLibraryService mysqlService;
    private BookLibraryService mongodbService;

    @Autowired
    public Initializer(final BookLibraryService mysqlService, final BookLibraryService mongodbService) {
        this.mysqlService = mysqlService;
        this.mongodbService = mongodbService;
    }

    public void initialize() {

        CLIImpl cli = null;

        while (cli == null) {
            System.out.println("1. MySql");
            System.out.println("2. MongoDB");

            final String choice;
            final Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    cli = new CLIImpl(mysqlService);
                    break;
                }
                case "2": {
                    cli = new CLIImpl(mongodbService);
                    break;
                }
                default: {
                    System.out.println("Wrong choice.");
                }
            }
        }
        cli.start();
    }
}
