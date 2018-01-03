package ml.ledv.library;

import ml.ledv.library.cli.CLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

public class Main implements CommandLineRunner {
    
    @Autowired
    private CLI cli;

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        cli.start();
    }
}
