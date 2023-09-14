/*
 * Copyright (c) 2023. Zihlu Wang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package wang.zihlu.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.zihlu.security.constant.BizErrorCode;
import wang.zihlu.security.constant.ResponseHeaders;
import wang.zihlu.security.exception.BaseException;

/**
 * ExceptionController
 *
 * @author Zihlu Wang
 * @since 13 Sept, 2023
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handle(BaseException exception) {
        return exception.composeResponseEntity();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handle(UsernameNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(ResponseHeaders.BIZ_ERROR, exception.getMessage())
                .header(ResponseHeaders.BIZ_ERROR_CODE, String.valueOf(BizErrorCode.INCORRECT_USERNAME_OR_PASSWORD))
                .build();
    }

}
