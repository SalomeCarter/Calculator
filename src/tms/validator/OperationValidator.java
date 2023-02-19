package tms.validator;

import java.util.regex.Pattern;

public class OperationValidator {
    private static final Pattern DIGITS = Pattern.compile("^\\d{1,5}$|(?=^.{1,5}$)^\\d+\\.\\d{0,2}$");

    public OperationValidator() {
    }

    public boolean isValidDigits(String digits) {
        return DIGITS.matcher(digits).matches();
    }
}
