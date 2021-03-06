package bikerental;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputProvider {

    private final Scanner scanner;

    public InputProvider(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeStringInput() {
        return scanner.next();
    }

    public int takeIntInput() {
        return scanner.nextInt();
    }

    public BigDecimal takeBigDecimalInput() {
        return scanner.nextBigDecimal();
    }
}
