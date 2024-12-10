package com.scratch.game.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.scratch.game.model.Probabilities;
import com.scratch.game.model.ProbabilityConfig;
import com.scratch.game.model.Scratch;
import com.scratch.game.model.symbol.Symbol;

public class MatrixGeneratorService {
	
    private static final double BONUS_SYMBOL_PROBABILITY = 0.2;

    public Symbol[][] generateMatrix(Scratch scratch) {
        Symbol[][] matrix = new Symbol[scratch.getRows()][scratch.getColumns()];
        Random random = new Random();
        for (int row = 0; row < scratch.getRows(); row++) {
            for (int col = 0; col < scratch.getColumns(); col++) {
                Symbol symbol = getRandomStandardSymbol(scratch.getProbabilities().getStandardSymbols());
                matrix[row][col] = symbol;
            }
        }
        for (int row = 0; row < scratch.getRows(); row++) {
            for (int col = 0; col < scratch.getColumns(); col++) {
                if (random.nextDouble() < BONUS_SYMBOL_PROBABILITY) {
                    Symbol bonusSymbol = getRandomBonusSymbol(scratch.getProbabilities().getBonusSymbols());
                    matrix[row][col] = bonusSymbol;
                }
            }
        }
        return matrix;
    }

    Symbol getRandomStandardSymbol(List<ProbabilityConfig> standardSymbols) {
        Random random = new Random();
        double totalProbability = 0;
        for (ProbabilityConfig prob : standardSymbols) {
            totalProbability += prob.getSymbols().values().stream().mapToDouble(Double::doubleValue).sum();
        }
        double rand = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;
        for (ProbabilityConfig prob : standardSymbols) {
            for (Map.Entry<String, Double> entry : prob.getSymbols().entrySet()) {
                cumulativeProbability += entry.getValue();
                if (rand <= cumulativeProbability) {
                    return new Symbol(entry.getKey(), "standard", entry.getValue());
                }
            }
        }
        return new Symbol("F", "standard", 1);
    }

    Symbol getRandomBonusSymbol(Probabilities.BonusSymbols bonusSymbolsConfig) {
        Map<String, Double> bonusSymbols = bonusSymbolsConfig.getSymbols();
        double totalProbability = bonusSymbols.values().stream().mapToDouble(Double::doubleValue).sum();
        Random random = new Random();
        double rand = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0.0;
        for (Map.Entry<String, Double> entry : bonusSymbols.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (rand <= cumulativeProbability) {
                return new Symbol(entry.getKey(), "bonus", getBonusImpact(entry.getKey()), entry.getValue(), getExtraBonus(entry.getKey()));
            }
        }
        return new Symbol("MISS", "none", "none", 0, 0);
    }

    private String getBonusImpact(String bonusSymbolName) {
        switch (bonusSymbolName) {
            case "10x":
            case "5x":
                return "multiply_reward";
            case "+1000":
            case "+500":
                return "extra_bonus";
            default:
                return "none";
        }
    }

    private int getExtraBonus(String bonusSymbolName) {
        switch (bonusSymbolName) {
            case "+1000":
                return 1000;
            case "+500":
                return 500;
            default:
                return 0;
        }
    }
}
