package com.Capgemini.IPLWorkshopWickets;

import com.opencsv.bean.CsvBindByName;

public class IplWicketsColumns {

	@CsvBindByName(column = "POS")
	public String position;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "Mat")
	public int matches;

	@CsvBindByName(column = "Inns")
	public int innings;

	@CsvBindByName(column = "Ov")
	public double over;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "Wkts")
	public int wickets;

	@CsvBindByName(column = "BBI")
	public int bestBowling;

	@CsvBindByName(column = "Avg")
	public String average;

	@CsvBindByName(column = "Econ")
	public double economy;

	@CsvBindByName(column = "SR")
	public String strikeRate;

	@CsvBindByName(column = "4w")
	public int four_Wickets;

	@CsvBindByName(column = "5w")
	public int five_Wickets;

	public double getAverage() {
		if (average.equals("-"))
			return 0.0;
		return Double.parseDouble(average);
	}

	public double getStrikeRate() {
		if (strikeRate.equals("-"))
			return 100.0;
		return Double.parseDouble(strikeRate);
	}

	public double getEconomy() {
		return economy;
	}

}
