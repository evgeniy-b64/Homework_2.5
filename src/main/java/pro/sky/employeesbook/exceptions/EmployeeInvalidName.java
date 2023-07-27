package pro.sky.employeesbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeInvalidName extends RuntimeException{
    public EmployeeInvalidName() {
        super();
    }

    public EmployeeInvalidName(String message) {
        super(message);
    }

    public EmployeeInvalidName(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeInvalidName(Throwable cause) {
        super(cause);
    }

    protected EmployeeInvalidName(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
