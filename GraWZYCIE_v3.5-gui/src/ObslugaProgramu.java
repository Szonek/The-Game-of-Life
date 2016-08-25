
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ObslugaProgramu {

	private static JButton jPrzyciskZatwierdzeniaDanych;
	private static JButton jStart;
	private static JButton jGenerujWykres;
	private static JButton jPrzyciskZatwierdzaniaMniejszy;

	private static JTextField tfSzansaNaZycie;
	private static JLabel labelSzansaNaZycoe;

	private static JTextField tfCoIleNowePokolenie;
	private static JLabel labelIleNowePokolenie;

	private static JTextField tfIlePokolen;
	private static JLabel labelIlePokolen;

	private static JTextField tfRozmiarMapy;
	private static JLabel labelRozmiarMapy;

	private static JLabel labelParametrySymulacji;
	private static JLabel labelOpisZasad;
	private static JLabel labelRysowanie;

	private static JRadioButton rStandard;
	private static JRadioButton rCustom;
	

	private static JTextField tfPierwszyParametrDoZycia;
	private static JLabel labelPierwszyParametrDoZycia;
	
	private static JTextField tfDrugiParametrDoZycia;
	private static JLabel labelDrugiParametrDoZycia;
	
	private static JTextField tfParametrDoOzywienia;
	private static JLabel labelParametrDoOzywienia;
	
	public JTextField getTfParametrDoOzywienia() {
		return tfParametrDoOzywienia;
	}

	public JLabel getLabelParametrDoOzywienia() {
		return labelParametrDoOzywienia;
	}

	public JTextField getTfIlePokolen() {
		return tfIlePokolen;
	}

	public JTextField getTfRozmiarMapy() {
		return tfRozmiarMapy;
	}

	public JTextField getTfPierwszyParametrDoZycia() {
		return tfPierwszyParametrDoZycia;
	}

	public JLabel getLabelPierwszyParametrDoZycia() {
		return labelPierwszyParametrDoZycia;
	}

	public JTextField getTfDrugiParametrDoZycia() {
		return tfDrugiParametrDoZycia;
	}

	public JLabel getLabelDrugiParametrDoZycia() {
		return labelDrugiParametrDoZycia;
	}

	public JTextField getTfSzansaNaZycie() {
		return tfSzansaNaZycie;
	}

	public JTextField getTfCoIleNowePokolenie() {
		return tfCoIleNowePokolenie;
	}

	public JRadioButton getrStandard() {
		return rStandard;
	}

	public JRadioButton getrCustom() {
		return rCustom;
	}

	public JButton getjPrzyciskZatwierdzaniaMniejszy() {
		return jPrzyciskZatwierdzaniaMniejszy;
	}
	
	public JButton getjGenerujWykres() {
		return jGenerujWykres;
	}

	public JButton getjStart() {
		return jStart;
	}

	public JButton getjPrzyciskZatwierdzeniaDanych() {
		return jPrzyciskZatwierdzeniaDanych;
	}

	public JTextField gettfSzansaNaZycie() {
		return tfSzansaNaZycie;
	}

	public JLabel getLabelSzansaNaZycoe() {
		return labelSzansaNaZycoe;
	}

	public JTextField gettfCoIleNowePokolenie() {
		return tfCoIleNowePokolenie;
	}

	public JLabel getLabelIleNowePokolenie() {
		return labelIleNowePokolenie;
	}

	public JTextField gettfIlePokolen() {
		return tfIlePokolen;
	}

	public JLabel getLabelIlePokolen() {
		return labelIlePokolen;
	}

	public JTextField gettfRozmiarMapy() {
		return tfRozmiarMapy;
	}

	public JLabel getLabelRozmiarMapy() {
		return labelRozmiarMapy;
	}

	public JLabel getLabelParametrySymulacji() {
		return labelParametrySymulacji;
	}

	public JLabel getLabelOpisZasad() {
		return labelOpisZasad;
	}
	
	public JLabel getLabelRysowanie() {
		return labelRysowanie;
	}
	
	public static int getSzansa() {
		String text = tfSzansaNaZycie.getText();
		int szansaNaZycie = Integer.parseInt(text);
		return szansaNaZycie;
	}

	public static int getCoIleNowePokolenie() {
		String text = tfCoIleNowePokolenie.getText();
		int czasWms = Integer.parseInt(text);
		return czasWms;
	}

	public static int getIlePokolenMaByc() {
		String text = tfIlePokolen.getText();
		int ilePokolen = Integer.parseInt(text);
		return ilePokolen;
	}

	public static int getJakiRozmiarMapy() {
		String text = tfRozmiarMapy.getText();
		int jakiRozmiar = Integer.parseInt(text);
		return jakiRozmiar;
	}
	
	public static int getJakiPierwszyParametr()
	{
		String text = tfPierwszyParametrDoZycia.getText();
		int jakiParametrPierwszy = Integer.parseInt(text);
		return jakiParametrPierwszy;
	}
	
	public static int getJakiDrugiParametr()
	{
		String text = tfDrugiParametrDoZycia.getText();
		int jakiParametrDrugi = Integer.parseInt(text);
		return jakiParametrDrugi;
	}
	
	public static int getJakiParametrOzywienia()
	{
		String text = tfParametrDoOzywienia.getText();
		int jakiParametrOzywienia = Integer.parseInt(text);
		return jakiParametrOzywienia;
	}
	
	public ObslugaProgramu() {

		// BUTTONY
		jPrzyciskZatwierdzaniaMniejszy = new JButton("Zatwierdz");
		jPrzyciskZatwierdzaniaMniejszy.setBounds(675, 170, 100, 25);
		
		jPrzyciskZatwierdzeniaDanych = new JButton("Zatwierdz obszar ewolucji");
		jPrzyciskZatwierdzeniaDanych.setBounds(675, 350, 150, 50);

		jStart = new JButton("Start ewolucji");
		jStart.setBounds(675, 410, 150, 50);

		jGenerujWykres = new JButton("Generuj wykres!");
		jGenerujWykres.setBounds(675, 460, 150, 50);
		// ----------------------

		// LABELE I POLA DO WPISYWANIA


		labelIleNowePokolenie = new JLabel("Nowa ewolucja[ms]: ");
		labelIleNowePokolenie.setBounds(510, 80, 125, 25);
		tfCoIleNowePokolenie = new JTextField();
		tfCoIleNowePokolenie.setBounds(640, 80, 100, 25);

		labelIlePokolen = new JLabel("Ile ewolucji: ");
		labelIlePokolen.setBounds(510, 105, 125, 25);
		tfIlePokolen = new JTextField();
		tfIlePokolen.setBounds(640, 105, 100, 25);

		labelRozmiarMapy = new JLabel("(Bok mapy)^2 <5,30>: ");
		labelRozmiarMapy.setBounds(510, 130, 125, 25);
		tfRozmiarMapy = new JTextField();
		tfRozmiarMapy.setBounds(640, 130, 100, 25);
		
		labelSzansaNaZycoe = new JLabel("Szansa na zycie[%]: ");
		labelSzansaNaZycoe.setBounds(510, 280, 125, 25);
		tfSzansaNaZycie = new JTextField();
		tfSzansaNaZycie.setBounds(640, 280, 100, 25);
		
		labelPierwszyParametrDoZycia = new JLabel("Zycie 1par.:");
		labelPierwszyParametrDoZycia.setBounds(800, 80, 70, 25);
		tfPierwszyParametrDoZycia = new JTextField("2");
		tfPierwszyParametrDoZycia.setBounds(875, 80, 80, 25);
		
		
		labelDrugiParametrDoZycia = new JLabel("Zycie 2par.:");
		getLabelDrugiParametrDoZycia().setBounds(800, 105, 70, 25);
		tfDrugiParametrDoZycia = new JTextField("3");
		tfDrugiParametrDoZycia.setBounds(875, 105, 80, 25);
		
		labelParametrDoOzywienia = new JLabel("Ozywienie: ");
		labelParametrDoOzywienia.setBounds(800, 130, 70, 25);
		tfParametrDoOzywienia = new JTextField("3");
		tfParametrDoOzywienia.setBounds(875, 130, 80, 25);
		
		// -----------------------------

		// RADIOBUTTONY
		ButtonGroup gp = new ButtonGroup();
		rStandard = new JRadioButton("Standard");
		rStandard.setBounds(650, 250, 125, 25);

		rCustom = new JRadioButton("Custom");
		rCustom.setBounds(775, 250, 125, 25);
		gp.add(rStandard);
		gp.add(rCustom);
		// ------------

		
		// LABELE INFORUMUJACE
		labelParametrySymulacji = new JLabel("Ustaw parametry symulacji:");
		labelParametrySymulacji.setBounds(650, 30, 200, 50);

		labelOpisZasad = new JLabel("Ustaw zasady symulacji:");
		labelOpisZasad.setBounds(650, 200, 200, 50);
		
		labelRysowanie = new JLabel("Rysowanie");
		labelRysowanie.setBounds(775, 280, 100, 25);
		// --------------------------------------------

	}

}
