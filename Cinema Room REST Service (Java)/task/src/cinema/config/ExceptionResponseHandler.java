package cinema.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getReason());
        return new ResponseEntity<>(exceptionMessage, ex.getHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServerWebInputException.class)
    ResponseEntity<Object> handleServerWebInputException(ServerWebInputException ex, WebRequest request) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getReason());
        return new ResponseEntity<>(exceptionMessage, null, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        return new ResponseEntity<Object>(exceptionMessage, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
