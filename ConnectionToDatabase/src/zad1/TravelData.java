package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TravelData {
	private List<Offer> listOffers = new LinkedList<>();
	private File dataDir;
	private List<String> tokens;
	
	public TravelData(File dataDir) {
		this.dataDir=dataDir;
	}
	
	public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
		tokens = new LinkedList<>();
		for( Offer o: getOffersListByLocation(locale, dateFormat)){
		tokens.add(o.getCountry() + " " + o.getDateFrom().toString() + " " + o.getDateTo().toString() + " "+ o.getPlace() +" " + o.getPrice() + " "+ o.getCurrency());
		}
		return tokens;
	}
		
	public List<Offer> getOffersListByLocation(String locale, String dateFormat) {
				listOffers = new LinkedList<>();
				Arrays.asList(dataDir.listFiles()).stream()
									.forEach(file->{
				String fileName	=file.getPath();			
				try {
					Scanner scanner = new Scanner(new File(fileName));		
					while(scanner.hasNext()) {
						String str = scanner.nextLine();
						String[] oldTabString =str.split("\t");
						Locale givenInMainLocale = Locale.forLanguageTag(locale.replace("_", "-"));
						Locale givenInOutputLocale = Locale.forLanguageTag(oldTabString[0].replace("_", "-"));
						NumberFormat numberFormatInMain= NumberFormat.getInstance(givenInMainLocale);
						NumberFormat numberFormatInOutPut= NumberFormat.getInstance(givenInOutputLocale);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, givenInMainLocale);

						Offer offer = new Offer(changeCountry(givenInOutputLocale ,givenInMainLocale,oldTabString[1]),
										LocalDate.parse(oldTabString[2], formatter),
										LocalDate.parse(oldTabString[3], formatter),
										changePlace(givenInMainLocale,oldTabString[4]),
										numberFormatInMain.format(numberFormatInOutPut.parse(oldTabString[5]).doubleValue()), 
										oldTabString[6]);
						listOffers.add(offer);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			});
		return listOffers;
	}
		
	private String changePlace(Locale givenLocaleInMain, String givenWord) {
		ResourceBundle rb2 = ResourceBundle.getBundle("resources.ResourceBundle", givenLocaleInMain);
			String	result = rb2.getString(givenWord);
		  return result;
		
	}

	private String changeCountry (Locale givenLocaleinOutput, Locale givenLocaleInMain, String givenCountry) {
		for(Locale locale: Locale.getAvailableLocales()) {
			if(locale.getDisplayCountry(givenLocaleinOutput).equals( givenCountry)) {
				return locale.getDisplayCountry(givenLocaleInMain);
			}
		}
		return null;
	}

	
}
