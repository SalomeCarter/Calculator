package tms.storage;

import tms.entity.Operation;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOperationStorage implements OperationStorage {
    private final List<Operation> history = new ArrayList<>();
    @Override
    public void save(Operation operation) {
        history.add(operation);
    }

    @Override
    public List<Operation> findAll() {
        return history;
    }
}
