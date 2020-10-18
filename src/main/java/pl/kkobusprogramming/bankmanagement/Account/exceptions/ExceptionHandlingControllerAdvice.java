package pl.kkobusprogramming.bankmanagement.Account.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {
    @ExceptionHandler(InvalidPeselNumberException.class)
    public ResponseEntity<CodeMsgResponse> handleInvalidPeselNumberException(InvalidPeselNumberException ex) {
        return new ResponseEntity<>(new CodeMsgResponse(ex, 400L), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<CodeMsgResponse> handleNotEnoughMoneyException(NotEnoughMoneyException ex) {
        return new ResponseEntity<>(new CodeMsgResponse(ex, 400L), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<CodeMsgResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        return new ResponseEntity<>(new CodeMsgResponse(ex, 400L), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownCurrencyException.class)
    public ResponseEntity<CodeMsgResponse> handleUnknownCurrencyException(UnknownCurrencyException ex) {
        return new ResponseEntity<>(new CodeMsgResponse(ex, 400L), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminException.class)
    public ResponseEntity<CodeMsgResponse> handleAdminException(AdminException ex) {
        return new ResponseEntity<>(new CodeMsgResponse(ex, 500L), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    @Setter
    class CodeMsgResponse {
        private Long code;
        private String message;

        CodeMsgResponse(Throwable e, Long code) {
            this.message = e.getMessage();
            this.code = code;
        }
    }
}
