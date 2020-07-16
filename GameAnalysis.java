import javax.swing.JLabel;

public class GameAnalysis {
	
	private JLabel[][] labelArray;
	
	public GameAnalysis(JLabel[][] labelArray) {
		this.labelArray = labelArray;
	}

	public int siegerErmitteln() {
		if(checkReihen() == 1 || checkSpalten() == 1 || checkQuer() == 1) {
			return 1;					// Spieler 1 gewinnt, wenn 1 returnt wird
		} 
		else if(checkReihen() == -1 || checkSpalten() == -1 || checkQuer() == -1) {
			return -1;					// Spieler 2 gewinnt, wenn -1 returnt wird
		}
		else {
			return 0;					// wenn 0 returnt wird geht es weiter(bis hier hat keiner gewonnen)
		}
	}
	
	
	/**
	 * the method checkReihen() allows it to check if there are five connected symbols in a row at the currect Field
	 *
	 */
	public int checkSpalten() {
		int zeilenCounter = 0;
		int h = 0;
		// Check Reihen:
		for(int i = 0;i < 7; i++) {		// Zeilen
			for(int t=0; t < 9; t++) {	// Spalten
				if(labelArray[i][t].getText().equals("X") && t < 8) {
					zeilenCounter = 1;
					while(labelArray[i][t+1+h].getText().equals("X") && (t+1+h) < 9) {
						zeilenCounter++;
						h++;
						if(zeilenCounter == 5) {
							return 1;						// wurden fünf 'X' gezählt hat player 1 gewonnen
						}
					}
					h = 0;
					zeilenCounter = 0;
				}
				if(labelArray[i][t].getText().equals("0") && (t) < 8) {
					zeilenCounter = 1;
					while(labelArray[i][t+1+h].getText().equals("0") && (t+1+h) < 9) {
						zeilenCounter++;
						h++;
						if(zeilenCounter == 5) {
							return -1;						// wurden fuenf '0' gezählt hat player 2 gewonnen
						}
					}
					h = 0;
					zeilenCounter = 0;
				}
			}
		}
	return 0;
	}
	
	/**
	 * the method checkSpalten() allows it to check if there are five connected symbols in a column at the currect Field
	 *
	 */
	public int checkReihen() {
		// Check Spalten:
		for(int i = 0; i < 7; i++) {				// Zeilen
			for(int t = 0; t < 9; t++) {			// Spalten
			
				if(labelArray[i][t].getText().equals("X") && (i+1) < 7) {
					if(labelArray[i+1][t].getText().equals("X") && (i+2) < 7) {
						if(labelArray[i+2][t].getText().equals("X") && (i+3) < 7) {
							if(labelArray[i+3][t].getText().equals("X") && (i+4) < 7) {
								if(labelArray[i+4][t].getText().equals("X")) {
									return 1;			// Player 1 gewinnt bei 5 mal 'X' -> return 1
								}
							}
						}
					}
				}
				if(labelArray[i][t].getText().equals("0") && (i+1) < 7) {
					if(labelArray[i+1][t].getText().equals("0") && (i+2) < 7) {
						if(labelArray[i+2][t].getText().equals("0") && (i+3) < 7) {
							if(labelArray[i+3][t].getText().equals("0") && (i+4) < 7) {
								if(labelArray[i+4][t].getText().equals("0") ) {
									return -1;			// Player 2 gewinnt bei 5 mal '0' -> return 1
								}
							}
						}
					}
				}
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
					if( labelArray[i+1][t+1].getText().equals("X") && i < 5 && t < 7) {
						if( labelArray[i+2][t+2].getText().equals("X") && i < 4 && t < 6) {
							if( labelArray[i+3][t+3].getText().equals("X") && i < 3 && t < 5) {
								if( labelArray[i+4][t+4].getText().equals("X")) {		// Sobald 5 mal 'X' Quer wahr ist hat player 1 gewonnen
									return 1;
								}
							}
						}
					}
				}
				// QUER NACH UNTEN FÜR 'X'
				if( labelArray[i][t].getText().equals("X") && i > 0 && t < 8 ) {
					if( labelArray[i-1][t+1].getText().equals("X") && i > 1 && t < 7) {
						if( labelArray[i-2][t+2].getText().equals("X") && i > 2 && t < 6) {
							if( labelArray[i-3][t+3].getText().equals("X")&& i > 3 && t < 5) {
								if( labelArray[i-4][t+4].getText().equals("X")) {		// Sobald 5 mal 'X' Quer wahr ist hat player 1 gewonnen
									return 1;
								}
							}
						}
					}
				}
				// QUER NACH OBEN FÜR '0'
				if( labelArray[i][t].getText().equals("0") && i < 6 && t < 8) {
					if( labelArray[i+1][t+1].getText().equals("0")  && i < 5 && t < 7) {
						if( labelArray[i+2][t+2].getText().equals("0")  && i < 4 && t < 6) {
							if( labelArray[i+3][t+3].getText().equals("0") && i < 3 && t < 5) {
								if( labelArray[i+4][t+4].getText().equals("0")) {		// Sobald 5 mal '0' Quer wahr ist hat player 2 gewonnen
									return -1;
								}
							}
						}
					}
				}
				// QUER NACH UNTEN FÜR '0'
				if( labelArray[i][t].getText().equals("0") && i > 0 && t < 8) {
					if( labelArray[i-1][t+1].getText().equals("0") && i > 1 && t < 7) {
						if( labelArray[i-2][t+2].getText().equals("0") && i > 2 && t < 6) {
							if( labelArray[i-3][t+3].getText().equals("0") && i > 3 && t < 5) {
								if( labelArray[i-4][t+4].getText().equals("0")) {		// Sobald 5 mal '0' Quer wahr ist hat player 2 gewonnen
									return -1;
								}
							}
						}
					}
				}
				
			}
		}
		return 0;
	}	
}
