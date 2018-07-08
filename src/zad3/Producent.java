package zad3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Producent extends Thread {
	private Synchronizacja synchro;

	public Producent(Synchronizacja synchro) {
		this.synchro = synchro;
	}

	public void run() {
		try {
			Scanner skaner = new Scanner(new File("../Towary.txt"));
			int obiekty = 0;
			List<Towar> nextPackage = new ArrayList<Towar>();
			while (skaner.hasNextLine()) {
				obiekty++;
				String[] tab = skaner.nextLine().split(" ");
				nextPackage.add(new Towar(tab[0], Integer.parseInt(tab[1])));
				if (obiekty % 200 == 0) {
					System.out.println("utworzono " + obiekty + " obiektów");
					synchro.put(nextPackage);
				}
			}
			skaner.close();
			if (nextPackage.size() > 0) {
				synchro.put(nextPackage);
			}
			synchro.put(new ArrayList<Towar>());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
