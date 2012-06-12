package com.danidemi.tutorial.tdd.darts;


public class Darts {
	
	enum Multiplier{
		DOUBLE, TRIPLE
	}
	
	private int dartsLeft = 3;
	private int turn = 1;
	
	private int score = 301;
	private int lastTurnScore = score;
	private boolean isFinished = false;

	public int score() {
		return score;
	}

	public void dart(int i) {
		computeScore(i, null);
	}

	public void dart(int score, Multiplier multiplier) {
		int mult = 0;
		switch (multiplier) {
		case DOUBLE:
			mult = 2;
			break;

		case TRIPLE:
			mult = 3;
			break;
		}
		int dartScore = mult * score;
		
		computeScore(dartScore, multiplier);
	}

	private void computeScore(int dartScore, Multiplier m) {
		
		int newScore = score - dartScore;
		
		if(newScore == 0 && m.equals(Multiplier.DOUBLE)){
			isFinished = true;
			return;
		}
		
		if( (newScore == 1 && dartsLeft != 1) || (newScore < 0)){ // bust
			newScore = lastTurnScore;
			dartsLeft = 3;
			turn++;			
		}else if(dartsLeft == 1){
			lastTurnScore = newScore;
			dartsLeft = 3;
			turn++;
		}else{
			dartsLeft--;
		}
		
		score = newScore;
		
	}

	public int getTurn() {
		return turn;
	}
	
	public int dartsLeft() {
		return dartsLeft;
	}

	public boolean isFinished() {
		return isFinished;
	}	

}
