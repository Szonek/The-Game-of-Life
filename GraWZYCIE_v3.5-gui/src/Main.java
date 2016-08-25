
import java.util.Random;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Main {

	public static int szerokoscMapyX = 30;
	public static int wysokoscMapyY = 30;
	public static int dlugoscZyciaJednegoPokoleniaWmiliSekundach = 0;
	public static int ilePokolenMaPokazacProgram = 0;
	public static int procentSzansNaZycieKomorki = 0;
	public static int minByKomorkaDalejZyla = 2;
	public static int maxByKomorkaDalejZyla = 3;
	public static int tyleByKomorkaOzylaZMartwych = 3;

	// losowanie jest od 0 do zakres-1
	public static int losowanieLiczby() {
		Random generator = new Random();

		int a = generator.nextInt(100); // deklaracja i definicja zmiennej;
										// wpisane 100 bo losowanie jest od 0 do
										// 99 ( czyli 100 % )
		return a;
	}

	public static void ustawZywePoczatkoweWylosowane(Komorka[][] komorka) {

		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {
				if (losowanieLiczby() <= procentSzansNaZycieKomorki - 1) {
					komorka[i][j].setStanZycia(true);
				} else {
					komorka[i][j].setStanZycia(false);
				}
			}
		}
	}

	public static int sprawdzLiczbeZywychSasiadow(Komorka[][] komorka, int polozenieY, int polozenieX) {
		int liczbaZywychSasiadow = 0;

		// warunki zeby nie wychodzic poza tablice
		int minY = 0, maxY = 0;
		int minX = 0, maxX = 0;
		// warunki dla wysokosc Y
		if (polozenieY == 0) {
			minY = polozenieY;
			maxY = polozenieY + 1;
		} else if (polozenieY == wysokoscMapyY - 1) {
			minY = polozenieY - 1;
			maxY = polozenieY;
		} else {
			minY = polozenieY - 1;
			maxY = polozenieY + 1;
		}
		// warunki dla szerokosci X
		if (polozenieX == 0) {
			minX = polozenieX;
			maxX = polozenieX + 1;
		} else if (polozenieX == szerokoscMapyX - 1) {
			minX = polozenieX - 1;
			maxX = polozenieX;
		} else {
			minX = polozenieX - 1;
			maxX = polozenieX + 1;
		}

		for (int i = minY; i <= maxY; i++) {
			for (int j = minX; j <= maxX; j++) {
				if (komorka[i][j].isStanZycia()) {
					liczbaZywychSasiadow++; // zwiekszamy liczbe sasiadow
				}

				if (komorka[polozenieY][polozenieX].isStanZycia() && i == polozenieY && j == polozenieX) // komorka
																											// nie
																											// moze
																											// sasiadowac
																											// sama
																											// ze
																											// soba
				{
					liczbaZywychSasiadow--;
				}
			}
		}
		return liczbaZywychSasiadow;
	}

	public static void ustawWartosciPoczatkoweKomorki(Komorka[][] komorka, boolean stanZycia) {
		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {
				komorka[i][j] = new Komorka(false, i, j, stanZycia);
			}
		}
	}

	public static void wyswietlajPlansze(Komorka[][] komorka) {
		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {
				komorka[i][j].wyswietlanieKomorki();
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	public static void ustawZycieWzaleznosciOdTegoIleSasiadow(Komorka[][] komorka) {
		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {

				if (komorka[i][j].isStanZycia() == true) {
					if (sprawdzLiczbeZywychSasiadow(komorka, komorka[i][j].getPolozenieY(),
							komorka[i][j].getPolozenieX()) == minByKomorkaDalejZyla
							|| sprawdzLiczbeZywychSasiadow(komorka, komorka[i][j].getPolozenieY(),
									komorka[i][j].getPolozenieX()) == maxByKomorkaDalejZyla) {

						komorka[i][j].setStanZyciaWnastepnejChwiliczasu(true);
					} else {
						komorka[i][j].setStanZyciaWnastepnejChwiliczasu(false); // w
																				// innym
																				// przypadku
																				// umiera
					}
				}
				if (komorka[i][j].isStanZycia() == false) {
					if (sprawdzLiczbeZywychSasiadow(komorka, komorka[i][j].getPolozenieY(),
							komorka[i][j].getPolozenieX()) == tyleByKomorkaOzylaZMartwych) {

						komorka[i][j].setStanZyciaWnastepnejChwiliczasu(true);
					} else {
						komorka[i][j].setStanZyciaWnastepnejChwiliczasu(false); // w
																				// innym
																				// przypadku
																				// podtrzymanie
																				// smierci
					}
				}

			}
		}
	}

	public static void przesunZegar(Komorka[][] komorka) {
		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {
				komorka[i][j].setStanZycia(komorka[i][j].isStanZyciaWnastepnejChwiliczasu());
			}
		}
	}

	public static int pokazIleZywychKomorek(Komorka[][] komorka) {
		int zywe = 0;
		for (int i = 0; i < wysokoscMapyY; i++) {
			for (int j = 0; j < szerokoscMapyX; j++) {
				if (komorka[i][j].isStanZycia() == true) {
					zywe++;
				}

			}
		}
		return zywe;
	}

	public static void obslugaUzytkownika() {
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj co ile czasu ma powstawac nowe pokolenie ( w milisekundach) ");
		dlugoscZyciaJednegoPokoleniaWmiliSekundach = input.nextInt();
		System.out.println();
		System.out.print("Podaj ile ma byc pokolen ");
		ilePokolenMaPokazacProgram = input.nextInt();
		System.out.println();
		do {
			System.out.print("Podaj jaka ma byc szansa na to ze komorka zyje na poczatku (w procentach): ");
			procentSzansNaZycieKomorki = input.nextInt();
		} while (procentSzansNaZycieKomorki >= 100 && procentSzansNaZycieKomorki <= 0);
		System.out.println();
		input.close();
	}

	public static void rysujWykres(XYSeries wartosci) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(wartosci);
		JFreeChart lineGraph = ChartFactory.createXYLineChart(
				"Liczba zywych komorek w poszczegolnych etapach eewolucji", // Title
				"Ewolucja", // X-Axis label
				"Zywe komorki", // Y-Axis label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot orientation
				false, // show legend
				true, // Show tooltips
				false // url show
		);

		ChartFrame frame = new ChartFrame("Wykres", lineGraph);
		frame.pack();
		frame.setVisible(true);
	}

}// koniec programu
