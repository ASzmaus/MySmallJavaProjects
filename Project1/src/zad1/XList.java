package zad1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class  XList<T> extends ArrayList<T> {
	
	private Collection<T> collection; 
	private List<T> tempList;
	private List<T> listFI;
	
	 public XList() {
	        collection = new ArrayList<>();
	    }

	public XList(T...i) {
		 collection = Arrays.asList(i);
	}
	
	public XList(Collection<T> col) {
		 collection = new ArrayList<T>(col);			
	}

	public XList(ArrayList<Character> result) {
		 collection=(ArrayList<T>) result;
	}

	public static <T> XList<T> of( T ...i) {
		return new XList<T>(i);
	}	

	public static <T> XList<T> of(Collection<T> sa) {
		XList<T> tempList = new XList<T>(sa);
		return tempList;
	}
	
	public static  XList<String> charsOf(String string) {
		ArrayList<Character> result = new ArrayList<Character>();
		char[]  chars= string.toCharArray();
		for (char c : chars) {
			result.add(c);
		}    
		return  new XList<String>(result);
	}
	
	public static XList<String> tokensOf(String s) {
		String[] tab = s.split(" ");
		return new XList<String>(tab);       			
	}
	
	public static XList<String> tokensOf(String string, String string2) {
		String s= string.replaceAll(string2, " ");
		String[] tab = s.split(" ");
		return new XList<String>(tab);    
	}
	
    @Override
    public boolean add(Object object) {
        collection.add((T) object);
        return true;
    }
	 
	 public XList<T> union(Collection<T> newCollection ) {
     XList<T> result = new XList<>();
     if (newCollection instanceof XList) {
    	 result.collection.addAll(this.collection);
    	 result.collection.addAll(((XList)newCollection).collection);
     } else {
    	 result.collection.addAll(this.collection);
    	 result.collection.addAll(newCollection);
     }
     return result;
 }

	 public XList<T> union(T[] ints) {	
		List<T> list1=Arrays.asList(ints);
		List<T> list =   Stream.concat(this.collection.stream(),list1.stream())
					.collect(Collectors.toList());
		XList<T> newlist = new XList<T>(list);
		return newlist; 
	}
	 
	 public XList<T> unique() {
		 List<T> union = this.collection.stream()
				 .distinct()
				 .collect(Collectors.toList());
		XList<T> newlist = new XList<T>(union);
		return newlist; 
		}
	 
	 public static <T> Predicate<T> not(Predicate<T> t) {
		    return t.negate();
		}
	 
	 public XList<T> diff(Collection<T> coll) {
		 Collection<T> diff;
		 if (coll instanceof XList) {
			 	diff =  (Collection<T>) coll.stream().filter(not(this.collection::contains)).collect(Collectors.toList());
	      } else {
	        	diff =  (Collection<T>) this.collection.stream().filter(not(coll::contains)).collect(Collectors.toList());
	      }
		 XList<T> newlist = new XList<T>(diff);
		 return newlist;
		}	  
	 
	public void forEachWithIndex(BiConsumer<T, Integer> fi) {  
		listFI = new ArrayList<>();
		tempList = new ArrayList<>();
		listFI.addAll(this.collection);
		tempList.addAll(this.collection);
		for( int i=0; i<listFI.size();i++) {
			fi.accept(listFI.get(i), i);
		}
		this. collection = tempList;
	}
		
	@Override
	public Object set(int i, Object e) {
		tempList.remove(i);
		tempList.add(i, (T)e);
		return e;
	}
	@Override
	public boolean remove(Object o) {
		tempList.remove(o);
			listFI.remove(o);
			return true;
	}
		 
	@Override
	public String  toString() {
			return "" + this.collection ;
	}

	}
