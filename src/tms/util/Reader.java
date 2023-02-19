package tms.util;

import tms.console.enumpack.ResponseType;
import tms.console.exception.OperationNotFoundException;
import tms.entity.OperationType;

public interface Reader {
    ResponseType readResponseType();
    OperationType readOperationType() throws OperationNotFoundException;
    double readDouble();
}
