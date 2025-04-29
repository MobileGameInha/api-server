package cat.cat.global.error;

import cat.cat.global.error.dto.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            RuntimeException.class,
    })
    public ResponseEntity<ExceptionResponse> handleIBadRequestException(final RuntimeException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(), "DESCRIPTION_DEFAULT");
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler({
            NoResourceFoundException.class
    })
    public ResponseEntity<ExceptionResponse> handleNoResourceFoundException(final NoResourceFoundException e) {
        logger.error(e.getMessage(), e);
        ExceptionResponse exceptionResponse = new ExceptionResponse("존재하지 않는 리소스 또는 URI 입니다.", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestBody(final HttpMessageNotReadableException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("잘못된 Request Body 형식 입니다.", e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleNotSupportedMethod(final HttpRequestMethodNotSupportedException e) {
        ExceptionResponse errorResponse = new ExceptionResponse("잘못된 HTTP 메소드 요청입니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("잘못된 타입을 가진 데이터가 포함되어 있습니다.", e.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleOverflowException(final Exception e) {
        logger.error(e.getMessage(), e);

        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse("서버에 예기치 못한 오류가 발생했습니다. 관리자에게 문의하세요.", e.getMessage()));
    }
}
