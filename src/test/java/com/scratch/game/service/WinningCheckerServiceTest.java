package com.scratch.game.service;

import static org.junit.jupiter.api.Assertions.*;

import com.scratch.game.model.Scratch;
import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.model.WinCombination;
import org.junit.jupiter.api.Test;

import java.util.*;

class WinningCheckerServiceTest {

    private static final String NAME_B = "B";
	private static final int MULTIPLIER = 5;
	private static final String STANDARD = "standard";
	private static final String NAME_A = "A";
	private static final String EXPECTED_AREA_NOT_TO_MATCH_BUT_IT_DID = "Expected area not to match but it did.";

	Scratch scratch = new Scratch();
	
	@Test
    void testCheckWinWithWinningCombination() {
        Map<String, WinCombination> winCombinations = new HashMap<>();
        WinCombination winCombination = new WinCombination();
        winCombination.setCoveredAreas(Arrays.asList(
                Arrays.asList("0:0", "0:1", "0:2") 
        ));
        winCombinations.put("rowWin", winCombination);
        scratch.setWinCombinations(winCombinations);
        Symbol symbolA = new Symbol(NAME_A, STANDARD, MULTIPLIER);
        Symbol[][] matrix = createMatrixWithLenght2(symbolA);
        WinningCheckerService service = new WinningCheckerService(scratch);
        assertTrue(service.checkWin(matrix), "Expected to find a winning combination but did not.");
    }
    
    @Test
    void testCheckLoseWithoutCoveredAreas() {
        Map<String, WinCombination> winCombinations = new HashMap<>();
        WinCombination winCombination = new WinCombination();
        winCombinations.put("rowWin", winCombination);
        scratch.setWinCombinations(winCombinations);
        Symbol symbolA = new Symbol(NAME_A, STANDARD, MULTIPLIER);
        Symbol[][] matrix = createMatrixWithLenght2(symbolA);
        WinningCheckerService service = new WinningCheckerService(scratch);
        assertFalse(service.checkWin(matrix), "Expected to find a lose combination but did not.");
    }

    @Test
    void testCheckWinWithoutWinningCombination() {
    	Scratch scratch = new Scratch();
        Map<String, WinCombination> winCombinations = new HashMap<>();
        WinCombination winCombination = new WinCombination();
        winCombination.setCoveredAreas(Arrays.asList(
                Arrays.asList("0:0", "0:1", "0:2")
        ));
        winCombinations.put("rowWin", winCombination);
        scratch.setWinCombinations(winCombinations);
        Symbol[][] matrix = createMatrixWithLenght3();
        WinningCheckerService service = new WinningCheckerService(scratch);
        assertFalse(service.checkWin(matrix), "Expected no winning combination but found one.");
    }

    @Test
    void testIsMatchingAreaWithMatchingSymbols() {
        Symbol symbolA = new Symbol(NAME_A, STANDARD, MULTIPLIER);
        Symbol[][] matrix = createMatrixWithLenght2(symbolA);
        WinningCheckerService service = new WinningCheckerService(null);
        List<String> coveredArea = Arrays.asList("0:0", "0:1", "0:2");
        assertTrue(service.isMatchingArea(matrix, coveredArea), "Expected area to match but it did not.");
    }

    @Test
    void testIsMatchingAreaWithNonMatchingSymbols() {
        Symbol[][] matrix = createMatrixWithLenght3();
        WinningCheckerService service = new WinningCheckerService(null);
        List<String> coveredArea = Arrays.asList("0:0", "0:1", "0:2");
        assertFalse(service.isMatchingArea(matrix, coveredArea), EXPECTED_AREA_NOT_TO_MATCH_BUT_IT_DID);
    }
    
    @Test
    void testIsEmptyAreaWithNonMatchingSymbols() {
        Symbol[][] matrix = createMatrixWithLenght3();
        WinningCheckerService service = new WinningCheckerService(null);
        List<String> coveredArea = Arrays.asList();
        assertFalse(service.isMatchingArea(matrix, coveredArea), EXPECTED_AREA_NOT_TO_MATCH_BUT_IT_DID);
    }

	private Symbol[][] createMatrixWithLenght3() {
		Symbol[][] matrix = {
                {new Symbol(NAME_A, STANDARD, MULTIPLIER), new Symbol(NAME_B, STANDARD, 3), new Symbol("C", STANDARD, 2)},
                {new Symbol(NAME_B, STANDARD, 3), new Symbol("C", STANDARD, 2), new Symbol("D", STANDARD, 1)},
                {new Symbol("E", STANDARD, 4), new Symbol("F", STANDARD, 6), new Symbol("G", STANDARD, 7)}
        };
		return matrix;
	}
	
	private Symbol[][] createMatrixWithLenght2(Symbol symbolA) {
		Symbol[][] matrix = {
                {symbolA, symbolA, symbolA},
                {new Symbol(NAME_B, STANDARD, 3), new Symbol("C", STANDARD, 2), new Symbol("D", STANDARD, 1)},
                {new Symbol("E", STANDARD, 4), new Symbol("F", STANDARD, 6), new Symbol("G", STANDARD, 7)}
        };
		return matrix;
	}
}
