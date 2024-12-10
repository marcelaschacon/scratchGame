package com.scratch.game.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Probabilities {
	
	@JsonProperty("standard_symbols")
    private List<ProbabilityConfig> standardSymbols;

    @JsonProperty("bonus_symbols")
    private BonusSymbols bonusSymbols;
    
    public Probabilities() {
    }
    
    public Probabilities(List<ProbabilityConfig> standardSymbols, BonusSymbols bonusSymbols) {
    	super();
        this.standardSymbols = standardSymbols;
        this.bonusSymbols = bonusSymbols;
    }

    public List<ProbabilityConfig> getStandardSymbols() {
        return standardSymbols;
    }

    public void setStandardSymbols(List<ProbabilityConfig> standardSymbols) {
        this.standardSymbols = standardSymbols;
    }

    public BonusSymbols getBonusSymbols() {
        return bonusSymbols;
    }

    public void setBonusSymbols(BonusSymbols bonusSymbols) {
        this.bonusSymbols = bonusSymbols;
    }

    public static class BonusSymbols {
        private Map<String, Double> symbols;

        public Map<String, Double> getSymbols() {
            return symbols;
        }

        public void setSymbols(Map<String, Double> symbols) {
            this.symbols = symbols;
        }
    }
    
    @Override
	public String toString() {
		return "Probabilities [standardSymbols=" + standardSymbols + ", bonusSymbols=" + bonusSymbols + "]";
	}
}
