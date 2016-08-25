
public class Komorka {

	private boolean stanZycia;
	private boolean stanZyciaWnastepnejChwiliczasu;
	private char zywaKomorka = '\u25A0'; // wypleniony kwadrat //full block to
											// '\u2588'; \u25A0
	private char martwaKomorka = '\u25CB'; // = '\u25CB'; //puste kolo //U+25CB
	private int polozenieX;
	private int polozenieY;

	Komorka(boolean stanZycia, int polozenieY, int polozenieX, boolean stanZyciaWnastepnejChwiliczasu) // konstruktor
																										// z
																										// 3zmiennymi
	{
		this.stanZycia = stanZycia;
		this.polozenieY = polozenieY;
		this.polozenieX = polozenieX;
		this.stanZyciaWnastepnejChwiliczasu = stanZyciaWnastepnejChwiliczasu;
	}

	public void wyswietlanieKomorki() {
		if (isStanZycia()) {
			System.out.print(zywaKomorka);
		} else {
			System.out.print(martwaKomorka);
		}
	}

	// ----------------------
	// settery i gettery
	// ---------------------
	public boolean isStanZycia() {
		return stanZycia;
	}

	public void setStanZycia(boolean stanZycia) {
		this.stanZycia = stanZycia;
	}

	public boolean isStanZyciaWnastepnejChwiliczasu() {
		return stanZyciaWnastepnejChwiliczasu;
	}

	public void setStanZyciaWnastepnejChwiliczasu(boolean stanZyciaWnastepnejChwiliczasu) {
		this.stanZyciaWnastepnejChwiliczasu = stanZyciaWnastepnejChwiliczasu;
	}

	public int getPolozenieX() {
		return polozenieX;
	}

	public void setPolozenieX(int polozenieX) {
		this.polozenieX = polozenieX;
	}

	public int getPolozenieY() {
		return polozenieY;
	}

	public void setPolozenieY(int polozenieY) {
		this.polozenieY = polozenieY;
	}
	// ----------------------
	// settery i gettery
	// ---------------------

}
