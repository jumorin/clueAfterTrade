//John Aspinwall
//Zachary Zembower

package clueTests;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;


public class PathTest {

	Board board;
	@Before
	public void init() throws FileNotFoundException, BadConfigFormatException {
		board = new Board("ClueMap.csv","legend.txt");
		board.loadConfigFiles();
		board.calcAdjacencies();
	}
	
	@Test
	public void testAdjacency0()
	{
		LinkedList testList = board.getAdjList(0);
		Assert.assertEquals(0, testList.size());
	}	
	@Test
	public void testAdjacency17()
	{
		LinkedList testList = board.getAdjList(17);
		Assert.assertTrue(testList.contains(16));
		Assert.assertTrue(testList.contains(42));
		Assert.assertEquals(2, testList.size());
	}	
	@Test
	public void testAdjacency57()
	{
		LinkedList testList = board.getAdjList(57);
		Assert.assertTrue(testList.contains(32));
		Assert.assertTrue(testList.contains(58));
		Assert.assertTrue(testList.contains(82));
		Assert.assertEquals(3, testList.size());
	}	
	@Test
	public void testAdjacency88()
	{
		LinkedList testList = board.getAdjList(88);
		Assert.assertTrue(testList.contains(63));
		Assert.assertTrue(testList.contains(87));
		Assert.assertTrue(testList.contains(89));
		Assert.assertTrue(testList.contains(113));
		Assert.assertEquals(4, testList.size());
	}	
	@Test
	public void testAdjacency212()
	{
		LinkedList testList = board.getAdjList(212);
		Assert.assertEquals(0, testList.size());
	}	
	@Test
	public void testAdjacency490()
	{
		LinkedList testList = board.getAdjList(490);
		Assert.assertTrue(testList.contains(465));
		Assert.assertTrue(testList.contains(489));
		Assert.assertTrue(testList.contains(491));
		Assert.assertTrue(testList.contains(515));
		Assert.assertEquals(4, testList.size());
	}
	@Test
	public void testAdjacency543()
	{
		LinkedList testList = board.getAdjList(543);
		Assert.assertTrue(testList.contains(518));
		Assert.assertEquals(1, testList.size());
	}
	@Test
	public void testAdjacency442()
	{
		LinkedList testList = board.getAdjList(442);
		Assert.assertTrue(testList.contains(441));
		Assert.assertTrue(testList.contains(443));
		Assert.assertTrue(testList.contains(467));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	public void testAdjacency261()
	{
		LinkedList testList = board.getAdjList(261);
		Assert.assertTrue(testList.contains(260));
		Assert.assertTrue(testList.contains(286));
		Assert.assertEquals(2, testList.size());
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testTargets28_3()
	{
		board.startTargets(28, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(25));
		Assert.assertTrue(targets.contains(51));
		Assert.assertTrue(targets.contains(77));
		Assert.assertTrue(targets.contains(103));
		Assert.assertTrue(targets.contains(27));
		Assert.assertTrue(targets.contains(79));
		Assert.assertTrue(targets.contains(53));
	}
	@Test
	public void testTargets509_2()
	{
		board.startTargets(509, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(511));
		Assert.assertTrue(targets.contains(535));
		Assert.assertTrue(targets.contains(485));
	}
	@Test
	public void testTargets450_6()
	{
		board.startTargets(450, 6);
		Set targets= board.getTargets();
		System.out.println(targets);
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(401));
		Assert.assertTrue(targets.contains(456));
		Assert.assertTrue(targets.contains(430));
		Assert.assertTrue(targets.contains(428));
		Assert.assertTrue(targets.contains(454));
		Assert.assertTrue(targets.contains(426));
		Assert.assertTrue(targets.contains(452));
	}
	@Test
	public void testTargets259_2()
	{
		board.startTargets(259, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(257));
		Assert.assertTrue(targets.contains(283));
		Assert.assertTrue(targets.contains(261));
		Assert.assertTrue(targets.contains(285));
	}
}