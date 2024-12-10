package com.scratch.game.service;

import static org.junit.jupiter.api.Assertions.*;

import com.scratch.game.model.Scratch;
import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.model.WinCombination;
import org.junit.jupiter.api.Test;

import java.util.*;

class RewardCalculatorServiceTest {

    @Test
    void testCalculateRewardWithoutWinningCombination() {
    	Scratch scratch = new Scratch();
        Map<String, WinCombination> winCombinations = new HashMap<>();
        scratch.setWinCombinations(winCombinations);
        Symbol[][] matrix = generateMatrix();
        RewardCalculatorService service = new RewardCalculatorService();
        double reward = service.calculateReward(matrix, scratch, 100);
        assertEquals(0, reward, "Reward should be zero for no winning combinations.");
    }

    @Test
    void testApplyBonusWithBonusSymbols() {
        Symbol bonusSymbolMultiply = new Symbol("10x", "bonus", "multiply_reward", 10, 0);
        Symbol bonusSymbolExtra = new Symbol("+500", "bonus", "extra_bonus", 1, 500);
        Symbol[][] matrix = {
                {bonusSymbolMultiply, bonusSymbolExtra, new Symbol("A", "standard", 5)},
                {new Symbol("B", "standard", 3), new Symbol("C", "standard", 2), new Symbol("D", "standard", 1)},
                {new Symbol("E", "standard", 4), new Symbol("F", "standard", 6), new Symbol("G", "standard", 7)}
        };
        RewardCalculatorService service = new RewardCalculatorService();
        double bonus = service.applyBonus(matrix, 100);
        assertEquals(5000, bonus, "Calculated bonus is incorrect.");
    }

    @Test
    void testApplyBonusWithoutBonusSymbols() {
    	Symbol[][] matrix = generateMatrix();
        RewardCalculatorService service = new RewardCalculatorService();
        double bonus = service.applyBonus(matrix, 100);
        assertEquals(0, bonus, "Bonus should be zero without bonus symbols.");
    }

    @Test
    void testGetRewardMultiplierWithMatchingCombination() {
    	Scratch scratch = new Scratch();
        Map<String, WinCombination> winCombinations = new HashMap<>();
        WinCombination winCombination = new WinCombination();
        winCombination.setCount(3);
        winCombination.setWhen("same_symbols");
        winCombination.setRewardMultiplier(2.0);
        winCombinations.put("rowWin", winCombination);
        scratch.setWinCombinations(winCombinations);
        RewardCalculatorService service = new RewardCalculatorService();
        double multiplier = service.getRewardMultiplier("A", 3, scratch);
        assertEquals(2.0, multiplier, "Reward multiplier is incorrect.");
    }

    @Test
    void testGetRewardMultiplierWithoutMatchingCombination() {
    	Scratch scratch = new Scratch();
    	scratch.setWinCombinations(new HashMap<>());
        RewardCalculatorService service = new RewardCalculatorService();
        double multiplier = service.getRewardMultiplier("A", 3, scratch);
        assertEquals(0, multiplier, "Reward multiplier should be zero for no matching combinations.");
    }
    
    private Symbol[][] generateMatrix() {
		Symbol[][] matrix = {
                {new Symbol("A", "standard", 5), new Symbol("B", "standard", 3), new Symbol("C", "standard", 2)},
                {new Symbol("B", "standard", 3), new Symbol("C", "standard", 2), new Symbol("D", "standard", 1)},
                {new Symbol("E", "standard", 4), new Symbol("F", "standard", 6), new Symbol("G", "standard", 7)}
        };
		return matrix;
	}
}
