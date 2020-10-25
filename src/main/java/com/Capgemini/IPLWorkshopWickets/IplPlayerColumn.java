package com.Capgemini.IPLWorkshopWickets;

public class IplPlayerColumn {
	
	public String playerName;
	String battingAvg;
	String bowlingAvg;
	int runs;
	int wickets;
	
	public IplPlayerColumn(String playerName, String battingAvg, String bowlingAvg, int runs, int wickets){
		this.playerName = playerName;
		this.battingAvg = battingAvg;
		this.bowlingAvg = bowlingAvg;
		this.runs = runs;
		this.wickets = wickets;
	}
	
	public double getBattingAvg() {
		if(battingAvg.equals("-"))
			return 0.0;
		return Double.parseDouble(battingAvg);
	}
	
	public double getBowlingAvg() {
		if(bowlingAvg.equals("-"))
			return 0.0;
		return Double.parseDouble(bowlingAvg);
	}

}
