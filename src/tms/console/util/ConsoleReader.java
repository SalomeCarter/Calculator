package tms.console.util;

import tms.console.enumpack.ResponseType;
import tms.console.exception.OperationNotFoundException;
import tms.entity.OperationType;
import tms.util.Reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private final Scanner scanner = new Scanner(System.in);
    public String readString() {
        return scanner.next();
    }

    @Override
    public ResponseType readResponseType() {
        String next = scanner.next();
        String s = next.toUpperCase();
        return ResponseType.valueOf(s);
    }

//    @Override
//    public OperationType readOperationType() {
//        String next = scanner.next();
//        String s = next.toUpperCase();
//        return OperationType.valueOf(s);
//    }

    public double readDouble() {
        return scanner.nextDouble();
    }
    private final String[] types = new String[]{"SUM", "SUB", "MUL", "DIV"};

    public OperationType readOperationType() throws OperationNotFoundException {
        String next = scanner.next();
        String type = next.toUpperCase();
        for (String s : types) {
            if (s.equals(type)) {
                return OperationType.valueOf(type);
            }
        }
        throw new OperationNotFoundException();
    }
}
