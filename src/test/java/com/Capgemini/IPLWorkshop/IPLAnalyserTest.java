package com.Capgemini.IPLWorkshop;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {

	private static final String IPL_MOSTRUN_PATH = "F:\\Capgemini workspace\\IPLWorkshop\\WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";

	@Test
	public void givenIPLFile_FindMaxAvgTest() throws IplAnalyzerException {
		Path pathname = Paths.get(IPL_MOSTRUN_PATH);
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(pathname);
		double maxAvg = iplLeagueAnalyser.topBattingAvg();
		Assert.assertEquals(83.2, maxAvg, 0.0);
	}

	@Test
	public void givenIPLFile_FindMaxStrikeRate() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH));
		double maxStrikeRate = iplLeagueAnalyser.maxStrikingRates();
		Assert.assertEquals(333.33, maxStrikeRate, 0.0);
	}

	@Test
	public void playerWithMaximum6Test() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH));
		String playerWithMax6 = iplLeagueAnalyser.cricketerWithMax6();
		Assert.assertEquals("Andre Russell", playerWithMax6);
	}

	@Test
	public void playerWithMaximum4Test() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH));
		String playerWithMax4 = iplLeagueAnalyser.cricketerWithMax4();
		Assert.assertEquals("Shikhar Dhawan", playerWithMax4);
	}

}
