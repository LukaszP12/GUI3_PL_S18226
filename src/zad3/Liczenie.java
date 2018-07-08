package zad3;

import java.util.ArrayList;

public class Liczenie extends Thread {
	private Synchronizacja t;
	private long suma;
	private ArrayList<Towar> lista;

	public Liczenie(Synchronizacja t) {
		this.t = t;
	}

	public void run() {
		int waga = 0;
		while (true) {
			lista = new ArrayList<Towar>(t.get());
			if (lista.size() != 0) {
				for (int i = 0; i < lista.size(); i++) {
					Towar towar = lista.get(i);
					if (towar == null) {
						continue;
					}
					waga++;
					if (waga % 100 == 0) {
						System.out.println("policzono wage " + waga
								+ " towarów");
					}
					suma += towar.getWaga();
				}
			} else {
				break;

			}
		}
		System.out.println("Waga wszystkich towarów wynosi: " + suma);
	}

}
