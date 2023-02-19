package tms.storage;

import tms.entity.Operation;

public class InMemoryOperationStorage implements OperationStorage {

    private final Operation[] operations = new Operation[10];
    public void save(Operation operation) {

    }

    public Operation[] findAll() { return operations; }
}
