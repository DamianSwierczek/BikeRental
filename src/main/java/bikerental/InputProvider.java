package bikerental;

import java.util.Scanner;

public class InputProvider {

    private final Scanner scanner;

    public InputProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeStringInput() {
        return scanner.next();
    }

    public long takeLongInput() {
        return scanner.nextLong();
    }
}
