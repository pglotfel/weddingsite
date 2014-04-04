package weddingsite.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import weddingsite.shared.SeatingChart;

public class SeatingChartTest {

	private SeatingChart seatingChart;
	
	@Before
	public void setUp() throws Exception {
		seatingChart = new SeatingChart();
		seatingChart.setID(42);
		seatingChart.setAccountID(42);
	}

	@Test
	public final void testGetID() {
		assertTrue(seatingChart.getID() == 42);
	}

	@Test
	public final void testSetID() {
		seatingChart.setID(7);
		assertTrue(seatingChart.getID() == 7);
	}

	@Test
	public final void testGetAccountID() {
		assertTrue(seatingChart.getID() == 42);
	}

	@Test
	public final void testSetAccountID() {
		seatingChart.setAccountID(7);
		assertTrue(seatingChart.getAccountID() == 7);
	}

}
