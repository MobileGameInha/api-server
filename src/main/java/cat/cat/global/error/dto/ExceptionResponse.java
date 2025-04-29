package cat.cat.global.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String message;
    private final String description;

    public ExceptionResponse(final String message, final String description) {
        this.message = message;
        this.description = description;
    }
}
