import javax.swing.JLabel;

public class GameAnalysis {
	
	private JLabel[][] labelArray;
	private int[][] winningLine;
	private int[][] realWinningLine;
	
	public GameAnalysis(JLabel[][] labelArray) {
		this.labelArray = labelArray;
		winningLine = new int[7][9];
		realWinningLine = new int[7][9];
	}

	public int siegerErmitteln() {
		if(checkReihen() == 1 || checkSpalten() == 1 || checkQuer() == 1) {
			return 1;					// Spieler 1 gewinnt, wenn 1 returnt wird
		} 
		else if(checkReihen() == -1 || checkSpalten() == -1 || checkQuer() == -1) {
			return -1;					// Spieler 2 gewinnt, wenn -1 returnt wird
		}
		else {
			return 0;		// wenn 0 returnt wird geht es weiter(bis hier hat keiner gewonnen)
		}
	}
	
	public int[][] getWinnigLine() {
		return realWinningLine;
	}
	
	public void deleteWinningLine() {
		for(int i = 0; i < 7; i++) {		// Zeilen
			for(int t=0; t < 9; t++) {	// Spalten
				winningLine[i][t] = 0;
			}
		}
	}
	
	public void saveWinningPositions() {
		for(int i = 0; i < 7; i++) {		// Zeilen
			for(int t=0; t < 9; t++) {	// Spalten
				realWinningLine[i][t] = winningLine[i][t];
			}
		}
	}
	
	/**
	 * the method checkSpalten() allows it to check if there are five connected symbols in a column at the Field
	 *
	 */
	public int checkSpalten() {
		int zeilenCounter = 0;
		int h = 0;
		// Check Reihen:
		for(int i = 0; i < 7; i++) {		// Zeilen
			for(int t=0; t < 9; t++) {	// Spalten
				if(labelArray[i][t].getText().equals("X") && t < 8) {
					zeilenCounter = 1;
					winningLine[i][t] = 1;
					while(labelArray[i][t+1+h].getText().equals("X") && (t+1+h) < 9) {
						winningLine[i][t+1+h] = 1;
						zeilenCounter++;
						h++;
						if(zeilenCounter == 5) {
							saveWinningPositions();
							return 1;						// wurden fuenf 'X' gezaehlt hat player 1 gewonnen
						}
					}
					h = 0;
					zeilenCounter = 0;
				}
				deleteWinningLine();
				if(labelArray[i][t].getText().equals("0") && (t) < 8) {
					zeilenCounter = 1;
					winningLine[i][t] = 1;
					while(labelArray[i][t+1+h].getText().equals("0") && (t+1+h) < 9) {
						zeilenCounter++;
						winningLine[i][t+1+h] = 1;
						h++;
						if(zeilenCounter == 5) {
							saveWinningPositions();
							return -1;						// wurden fuenf '0' gezählt hat player 2 gewonnen
						}
					}
					h = 0;
					zeilenCounter = 0;
				}
				deleteWinningLine();
			}
		}
	return 0;
	}
	
	/**
	 * the method checkReihen() allows it to check if there are five connected symbols in a row at the Field
	 *
	 */
	public int checkReihen() {
		// Check Spalten:
		for(int i = 0; i < 7; i++) {				// Zeilen
			for(int t = 0; t < 9; t++) {			// Spalten
				if(labelArray[i][t].getText().equals("X") && (i+1) < 7) {
					winningLine[i][t] = 1;
					if(labelArray[i+1][t].getText().equals("X") && (i+2) < 7) {
						winningLine[i+1][t] = 1;
						if(labelArray[i+2][t].getText().equals("X") && (i+3) < 7) {
							winningLine[i+2][t] = 1;
							if(labelArray[i+3][t].getText().equals("X") && (i+4) < 7) {
								winningLine[i+3][t] = 1;
								if(labelArray[i+4][t].getText().equals("X")) {
									winningLine[i+4][t] = 1;
									saveWinningPositions();
									return 1;			// Player 1 gewinnt bei 5 mal 'X' -> return 1
								}
							}
						}
					}
				}
				deleteWinningLine();
				if(labelArray[i][t].getText().equals("0") && (i+1) < 7) {
					winningLine[i][t] = 1;
					if(labelArray[i+1][t].getText().equals("0") && (i+2) < 7) {
						winningLine[i+1][t] = 1;
						if(labelArray[i+2][t].getText().equals("0") && (i+3) < 7) {
							winningLine[i+2][t] = 1;
							if(labelArray[i+3][t].getText().equals("0") && (i+4) < 7) {
								winningLine[i+3][t] = 1;
								if(labelArray[i+4][t].getText().equals("0") ) {
									winningLine[i+4][t] = 1;
									saveWinningPositions();
									return -1;			// Player 2 gewinnt bei 5 mal '0' -> return 1
								}
							}
						}
					}
				}
				deleteWinningLine();
			}
		}
	return 0;
	}
	
