package zad2;

import java.util.Comparator;

public class Purchase {
	private String customerId;
	private String surnameName;
	private String productName;
	private Double price;
	private Double quantityPurchased;
	
	public Purchase(String customerId, String surnameName, String productName, Double price, Double quantityPurchased) {
		this.customerId = customerId;
		this.surnameName = surnameName;
		this.productName = productName;
		this.price = price;
		this.quantityPurchased = quantityPurchased;
	}
	
	public String getsurnameName() {
		return surnameName;
	}
	public void setNurnameName(String surnameName) {
		this.surnameName = surnameName;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Double getQuantityPurchased() {
		return quantityPurchased;
	}
	public void setQuantityPurchased(Double quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	
	@Override
	public String  toString() {
		return customerId + ";" + surnameName + ";" + productName + ";" + price + ";" + quantityPurchased ;
	}
	
	
	public static class PurchaseComparator implements Comparator<Purchase>{
		
		public static final String SURNAMENAMECUSTOMERID = "Nazwiska";
		public static final String COSTCUSTOMERID = "Koszty";
		public static final String PRODUCTNAME = "ProductName";
		public String sortingField;
	 
		@Override
	    public int compare(Purchase purchase1, Purchase purchase2) {
	        int diff = 0;
	        switch (sortingField) {
	        case SURNAMENAMECUSTOMERID:
	        	diff=purchase1.getsurnameName().compareTo(purchase2.getsurnameName()) + purchase1.customerId.compareTo(purchase2.customerId);
	            break;
	        case COSTCUSTOMERID:
	        	diff=(int) (purchase2.getPrice()*purchase2.getQuantityPurchased() - purchase1.getPrice()*purchase1.getQuantityPurchased()) + purchase1.customerId.compareTo(purchase2.customerId );
	            break; 
	        case PRODUCTNAME:
	        	diff= purchase2.productName.compareTo(purchase1.productName );
	            break; 
	        }
	        return diff;
	    }
	 
	    public String getSortingField() {
	        return sortingField;
	    }
	 
	    public void setSortingField(String sortingField) {
	        this.sortingField = sortingField;
	    }
	}	
}
	
