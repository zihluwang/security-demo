package wang.zihlu.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wang.zihlu.security.constant.ResponseHeaders;

/**
 * BadRequestException
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Getter
public class BadRequestException extends RuntimeException {

    private final Long bizErrorCode;

    public BadRequestException(Long bizErrorCode, String message) {
        super(message);
        this.bizErrorCode = bizErrorCode;
    }

    public ResponseEntity<Void> composeResponseEntity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(ResponseHeaders.BIZ_ERROR, getMessage())
                .header(ResponseHeaders.BIZ_ERROR_CODE, String.valueOf(bizErrorCode))
                .body(null);
    }

}
