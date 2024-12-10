package com.scratch.game.service;

import static org.junit.jupiter.api.Assertions.*;

import com.scratch.game.model.Probabilities;
import com.scratch.game.model.Probabilities.BonusSymbols;
import com.scratch.game.model.ProbabilityConfig;
import com.scratch.game.model.Scratch;
import com.scratch.game.model.symbol.Symbol;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MatrixGeneratorServiceTest {

    private static final String C = "C";
	private static final String B = "B";
	private static final String A = "A";
	private static final String _500 = "+500";
	private static final String _1000 = "+1000";
	private static final String _10X = "10x";
	private final MatrixGeneratorService matrixGeneratorService = new MatrixGeneratorService();
    
	Scratch scratch = new Scratch();
	
    @Test
    void testGenerateMatrixWithStandardAndBonusSymbolsIsNotNull() {
    	scratch = mapScratch();
        Symbol[][] matrix = matrixGeneratorService.generateMatrix(scratch);
        assertNotNull(matrix);
    }

    @Test
    void testGenerateMatrixWithStandardAndBonusSymbolsLenghtMatches() {
    	scratch = mapScratch();
        Symbol[][] matrix = matrixGeneratorService.generateMatrix(scratch);
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);
    }
    
    @Test
    void testGenerateMatrixWithStandardAndBonusSymbolsNameMatches() {
    	Scratch scratch = mapScratch();
        Symbol[][] matrix = matrixGeneratorService.generateMatrix(scratch);
        List<String> validSymbols = Arrays.asList(A, B, C, _10X, _1000);
        for (Symbol[] row : matrix) {
            for (Symbol symbol : row) {
                assertNotNull(symbol);
                assertTrue(validSymbols.contains(symbol.getName()), "Unexpected symbol: " + symbol.getName());
            }
        }
    }

    @Test
    void testGetRandomStandardSymbolIsNotNull() {
        ProbabilityConfig standardConfig = new ProbabilityConfig();
        mapStandartSymbols(standardConfig);
        List<ProbabilityConfig> standardConfigs = Collections.singletonList(standardConfig);
        Symbol result = matrixGeneratorService.getRandomStandardSymbol(standardConfigs);
        assertNotNull(result);
    }
    
    @Test
    void testGetRandomStandardSymbolNameMatches() {
        ProbabilityConfig standardConfig = new ProbabilityConfig();
        Map<String, Double> standardSymbolsMap = mapStandartSymbols(standardConfig);
        List<ProbabilityConfig> standardConfigs = Collections.singletonList(standardConfig);
        Symbol result = matrixGeneratorService.getRandomStandardSymbol(standardConfigs);
        assertTrue(standardSymbolsMap.containsKey(result.getName()));
    }

    @Test
    void testGetRandomBonusSymbolIsNotNull() {
        BonusSymbols bonusSymbols = new BonusSymbols();
        mapBonusSymbols(bonusSymbols);
        Symbol result = matrixGeneratorService.getRandomBonusSymbol(bonusSymbols);
        assertNotNull(result);
    }
    
    @Test
    void testGetRandomBonusSymbolNameMatches() {
        BonusSymbols bonusSymbols = new BonusSymbols();
        Map<String, Double> bonusSymbolsMap = mapBonusSymbols(bonusSymbols);
        Symbol result = matrixGeneratorService.getRandomBonusSymbol(bonusSymbols);
        assertTrue(bonusSymbolsMap.containsKey(result.getName()), "Unexpected symbol: " + result.getName());
    }

	private Map<String, Double> mapBonusSymbols(BonusSymbols bonusSymbols) {
		Map<String, Double> bonusSymbolsMap = new HashMap<>();
        bonusSymbolsMap.put(_10X, 1.0);
        bonusSymbolsMap.put(_1000, 2.0);
        bonusSymbolsMap.put(_500, 2.0);
        bonusSymbolsMap.put("AS", 4.0);
        bonusSymbols.setSymbols(bonusSymbolsMap);
		return bonusSymbolsMap;
	}
	
	private Map<String, Double> mapStandartSymbols(ProbabilityConfig standardConfig) {
		Map<String, Double> standardSymbolsMap = new HashMap<>();
        standardSymbolsMap.put(A, 5.0);
        standardSymbolsMap.put(B, 3.0);
        standardSymbolsMap.put(C, 2.0);
        standardConfig.setSymbols(standardSymbolsMap);
		return standardSymbolsMap;
	}
    
    private Scratch mapScratch() {
    	Scratch scratch = new Scratch();
    	scratch.setRows(3);
    	scratch.setColumns(3);
    	ProbabilityConfig standardConfig = new ProbabilityConfig();
    	mapStandartSymbols(standardConfig);
    	BonusSymbols bonusSymbols = new BonusSymbols();
        Map<String, Double> bonusSymbolsMap = new HashMap<>();
        bonusSymbolsMap.put(_10X, 1.0);
        bonusSymbolsMap.put(_1000, 2.0);
        bonusSymbolsMap.put(_500, 2.0);
        bonusSymbols.setSymbols(bonusSymbolsMap);
    	Probabilities probabilities = new Probabilities();
    	probabilities.setStandardSymbols(Collections.singletonList(standardConfig));
    	probabilities.setBonusSymbols(bonusSymbols);
    	scratch.setProbabilities(probabilities);
    	return scratch;
    }
}
