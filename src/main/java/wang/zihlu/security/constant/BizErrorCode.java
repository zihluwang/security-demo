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
package wang.zihlu.security.constant;

/**
 * BizErrorCode
 *
 * @author Zihlu Wang
 * @since 14 Sept, 2023
 */
public final class BizErrorCode {
    // region Errors related to accounts(starts from 1000).
    public static final Long INCORRECT_USERNAME_OR_PASSWORD = 1000L;
    public static final Long MISSING_TOKEN = 1001L;
    // endregion

    // region Errors related to server status
    public static final Long SERVER_ERROR = 9000L;
    // endregion
}
