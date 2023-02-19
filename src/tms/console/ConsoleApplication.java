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

public class ConsoleApplication implements Application {
    private final OperationStorage storage = new InMemoryOperationStorage();
    private final Calculator calculator = new Calculator();
    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private final OperationValidator operationValidator = new OperationValidator();

    public void run() {
        boolean continuation = true;
        double num1 = 0;
        double num2 = 0;
        boolean isValid = false;

        while (continuation) {
            String answer = "";
            if (!isValid) {
                writer.write("Enter number 1");
                num1 = reader.readDouble();
                isValid = operationValidator.isValidDigits(answer);
                num1 = Double.parseDouble(answer);

                isValid = false;
                continue;
            }


            if (!isValid) {
                writer.write("Enter number 2");
                num2 = reader.readDouble();
                isValid = operationValidator.isValidDigits(answer);

            }
            num2 = Double.parseDouble(answer);

            isValid = false;

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
                    Operation[] all = storage.findAll();
                    for (Operation operation : all) {
                        if(operation != null)
                            writer.write("Result = " + operation.getResult());
                    }
                    writer.write("Ok, let`s continue");
                    break;
                }
                case NO: {
                    writer.write(" ");
                    continuation = false;
                    break;
                }
                default: {
                    continuation = false;
                }
            }
        }
        try {
            OperationType operationType = reader.readOperationType();
        } catch (OperationNotFoundException e) {
            writer.write("Operation not found!");
        }
    }
}