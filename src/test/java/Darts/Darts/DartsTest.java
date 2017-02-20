/**
 * 
 */
package Darts.Darts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import Darts.Darts.Darts.Player;
import Darts.Util.Pair;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.partialMockBuilder;

/**
 * @author Paweł
 *
 */

public class DartsTest{
	
	Darts darts;
	Darts mockDarts;
	Player player1;
	Player player2;
	Pair pair;
	Pair pair2;
	
	@Before
	public void init() throws Exception {
		darts = new Darts(3);
		player1 = darts.player1;
		player2 = darts.player2;
	}

	@Test
	public void checkPointsAfterHitTest() {
		pair = new Pair(true, 0);
		pair2 = new Pair(false,301);
		Assert.assertNull(darts.checkPointsAfterHit(player1, 100, 5, false));
		Assert.assertNotNull(darts.checkPointsAfterHit(player1, 100, 100, false));
		Assert.assertNotNull(darts.checkPointsAfterHit(player1, 100, 102, true));
		Assert.assertEquals(pair, darts.checkPointsAfterHit(player1, 100, 100, true));
		Assert.assertEquals(pair2, darts.checkPointsAfterHit(player1, 100, 100, false));
		Assert.assertNotEquals(pair2, darts.checkPointsAfterHit(player1, 100, 100, true));
		Assert.assertNotEquals(pair, darts.checkPointsAfterHit(player1, 100, 100, false));
		
	}
	
	@Test
	public void throwDartsWinInFirstHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 10;
		pair = new Pair(true, 10);
		pair2 = new Pair(true, 0);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsWinInSecondHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 25;
		pair = new Pair(false, 15);
		pair2 = new Pair(true, 0);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 10);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsWinInThirdHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 25;
		pair2 = new Pair(true, 0);
		pair = new Pair(false, 5);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 10);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 10);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsBurstInFirstHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 10;
		pair2 = new Pair(false, player1.points);
		pair = new Pair(false, 22);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsBurstInSecondHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 10;
		pair2 = new Pair(false, player1.points);
		pair = new Pair(false, 5);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 10);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsBurstInThirdHitTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 10;
		pair2 = new Pair(false, player1.points);
		pair = new Pair(false, 5);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 2);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 2);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void throwDartsSubtractHitsTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("pointsForHit").createMock();
		player1.points = 100;
		pair2 = new Pair(false, 60);
		pair = new Pair(false, 20);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(true, 20);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		pair = new Pair(false, 0);
		expect(mockDarts.pointsForHit()).andReturn(pair);
		replay(mockDarts);
		Assert.assertEquals(pair2, mockDarts.throwDarts(player1,new StringBuilder("")));
		
	}
	
	@Test
	public void playerTurnPlayerWinTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("throwDarts").createMock();
		pair = new Pair(true,0);
		StringBuilder builder = new StringBuilder("");
		expect(mockDarts.throwDarts(player1, builder)).andReturn(pair);
		replay(mockDarts);
		Assert.assertTrue(mockDarts.playerTurn(player1, builder, false));
	}
	
	@Test
	public void playerTurnFinishLegTrueTest() {
		StringBuilder builder = new StringBuilder("");
		Assert.assertTrue(darts.playerTurn(player1, builder, true));
	}
	
	@Test
	public void playerTurnContinueLegTest() {
		mockDarts = partialMockBuilder(Darts.class).addMockedMethod("throwDarts").createMock();
		pair = new Pair(false,15);
		StringBuilder builder = new StringBuilder("");
		expect(mockDarts.throwDarts(player1, builder)).andReturn(pair);
		replay(mockDarts);
		Assert.assertFalse(mockDarts.playerTurn(player1, builder, false));
	}
	
	@After
	public void destroy() throws Exception {
		
	}

}
