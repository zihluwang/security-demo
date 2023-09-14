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

package wang.zihlu.security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wang.zihlu.security.constant.ResponseHeaders;

/**
 * BaseRequestException
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
public class BaseException extends RuntimeException {

    @Getter
    private final Long bizErrorCode;

    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public BaseException(Long bizErrorCode, String message) {
        super(message);
        this.bizErrorCode = bizErrorCode;
    }

    public ResponseEntity<Void> composeResponseEntity() {
        return ResponseEntity.status(status)
                .header(ResponseHeaders.BIZ_ERROR, getMessage())
                .header(ResponseHeaders.BIZ_ERROR_CODE, String.valueOf(bizErrorCode))
                .body(null);
    }

}
