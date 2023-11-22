package mikolaj.project.backendapp.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private HttpStatus httpStatus;
    private int statusCode;
    private String reason;
    private LocalDateTime timestamp;
    private Map<?,?> data;
}
