package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.UserService;

import java.util.Optional;
import java.util.Scanner;

public class DeleteUserTask implements Task {

    private UserService userService;
    private Scanner scanner;

    private UserEntity userEntity;

    public DeleteUserTask(final UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {

        String userId = null;

        while (userId == null) {

            System.out.println("\nEnter user id: ");
            userId = scanner.nextLine();
        }

        final Optional<UserEntity> userOptional = userService.getUserById(userId);

        if (!userOptional.isPresent()) {
            System.out.println("\nUser with id " + userId + " is not exist!\n");
            return null;
        } else {
            userEntity = userOptional.get();
            userService.deleteUser(userEntity);
        }

        System.out.println("Deleted user with id - " + userId + "\n");
        return this;
    }

    @Override
    public void undo() {
        System.out.println("\nUndo deleting user - " + userEntity.getLogin());

        final UserEntity user = userService.saveUser(userEntity);
        userService.updateUserId(userEntity.getId(), user.getId());

        System.out.println(".... user " + userEntity.getLogin() + " is restored!");
    }
}
