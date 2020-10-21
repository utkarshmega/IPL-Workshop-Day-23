package com.Capgemini.IPLWorkshop;

import com.opencsv.bean.CsvBindByName;

public class IplColumns {

	@CsvBindByName(column = "POS")
	public int position;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "Mat")
	public int matches;

	@CsvBindByName(column = "Inns")
	public int innings;

	@CsvBindByName(column = "NO")
	public int notOut;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "HS")
	public String highestScore;

	@CsvBindByName(column = "Avg")
	public String average;

	@CsvBindByName(column = "BF")
	public int ballsFaced;

	@CsvBindByName(column = "SR")
	public double strikeRate;

	@CsvBindByName(column = "100")
	public int hundreds;

	@CsvBindByName(column = "50")
	public int fifties;

	@CsvBindByName(column = "4s")
	public int fours;

	@CsvBindByName(column = "6s")
	public int sixes;
}
