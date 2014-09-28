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

package jk_5.nailed.api.command.context;

public class SuggestionContext {

    private static final SuggestionContext FOR_LAST = new SuggestionContext(null, true);
    private static final SuggestionContext FOR_HANGING = new SuggestionContext(null, false);
    
    private final Character flag;
    private final boolean forLast;

    private SuggestionContext(Character flag, boolean forLast) {
        this.flag = flag;
        this.forLast = forLast; 
    }
    
    public boolean forHangingValue() {
        return flag == null && !forLast;
    }
    
    public boolean forLastValue() {
        return flag == null && forLast;
    }

    public boolean forFlag() {
        return flag != null;
    }

    public Character getFlag() {
        return flag;
    }
    
    @Override
    public String toString() {
        return forFlag() ? ("-" + getFlag()) : (forHangingValue() ? "hanging" : "last");
    }
    
    public static SuggestionContext flag(Character flag) {
        return new SuggestionContext(flag, false);
    }
    
    public static SuggestionContext lastValue() {
        return FOR_LAST;
    }
    
    public static SuggestionContext hangingValue() {
        return FOR_HANGING;
    }

}
