package zad2;

//Loads data from a file with data about customer purchases and prints it to the console in the following lines.
public class Main {

  public static void main(String[] args)  {
    CustomersPurchaseSortFind cpsf = new CustomersPurchaseSortFind();
    String fname = System.getProperty("user.home") + "/customers.txt";
    cpsf.readFile(fname);
    cpsf.showSortedBy("Nazwiska");
    cpsf.showSortedBy("Koszty");

    String[] custSearch = { "c00001", "c00002" };

    for (String id : custSearch) {
      cpsf.showPurchaseFor(id);
    }
  }

}
