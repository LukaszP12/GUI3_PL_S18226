package zad3;

public class Main {

	public static void main(String[] args) {
		Synchronizacja towary = new Synchronizacja();
		new Producent(towary).start();
		new Liczenie(towary).start();
	}
}
