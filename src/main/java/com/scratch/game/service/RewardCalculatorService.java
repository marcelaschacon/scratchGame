package com.scratch.game.service;

import java.util.HashMap;
import java.util.Map;

import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.model.Scratch;
import com.scratch.game.model.ProbabilityConfig;
import com.scratch.game.model.WinCombination;

public class RewardCalculatorService {
	
    private static final String SAME_SYMBOLS = "same_symbols";
	private static final String EXTRA_BONUS = "extra_bonus";
	private static final String MULTIPLY_REWARD = "multiply_reward";
	
	public double calculateReward(Symbol[][] matrix, Scratch Scratch, double betAmount) {
        double totalReward = 0.0;
        Map<String, Integer> symbolMatchCount = new HashMap<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                Symbol symbol = matrix[row][col];
                String symbolType = symbol.getType(); 
                symbolMatchCount.put(symbolType, symbolMatchCount.getOrDefault(symbolType, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : symbolMatchCount.entrySet()) {
            String symbolType = entry.getKey();
            int count = entry.getValue();
            double rewardMultiplier = getRewardMultiplier(symbolType, count, Scratch);
            if (rewardMultiplier > 0) {
                totalReward += rewardMultiplier;
            }
        }
        return betAmount * totalReward;
    }

    double getRewardMultiplier(String symbolName, int count, Scratch Scratch) {
        double reward = 0.0;
        if (Scratch == null || Scratch.getWinCombinations() == null) {
            return reward;
        }
        for (Map.Entry<String, WinCombination> combinationEntry : Scratch.getWinCombinations().entrySet()) {
            WinCombination combination = combinationEntry.getValue();
            if ((combination.getWhen().equals(SAME_SYMBOLS) || combination.getWhen().equals("linear_symbols")) 
    && combination.getCount() == count) {
                reward += combination.getRewardMultiplier() * getSymbolMultiplier(symbolName, Scratch);
            }
        }
        return reward;
    }
	
    private double getSymbolMultiplier(String symbolName, Scratch Scratch) {
        if (Scratch == null || Scratch.getProbabilities() == null) {
            return 1.0;
        }
        for (ProbabilityConfig probabilityScratch : Scratch.getProbabilities().getStandardSymbols()) {
            if (probabilityScratch.getSymbols() != null && probabilityScratch.getSymbols().containsKey(symbolName)) {
            	return probabilityScratch.getSymbols().get(symbolName);
            }
        }
        return 1.0;
    }

    public double applyBonus(Symbol[][] matrix, double bettingAmount) {
        double bonusMultiplier = 1.0;
        double extraBonus = 0.0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                Symbol symbol = matrix[row][col];
                if (symbol.getType().equals("bonus")) {
                    switch (symbol.getImpact()) {
                        case MULTIPLY_REWARD:
                            bonusMultiplier *= symbol.getRewardMultiplier();
                            break;
                        case EXTRA_BONUS:
                            extraBonus += symbol.getExtra();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return bonusMultiplier * extraBonus;
    }
}
