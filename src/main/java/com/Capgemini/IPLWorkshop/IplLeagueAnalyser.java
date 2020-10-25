package com.Capgemini.IPLWorkshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.Capgemini.IPLWorkshopWickets.IplPlayerColumn;
import com.Capgemini.IPLWorkshopWickets.IplWicketsColumns;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplLeagueAnalyser {

	public Path iplMostRunPath;
	public Path mostWicketsPath;

	public IplLeagueAnalyser(Path iplMostRunPath, Path mostWicketsPath) {

		this.iplMostRunPath = iplMostRunPath;
		this.mostWicketsPath = mostWicketsPath;
	}

	ArrayList<IplColumns> list;
	ArrayList<IplWicketsColumns> listWicketData;

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

	public ArrayList<IplWicketsColumns> mostWickets() throws IplAnalyzerException {

		listWicketData = new ArrayList<>();
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(mostWicketsPath);
			CsvToBeanBuilder<IplWicketsColumns> builder = new CsvToBeanBuilder<IplWicketsColumns>(reader);
			builder.withType(IplWicketsColumns.class);
			builder.withIgnoreLeadingWhiteSpace(true);

			CsvToBean<IplWicketsColumns> csvToBean = builder.build();

			Iterator<IplWicketsColumns> iplBattingitr = csvToBean.iterator();
			while (iplBattingitr.hasNext()) {
				IplWicketsColumns csvReader = iplBattingitr.next();
				listWicketData.add(csvReader);
			}
			return listWicketData;
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

	/**
	 * to find the player with top bowling average
	 */
	public double topBowlingAvg() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		double maxAvgScoreWicket = listWickets.stream().filter(x -> !x.average.equals("-"))
				.map(x -> Double.parseDouble(x.average)).max(Double::compare).get();
		ArrayList<IplWicketsColumns> maxAvgWicketPlayerList = (ArrayList<IplWicketsColumns>) listWickets.stream()
				.sorted((Player1, Player2) -> {
					return (int) (Player2.getAverage() - Player1.getAverage());
				}).collect(Collectors.toList());
		System.out.println("Player with Max Top Bowling Average Scores");
		int i = 0;
		for (IplWicketsColumns data : maxAvgWicketPlayerList) {
			System.out.println(data.player + "with top bowling avg " + data.average);
			if (++i == 5)
				break;
		}
		return maxAvgScoreWicket;
	}

	/**
	 * methods to find the top strike rates for the bowlers
	 */
	public double topStrikingRates_Bowling() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		Comparator<IplWicketsColumns> compare = Comparator.comparing(IplWicketsColumns::getStrikeRate);
		ArrayList<IplWicketsColumns> topSrPlayer = (ArrayList<IplWicketsColumns>) listWickets.stream().sorted(compare)
				.collect(Collectors.toList());
		System.out.println("Players with top bowling striking rate :");
		int i = 0;
		for (IplWicketsColumns data : topSrPlayer) {
			System.out.println(data.player + "with striking rate of " + data.getStrikeRate());
			if (++i == 5)
				break;
		}
		return topSrPlayer.get(0).getStrikeRate();
	}

	/**
	 * added method to find the top economy rate of the player
	 */
	public double topEconomyRate_Bowler() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		Comparator<IplWicketsColumns> compare = Comparator.comparing(IplWicketsColumns::getEconomy);
		ArrayList<IplWicketsColumns> topEconomyRateList = (ArrayList<IplWicketsColumns>) listWickets.stream()
				.sorted(compare).collect(Collectors.toList());
		System.out.println("Player with best economy Rate: " + topEconomyRateList.get(0).player
				+ " with economy rate of " + topEconomyRateList.get(0).economy);
		return topEconomyRateList.get(0).economy;
	}

	/**
	 * method to find the player with max strike rate with 5 wickets and 4 wickets
	 */
	public String bestSrwith4wAnd5w() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		int maxWickets = listWickets.stream().map(i -> i.four_Wickets + i.five_Wickets).max(Integer::compare).get();
		ArrayList<IplWicketsColumns> maxWcktList = (ArrayList<IplWicketsColumns>) listWickets.stream()
				.filter(i -> (i.five_Wickets + i.four_Wickets) == maxWickets).collect(Collectors.toList());
		Comparator<IplWicketsColumns> compare = Comparator.comparing(IplWicketsColumns::getStrikeRate);
		ArrayList<IplWicketsColumns> maxWcktListSr = (ArrayList<IplWicketsColumns>) maxWcktList.stream().sorted(compare)
				.collect(Collectors.toList());
		System.out.println("Player with max Strike rate with 5w and 4w is ");
		System.out.println(maxWcktListSr.get(0).player);
		return maxWcktListSr.get(0).player;
	}

	/**
	 * method to find the player with max strike rate and bowling average
	 */
	public String bestBowlingAvgWithSR() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		double maxStrikingRate = listWickets.stream().map(i -> i.getStrikeRate()).min(Double::compare).get();
		ArrayList<IplWicketsColumns> playerMaxSR = (ArrayList<IplWicketsColumns>) listWickets.stream()
				.filter(i -> i.getStrikeRate() == maxStrikingRate).collect(Collectors.toList());
		Comparator<IplWicketsColumns> compare = Comparator.comparing(IplWicketsColumns::getAverage);
		ArrayList<IplWicketsColumns> maxSR_Avg_bowlers = (ArrayList<IplWicketsColumns>) playerMaxSR.stream()
				.sorted(compare).collect(Collectors.toList());
		System.out.println("Player with max striking rate and bowling average is ");
		System.out.println(maxSR_Avg_bowlers.get(0).player);
		return maxSR_Avg_bowlers.get(0).player;
	}

	/**
	 * method to find the player with max number of wickets and bowling average
	 */
	public String maxWickets_maxBowlingAvg() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		int maxWIckets = listWickets.stream().map(i -> i.wickets).max(Integer::compare).get();
		ArrayList<IplWicketsColumns> maxWicketList = (ArrayList<IplWicketsColumns>) listWickets.stream()
				.filter(i -> i.wickets == maxWIckets).collect(Collectors.toList());
		Comparator<IplWicketsColumns> compare = Comparator.comparing(IplWicketsColumns::getAverage);
		ArrayList<IplWicketsColumns> maxWicket_Avg = (ArrayList<IplWicketsColumns>) maxWicketList.stream()
				.sorted(compare).collect(Collectors.toList());
		Collections.reverse(maxWicket_Avg);
		System.out.println("Player with max number of wickets and best average is");
		System.out.println(maxWicket_Avg.get(0).player);
		return maxWicket_Avg.get(0).player;
	}

	/**
	 * Method to create the list of players which bowl and bat both
	 */
	public ArrayList<IplPlayerColumn> findNewListForUCs() throws IplAnalyzerException {
		ArrayList<IplWicketsColumns> listWickets = mostWickets();
		ArrayList<IplColumns> list = mostRuns();
		ArrayList<IplPlayerColumn> newPlayerList = new ArrayList<>();
		for (int i = 0; i < listWickets.size(); i++) {
			if (listWickets.get(i).player.equals(list.get(i).player)) {
				newPlayerList.add(new IplPlayerColumn(list.get(i).player, list.get(i).average,
						listWickets.get(i).average, list.get(i).runs, listWickets.get(i).wickets));
			}
		}
		return newPlayerList;
	}

	/**
	 * Method to find the best to player name with batting nd bowling average
	 */
	public String bestBowlingBattingAvg() throws IplAnalyzerException {
		ArrayList<IplPlayerColumn> newPlayerList = findNewListForUCs();
		double bestScore = newPlayerList.stream().map(i -> i.getBattingAvg() + i.getBowlingAvg()).max(Double::compare)
				.get();
		ArrayList<IplPlayerColumn> bestBatting_Bowling_avg = (ArrayList<IplPlayerColumn>) newPlayerList.stream()
				.filter(i -> (i.getBattingAvg() + i.getBowlingAvg()) == bestScore).collect(Collectors.toList());
		System.out.println("Player with best average batting and bowling score is");
		System.out.println(bestBatting_Bowling_avg.get(0).playerName);
		return bestBatting_Bowling_avg.get(0).playerName;
	}

	/**
	 * to find the all rounder of the series
	 */
	public String allRounder() throws IplAnalyzerException {
		ArrayList<IplPlayerColumn> newPlayerList = findNewListForUCs();
		int mostRunsAndWickets = newPlayerList.stream().map(i -> i.runs + i.wickets).max(Integer::compare).get();
		ArrayList<IplPlayerColumn> allRounderlist = (ArrayList<IplPlayerColumn>) newPlayerList.stream()
				.filter(i -> (i.runs + i.wickets) == mostRunsAndWickets).collect(Collectors.toList());
		System.out.println("All rounder player of the series is");
		System.out.println(allRounderlist.get(0).playerName);
		return allRounderlist.get(0).playerName;
	}

	public String maxHundred_BattingAvg() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		ArrayList<IplColumns> maxHund = (ArrayList<IplColumns>) list.stream()
				.sorted((player1, player2) -> player2.hundreds - player1.hundreds).collect(Collectors.toList());
		Comparator<IplColumns> compare = Comparator.comparing(IplColumns::getAverage);
		ArrayList<IplColumns> maxHund_batAvg = (ArrayList<IplColumns>) maxHund.stream()
				.sorted(compare).collect(Collectors.toList());
		System.out.println("Player with max hundred and best striking rate is");
		System.out.println(maxHund_batAvg.get(0).player);
		return maxHund_batAvg.get(0).player;
	}

}
