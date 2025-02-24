package labb3.vy;

import java.awt.*;
import java.util.HashSet;

import labb3.GlobalaKonstanter;
import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Grafik;
import labb3.verktyg.Punkt;

import javax.swing.*;
import static labb3.GlobalaKonstanter.*;


// TODO:
// Ändra nästa rad så att en Målarduk "är-en" JPanel.
// ========= KLART! ==========
public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		// TODO:
		// Sätt bakgrundsfärgen på this till MARKFÄRG.
		// TODO:
		// Anropa metoden setFocusable på this och med argumentet true.
		// Detta behövs för att lyssnaren i programmet ska reagera.
		// ========== KLART! ==========
		this.enNivå = enNivå;
		setBackground(GlobalaKonstanter.MARKFÄRG);
		setFocusable(true);
	}

	// TODO:
	// Lägg till @Override på metoden nedan.
	// ========== KLART! ==========
	@Override
	protected void paintComponent(Graphics g) {
		// TODO:
		// Lägg till ett anrop till paintComponent i omedelbara
		// överklassen (JPanel). Skicka med g som argument.
		// ========== KLART! ==========
		super.paintComponent(g);

		HashSet<Object> ritadeGångar = new HashSet<>();

		for (Rum rum: enNivå.getRummen()){
			ritaRum(g, rum);

			for(Väderstreck riktning : Väderstreck.values()){
				if (rum.finnsUtgångÅt(riktning)){
					Gång gång = rum.gångenÅt(riktning);

					if (!ritadeGångar.contains(gång)){
						ritaGång(g, gång);
						ritadeGångar.add(gång);
					}
				}
			}
		}


		// TODO:
		// Lägg till kod som ritar ut en grafisk vy av enNivå.
		//  För att underlätta finns hjälpmetoder som ska skrivas klara. Studera
		//  noga bilderna i labbinstruktionen för att få fram formlerna för
		//  bas- och pivotpunkternas koordinater. Använd ritmetoderna i klassen
		//  labb3.verktyg.Grafik. Anropa hjälpmetoderna från paintComponent.
		// ========== KLART! ==========
		for (Rum rum: enNivå.getRummen()) {
			ritaRum(g, rum);
		}

		ritaMarkörFörVarAnvändarenÄr(g);
	}

	private void ritaRum(Graphics g, Rum ettRum) {
		Punkt övreVänstraHörn = ettRum.getÖVH();
		int x = övreVänstraHörn.x();
		int y = övreVänstraHörn.y();
		int bredd = ettRum.getBredd();
		int höjd = ettRum.getHöjd();
		int väggTjocklek = GlobalaKonstanter.VÄGGTJOCKLEK;


		/*
		Ritar ut golvet, sen en ram runt rektangeln, har lagts till en variant
		med tjocka väggar för att man det kanske är ett krav i instruktionen?
		 */
		g.setColor(ettRum.getGolvfärg());
		g.fillRect(x, y, bredd, höjd);

		/*
		Detta är alltså det smarta sättet att göra det på.
		*/
//		g.setColor(Color.BLACK);
//		g.drawRect(x, y, bredd, höjd);
		/*
		 Använd metoden drawThickLine för att bestämma en start
		 och slutpunkt för både x och y koordinaten.
		 Detta visar vart linjer ska ritas ut.

		 Man hade kunnat göra nya Punkt Objekt för att hålla
		 reda på vart punkten är istället för att beräkna
		 varje gång i drawThickLine metoden, detta skulle
		 framförallt vara fördelaktigt ifall man återanvänder
		 samma punkter flera gånger. Det kan eventuellt göra koden
		 lite mer läsbar.
		*/
		// Norrväggen (överst)
		Grafik.drawThickLine(g, new Punkt(x, y), new Punkt(x + bredd, y), väggTjocklek, GlobalaKonstanter.VÄGGFÄRG);

		// Söderväggen (nederst)
		Grafik.drawThickLine(g, new Punkt(x, y + höjd), new Punkt(x + bredd, y + höjd), väggTjocklek, GlobalaKonstanter.VÄGGFÄRG);

		// Vägg öster (höger)
		Grafik.drawThickLine(g, new Punkt(x + bredd, y), new Punkt(x + bredd, y + höjd), väggTjocklek, GlobalaKonstanter.VÄGGFÄRG);

		// Vägg väster (vänster)
		Grafik.drawThickLine(g, new Punkt(x, y), new Punkt(x, y + höjd), väggTjocklek, GlobalaKonstanter.VÄGGFÄRG);
	}

	/*
	Ska rita ut alla gånger från ett givet rum. Skrivs med en loop som
	går igenom alla väderstreck, (metoden values() som alla enum:s har är
	här användbar

	I loopen kollar vi vilka gångar som finns från rummet och ritar ut var
	och en i fem steg enligt labbinstruktioner. (Det finns en ineffektivitet
	som man förbättras, nämligen att varje gång ritas från och tillbaka, men
	egentligen behöver man bara rita ut en väg. (ok att lämna detta för labben)
	 */
	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		for (Väderstreck riktning: Väderstreck.values()) {
			if (ettRum.finnsUtgångÅt(riktning)) {
				Grafik.drawThickLine(g, baspunkt(ettRum, riktning), pivotpunkt(ettRum, riktning), VÄGGTJOCKLEK, GÅNGFÄRG);
				Grafik.fillCircle(g, pivotpunkt(ettRum, riktning), HALV_VÄGGTJOCKLEK, GÅNGFÄRG);
			}
		}
	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		switch(enRiktning){
			case NORR :
				return new Punkt(
						ettRum.getÖVH().x() + ettRum.getBredd()/2, // mitten av bredd
						ettRum.getÖVH().y() + VÄGGTJOCKLEK);
			case ÖSTER:
				return new Punkt(
						ettRum.getÖVH().x() + ettRum.getBredd() - VÄGGTJOCKLEK,
						ettRum.getÖVH().y() + ettRum.getHöjd()/2);
			case SÖDER:
				return new Punkt(
						ettRum.getÖVH().x() + ettRum.getBredd()/2,
						ettRum.getÖVH().y() + ettRum.getHöjd() - VÄGGTJOCKLEK);
			case VÄSTER:
				return new Punkt(
						ettRum.getÖVH().x() + VÄGGTJOCKLEK,
						ettRum.getÖVH().y() + ettRum.getHöjd()/2) ;
			default:
				return null; //Här vill jag byta ut mot currentPos eller något liknande senare.
		}
	}

	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) { // Definierar pivotpunkter
		switch(enRiktning){
			case NORR: return new Punkt(ettRum.getÖVH().x() + ettRum.getBredd()/2, ettRum.getÖVH().y() - VÄGGTJOCKLEK);
			case ÖSTER: return new Punkt(ettRum.getÖVH().x() + ettRum.getBredd() + VÄGGTJOCKLEK, ettRum.getÖVH().y() + ettRum.getHöjd()/2);
			case SÖDER: return new Punkt(ettRum.getÖVH().x() + ettRum.getBredd()/2, ettRum.getÖVH().y() + ettRum.getHöjd() + VÄGGTJOCKLEK);
			case VÄSTER: return new Punkt(ettRum.getÖVH().x() - VÄGGTJOCKLEK, ettRum.getÖVH().y()+ettRum.getHöjd()/2);
			default:
				return null; //Också currentPos eller nå senare. Kanske nivå.getRum();?
		}
	}


	private void ritaGång(Graphics g, Gång enGång) {
		Grafik.drawThickLine(g, pivotpunkt(enGång.getFrån(), enGång.getRiktningUtUrFrån()), pivotpunkt(enGång.getTill(), enGång.getRiktningInITill()), VÄGGTJOCKLEK, GÅNGFÄRG);
	}


	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		Rum nuvarandeRum = enNivå.getNuvarandeRum();
		Punkt mitt = new Punkt(
				nuvarandeRum.getÖVH().x() + nuvarandeRum.getBredd() / 2,
				nuvarandeRum.getÖVH().y() + nuvarandeRum.getHöjd() / 2
		);
		Grafik.fillCircle(g, mitt, GlobalaKonstanter.ANVÄNDARRADIE, GlobalaKonstanter.ANVÄNDARFÄRG);
	}
}
