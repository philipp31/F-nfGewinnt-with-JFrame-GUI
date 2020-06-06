import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JFrame;

public class FuenfGewinnt extends JFrame implements ActionListener {
	
	private JLabel label;
	private JButton[] b;			// button als Array für das Spielfeld
	private int spieler;
	private int[] freeSpace;		// erste freie Position je Spalte
	private JLabel label2;
	private JLabel[][] labelArray;
	
	public static void main(String[] args){
		new FuenfGewinnt();			// abstrakte Klasse/Interface
	}
	
	// Konstruktor des JFrames
	public FuenfGewinnt() {		// abstrakte Klasse/Interface wird IMPLEMENTIERT, auch in main möglich aber unübersichtlich
		super("Das ist ein Testframe");					// aufrufen des Konstruktors der Vaterklasse
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);							// BREITE x HÖHE
		this.setTitle("Connect-Five");
		label = new JLabel("Es ist Player 1 ('X') dran!", SwingConstants.CENTER);			// JLABEL ist ein TEXT
		label.setLocation(500-100, 20);							// Um mittig zu platzieren eigene Breite abziehen
		label.setSize(300,50);		//  (zur Seite, Nach Unten)
		label.setOpaque(true);	// sichtbarkeit des BACKROUNDS!
		label.setBackground(Color.white);
		label2 = new JLabel("Waehlen Sie eine Spalte aus um Ihren Zug zu taetigen: ", SwingConstants.CENTER);			// JLABEL ist ein TEXT
		label2.setLocation(250, 100);							
		label2.setSize(500,50);		// (zur Seite, Nach Unten)
		label2.setOpaque(true);		// Sichtbarkeit des BACKROUNDS!
		label2.setBackground(Color.white);
		spieler = 0;
		b = new JButton[9];						// Spielfeld soll Größe 7x9 = 63 haben
		freeSpace = new int[9];
		labelArray = new JLabel[7][9];			// Das SPIELFELD 7x9
		
		for(int m  = 0; m < 9 ; m++) {
			freeSpace[m] = 0;	
			b[m] = new JButton("Spalte" + (m+1) );
			b[m].setSize(100,60);						// BREITE x HÖHE
			b[m].setLocation((40 + (100*m)) ,150);		// (SEITE,NACH UNTEN)
			b[m].addActionListener(this);				// ActionListener für Buttons hinzufügen
		}
		for(int i = 0; i < 7; i++) {
			for(int k = 0; k < 9; k++) {
				labelArray[i][k] = new JLabel("", SwingConstants.CENTER);	// Konstr.(text, art der Zentrierung)
				labelArray[i][k].setSize(80 , 60);
				labelArray[i][k].setOpaque(true);
				labelArray[i][k].setBackground(Color.white);
				labelArray[i][k].setLocation((50 + (k*100)) , (850 - (100*i)));	//	(SEITE,HÖHE)
			}
		}
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.GRAY);
		c.add(label2);
		//Hinzufügen von Buttons,Label, etc:
		c.add(label);								// Label, Buttons etc. müssen beim Container-Obj geaddet werden
		for(int k = 0; k < 9 ; k++) {
			c.add(b[k]);
		}
		for(int w = 0; w < 7; w++) {
			for(int q = 0; q < 9; q++) {
				c.add(labelArray[w][q]);
			}
		}
		setVisible(true);
	}
	
	// WICHTIGSTES ELEMENT -> ACTIONLISTENER :
	public void actionPerformed(ActionEvent e) {
		if(siegerErmitteln() == 1) {
			label.setText("SPIEL IST VORBEI, SPIELER 1('X') HAT GEWONNEN");
			return;
		}
		else if(siegerErmitteln() == -1) {
			label.setText("SPIEL IST VORBEI, SPIELER 2('0') HAT GEWONNEN");
		}
		int t = 0;
		for(int h = 0; h < 9; h++) {
			if(e.getSource() == this.b[h]) {	// finden der Spalte 'h' in der Eingefügt werden soll
				while(labelArray[t][h].getText().equals("X") || labelArray[t][h].getText().equals("0")) {
					t++;
					if(t > 6) {
						return;
					}
				}
				if(spieler == 0) {
					labelArray[t][h].setText("X");
					spieler++;
				} 
				else if(spieler == 1) {
					labelArray[t][h].setText("0");
					spieler--;
				}
				t=0;
			}
		}
	if(spieler == 0) {
		label.setText("Es ist Spieler 1 dran (->hat das 'X') ");
	} else if(spieler == 1) {
		label.setText("Es ist Spieler 2 dran (->hat das '0') ");
	}
	if(siegerErmitteln() == 1) {
			label.setText("SPIEL IST VORBEI, SPIELER 1('X') HAT GEWONNEN");
			return;
	}
	else if(siegerErmitteln() == -1) {
		label.setText("SPIEL IST VORBEI, SPIELER 2('0') HAT GEWONNEN");
	}
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
							return -1;						// wurden fünf '0' gezählt hat player 2 gewonnen
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
