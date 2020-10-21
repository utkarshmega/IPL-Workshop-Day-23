package com.Capgemini.IPLWorkshop;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class IPLAnaylserTest {

	private static final String IPL_FILE_PATH = "F:\\Capgemini workspace\\IPLWorkshop\\WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	
	@Test
	public void givenILFile() {
		Path pathname = Paths.get(IPL_FILE_PATH);
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser();
		iplLeagueAnalyser.countMostRuns(IPL_FILE_PATH);
	}
}
