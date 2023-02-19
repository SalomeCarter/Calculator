package tms.service;


import tms.entity.Operation;

public class Calculator {
    public Operation calculate(Operation operation) {
        switch (operation.getType()) {
            case SUM:
                operation.setResult(operation.getNum1() + operation.getNum2());
                return operation;
            case SUB:
                operation.setResult(operation.getNum1() - operation.getNum2());
                return operation;
            case MUL:
                operation.setResult(operation.getNum1() * operation.getNum2());
                return operation;
            case DIV:
                operation.setResult(operation.getNum1() / operation.getNum2());
                return operation;
        }
        return operation;
    }
}
