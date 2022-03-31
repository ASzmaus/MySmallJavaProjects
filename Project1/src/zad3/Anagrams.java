/**
 *
 *  @author Szmaus Agnieszka PD3536
 *
 */

package zad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
	private String allWords;
	Map<String,Integer> sortedStringMap = new TreeMap<>();
	List<List<String>> anagramList = new LinkedList<>();
	Map<String,List<String>> listAnagramMap = new HashMap<>();
		
	public Anagrams(String allWords) {
		this.allWords=allWords;
	}
	
	 public static String sortString(String word){
	        char charArray[] = word.toCharArray();
	        Arrays.sort(charArray);
	        return new String(charArray);
	 }
	 
	public List<List<String>> getSortedByAnQty() {
		File file = new File(allWords);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String word = sc.next();
				int numberOfOccurrencesWord = 1;
				String sortedString	= sortString(word);
				if( sortedStringMap.keySet().contains(sortedString)) {
					sortedStringMap.put(sortedString, sortedStringMap.get(sortedString)+1);
				}else {
					numberOfOccurrencesWord=1;
					sortedStringMap.put(sortedString, numberOfOccurrencesWord);
				}
			
				if(listAnagramMap.keySet().contains(sortedString)){
					listAnagramMap.get(sortedString).add(word);	
				}else {
					listAnagramMap.put(sortedString,  new LinkedList<>());
					listAnagramMap.get(sortedString).add(word);
				}
			}
			List<String> newSortedStringList=new ArrayList<>();
			newSortedStringList= sortedStringMap.entrySet().stream()
					   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					   .map(Map.Entry::getKey)
					   .collect(Collectors.toList());
				
			newSortedStringList.forEach(string->{
				if(listAnagramMap.get(string)!=null)
					anagramList.add(listAnagramMap.get(string));		
			});
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}finally {
			sc.close();
	    }
		return anagramList;
	}

	public String getAnagramsFor(String next) {
	List<String> newList = listAnagramMap.get(sortString(next))
			.stream()
			.filter(p->!p.equals(next))
			.collect(Collectors.toList());
	return next + ": "+ newList;
	}		
}

	
