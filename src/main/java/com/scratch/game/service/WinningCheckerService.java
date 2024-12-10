package com.scratch.game.service;

import java.util.List;
import java.util.Map;

import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.model.Scratch;
import com.scratch.game.model.WinCombination;

public class WinningCheckerService {
	
    private final Scratch scratch;

    public WinningCheckerService(Scratch scratch) {
        this.scratch = scratch;
    }

    public boolean checkWin(Symbol[][] matrix) {
        for (Map.Entry<String, WinCombination> entry : scratch.getWinCombinations().entrySet()) {
        	WinCombination combination = entry.getValue();
            if (checkCombination(matrix, combination)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCombination(Symbol[][] matrix, WinCombination combination) {
        if (combination.coveredAreas == null) {
            return false; 
        }
        for (List<String> area : combination.coveredAreas) {
            if (isMatchingArea(matrix, area)) {
                return true;
            }
        }
        return false;
    }

    public boolean isMatchingArea(Symbol[][] matrix, List<String> coveredArea) {
        if (coveredArea == null || coveredArea.isEmpty()) {
            return false;
        }
        Symbol firstSymbol = null;
        for (String position : coveredArea) {
            String[] parts = position.split(":");
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);
            Symbol currentSymbol = matrix[row][column];
            if (firstSymbol == null) {
                firstSymbol = currentSymbol;
            }
            if (!isMatching(firstSymbol, currentSymbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatching(Symbol symbol1, Symbol symbol2) {
        return symbol1.getRewardMultiplier() == symbol2.getRewardMultiplier()
               && symbol1.getType().equals(symbol2.getType());
    }
}