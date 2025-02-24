package labb3.modell;

import labb3.verktyg.Punkt;

import java.awt.Color;
import static labb3.GlobalaKonstanter.ANTAL_VÄDERSTRECK;
public class Rum {
	// TODO:
	// Lägg till tillståndsvariabler.
	// ========== KLART! ==========
	private Color golvfärg;
	private int bredd;
	private int höjd;
	private Punkt övreVänstraHörn;
	private Gång[] gångar = new Gång[ANTAL_VÄDERSTRECK];


	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		// TODO:
		// Kopiera parametrarna in i tillståndsvariablerna. (övX,övY) är
		//  koordinaten för rummets övre vänstra hörn och lagras lämpligen som en
		//  instans av klassen Punkt i paketet verktyg.
		// ========== KLART! ==========
		this.golvfärg = golvfärg;
		this.bredd = bredd;
		this.höjd = höjd;
		this.övreVänstraHörn = new Punkt(övX, övY);
	}

	// TODO:
	// Skriv "getters", metoder som returnerar tillståndsvariablernas
	//  värden.
	// ========== KLART! ==========
	public Color getGolvfärg() { return golvfärg; }

	public int getBredd() {
		return bredd;
	}

	public int getHöjd() {
		return höjd;
	}

	public Punkt getÖVH() {
		return övreVänstraHörn;
	}


	// TODO:
	// Skriv instansmetoden
	//  finnsUtgångÅt(Väderstreck väderstreck)
	//  som ska kontrollera om det från ett rum finns en utgång åt visst
	//  väderstreck.
	// ========== KLART! ==========
	public boolean finnsUtgångÅt(Väderstreck väderstreck){
		return this.gångar[väderstreck.getIndex()] != null;
	}


	// TODO:
	// Skriv instansmetoden
	//  Gång gångenÅt(Väderstreck väderstreck) som
	//  returnerar den gång som leder från ett rum i riktning väderstreck. Om
	//  sådan gång saknas ska ett undantag kastas med lämpligt felmeddelande.
	// ========== KLART! ==========

	public Gång gångenÅt(Väderstreck väderstreck){
			if(finnsUtgångÅt(väderstreck)){
				return gångar[väderstreck.getIndex()];
			} else {
				throw new IllegalArgumentException("Inga gångar åt det hållet.");
		}
	}


	// TODO:
	// Skrivklar metoden nedan som kopplar ihop två rum med en gång.
	// ========== KLART! ==========
	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {
		Gång gångFrånTill = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		Gång gångTillFrån = new Gång(till, riktningInITill, från, riktningUtUrFrån);

		från.gångar[riktningUtUrFrån.getIndex()] = gångFrånTill;
		till.gångar[riktningInITill.getIndex()] = gångTillFrån;
	}
}
