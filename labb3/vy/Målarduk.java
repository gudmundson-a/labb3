package labb3.vy;

import java.awt.*;
import javax.swing.*;

import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Punkt;

import static labb3.GlobalaKonstanter.*;
import static labb3.verktyg.Grafik.drawThickLine;
import static labb3.verktyg.Grafik.fillCircle;

public class Målarduk extends JPanel {
	private final Nivå nivå;

	public Målarduk(Nivå nivå) {
		this.nivå = nivå;
		this.setBackground(MARKFÄRG);
		this.setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Rum rum : nivå.getRummen()) {
			ritaRum(g, rum);
			ritaGångarFrånRum(g, rum);
			ritaUtgångar(g, rum);
		}
		ritaMarkörFörAnvändare(g);
	}


	private void ritaRum(Graphics g, Rum rum) {
		// Rita golv
		g.setColor(rum.getGolvfärg());
		g.fillRect(rum.getPunkt().x(), rum.getPunkt().y(), rum.getBredd(), rum.getHöjd());


		// Beräkna väggkoordinater
		int x = rum.getPunkt().x();
		int y = rum.getPunkt().y();
		int bredd = rum.getBredd();
		int höjd = rum.getHöjd();

		// Rita väggar
		ritaVägg(g, x - HALV_VÄGGTJOCKLEK, y, x + bredd + HALV_VÄGGTJOCKLEK, y); // Norr
		ritaVägg(g, x, y - HALV_VÄGGTJOCKLEK, x, y + höjd + HALV_VÄGGTJOCKLEK); // Väster
		ritaVägg(g, x - HALV_VÄGGTJOCKLEK, y + höjd, x + bredd + HALV_VÄGGTJOCKLEK, y + höjd); // Söder
		ritaVägg(g, x + bredd, y - HALV_VÄGGTJOCKLEK, x + bredd, y + höjd + HALV_VÄGGTJOCKLEK); // Öster
	}

	private void ritaVägg(Graphics g, int x1, int y1, int x2, int y2) {
		drawThickLine(g, new Punkt(x1, y1), new Punkt(x2, y2), VÄGGTJOCKLEK, VÄGGFÄRG);
	}

	private void ritaGångarFrånRum(Graphics g, Rum rum) {
		for (Väderstreck riktning : Väderstreck.values()) {
			if (rum.finnsUtgångÅt(riktning)) {
				Punkt baspunkt = beräknaBaspunkt(rum, riktning);
				Punkt pivotpunkt = beräknaPivotpunkt(rum, riktning);
				drawThickLine(g, baspunkt, pivotpunkt, VÄGGTJOCKLEK, GÅNGFÄRG);
				fillCircle(g, pivotpunkt, HALV_VÄGGTJOCKLEK, GÅNGFÄRG);
			}
		}
	}

	private Punkt beräknaBaspunkt(Rum rum, Väderstreck riktning) {
		int x = rum.getPunkt().x();
		int y = rum.getPunkt().y();
		int bredd = rum.getBredd();
		int höjd = rum.getHöjd();

		switch (riktning) {
			case NORR:
				return new Punkt(x + bredd / 2, y + HALV_VÄGGTJOCKLEK);
			case ÖSTER:
				return new Punkt(x + bredd - HALV_VÄGGTJOCKLEK, y + höjd / 2);
			case SÖDER:
				return new Punkt(x + bredd / 2, y + höjd - HALV_VÄGGTJOCKLEK);
			case VÄSTER:
				return new Punkt(x + HALV_VÄGGTJOCKLEK, y + höjd / 2);
			default:
				throw new IllegalArgumentException("Ogiltig riktning: " + riktning);
		}
	}

	private Punkt beräknaPivotpunkt(Rum rum, Väderstreck riktning) {
		int x = rum.getPunkt().x();
		int y = rum.getPunkt().y();
		int bredd = rum.getBredd();
		int höjd = rum.getHöjd();

		switch (riktning) {
			case NORR:
				return new Punkt(x + bredd / 2, y - VÄGGTJOCKLEK);
			case ÖSTER:
				return new Punkt(x + bredd + VÄGGTJOCKLEK, y + höjd / 2);
			case SÖDER:
				return new Punkt(x + bredd / 2, y + höjd + VÄGGTJOCKLEK);
			case VÄSTER:
				return new Punkt(x - VÄGGTJOCKLEK, y + höjd / 2);
			default:
				throw new IllegalArgumentException("Ogiltig riktning: " + riktning);
		}
	}

	private void ritaUtgångar(Graphics g, Rum rum) {
		for (Väderstreck riktning : Väderstreck.values()) {
			if (rum.finnsUtgångÅt(riktning)) {
				Gång gång = rum.gångenÅt(riktning);
				ritaGång(g, gång);
			}
		}
	}

	private void ritaGång(Graphics g, Gång gång) {
		Punkt frånPunkt = beräknaPivotpunkt(gång.getFrån(), gång.getRiktningUtUrFrån());
		Punkt tillPunkt = beräknaPivotpunkt(gång.getTill(), gång.getRiktningInITill());
		drawThickLine(g, frånPunkt, tillPunkt, VÄGGTJOCKLEK, GÅNGFÄRG);
	}

	private void ritaMarkörFörAnvändare(Graphics g) {
		Rum aktuelltRum = nivå.getNuvarandeRum();
		Punkt mitten = new Punkt(
				aktuelltRum.getPunkt().x() + aktuelltRum.getBredd() / 2,
				aktuelltRum.getPunkt().y() + aktuelltRum.getHöjd() / 2
		);
		fillCircle(g, mitten, HALV_VÄGGTJOCKLEK, ANVÄNDARFÄRG);
	}
}