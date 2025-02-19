package labb3;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import labb3.kontroll.Tangentbordslyssnare;
import labb3.modell.Nivå;
import labb3.vy.Målarduk;

import javax.swing.*;

// TODO:
// Klassen ska ärva klassen JFrame i standardbibliotekets paket
// javax.swing och implementera gränssnittet Observer i paketet java.util.
// ========== KLART! ==========
public class GUI extends JFrame implements Observer {

	private Målarduk målarduk;

	public GUI(Nivå enNivå) {
		// TODO:
		// Anropa metoden setDefaultCloseOperation med konstanten
		//  JFrame.EXIT_ON_CLOSE.
		// ========== KLART! ==========
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// TODO:
		// Gör så att enNivå observerar this (instansen av GUI som denna
		//  kod håller på att skapa.
		// ========== KLART! ==========
		enNivå.addObserver(this);

		// TODO:
		// Tilldela tillståndsvariabeln målarduk en instans av klassen
		//  Målarduk.
		// ========== KLART! ==========
		målarduk = new Målarduk(enNivå);

		// TODO:
		// Använd setPreferredSize på målarduk och sätt dess dimensioner
		//  så alla rum faktiskt syns. Ett tips är att loopa igenom nivåns alla
		//  rum och räkna ut vilken bredd och höjd målarduken måste ha(!)
		//  Annars går det också att dra till med en storlek och sen bara
		//  använda rum som säkert kan visas på målarduken.
		// ========== KLART! ==========
		målarduk.setPreferredSize(new java.awt.Dimension(500, 500));

		// TODO:
		// Lägg till en KeyListener på målardiken, dvs en instans av
		//  typen Tangentbordslyssnare. Notera att lyssnaren vill ha enNivå
		//  som argument till konstruktorn för att kunna påverka just den
		//  nivån.
		// ========== KLART! ==========
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		// TODO:
		// Anropa setContentPane med målarduk, så att målarduk är
		//  den yta som vår JFrame kommer att ha. Sen återstår bara att
		//  göra setVisible(true) och pack().
		// ========== KLART! ==========
		setContentPane(målarduk);
		setVisible(true);
		pack();
	}

	// TODO:
	// Metoden update ska vara den i gränssnittet Observer. Lägg därför
	//  till @Override på raden innan metodhuvudet. (Klassen måste sen importera
	//  java.util.Observer.)
	// ========== KLART! ==========
	@Override
	public void update(Observable o, Object arg) {
		// Detta anrop triggar ett anrop till paintComponent i Målarduk.
		// Avkommentera raden (när Målarduk "är-en" JPanel). 
		this.målarduk.repaint();
	}
}
