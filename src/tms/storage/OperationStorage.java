package tms.storage;

import tms.entity.Operation;

public interface OperationStorage {
    void save(Operation operation);
    Operation[] findAll();
}
