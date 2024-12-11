package com.scratch.game.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scratch.game.model.symbol.Symbol;

public class Scratch {
	
    @JsonProperty("rows")
    private int rows = 3;
    @JsonProperty("columns")
    private int columns = 3;
    @JsonProperty("symbols")
    private Map<String, Symbol> symbols;
    @JsonProperty("probabilities")
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    @Override
	public String toString() {
		return "Config [rows=" + rows + ", columns=" + columns + ", symbols=" + symbols + ", probabilities="
				+ probabilities + ", winCombinations=" + winCombinations + "]";
	}
}
