package labb3;

import static labb3.modell.Väderstreck.*;

import java.awt.Color;
import java.util.ArrayList;

import labb3.kontroll.Tangentbordslyssnare;
import labb3.modell.*;

public class Huvudprogram {

	public static void main(String[] args) {

		ArrayList<Rum> rum = new ArrayList<Rum>();

		// Dessa rum och gångar motsvarar de i laborationsinstruktionen.

		// TODO:
		// Skapa även andra uppsättningar rum/gångar för att kunna testköra
		//  ordentligt. Lägg varje uppsättning (även den givna nedan) i separata
		//  metoder här i klassen. Såna bör vara deklarerade static för att kunna
		//  anropas från main (som ju också är static).
		// ========== KLART! ==========

		rum.add(new Rum(Color.RED, 75, 75, 25, 25));
		rum.add(new Rum(Color.BLUE, 75, 50, 50, 150));
		rum.add(new Rum(Color.MAGENTA, 100, 50, 175, 100));
		rum.add(new Rum(Color.YELLOW, 100, 75, 200, 200));
		rum.add(new Rum(Color.CYAN, 100, 75, 325, 50));
		rum.add(new Rum(Color.ORANGE, 75, 75, 450, 125));
		rum.add(new Rum(Color.PINK, 100, 50, 275, 325));
		rum.add(new Rum(Color.GREEN, 75, 100, 75, 275));

		Rum.kopplaIhop(rum.get(0), SÖDER, rum.get(1), NORR);
		Rum.kopplaIhop(rum.get(0), ÖSTER, rum.get(2), NORR);
		Rum.kopplaIhop(rum.get(1), SÖDER, rum.get(3), VÄSTER);
		Rum.kopplaIhop(rum.get(2), SÖDER, rum.get(3), NORR);
		Rum.kopplaIhop(rum.get(2), ÖSTER, rum.get(4), VÄSTER);
		Rum.kopplaIhop(rum.get(4), ÖSTER, rum.get(5), NORR);
		Rum.kopplaIhop(rum.get(5), SÖDER, rum.get(6), ÖSTER);
		Rum.kopplaIhop(rum.get(3), ÖSTER, rum.get(5), VÄSTER);
		Rum.kopplaIhop(rum.get(3), SÖDER, rum.get(6), NORR);
		Rum.kopplaIhop(rum.get(7), ÖSTER, rum.get(6), VÄSTER);

		// ================================================= //
//		rum.add(new Rum(Color.LIGHT_GRAY, 100, 100, 50, 50));
//		rum.add(new Rum(Color.DARK_GRAY, 75, 100, 200, 50));
//		rum.add(new Rum(Color.BLUE, 50, 75, 350, 75));
//		rum.add(new Rum(Color.RED, 125, 75, 150, 200));
//		rum.add(new Rum(Color.GREEN, 100, 125, 300, 250));
//		rum.add(new Rum(Color.MAGENTA, 150, 100, 450, 150));
//
//		Rum.kopplaIhop(rum.get(0), ÖSTER, rum.get(1), VÄSTER);
//		Rum.kopplaIhop(rum.get(1), SÖDER, rum.get(3), NORR);
//		Rum.kopplaIhop(rum.get(1), ÖSTER, rum.get(2), VÄSTER);
//		Rum.kopplaIhop(rum.get(2), SÖDER, rum.get(4), NORR);
//		Rum.kopplaIhop(rum.get(4), VÄSTER, rum.get(3), ÖSTER);
//		Rum.kopplaIhop(rum.get(4), ÖSTER, rum.get(5), VÄSTER);
//		Rum.kopplaIhop(rum.get(0), SÖDER, rum.get(3), VÄSTER);

		// ================================================= //
		// TODO:
		// Skapa en nivå med argumenten rum.get(3) och rum.
		// ========== KLART! ==========
		Nivå nivå = new Nivå(rum.get(3), rum);

		// TODO:
		// Skapa en instans av klassen GUI och skicka med nivån ovan som
		//  argument. Man kan ha en referensvariabel som refererar till
		//  GUI-instansen men det är är inte nödvändigt.
		// ========== KLART! ==========
		GUI gui = new GUI(nivå);

	}

}
