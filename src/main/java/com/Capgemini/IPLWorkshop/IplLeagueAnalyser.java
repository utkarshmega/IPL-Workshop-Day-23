package com.Capgemini.IPLWorkshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.security.auth.x500.X500Principal;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplLeagueAnalyser {

	public Path iplMostRunPath;

	public IplLeagueAnalyser(Path iplMostRunPath) {

		this.iplMostRunPath = iplMostRunPath;
	}

	public ArrayList<IplColumns> mostRuns() throws IplAnalyzerException {

		ArrayList<IplColumns> list = new ArrayList<>();

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

	public double maxStrikingRates() throws IplAnalyzerException {
		ArrayList<IplColumns> list = mostRuns();
		double maxStrikingRate = list.stream().map(x -> Double.parseDouble(x.strikeRate)).max(Double::compare).get();
		ArrayList<IplColumns> maxStrikeRateList = (ArrayList<IplColumns>) list.stream()
				.filter(x -> x.strikeRate.equals(Double.toString(maxStrikingRate))).collect(Collectors.toList());
		System.out.println("Max Strike Rates Player");
		for(IplColumns data : maxStrikeRateList)
			System.out.println(data.player);
		return maxStrikingRate;
	}

}
