package zad1;


import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;
public class MyTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int COUNTRY = 0;
    public static final int DATEFROM = 1;
    public static final int DATETO = 2;
    public static final int PLACE = 3;
    public static final int PRICE = 4;
    public static final int CURRENCY = 5;

	
    protected String[] columnNames;
    protected LinkedList<Offer> offerList;

	
	 public MyTableModel(String[] columnNames) { this.columnNames = columnNames;
	 offerList = new LinkedList<Offer>(); }  
  
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return offerList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
       Offer offer = offerList.get(row);
       switch (column) {
            case COUNTRY:
               return offer.getCountry();
           case DATEFROM:
               return offer.getDateFrom();
           case DATETO:
               return offer.getDateTo();
           case PLACE:
               return offer.getPlace();
           case PRICE:
               return offer.getPrice();
           case CURRENCY:
               return offer.getCurrency();
           default:
              return new Object();
       }        		
	}
	
	@Override
	public void setValueAt(Object object, int row, int column) {
       Offer offer = offerList.get(row);
       switch (column) {
       		
       		case COUNTRY:
              offer.setCountry((String) object);
       		case DATEFROM:
                offer.setDateFrom( (LocalDate) object);
       		case DATETO:
                offer.setDateTo( (LocalDate) object);
       		case PLACE:
                offer.setPlace( (String) object);
       		case PRICE:
                offer.setPrice((String) object);
       		case CURRENCY:
                offer.setCurrency( (String) object);
      }        
       fireTableCellUpdated(row, column);
	}
	
	  public String getColumnName(int col) {
		    return columnNames[col];
		  }
	
   public boolean isCellEditable(int row, int column) {
      	   return true;
   }
   
	public Class<?> getColumnClass(int column) {
       switch (column) {
            case COUNTRY:            
              return String.class;
           case DATEFROM:
           	return LocalDate.class;
           case DATETO:
           	return LocalDate.class;
           case PLACE:          
               return String.class;
            case  PRICE:
            	return String.class;
            case CURRENCY:
            	return String.class;
           default:
              return Object.class;
       }
   }
	
	public boolean hasEmptyRow() {
       if (offerList.size() == 0) 
    	   return false;
       else 
    	   return false;
   }

	public void addEmptyRow() {
		offerList.add(new Offer());
        fireTableRowsInserted(offerList.size() - 1,offerList.size() - 1);
    }
   
	public void addRow(Offer offer) {
		offerList.add(offer);
       fireTableRowsInserted(offerList.size() - 1, offerList.size() - 1);
   }
   
   public void deleteRowAt(int index) {
	   offerList.remove(index);
   	   fireTableRowsDeleted(index, index);
   }
}