	/**
	 * the method checkQuer() allows it to check if there are five connected symbols crosswise
	 *
	 */
	public int checkQuer(){
		for(int i = 0; i < 7; i++) {				// Zeilen
			for(int t = 0; t < 9; t++) {			// Spalten
				// QUER NACH OBEN FÜR 'X'
				if( labelArray[i][t].getText().equals("X") && i < 6 && t < 8) {
					winningLine[i][t] = 1;
					if( labelArray[i+1][t+1].getText().equals("X") && i < 5 && t < 7) {
						winningLine[i+1][t+1] = 1;
						if( labelArray[i+2][t+2].getText().equals("X") && i < 4 && t < 6) {
							winningLine[i+2][t+2] = 1;
							if( labelArray[i+3][t+3].getText().equals("X") && i < 3 && t < 5) {
								winningLine[i+3][t+3] = 1;
								if( labelArray[i+4][t+4].getText().equals("X")) {		// Sobald 5 mal 'X' Quer wahr ist hat player 1 gewonnen
									winningLine[i+4][t+4] = 1;
									saveWinningPositions();
									return 1;
								}
							}
						}
					}
				}
				deleteWinningLine();
				// QUER NACH UNTEN FÜR 'X'
				if( labelArray[i][t].getText().equals("X") && i > 0 && t < 8 ) {
					winningLine[i][t] = 1;
					if( labelArray[i-1][t+1].getText().equals("X") && i > 1 && t < 7) {
						winningLine[i-1][t+1] = 1;
						if( labelArray[i-2][t+2].getText().equals("X") && i > 2 && t < 6) {
							winningLine[i-2][t+2] = 1;
							if( labelArray[i-3][t+3].getText().equals("X")&& i > 3 && t < 5) {
								winningLine[i-3][t+3] = 1;
								if( labelArray[i-4][t+4].getText().equals("X")) {		// Sobald 5 mal 'X' Quer wahr ist hat player 1 gewonnen
									winningLine[i-4][t+4] = 1;
									saveWinningPositions();
									return 1;
								}
							}
						}
					}
				}
				deleteWinningLine();
				// QUER NACH OBEN FÜR '0'
				if( labelArray[i][t].getText().equals("0") && i < 6 && t < 8) {
					winningLine[i][t] = 1;
					if( labelArray[i+1][t+1].getText().equals("0")  && i < 5 && t < 7) {
						winningLine[i+1][t+1] = 1;
						if( labelArray[i+2][t+2].getText().equals("0")  && i < 4 && t < 6) {
							winningLine[i+2][t+2] = 1;
							if( labelArray[i+3][t+3].getText().equals("0") && i < 3 && t < 5) {
								winningLine[i+3][t+3] = 1;
								if( labelArray[i+4][t+4].getText().equals("0")) {		// Sobald 5 mal '0' Quer wahr ist hat player 2 gewonnen
									winningLine[i+4][t+4] = 1;
									saveWinningPositions();
									return -1;
								}
							}
						}
					}
				}
				deleteWinningLine();
				// QUER NACH UNTEN FUER '0'
				if( labelArray[i][t].getText().equals("0") && i > 0 && t < 8) {
					winningLine[i][t] = 1;
					if( labelArray[i-1][t+1].getText().equals("0") && i > 1 && t < 7) {
						winningLine[i-1][t+1] = 1;
						if( labelArray[i-2][t+2].getText().equals("0") && i > 2 && t < 6) {
							winningLine[i-2][t+2] = 1;
							if( labelArray[i-3][t+3].getText().equals("0") && i > 3 && t < 5) {
								winningLine[i-3][t+3] = 1;
								if( labelArray[i-4][t+4].getText().equals("0")) {		// Sobald 5 mal '0' Quer wahr ist hat player 2 gewonnen
									winningLine[i-4][t+4] = 1;
									saveWinningPositions();
									return -1;
								}
							}
						}
					}
				}
				deleteWinningLine();
			}
		}
		return 0;
	}	
}
