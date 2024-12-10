package com.scratch.game.model.symbol;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol {
	
	private String name;
	@JsonProperty("reward_multiplier")
    private double rewardMultiplier;
	@JsonProperty("type")
    private String type;
	@JsonProperty("impact")
    private String impact; 
	@JsonProperty("extra")
    private int extra;    
	
	public Symbol() {
	}

    public Symbol(String name, String type, String impact, double rewardMultiplier, int extra) {
		super();
		this.name = name;
		this.type = type;
		this.rewardMultiplier = rewardMultiplier;
		this.impact = impact;
		this.extra = extra;
	}
    
    public Symbol(String name, String type, double rewardMultiplier) {
		super();
		this.name = name;
		this.rewardMultiplier = rewardMultiplier;
		this.type = type;
	}
    
    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }


	public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "[" + name + ",";
	}
}
