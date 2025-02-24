package labb3.modell;

import labb3.verktyg.Punkt;

import java.util.ArrayList;
import java.util.Observable;

// TODO:
// Gör så att klassen Nivå ärver Observable i paketet java.util.
// ============== KLART! ============
public class Nivå extends Observable {
	// TODO:
	// Lägg till tillståndsvariabler för att hålla reda på nivåns rum
	// och i vilket rum som användaren "är".
	//  =========== KLART! =============
	private Rum nuvarandeRum;
	private ArrayList<Rum> rummen;

	public Nivå(Rum nuvarandeRum, ArrayList<Rum> rummen) {
		// TODO:
		// Kopiera in startrum och rum in i tillståndsvariablerna.
		//  ============== KLART! ============
		this.nuvarandeRum = nuvarandeRum;
		this.rummen = rummen;


		// TODO:
		// Kontrollera att startrum finns med i rum. Om inte, kasta
		//  undantag med lämpligt felmeddelande.
		//  ============== KLART! ============
		if (!rummen.contains(nuvarandeRum)) {
			throw new RuntimeException("Startrum existerar ej.");
		}


		// TODO:
		// Kontrollera att inga rum överlappar varandra. Om det ändå är
		//  fallet, kasta undantag med lämpligt felmeddelande.
		// ========== KLART! ==========
		for (int i = 0; i < rummen.size(); i++) {
			for (int j = i + 1; j < rummen.size(); j++) {
				if (rumÖverlappar(rummen.get(i), rummen.get(j))) {
					throw new RuntimeException("Rummen överlappar.");
				}
			}
		}
	}



	private boolean rumÖverlappar(Rum rum1, Rum rum2) {
		Punkt p1 = rum1.getÖVH();
		Punkt p2 = rum2.getÖVH();

		return p1.x() < p2.x() + rum2.getBredd() &&
				p1.x() + rum1.getBredd() > p2.x() &&
				p1.y() < p2.y() + rum2.getHöjd() &&
				p1.y() + rum1.getHöjd() > p2.y();
	}


	// TODO: Skriv en instansmetod som returnerar alla rummen. Denna behöver
	//  Målarduk för att veta vilka rum som finns på nivån och som ska ritas ut.

	//Getter för alla rum
	public ArrayList<Rum> getRummen() {
		return rummen;
	}

	// TODO:
	// Skriv en instansmetod som returnerar en referens till det rum som
	//  användaren "är i".
	//  ============== KLART! ============

	public Rum getNuvarandeRum() {
		return nuvarandeRum;
	}

	// TODO:
	// Skriv klar instansmetoden hoppaÅt nedan så att den ändrar det rum
	//  som användaren "är i" om det är möjligt genom att följa en gång från
	//  rummet och i riktning väderstreck.
	//  Om väderstreck inte är en riktning i vilken det finns en gång, så ändras
	//  inte rummet användaren "är i" (och inte heller kastas undantag). (Denna
	//  metod använder kontrolldelen av programmet för att begära ett hopp till
	//  angränsande rum efter att användaren tryckt på en tangent.)
	// ========== KLART! ==========

	public void hoppaÅt(Väderstreck väderstreck) {
		try {
			Rum nästaRum = nuvarandeRum.gångenÅt(väderstreck).getTill();
			nuvarandeRum = nästaRum;
			setChanged();
			notifyObservers();
		} catch (IllegalArgumentException e) {
			System.out.println("Ingen gång åt " + väderstreck);
		}
	}
}

