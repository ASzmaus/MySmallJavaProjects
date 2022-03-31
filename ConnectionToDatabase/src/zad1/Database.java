package zad1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.*;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Database extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	private	JFrame	frame;
	private static final String DRIVER ="org.sqlite.JDBC";
	private Statement stmt;
	private Connection con;
	private String url;
	private TravelData travelData;
	private JTable table = new JTable();
	private MyTableModel myTableModel;
	private Locale language;
	
	public static final String[] columnNames = {"country", "date from", "data to", "place", "price", "currency"};		
	public static final String dateFormat = "yyyy-MM-dd";
	public static final String[] availableLanguages = { "pl_PL", "en_GB"};
	  
	  
	public Database(String url, TravelData travelData)  {
		 this.url=url;
		 this.travelData=travelData;	 
	 }
	
	public void create() {
		 try {
	            Class.forName(Database.DRIVER);
	            con = DriverManager.getConnection("jdbc:sqlite:zad1.db");
	            stmt = con.createStatement();
	            String createTravelData = "CREATE TABLE IF NOT EXISTS travel_data (country varchar(255), date_from DATE, date_to DATE, place varchar(255), price varchar(255), currency varchar(255))";
	           stmt.execute(createTravelData);
		       PreparedStatement preparedStatement
		                    = con.prepareStatement("insert into travel_data (country, date_from, date_to, place, price, currency) values (?,?,?,?,?,?)");
		      for(String s: availableLanguages) {
		       for (Offer o : travelData.getOffersListByLocation(s, dateFormat)) {
		                int i=1;
		                preparedStatement.setObject(i++, o.getCountry());
		                preparedStatement.setObject(i++, o.getDateFrom());
		                preparedStatement.setObject(i++, o.getDateTo());
		                preparedStatement.setObject(i++, o.getPlace());
		                preparedStatement.setObject(i++, o.getPrice());
		                preparedStatement.setObject(i++, o.getCurrency());
		                preparedStatement.execute();
		            }
		      }
		  	} catch (ClassNotFoundException e) {
		    	e.printStackTrace();
			}catch (SQLException exc)  {
			     System.out.println("SQL except.: " + exc.getMessage());  
			     System.out.println("SQL state  : " + exc.getSQLState()); 
			     System.out.println("Vendor errc: " + exc.getErrorCode()); 
			     System.exit(1);
			} finally {    	         
	   		  try {
					con.close();
					stmt.close();
			  } catch (SQLException e) {
					e.printStackTrace();
			  }
			}
	}

	public void showGui() {
		 frame = new JFrame();
		 frame.setLayout( new BorderLayout() );
	     myTableModel = new MyTableModel(columnNames);
	     table = new JTable();
	     table.setModel(myTableModel);
	     table.setSurrendersFocusOnKeystroke(true);
	    
		 JScrollPane scrollpane = new JScrollPane(table);
         table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
         int rc = 0;    
         rc = JOptionPane.showOptionDialog(null,
                   null,
                   "Choose language",
                   JOptionPane.DEFAULT_OPTION,
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   availableLanguages,
                   availableLanguages[0]);

        switch (rc) {
             case 0 : 
             case 1 :            
            	 	language=Locale.forLanguageTag(availableLanguages[rc].replace("_", "-"));               
            	 	break;
             case 2 :	
            	 	language=new Locale(availableLanguages[rc].substring(0,1),availableLanguages[rc].substring(3,4));                     
            	 	break;
             default: break;
         }
         for (Offer o :  travelData.getOffersListByLocation(language.toString(), dateFormat) ){
   	 			myTableModel.addRow(o);
     	 }  
 		 scrollpane.setPreferredSize(new Dimension(600, 400));
		 getContentPane().add(scrollpane, "Center");
	     pack();
		 setVisible(true);     
	}
}
