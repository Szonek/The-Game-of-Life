import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ObslugaProgramu obs;
	Color c = Color.WHITE;
	Rectangle[][] kwadrat = new Rectangle[Main.szerokoscMapyX][Main.wysokoscMapyY];
	boolean start = false;
	private Timer zegar;
	private int licznikPokolenWTimerze = 0;

	class Zadanie extends TimerTask {
		public void run() {
			licznikPokolenWTimerze++;
			if (licznikPokolenWTimerze == Main.ilePokolenMaPokazacProgram) {
				czynnoscGdyAnimacjaDobiegniaDoKonca();
			}

			Main.ustawZycieWzaleznosciOdTegoIleSasiadow(Test.komorka);
			Main.przesunZegar(Test.komorka);
			Test.wartosci.add(licznikPokolenWTimerze, Main.pokazIleZywychKomorek(Test.komorka));
			repaint();
			Main.wyswietlajPlansze(Test.komorka);
			System.out.println("");

		}

		public void czynnoscGdyAnimacjaDobiegniaDoKonca() {
			zegar.cancel(); // -> zatrzymanie animacji
			ustawStanButtona(obs.getjPrzyciskZatwierdzeniaDanych(), false);
			ustawStanButtona(obs.getjStart(), false);
			ustawStanButtona(obs.getjGenerujWykres(), true);
			
		}

	}

	MyFrame() {
		super("Gra w zycie");
		setBounds(50, 50, 1024, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		Main.ustawWartosciPoczatkoweKomorki(Test.komorka, false);

		obs = new ObslugaProgramu();

		// buttony
		dodajButtonyDoOkna();

		// radiobuttony
		add(obs.getrStandard());
		add(obs.getrCustom());
		obs.getrStandard().setEnabled(false);
		obs.getrCustom().setEnabled(false);
		obs.getrStandard().addActionListener(this);
		obs.getrCustom().addActionListener(this);
		// labele
		dodajLabeleDoOkna();

		// textfieldy
		dodajTextFieldyDoOkna();

		// koncowka inicjalizacji
		addMouseListener((MouseListener) this);
		setVisible(true);
		createBufferStrategy(2);
	}

	public void dodajLabeleDoOkna() {
		add(obs.getLabelSzansaNaZycoe());
		add(obs.getLabelIleNowePokolenie());
		add(obs.getLabelIlePokolen());
		add(obs.getLabelRozmiarMapy());
		add(obs.getLabelRysowanie());
		add(obs.getLabelPierwszyParametrDoZycia());
		add(obs.getLabelDrugiParametrDoZycia());
		add(obs.getLabelParametrDoOzywienia());
		
		obs.getLabelRysowanie().setEnabled(false);
		obs.getLabelSzansaNaZycoe().setEnabled(false);
		
		
		obs.getLabelParametrySymulacji().setFont(new Font("Serif", Font.BOLD, 15));
		add(obs.getLabelParametrySymulacji());

		obs.getLabelOpisZasad().setFont(new Font("Serif", Font.BOLD, 15));
		add(obs.getLabelOpisZasad());

	}

	public void dodajTextFieldyDoOkna() {
		add(obs.gettfSzansaNaZycie());
		add(obs.gettfIlePokolen());
		add(obs.gettfCoIleNowePokolenie());
		add(obs.gettfRozmiarMapy());
		add(obs.getTfPierwszyParametrDoZycia());
		add(obs.getTfDrugiParametrDoZycia());
		add(obs.getTfParametrDoOzywienia());
		obs.gettfSzansaNaZycie().setEnabled(false);

	}

	public void dodajButtonyDoOkna() {
		add(obs.getjPrzyciskZatwierdzeniaDanych());
		add(obs.getjStart());
		add(obs.getjGenerujWykres());
		add(obs.getjPrzyciskZatwierdzaniaMniejszy());

		ustawStanButtona(obs.getjPrzyciskZatwierdzeniaDanych(), false);
		ustawStanButtona(obs.getjGenerujWykres(), false);
		ustawStanButtona(obs.getjStart(), false);
		ustawStanButtona(obs.getjGenerujWykres(), false);
		dodajActionListeneryDoButtonow();

	}

	public void dodajActionListeneryDoButtonow() {
		obs.getjPrzyciskZatwierdzeniaDanych().addActionListener(this);
		obs.getjStart().addActionListener(this);
		obs.getjGenerujWykres().addActionListener(this);
		obs.getjPrzyciskZatwierdzaniaMniejszy().addActionListener(this);
	}

	public void ustawStanButtona(JButton jb, boolean stan) {
		jb.setEnabled(stan);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (obs.getrCustom().isSelected()) {
			if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
				sprawdzCzyKlkniecieMiesciSieWZakresieIUstawStanKomorki(e.getX(), e.getY(), e);
			}

		}
		repaint();
	}

	public void sprawdzCzyKlkniecieMiesciSieWZakresieIUstawStanKomorki(int wspX, int wspY, MouseEvent e) {
		for (int i = 0; i < Main.wysokoscMapyY; i++) {
			for (int j = 0; j < Main.szerokoscMapyX; j++) {

				if (wspX > 50 + j * (450 / Main.szerokoscMapyX)
						&& wspX < (50 + j * (450 / Main.szerokoscMapyX) + (450 / Main.szerokoscMapyX))) {
					if (wspY > 50 + i * (450 / Main.szerokoscMapyX)
							&& wspY < (50 + i * (450 / Main.szerokoscMapyX) + (450 / Main.szerokoscMapyX))) {

						System.out.println("X: " + wspX + ", " + "Y: " + wspY + ", ");
						if (e.getButton() == MouseEvent.BUTTON1)
							Test.komorka[i][j].setStanZycia(true);
						else if (e.getButton() == MouseEvent.BUTTON3)
							Test.komorka[i][j].setStanZycia(false);

					}
				}

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();

		if (source == obs.getjPrzyciskZatwierdzaniaMniejszy()) {
			wykonajCzynnosciPoWcisniesuMalyZatwierdz();
		} else if (source == obs.getjPrzyciskZatwierdzeniaDanych()) {
			wykonajCzynnosciPoWcisniesciuAkceptacjiDanych();
		} else if (source == obs.getjStart()) {
			wykonajCzynnosciPoWciniesciuStartuSymulacji();
		} else if (source == obs.getjGenerujWykres()) {
			wykonajCzynnosciPoWcisniescuGeneracjiWykresu();
		}
		if (obs.getrStandard().isSelected()) {
			obs.getTfSzansaNaZycie().setEnabled(true);
			obs.getLabelSzansaNaZycoe().setEnabled(true);
			obs.getLabelRysowanie().setEnabled(false);
		} else if (obs.getrCustom().isSelected()) {
			obs.getTfSzansaNaZycie().setEnabled(false);
			obs.getLabelSzansaNaZycoe().setEnabled(false);
			obs.getLabelRysowanie().setEnabled(true);
		}
	}

	public void wykonajCzynnosciPoWcisniesciuAkceptacjiDanych() {
		licznikPokolenWTimerze = 0;
		przypiszWylosowaneDoMaina();
		Main.wyswietlajPlansze(Test.komorka);
		Test.wartosci.clear();
		Test.wartosci.add(0, Main.pokazIleZywychKomorek(Test.komorka));
		repaint();
		ustawStanButtona(obs.getjStart(), true);
		ustawStanButtona(obs.getjGenerujWykres(), false);
		ustawStanButtona(obs.getjPrzyciskZatwierdzaniaMniejszy(), false);
		obs.gettfRozmiarMapy().setEnabled(false);
		obs.gettfIlePokolen().setEnabled(false);
		obs.getTfCoIleNowePokolenie().setEnabled(false);
		obs.getTfDrugiParametrDoZycia().setEnabled(false);
		obs.getTfParametrDoOzywienia().setEnabled(false);
		obs.getTfPierwszyParametrDoZycia().setEnabled(false);

	}

	public void wykonajCzynnosciPoWcisniesuMalyZatwierdz() {
		przypiszZmienneZFieldowDoWartosciMaina();
		if (Main.ilePokolenMaPokazacProgram > 0 && Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach > 0
				&& Main.wysokoscMapyY >= 5 && Main.wysokoscMapyY <= 30
				&& Main.minByKomorkaDalejZyla >=0 && Main.minByKomorkaDalejZyla <=8
				&& Main.maxByKomorkaDalejZyla >=0 && Main.maxByKomorkaDalejZyla <=8
				&& Main.maxByKomorkaDalejZyla != Main.minByKomorkaDalejZyla
				&& Main.tyleByKomorkaOzylaZMartwych >=0 && Main.tyleByKomorkaOzylaZMartwych <=8
				) {
			
			ustawStanButtona(obs.getjPrzyciskZatwierdzeniaDanych(), true);
			obs.getrCustom().setEnabled(true);
			obs.getrStandard().setEnabled(true);
			obs.getTfSzansaNaZycie().setEnabled(true);
			obs.getLabelSzansaNaZycoe().setEnabled(true);
			Main.ustawWartosciPoczatkoweKomorki(Test.komorka, false);
			repaint();
		} else {
			System.out.println("Bledne dane.");
			ustawStanButtona(obs.getjPrzyciskZatwierdzeniaDanych(), false);
			obs.getrCustom().setEnabled(false);
			obs.getrStandard().setEnabled(false);
			obs.getTfSzansaNaZycie().setEnabled(false);
			obs.getLabelSzansaNaZycoe().setEnabled(false);
		}
	}

	public void przypiszZmienneZFieldowDoWartosciMaina() {
		Main.ilePokolenMaPokazacProgram = ObslugaProgramu.getIlePokolenMaByc();
		Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach = ObslugaProgramu.getCoIleNowePokolenie();
		Main.wysokoscMapyY = ObslugaProgramu.getJakiRozmiarMapy();
		Main.szerokoscMapyX = ObslugaProgramu.getJakiRozmiarMapy();
		Main.minByKomorkaDalejZyla = ObslugaProgramu.getJakiPierwszyParametr();
		Main.maxByKomorkaDalejZyla = ObslugaProgramu.getJakiDrugiParametr();
		Main.tyleByKomorkaOzylaZMartwych = ObslugaProgramu.getJakiParametrOzywienia();
		System.out.println("Bedzie przeprowadzonych pokolen: " + Main.ilePokolenMaPokazacProgram);
		System.out.println(
				"Nastepne pokolenia beda sie pojawiac co [ms]: " + Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach);
		System.out.println("Bok mapy ma dlugosc: " + Main.wysokoscMapyY + ", " + Main.szerokoscMapyX);
		System.out.println("Komorka dalej bedzie zyla jesli bedzie miec zywych sasiadow: " +Main.minByKomorkaDalejZyla +", lub " + Main.maxByKomorkaDalejZyla);
		System.out.println("Komorka ozyje z martwych, gdy bedzie miec zywych sasiadow: " + Main.tyleByKomorkaOzylaZMartwych );
	}

	public void przypiszWylosowaneDoMaina() {

		if (obs.getrStandard().isSelected()) {
			Main.procentSzansNaZycieKomorki = ObslugaProgramu.getSzansa();
			
			Main.ustawZywePoczatkoweWylosowane(Test.komorka);

		} else if (obs.getrCustom().isSelected()) {
			Main.procentSzansNaZycieKomorki = 0;
		}

		System.out.println("Sznasa ze komorka zyje: " + Main.procentSzansNaZycieKomorki);

	}

	public void wykonajCzynnosciPoWciniesciuStartuSymulacji() {
		if (Main.procentSzansNaZycieKomorki <= 100 && Main.ilePokolenMaPokazacProgram != 0
				&& Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach != 0 && Main.wysokoscMapyY >= 5
				&& Main.wysokoscMapyY <= 30) {
			System.out.println("Akceptacja startu programu");
			ustawStanButtona(obs.getjPrzyciskZatwierdzeniaDanych(), false);
			ustawStanButtona(obs.getjStart(), false);
			obs.getrCustom().setEnabled(false);
			obs.getrStandard().setEnabled(false);
			obs.gettfSzansaNaZycie().setEnabled(false);

			zegar = new Timer();
			zegar.scheduleAtFixedRate(new Zadanie(), Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach,
					Main.dlugoscZyciaJednegoPokoleniaWmiliSekundach);

		} else
			System.out.println("Blad startu programu. Jakas wartosc jest bledna.");
	}

	public void wykonajCzynnosciPoWcisniescuGeneracjiWykresu() {
		if (licznikPokolenWTimerze == Main.ilePokolenMaPokazacProgram) {

			Main.rysujWykres(Test.wartosci);
			ustawStanButtona(obs.getjStart(), false);
			ustawStanButtona(obs.getjGenerujWykres(), false);
			ustawStanButtona(obs.getjPrzyciskZatwierdzaniaMniejszy(), true);
			obs.gettfRozmiarMapy().setEnabled(true);
			obs.gettfIlePokolen().setEnabled(true);
			obs.gettfCoIleNowePokolenie().setEnabled(true);
			obs.getTfSzansaNaZycie().setText("0");
			obs.getTfDrugiParametrDoZycia().setEnabled(true);
			obs.getTfParametrDoOzywienia().setEnabled(true);
			obs.getTfPierwszyParametrDoZycia().setEnabled(true);
		}
	}

	@Override
	public void paint(Graphics g) {

		BufferStrategy bstrategy = this.getBufferStrategy();
		Graphics2D g2D = (Graphics2D) bstrategy.getDrawGraphics();
		super.paint((Graphics2D) bstrategy.getDrawGraphics());

		for (int i = 0; i < Main.wysokoscMapyY; i++) {
			for (int j = 0; j < Main.szerokoscMapyX; j++) {
				Rectangle r = new Rectangle(50 + j * (450 / Main.szerokoscMapyX), 50 + i * (450 / Main.szerokoscMapyX),
						(450 / Main.szerokoscMapyX), (450 / Main.szerokoscMapyX));
				kwadrat[i][j] = r;
				if (Test.komorka[i][j].isStanZycia() == true) {
					c = Color.black;

				} else if (Test.komorka[i][j].isStanZycia() == false) {
					c = Color.WHITE;
				}
				g2D.setPaint(c);
				g2D.fill(kwadrat[i][j]);
			}

		}
		g2D.dispose();
		bstrategy.show();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
