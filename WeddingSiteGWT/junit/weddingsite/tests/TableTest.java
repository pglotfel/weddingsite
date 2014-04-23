package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.model.Table;

public class TableTest {
	
	Table table;

	@Before
	public void setUp() throws Exception {
		table = new Table();
		
		table.setID(4);
		table.setNumSeats(8);
		table.setSeatingChartID(2);
	}



	@Test
	public final void testGetID() {
		assertEquals(4, table.getID());
	}

	@Test
	public final void testSetID() {
		table.setID(56);
		
		assertEquals(56, table.getID());
	}

	@Test
	public final void testGetSeatingChartID() {
		assertEquals(2, table.getSeatingChartID());
	}

	@Test
	public final void testSetSeatingChartID() {
		table.setSeatingChartID(1);
		
		assertEquals(1, table.getSeatingChartID());
	}
	
	@Test
	public final void testGetNumSeats() {
		assertEquals(8,  table.getNumSeats());
	}
	
	@Test
	public final void testSetNumSeats() {
		table.setNumSeats(12);
		
		assertEquals(12, table.getNumSeats());
	}

}
