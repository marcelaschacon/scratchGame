package com.scratch.game.model.symbol;

public class BonusSymbol extends Symbol {
	
    public BonusSymbol(String name, double rewardMultiplier, String impact) {
        super(name, "standard", impact, rewardMultiplier, 0);
    }
}
