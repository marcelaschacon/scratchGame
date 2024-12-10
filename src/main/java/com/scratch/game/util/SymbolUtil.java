package com.scratch.game.util;

import java.util.HashMap;
import java.util.Map;

import com.scratch.game.model.Scratch;
import com.scratch.game.model.symbol.Symbol;

public class SymbolUtil {

	private static final String BONUS = "bonus";

	public static Map<String, Symbol> initializeSymbols(Scratch scratch) {
        Map<String, Symbol> initializedSymbols = new HashMap<>();
        Map<String, Symbol> symbolsConfig = scratch.getSymbols();
        
        for (Map.Entry<String, Symbol> entry : symbolsConfig.entrySet()) {
            String symbolName = entry.getKey();
            Symbol symbolConfig = entry.getValue();
            Symbol symbol = new Symbol(
            		symbolName,
            		symbolConfig.getType(),
            		symbolConfig.getImpact(),
            		symbolConfig.getRewardMultiplier(),
            		symbolConfig.getExtra()
            );
            if (symbolConfig.getType().equals(BONUS)) {
                if (symbolConfig.getImpact() != null) {
                    symbol.setImpact(symbolConfig.getImpact());
                }
                if (symbolConfig.getExtra() > 0) {
                    symbol.setExtra(symbolConfig.getExtra());
                }
            }
            initializedSymbols.put(symbolName, symbol);
        }
        return initializedSymbols;
    }
}
