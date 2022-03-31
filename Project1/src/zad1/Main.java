package zad1;

import java.util.*;
import java.util.stream.Collectors;

import zad1.XList;


//Creates the XList class, which provides additional possibilities for creating and manipulating lists.
public class Main {
  public static void main(String[] args) {

    // Some additional datasets
    Integer[] ints = { 100, 200, 300 };
    Set<Integer> set = new HashSet<>(Arrays.asList(3, 4, 5));

    // Sposoby tworzenia
    XList<Integer> list1 = new XList<>(1, 3, 9, 11);
    XList<Integer> list2 = XList.of(5, 6, 9);
    XList<Integer> list3 = new XList<Integer>(ints);
    XList<Integer> list4 = XList.of(ints);
    XList<Integer> list5 = new XList<Integer>(set);
    XList<Integer> list6 = XList.of(set);

    System.out.println(list1);
    System.out.println(list2);
    System.out.println(list3);
    System.out.println(list4);
    System.out.println(list5);
    System.out.println(list6);

    // --- and auxiliary methods for creating from subtitles
    XList<String> slist1 = XList.charsOf("ala ma kota");
    XList<String> slist2 = XList.tokensOf("ala ma kota");
    XList<String> slist3 = XList.tokensOf("A-B-C", "-");

    System.out.println(slist1);
    System.out.println(slist2);
    System.out.println(slist3);

    // Union method - sum of elements
    List<Integer> m1 =  list1.union(list2);  
    System.out.println(m1);
    // all operations can be performed from the List interface, e.g .:
    m1.add(11);
    System.out.println(m1);
    XList<Integer> m2 = (XList<Integer>)m1;

    XList<Integer> m3 = m2.union(ints).union(XList.of(4, 4));
    System.out.println(m2); 
    System.out.println(m3); 
    m3 = (XList<Integer>) m3.union(set);
    System.out.println(m3);

      
    System.out.println(m3.diff(set));   
    System.out.println(XList.of(set).diff(m3)); /

    // method unique - returns a new Xlist with no duplicates  
    XList<Integer> uniq = m3.unique(); // lista, nie Set
    System.out.println(uniq);

    //combinations (the order is important)
    List<String> sa = Arrays.asList( "a", "b");
    List<String> sb = Arrays.asList( "X", "Y", "Z" );
    XList<String> sc = XList.charsOf( "12" );
    XList toCombine = XList.of(sa, sb, sc);
    System.out.println(toCombine);

    
    XList<Integer> lmod = XList.of(1,2,8, 10, 11, 30, 3, 4);  
    lmod.forEachWithIndex((e, i) -> lmod.set(i, e*2));
    System.out.println(lmod);
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(e); } );
    System.out.println(lmod);
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(i); } );
    System.out.println(lmod); 

  }
}
