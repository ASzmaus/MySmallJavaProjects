package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import zad2.Purchase.PurchaseComparator;

public class CustomersPurchaseSortFind {
	private List<Purchase> purchaseList;
	
	public void readFile(String fname) {
		File file = new File(fname);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			purchaseList = new LinkedList<>();
			while(sc.hasNextLine()) {
				String stringToSplit=sc.nextLine();
				String[] splitStringArray =stringToSplit.split(";");
				Purchase newPurchase = new Purchase(splitStringArray[0], splitStringArray[1],splitStringArray[2],Double.parseDouble(splitStringArray[3]), Double.parseDouble(splitStringArray[4]));
				purchaseList.add(newPurchase);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
	}

	public void showSortedBy(String string) {
		System.out.println(string);
		PurchaseComparator purchaseComparator = new PurchaseComparator();
        purchaseComparator.setSortingField(string);
        Collections.sort(purchaseList, purchaseComparator);
        if(string.equals("Koszty")){
        	purchaseList.forEach(p->System.out.println(p + " (koszt: "+ p.getPrice()*p.getQuantityPurchased()+")"));
        }else {
        		purchaseList.forEach(System.out::println);
        }
		System.out.println(" ");;		
	}

	public void showPurchaseFor(String id) {
		System.out.println("Klient " + id);
		PurchaseComparator purchaseComparator = new PurchaseComparator();
		purchaseList.stream()
					.filter(p->p.getCustomerId().equals(id))
					.sorted(Comparator.comparing(Purchase::getProductName).reversed()) 
					.forEach(p->System.out.println(p));
		System.out.println(" ");
	}
}
