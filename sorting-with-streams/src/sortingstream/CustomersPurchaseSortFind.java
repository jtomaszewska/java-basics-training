/**
 *
 *  @author Tomaszewska Justyna S15313
 *
 */

package sortingstream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CustomersPurchaseSortFind {

	private List<Purchase> purchase = new ArrayList<>();
	private Map<String, Comparator<Purchase>> sortPurchase = new HashMap<>();
	private Map<String, Consumer<Purchase>> printPurchase = new HashMap<>();

	public CustomersPurchaseSortFind() {
		sortPurchase.put("Nazwiska", Comparator.comparing(Purchase::getNazwiskoImie)
				.thenComparing(Purchase::getIdKlienta));
		sortPurchase.put("Koszty", Comparator.comparing(Purchase::getKoszty).reversed()
				.thenComparing(Purchase::getIdKlienta));
		printPurchase.put("Nazwiska", p -> System.out.println(p));
		printPurchase.put("Koszty", p -> System.out.println(p+" (koszt: "+p.getKoszty()+")"));
	}


	public void readFile(String fname) {
		Path path = Paths.get(fname);
		try {
			for (String line : Files.readAllLines(path)) {
				purchase.add(new Purchase(line.split(";")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void showSortedBy(String string) {
		System.out.println(string);
		purchase
		.stream()
		.sorted(sortPurchase.get(string))
		.forEach(printPurchase.get(string));
		System.out.println();
	}

	public void showPurchaseFor(String id) {
		System.out.println("Klient "+id);
		 purchase.stream().filter(p -> p.getIdKlienta().equals(id))
		 .forEach(p -> System.out.println(p));
		 System.out.println();
	}
}
