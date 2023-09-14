package wang.zihlu.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.zihlu.security.constant.ResponseHeaders;
import wang.zihlu.security.exception.BadRequestException;

/**
 * ExceptionController
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handle(BadRequestException exception) {
        return exception.composeResponseEntity();
    }

}
