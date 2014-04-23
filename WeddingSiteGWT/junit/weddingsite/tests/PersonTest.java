package weddingsite.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import weddingsite.model.Person;

public class PersonTest {
	
	Person person;

	@Before
	public void setUp() throws Exception {
		
		person = new Person();
		
		person.setId(6);
		person.setName("Bob Bob");
		person.setTableID(4);
	}

	

	@Test
	public final void testGetId() {
		assertEquals(6, person.getId());
	}

	@Test
	public final void testSetId() {
		person.setId(3);
		
		assertEquals(3, person.getId());
	}

	@Test
	public final void testGetTableID() {
		assertEquals(4, person.getTableID());
	}

	@Test
	public final void testSetTableID() {
		person.setTableID(5);
		
		assertEquals(5, person.getTableID());
	}

	@Test
	public final void testGetName() {
		assertEquals("Bob Bob", person.getName());
	}

	@Test
	public final void testSetName() {
		person.setName("Bobie Bob");
		
		assertEquals("Bobbie Bob", person.getName());
	}

}
