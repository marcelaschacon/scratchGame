package com.scratch.game;

import com.scratch.game.facade.ScratchFacade;

public class ScratchGame {
	
	private static final String BETTING_AMOUNT = "--betting-amount";
	private static final String CONFIG = "--config";

	public static void main(String[] input) {
		
        String configFilePath = null;
        double bettingAmount = 0.0;
        for (int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case CONFIG:
                    if (i + 1 < input.length) {
                        configFilePath = input[i + 1];
                    } else {
                        System.err.println("Missing value for --config");
                        return;
                    }
                    i++;
                    break;
                case BETTING_AMOUNT:
                    if (i + 1 < input.length) {
                        try {
                            bettingAmount = Double.parseDouble(input[i + 1]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid betting amount");
                            return;
                        }
                    } else {
                        System.err.println("Missing value for --betting-amount");
                        return;
                    }
                    i++;
                    break;
                default:
                    System.err.println("Unknown argument " + input[i]);
                    return;
            }
        }
        if (configFilePath == null) {
            System.err.println("--config is required");
            return;
        }
        if (bettingAmount <= 0) {
            System.err.println("--betting-amount must be greater than 0");
            return;
        }
        ScratchFacade.playGame(configFilePath, bettingAmount);
	}
}
