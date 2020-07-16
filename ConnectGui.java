import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ConnectGui extends JFrame implements ActionListener {
	
	private JLabel label;
	private JButton[] b;			// button als Array fuer das Spielfeld
	private JLabel 	label2;
	private JLabel[][] labelArray;
	private GameAnalysis gameAnalysis;
	private int spieler;
	private int[] freeSpace;
	
	public ConnectGui() {
		super("Das ist ein Testframe");					// Aufrufen des Konstruktors der Vaterklasse
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 1000);						// BREITE, HOEHE
		this.setTitle("Connect-Five");
		initComponents();
		
		// Adding all components:
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.GRAY);
		c.add(label2);
		c.add(label);				
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
	
	public void initComponents() {
		spieler = 0;
		label = new JLabel("Es ist Player 1 ('X') dran!", SwingConstants.CENTER);			// JLABEL ist ein TEXT
		label2 = new JLabel("Waehlen Sie eine Spalte aus um Ihren Zug zu taetigen: ", SwingConstants.CENTER);	
		b = new JButton[9];						// Die 9 Tasten zum Befüllen des Spielfelds
		labelArray = new JLabel[7][9];			// Das SPIELFELD der Größe 7x9
		freeSpace = new int[9];
		gameAnalysis = new GameAnalysis(labelArray);
		
		label.setLocation(500-100, 20);							// Um mittig zu platzieren eigene Breite abziehen
		label.setSize(300,50);		//  (zur Seite, Nach Unten)
		label.setOpaque(true);	// Sichtbarkeit des BACKROUNDS!
		label.setBackground(Color.white);
		label2.setLocation(250, 100);							
		label2.setSize(500,50);		// (zur Seite, Nach Unten)
		label2.setOpaque(true);		// Sichtbarkeit des BACKROUNDS!
		label2.setBackground(Color.white);
		for(int i = 0; i < 7; i++) {
			for(int k = 0; k < 9; k++) {
				labelArray[i][k] = new JLabel("", SwingConstants.CENTER);	// Konstr.(text, art der Zentrierung)
				labelArray[i][k].setSize(80 , 60);
				labelArray[i][k].setOpaque(true);
				labelArray[i][k].setBackground(Color.white);
				labelArray[i][k].setLocation((50 + (k*100)) , (850 - (100*i)));	//	(SEITE,HÃ–HE)
			}
		}
		for(int m  = 0; m < 9 ; m++) {
			freeSpace[m] = 0;	
			b[m] = new JButton("Spalte" + (m+1) );
			b[m].setSize(100,60);						// BREITE x HÖHE
			b[m].setLocation((40 + (100*m)) ,150);		// (SEITE,NACH UNTEN)
			b[m].addActionListener(this);				// ActionListener für Buttons hinzufügen
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(gameAnalysis.siegerErmitteln() == 1) {
			label.setText("SPIEL IST VORBEI, SPIELER 1('X') HAT GEWONNEN");
			return;
		}
		else if(gameAnalysis.siegerErmitteln() == -1) {
			label.setText("SPIEL IST VORBEI, SPIELER 2('0') HAT GEWONNEN");
			return;
		}
		int t = 0;
		for(int h = 0; h < 9; h++) {
			if(e.getSource() == this.b[h]) {	// finden der Spalte 'h' in der eingefuegt werden soll
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
		if(gameAnalysis.siegerErmitteln() == 1) {
				label.setText("SPIEL IST VORBEI, SPIELER 1('X') HAT GEWONNEN");
				return;
		}
		else if(gameAnalysis.siegerErmitteln() == -1) {
			label.setText("SPIEL IST VORBEI, SPIELER 2('0') HAT GEWONNEN");
			return;
		}
	}

}
