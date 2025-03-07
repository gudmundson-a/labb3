package labb3.kontroll;

import labb3.modell.Nivå;
import labb3.modell.Väderstreck;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tangentbordslyssnare implements KeyListener {
    private final Nivå enNivå;

    public Tangentbordslyssnare(Nivå enNivå) {
        this.enNivå = enNivå;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO:
        // Skriv klar denna metod som automatiskt anropas så snart
        //  användaren tryckt på en tangent på tangentbordet. Metoden ska
        //  översätta tangenten till ett väderstreck och sen anropa hoppaÅt i
        //  enNivå med detta väderstreck. Här ska:
        //  w betyda "hoppa åt NORR",
        //  d betyda "hoppa åt ÖSTER",
        //  s betyda "hoppa åt SÖDER" och
        //  a betyda "hoppa åt VÄSTER".
        // ========== KLART! ==========

        char c = e.getKeyChar();

        Väderstreck riktning = null;
        switch (c) {
            case 'w':
                riktning = Väderstreck.NORR;
                break;
            case 's':
                riktning = Väderstreck.SÖDER;
                break;
            case 'd':
                riktning = Väderstreck.ÖSTER;
                break;
            case 'a':
                riktning = Väderstreck.VÄSTER;
                break;

            default:
                return;
        }

        if (riktning != null) {
            System.out.println("Index för: " + riktning + " = " + riktning.getIndex());
            enNivå.hoppaÅt(riktning);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        // Används inte men måste implementeras eftersom keyTyped finns i
        // gränssnittet KeyListener.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Används inte men måste implementeras eftersom keyReleased finns is
        // gränssnittet KeyListener.
    }
}
