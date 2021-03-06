package com.Capgemini.IPLWorkshop;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPLAnalyserTest {

	private static final String IPL_MOSTRUN_PATH = "F:\\Capgemini workspace\\IPLWorkshop\\WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String IPL_MOSTWCKTS_PATH = "F:\\Capgemini workspace\\IPLWorkshop\\WP DP Data_02 IPL2019FactsheetMostWkts - WP DP Data_02 IPL2019FactsheetMostWkts.csv";

	@Test
	public void givenIPLFile_FindMaxAvgTest() throws IplAnalyzerException {
		Path pathname = Paths.get(IPL_MOSTRUN_PATH);
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(pathname, Paths.get(IPL_MOSTWCKTS_PATH));
		double maxAvg = iplLeagueAnalyser.topBattingAvg();
		Assert.assertEquals(83.2, maxAvg, 0.0);
	}

	@Test
	public void givenIPLFile_FindMaxStrikeRate() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		double maxStrikeRate = iplLeagueAnalyser.maxStrikingRates();
		Assert.assertEquals(333.33, maxStrikeRate, 0.0);
	}

	@Test
	public void playerWithMaximum6Test() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithMax6 = iplLeagueAnalyser.cricketerWithMax6();
		Assert.assertEquals("Andre Russell", playerWithMax6);
	}

	@Test
	public void playerWithMaximum4Test() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithMax4 = iplLeagueAnalyser.cricketerWithMax4();
		Assert.assertEquals("Shikhar Dhawan", playerWithMax4);
	}

	@Test
	public void playerWithBestStrikeRate6s4s() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithBestSR6s4s = iplLeagueAnalyser.cricketerWithMax6sand4sandSR();
		Assert.assertEquals("Andre Russell", playerWithBestSR6s4s);
	}

	@Test
	public void playerWithBestStrikeRate_Avg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithBestSR_Avg = iplLeagueAnalyser.cricketerWithBestSR_Avg();
		Assert.assertEquals("MS Dhoni", playerWithBestSR_Avg);
	}
	
	@Test
	public void playerWithBestRuns_Avg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithBestRun_Avg = iplLeagueAnalyser.playerWithMaxRuns_Avg();
		Assert.assertEquals("David Warner", playerWithBestRun_Avg);
	}
	
	@Test
	public void topBowlingAverages() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		double playerBowlingAvg = iplLeagueAnalyser.topBowlingAvg();
		Assert.assertEquals(166.0, playerBowlingAvg, 0.0);
	}
	
	@Test
	public void topStrikeRate_Bowlers() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		double maxStrikeRate = iplLeagueAnalyser.topStrikingRates_Bowling();
		Assert.assertEquals(8.66, maxStrikeRate, 0.0);
	}
	
	@Test
	public void topEconomyRate() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		double maxEconomyRate = iplLeagueAnalyser.topEconomyRate_Bowler();
		Assert.assertEquals(4.8, maxEconomyRate, 0.0);
	}
	
	@Test
	public void maxSR5w4w() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithMax4w5w = iplLeagueAnalyser.bestSrwith4wAnd5w();
		Assert.assertEquals("Kagiso Rabada", playerWithMax4w5w);
	}
	
	@Test
	public void maxSR_BowlingAvg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithMaxSR_Avg = iplLeagueAnalyser.bestBowlingAvgWithSR();
		Assert.assertEquals("Alzarri Joseph", playerWithMaxSR_Avg); 
	}
	
	@Test
	public void maxWickets_BowlingAvg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithWickets_Avg = iplLeagueAnalyser.maxWickets_maxBowlingAvg();
		Assert.assertEquals("Imran Tahir", playerWithWickets_Avg); 
	}
	
	@Test
	public void maxBowling_BattingAvg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String playerWithWickets_Avg = iplLeagueAnalyser.bestBowlingBattingAvg();
		Assert.assertEquals("Ben Cutting", playerWithWickets_Avg);
	}
	
	@Test
	public void allRounder() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String allRounder = iplLeagueAnalyser.allRounder();
		Assert.assertEquals("Ben Cutting", allRounder);
	}
	
	@Test
	public void maxHundred_Avg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String hund_battingAvg = iplLeagueAnalyser.maxHundred_BattingAvg();
		Assert.assertEquals("Harpreet Brar", hund_battingAvg);
	}
	
	@Test
	public void zeroHundred_Avg() throws IplAnalyzerException {
		IplLeagueAnalyser iplLeagueAnalyser = new IplLeagueAnalyser(Paths.get(IPL_MOSTRUN_PATH), Paths.get(IPL_MOSTWCKTS_PATH));
		String zerohund_battingAvg = iplLeagueAnalyser.zeroCenturies_maxAvg();
		Assert.assertEquals("Harpreet Brar", zerohund_battingAvg);
	}
}





