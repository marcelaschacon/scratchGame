package com.scratch.game.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.scratch.game.model.symbol.Symbol;
import com.scratch.game.serializer.MatrixSerializer;

public class Response {
	
	@JsonSerialize(using = MatrixSerializer.class)
    private Symbol[][] matrix;
	
	private double reward;
	
	private String appliedWinningCombinations;
	
	private String appliedBonusSymbol;
	
	public Response(Symbol[][] matrix, double reward, String appliedWinningCombinations, String appliedBonusSymbol) {
		super();
		this.matrix = matrix;
		this.reward = reward;
		this.appliedWinningCombinations = appliedWinningCombinations;
		this.appliedBonusSymbol = appliedBonusSymbol;
	}
	
	public Symbol[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Symbol[][] matrix) {
		this.matrix = matrix;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

	public String getAppliedWinningCombinations() {
		return appliedWinningCombinations;
	}

	public void setAppliedWinningCombinations(String appliedWinningCombinations) {
		this.appliedWinningCombinations = appliedWinningCombinations;
	}

	public String getAppliedBonusSymbol() {
		return appliedBonusSymbol;
	}

	public void setAppliedBonusSymbol(String appliedBonusSymbol) {
		this.appliedBonusSymbol = appliedBonusSymbol;
	}
}
