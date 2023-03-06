package tms.console;

import tms.Application;
import tms.console.enumpack.ResponseType;
import tms.console.exception.OperationNotFoundException;
import tms.console.util.ConsoleReader;
import tms.console.util.ConsoleWriter;
import tms.entity.Operation;
import tms.entity.OperationType;
import tms.service.Calculator;
import tms.storage.InMemoryOperationStorage;
import tms.storage.OperationStorage;
import tms.util.Reader;
import tms.util.Writer;
import tms.validator.OperationValidator;

import java.util.List;

public class ConsoleApplication implements Application {
    private final OperationStorage storage = new InMemoryOperationStorage();
    private final Calculator calculator = new Calculator();
    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private final OperationValidator operationValidator = new OperationValidator();

    public void run() {
        boolean continuation = true;
        while (continuation) {
            double num1 = 0;
            double num2 = 0;
            String answer = "";

            writer.write("Enter number 1");
            num1 = reader.readDouble();
            boolean isValid = operationValidator.isValidDigits(answer);
            if (isValid) {
            continue;
            }

            writer.write("Enter number 2");
            num2 = reader.readDouble();
           isValid = operationValidator.isValidDigits(answer);
            if (isValid) {
                continue;
            }

            writer.write("Enter operation type. Sum, sub, mul or div?");

            Operation op;

            try {
                OperationType type = reader.readOperationType();
                op = new Operation(num1, num2, type);
            } catch (OperationNotFoundException e) {
                writer.write("Operation not found!");
                continue;
            }

            Operation result = calculator.calculate(op);
            storage.save(result);
            writer.write("Result = " + result.getResult());
            writer.write("");

            writer.write("Would you like to continue? yes OR no?");
            ResponseType response = reader.readResponseType();
            switch (response) {
                case YES: {
                    List<Operation> operations = storage.findAll();
                    for (Operation operation : operations) {
                        if(operation != null)
                            writer.write("Result = " + operation.getResult());
                    }
                    writer.write("Ok, let`s continue");
                    break;
                }
                case NO: {
                    writer.write(" ");
                    continuation = false;
                    return;
                }
                default: {
                    continuation = false;
                }
            }
        }

        writer.write("Would you like to see operation storage? YES or NO?");
        ResponseType reply = reader.readResponseType();
        switch (reply) {
            case YES: {
                List<Operation> operations = storage.findAll();
                for (Operation operation : operations) {
                    writer.write("num1: " + operation.getNum1() + " " + operation.getType() + " num2: " + operation.getNum2() + " = " + operation.getResult());
                }
            }
            default:
                return;
        }

        try {
            OperationType operationType = reader.readOperationType();
        } catch (OperationNotFoundException e) {
            writer.write("Operation not found!");
        }
    }
}