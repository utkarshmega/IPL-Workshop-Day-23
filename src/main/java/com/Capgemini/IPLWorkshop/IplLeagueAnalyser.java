package com.Capgemini.IPLWorkshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplLeagueAnalyser {

	public Path iplMostRunPath;

	public IplLeagueAnalyser(Path iplMostRunPath) {

		this.iplMostRunPath = iplMostRunPath;
	}

	ArrayList<IplColumns> list;

	/**
	 * to create an ArrayList of all the data in the FactsSheetOfMostRuns and then
	 * using it in different functions
	 */
	public ArrayList<IplColumns> mostRuns() throws IplAnalyzerException {

		list = new ArrayList<>();

		Reader reader = null;
		try {
			reader = Files.newBufferedReader(iplMostRunPath);
			CsvToBeanBuilder<IplColumns> builder = new CsvToBeanBuilder<IplColumns>(reader);
			builder.withType(IplColumns.class);
			builder.withIgnoreLeadingWhiteSpace(true);

			CsvToBean<IplColumns> csvToBean = builder.build();

			Iterator<IplColumns> iplBattingitr = csvToBean.iterator();
			while (iplBattingitr.hasNext()) {

				IplColumns csvReader = iplBattingitr.next();
				list.add(csvReader);
			}
			return list;
		} catch (IOException E1) {
			throw new IplAnalyzerException("Invalid Path Provided", IplAnalyzerException.ExceptionType.INCORRECT_PATH);
		}

	}

	/**
	 * to find the player with maximum average scores and return the player name
	 */
	public double topBattingAvg() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		double maxAvgScore = list.stream().filter(x -> !x.average.equals("-")).map(x -> Double.parseDouble(x.average))
				.max(Double::compare).get();
		ArrayList<IplColumns> maxAvgPlayerList = (ArrayList<IplColumns>) list.stream()
				.filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
		System.out.println("Max Average Scores Player");
		for (IplColumns data : maxAvgPlayerList)
			System.out.println(data.player);
		return maxAvgScore;
	}

	/**
	 * to find the player with maximum strike rate and return the player name
	 */
	public double maxStrikingRates() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		double maxStrikingRate = list.stream().map(x -> x.strikeRate).max(Double::compare).get();
		ArrayList<IplColumns> maxStrikeRateList = (ArrayList<IplColumns>) list.stream()
				.filter(x -> x.strikeRate == maxStrikingRate).collect(Collectors.toList());
		System.out.println("Max Strike Rates Player");
		for (IplColumns data : maxStrikeRateList)
			System.out.println(data.player);
		return maxStrikingRate;
	}

	/**
	 * to create a list of maximum 6s scoring player in descending order and return
	 * the player name
	 */
	public String cricketerWithMax6() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		ArrayList<IplColumns> sortedMax6 = (ArrayList<IplColumns>) list.stream()
				.sorted((player1, player2) -> Integer.compare(player1.sixes, player2.sixes))
				.collect(Collectors.toList());
		Collections.reverse(sortedMax6);
		System.out.println("Player with maximum sixes is");
		System.out.println(sortedMax6.get(0).player + " with total number of sixes " + sortedMax6.get(0).sixes);
		return sortedMax6.get(0).player;
	}

	/**
	 * to create a list of maximum 4s scoring player in descending order and return
	 * the player name
	 */
	public String cricketerWithMax4() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		ArrayList<IplColumns> sortedMax4 = (ArrayList<IplColumns>) list.stream().sorted((player1, player2) -> {
			return player2.fours - player1.fours;
		}).collect(Collectors.toList());
		System.out.println("Players with maximum number of 4s is");
		System.out.println(sortedMax4.get(0).player + " with total number of fours " + sortedMax4.get(0).fours);
		return sortedMax4.get(0).player;

	}

	/**
	 * to create a list of player with maximum strike rate, no of 6s and no of 4s
	 */
	public String cricketerWithMax6sand4sandSR() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();

		double bestRate = list.stream().map(i -> i.strikeRate * 0.2 + i.sixes * 0.5 + i.fours * 0.3)
				.max(Double::compare).get();
		ArrayList<IplColumns> playersWtihMax6s4sSr = (ArrayList<IplColumns>) list.stream()
				.filter(i -> (i.strikeRate * 0.2 + i.sixes * 0.5 + i.fours * 0.3) == bestRate)
				.collect(Collectors.toList());
		System.out.println("Player with best strike rate and 6s and 4s");
		for (IplColumns data : playersWtihMax6s4sSr)
			System.out.println(data.player);
		return playersWtihMax6s4sSr.get(0).player;
	}

	/**
	 * to create a list of player with maximum averages and then with maximum Strike
	 * rate
	 */
	public String cricketerWithBestSR_Avg() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();

		double bestAvg = list.stream().map(i -> i.getAverage()).max(Double::compare).get();
		ArrayList<IplColumns> playerWithBestAvg = (ArrayList<IplColumns>) list.stream()
				.filter(i -> i.getAverage() == bestAvg).collect(Collectors.toList());

		double bestSR = playerWithBestAvg.stream().map(i -> i.strikeRate).max(Double::compare).get();
		ArrayList<IplColumns> playerWithBestSR_Avg = (ArrayList<IplColumns>) playerWithBestAvg.stream()
				.filter(i -> i.strikeRate == bestSR).collect(Collectors.toList());

		System.out.println("Player With Best Strike Rate and Average");
		for (IplColumns data : playerWithBestSR_Avg)
			System.out.println(data.player);
		return playerWithBestSR_Avg.get(0).player;
	}

	/**
	 * to create a list of player with maximum runs and best average scores
	 */
	public String playerWithMaxRuns_Avg() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();

		int maxRuns = list.stream().map(i -> i.runs).max(Integer::compare).get();
		ArrayList<IplColumns> playerMaxRunList = (ArrayList<IplColumns>) list.stream().filter(i -> i.runs == maxRuns)
				.collect(Collectors.toList());

		double maxAvg = playerMaxRunList.stream().map(i -> i.getAverage()).max(Double::compare).get();
		ArrayList<IplColumns> playerWithMaxRun_Avg = (ArrayList<IplColumns>) playerMaxRunList.stream()
				.filter(i -> i.getAverage() == maxAvg).collect(Collectors.toList());
		System.out.println("Player with max Runs and Best average");
		for (IplColumns data : playerWithMaxRun_Avg)
			System.out.println(data.player);
		return playerWithMaxRun_Avg.get(0).player;
	}

}
