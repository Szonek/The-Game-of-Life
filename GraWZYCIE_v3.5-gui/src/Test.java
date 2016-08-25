import java.io.IOException;

import org.jfree.data.xy.XYSeries;

public class Test {
	public static Komorka[][] komorka;
	public static XYSeries wartosci;

	public static void main(String[] args) throws IOException, InterruptedException {

		Test.komorka = new Komorka[Main.szerokoscMapyX][Main.wysokoscMapyY];
		wartosci = new XYSeries("XYGraph");

		new MyFrame();
		///asfagagag

	}

}
