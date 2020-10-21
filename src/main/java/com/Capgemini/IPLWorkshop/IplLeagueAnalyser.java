package com.Capgemini.IPLWorkshop;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplLeagueAnalyser {

	public Path iplMostRunPath;

	public IplLeagueAnalyser(Path iplMostRunPath) {

		this.iplMostRunPath = iplMostRunPath;
	}

	public double topBattingAvg() throws IplAnalyzerException {

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
			double maxAvgScore = list.stream().filter(x -> !x.average.equals("-"))
					.map(x -> Double.parseDouble(x.average)).max(Double::compare).get();
			ArrayList<IplColumns> maxAvgPlayerList = (ArrayList<IplColumns>) list.stream()
					.filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
			for (IplColumns data : maxAvgPlayerList)
				System.out.println(data.player);
			return maxAvgScore;
		} catch (IOException E1) {
			throw new IplAnalyzerException("Invalid Path Provided", IplAnalyzerException.ExceptionType.INCORRECT_PATH);
		}

	}

}
