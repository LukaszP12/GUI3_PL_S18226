package zad3;

import java.util.ArrayList;
import java.util.List;

public class Synchronizacja {
	private ArrayList<Towar> listaTowarow = new ArrayList<Towar>();
	private volatile boolean pobranie = false;

	public synchronized void put(List<Towar> tow) {

		try {
			while (pobranie) {
				try {
					wait();
				} catch (Exception e) {
				}

			}
			listaTowarow = new ArrayList<Towar>(tow);
			tow.clear();
			pobranie = true;
		} catch (Exception e) {
		} finally {
			notifyAll();
		}

	}

	public synchronized ArrayList<Towar> get() {
		try {
			while (!pobranie) {
				try {
					wait();
				} catch (Exception e) {
				}

			}
			pobranie = false;
			return listaTowarow;
		} catch (Exception e) {
			return listaTowarow;
		} finally {
			notifyAll();
		}

	}

}
