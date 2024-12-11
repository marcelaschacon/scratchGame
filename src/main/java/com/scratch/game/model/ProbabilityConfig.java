package com.scratch.game.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProbabilityConfig {
	
    @JsonProperty("column")
    private int column;
    @JsonProperty("row")
    private int row;
    @JsonProperty("symbols")
    private Map<String, Double> symbols;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map<String, Double> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Double> symbols) {
        this.symbols = symbols;
    }
    
    @Override
  	public String toString() {
  		return "ProbabilityConfig [column=" + column + ", row=" + row + ", symbols=" + symbols + "]";
  	}
}
