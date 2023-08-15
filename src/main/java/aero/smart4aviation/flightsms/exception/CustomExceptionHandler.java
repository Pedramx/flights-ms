package aero.smart4aviation.flightsms.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpClientErrorException(HttpClientErrorException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(ex.getRawStatusCode());
        errorResponse.setError(ex.getStatusText());

        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

}
