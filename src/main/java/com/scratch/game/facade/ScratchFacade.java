package com.scratch.game.facade;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.scratch.game.ScratchGame;
import com.scratch.game.model.Scratch;
import com.scratch.game.model.response.Response;
import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.service.MatrixGeneratorService;
import com.scratch.game.service.RewardCalculatorService;
import com.scratch.game.service.WinningCheckerService;
import com.scratch.game.util.SymbolUtil;

public class ScratchFacade {

	private static final String LOST_GAME = "LOST GAME";

	public static void playGame(String configFilePath, double bettingAmount) {
		try (InputStream inputStream = ScratchGame.class.getClassLoader().getResourceAsStream(configFilePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Config file not found in resources directory");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Scratch scratch = objectMapper.readValue(inputStream, Scratch.class);
         	Map<String, Symbol> symbols = SymbolUtil.initializeSymbols(scratch);
         	scratch.setSymbols(symbols);
         	MatrixGeneratorService generator = new MatrixGeneratorService();
         	Symbol[][] matrix = generator.generateMatrix(scratch);
         	WinningCheckerService winningChecker = new WinningCheckerService(scratch);
            if(winningChecker.checkWin(matrix)) {
            	RewardCalculatorService calculator = new RewardCalculatorService();
            	double reward = calculator.calculateReward(matrix, scratch, bettingAmount);
            	if (!(reward > 0)) {
            		System.out.println(LOST_GAME);
            	} else {
            		double applyBonus = calculator.applyBonus(matrix, bettingAmount);
            		reward = reward + applyBonus;
            		StringBuilder stringBuilder = new StringBuilder();
            		stringBuilder.append("+");
            		stringBuilder.append(String.valueOf(applyBonus));
            		printResult(matrix, reward, stringBuilder);
            	}
            } else {
        	  System.out.println(LOST_GAME);
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
	}

	private static void printResult(Symbol[][] matrix, double reward,
			StringBuilder stringBuilder) throws JsonProcessingException {
		Response response = new Response(matrix, reward, "winning", stringBuilder.toString());
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		ObjectWriter ow = jsonMapper.writer().withDefaultPrettyPrinter();
		System.out.println(ow.writeValueAsString(response));
	}
	
	public static String formatMatrixAsJson(Symbol[][] matrix) {
		StringBuilder result = new StringBuilder();
	    result.append("\"matrix\": [");
	    for (int i = 0; i < matrix.length; i++) {
	        result.append("[");
	        for (int j = 0; j < matrix[i].length; j++) {
	            String symbolName = matrix[i][j].getName();
	            result.append("\"").append(symbolName).append("\"");
	            if (j < matrix[i].length - 1) {
	                result.append(", ");
	            }
	        }
	        result.append("]");
	        if (i < matrix.length - 1) {
	            result.append(", ");
	        }
	    }
	    result.append("]");
	    return result.toString();
	}
}
