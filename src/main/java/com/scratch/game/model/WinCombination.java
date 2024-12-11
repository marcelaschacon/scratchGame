package com.scratch.game.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WinCombination {
	
	@JsonProperty("reward_multiplier")
	public double rewardMultiplier;
	@JsonProperty("when")
        public String when;
 	@JsonProperty("count")
        public int count;
        @JsonProperty("group")
        public String group;
        @JsonProperty("covered_areas")
        public List<List<String>> coveredAreas;
        
        public double getRewardMultiplier() {
		return rewardMultiplier;
	}

	public void setRewardMultiplier(double rewardMultiplier) {
		this.rewardMultiplier = rewardMultiplier;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<List<String>> getCoveredAreas() {
		return coveredAreas;
	}

	public void setCoveredAreas(List<List<String>> coveredAreas) {
		this.coveredAreas = coveredAreas;
	}

		@Override
        public String toString() {
        	return "WinCombination [reward_multiplier=" + rewardMultiplier + ", when=" + when + ", count=" + count
        			+ ", group=" + group + ", covered_areas=" + coveredAreas + "]";
        }
}
