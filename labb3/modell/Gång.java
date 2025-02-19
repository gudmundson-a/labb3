package labb3.modell;

public class Gång {
    // TODO:
    // Lägg till tillståndsvariabler för att hålla parametrarna till
    //  konstruktorn.
    // ========== KLART! ==========
    private Rum från;
    private Väderstreck riktningUtUrFrån;
    private Rum till;
    private Väderstreck riktningInITill;

    public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till,
                Väderstreck riktningInITill) {
        // TODO:
        // Tilldela tillståndsvariablerna parametervärdena.
        // ========== KLART! ==========
        this.från = från;
        this.riktningUtUrFrån = riktningUtUrFrån;
        this.till = till;
        this.riktningInITill = riktningInITill;
    }

    // TODO:
    // Lägg till instansmetoder som returnerar tillståndsvariablernas
    //  värden.
    // ========== KLART! ==========
    public Rum getFrån() {
        return från;
    }

    public Väderstreck getRiktningUtUrFrån() {
        return riktningUtUrFrån;
    }

    public Rum getTill() {
        return till;
    }

    public void setRiktningInITill(Väderstreck riktningInITill) {
        this.riktningInITill = riktningInITill;
    }
}
