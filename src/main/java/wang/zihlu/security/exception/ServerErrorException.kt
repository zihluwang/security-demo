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

package wang.zihlu.security.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import wang.zihlu.security.constant.ResponseHeaders

class ServerErrorException(bizErrorCode: Long, message: String) : BaseException(bizErrorCode, message) {

    private val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR

    override fun composeResponseEntity(): ResponseEntity<Void> {
        return ResponseEntity.status(status)
                .header(ResponseHeaders.BIZ_ERROR, message)
                .header(ResponseHeaders.BIZ_ERROR_CODE, bizErrorCode.toString())
                .body(null)
    }

}