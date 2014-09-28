/*
 * Nailed, a Minecraft PvP server framework
 * Copyright (C) jk-5 <http://github.com/jk-5/>
 * Copyright (C) Nailed team and contributors <http://github.com/nailed/>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the MIT License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the MIT License along with
 * this program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package jk_5.nailed.api.command.parametric;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Thrown when there are leftover parameters that were not consumed, particular in the
 * case of the user providing too many parameters.
 */
public class UnconsumedParameterException extends ParameterException {
    
    private String unconsumed;

    /**
     * Create a new instance.
     *
     * @param unconsumed the unconsumed tokens
     */
    public UnconsumedParameterException(String unconsumed) {
        checkNotNull(unconsumed);
        this.unconsumed = unconsumed;
    }

    /**
     * Get the unconsumed tokens.
     *
     * @return unconsumed tokens
     */
    public String getUnconsumed() {
        return unconsumed;
    }

}
