package Darts.Darts;

import java.util.Random;
import org.apache.log4j.Logger;

import Darts.Util.Pair;

public class Darts {
	final Logger logger = Logger.getLogger(Darts.class);
	private int numberOfLegs;
	Player player1;
	Player player2;
	public String winner;
	public Darts() {
		super();
		this.numberOfLegs = 1;
		player1 = new Player(1);
		player2 = new Player(2);
	}
	public Darts(int numberOfLegs) {
		super();
		this.numberOfLegs = numberOfLegs;
		player1 = new Player(1);
		player2 = new Player(2);
	}
	
	public StringBuilder startGame() {
		int currentLeg = 1;
		StringBuilder courseOfTheGame = new StringBuilder("Game on");
		while(currentLeg <= numberOfLegs) {
			logger.debug("startGame: currentLeg = " + currentLeg);
			courseOfTheGame.append("Leg number: " + currentLeg);
			courseOfTheGame.append(startLeg());
			currentLeg++;
		}
		return courseOfTheGame;
	}

	StringBuilder startLeg() {
		boolean finishLeg = false;
		StringBuilder courseOfTheLeg = new StringBuilder(" start");
		while (!finishLeg){
			finishLeg = playerTurn(player1, courseOfTheLeg, finishLeg);
			finishLeg = playerTurn(player2, courseOfTheLeg, finishLeg);
			//logger.debug("startLeg: while(!finishLeg) -> finishLeg = " + finishLeg);
		}
		return courseOfTheLeg;
	}
	boolean playerTurn(Player player, StringBuilder courseOfTheLeg, boolean finishLeg) {
		if (finishLeg) {
			return true;
		}
		Pair score = throwDarts(player, courseOfTheLeg);
		finishLeg = true;
	//	logger.debug("playerTurn: player nr " + player.number + " score = " + score);
		player.points = score.getValue();
		if(player.points == 0) {
			return true;
		}
		return false;
	}
	
	Pair throwDarts(Player player, StringBuilder courseOfTheLeg) {
		//logger.debug("throwDarts: start");
		int startingPoints = player.points;
		Pair hit1 = pointsForHit();
		Pair scoreAfterHit1 = checkPointsAfterHit(player, startingPoints, hit1.getValue(), hit1.getKey());
		if (scoreAfterHit1 != null) {
			return scoreAfterHit1;
		}
		startingPoints = startingPoints - hit1.getValue();
		//logger.debug("throwDarts: after hit1: points = " + startingPoints);
		Pair hit2 = pointsForHit();
		Pair scoreAfterHit2 = checkPointsAfterHit(player, startingPoints, hit2.getValue(), hit2.getKey());
		if (scoreAfterHit2 != null) {
			return scoreAfterHit2;
		}
		startingPoints = startingPoints - hit2.getValue();
		Pair hit3 = pointsForHit();
		Pair scoreAfterHit3 = checkPointsAfterHit(player, startingPoints, hit3.getValue(), hit3.getKey());
		if (scoreAfterHit3 != null) {
			return scoreAfterHit3;
		}
		startingPoints = startingPoints - hit3.getValue();
		return new Pair(false,startingPoints);
	}
	
	Pair pointsForHit() {
		Random random = new Random();
		int randomInt = random.nextInt(13) +1;
		if(randomInt > 0 && randomInt <= 5) {
			return new Pair(false,random.nextInt(20) + 1);
		}
		else if (randomInt > 5 && randomInt <= 7 ) {
			return new Pair(true,(random.nextInt(20) + 1) * 2);
		}
		else if (randomInt == 8) {
			return new Pair(false,(random.nextInt(20) + 1) * 3);
		}
		else if (randomInt == 9) {
			return new Pair(false,25);
		}
		else if (randomInt == 10) {
			return new Pair(true,50);
		}
		else {
			return new Pair(false,0);
		}
	}
	Pair checkPointsAfterHit(Player player, int points, int hitValue, boolean isHitDouble) {
		if (points - hitValue == 0 && isHitDouble) {
		//	logger.debug("throwDarts: hit1 -> starting points = 0");
			winner = player.toString();
			return new Pair(true,0);
		}
		else if ((points - hitValue) < 2) {
			//logger.debug("throwDarts: hit1 -> starting points < 2");
			return new Pair(false,player.points);
		}
		return null;
	}
	class Player {
		private int number;
		int points;
		Player(int number) {
			super();
			this.number = number;
			this.points = 301;
		}
		@Override
		public String toString() {
			return "Player [number=" + number + "]";
		}
		
		
	}
}
