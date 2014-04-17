package test.java;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ehud.Radio;
import ehud.comparators.AxisSorter;


public class AxisSorterTest {
	
	private Radio r1;
	Radio r2;
	AxisSorter sorter;
	
	@Before
	public void setUp(){
		r1 = new Radio();
		r2 = new Radio();
		sorter = new AxisSorter();
	}
	
	/**
	 * Test when both radios have same x cord set to 0
	 */
	@Test
	public void testComparatorWhenXEquals(){
		r1.setxCoord(0);
		r1.setyCoord(5);
		r2.setxCoord(0);
		r2.setyCoord(6);
		int ret = sorter.compare(r1, r2);
		assertEquals(0,ret);
	}
	
	/**
	 * Test when both radios have same y coordinate
	 */
	@Test
	public void testComparatorWhenYEquals(){
		r1.setxCoord(2);
		r1.setyCoord(5);
		r2.setxCoord(4);
		r2.setyCoord(5);
		int ret = sorter.compare(r1, r2);
		assertEquals(0,ret);
	}
	
	/**
	 * Test when both radios have non zero coordinates
	 */
	@Test
	public void testComparatorWhenNonZero(){
		r1.setxCoord(7);
		r1.setyCoord(7);
		r2.setxCoord(2);
		r2.setyCoord(5);
		int ret = sorter.compare(r1, r2);
		assertEquals(1,ret);
	}
	
}
