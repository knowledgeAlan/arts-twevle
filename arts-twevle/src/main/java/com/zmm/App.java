package com.zmm;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.WeakHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//220
      //  String p = "rte";
       /* System.out.println(p.substring(2));
        System.out.println(p.charAt(2));

        HashSet<String> hashSet = new HashSet();
        hashSet.add("1");
        hashSet.add("1");
        System.out.println(hashSet);*/

/*
       List<String> words = Arrays.asList("java8","in");
       List<Integer> wordsLengths = words.stream().
               map(String::length).collect(Collectors.toList());

       wordsLengths.forEach(item->{
           System.out.println(item);
       });*/


  /*      LocalDate localDate = LocalDate.of(2019,2,3);
        LocalDate localDate2 = LocalDate.of(2019,1,3);


        System.out.println(localDate.compareTo(localDate2));


        System.out.println(localDate);

        Long integer= 1239L;
        Long integer1= 1239L;
        System.out.println(integer==124);
*/
        String event = "a disturbance in the Force";
        int planet = 7;
         String result = MessageFormat.format(
                 "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.", planet, new Date(), event);

         String res = MessageFormat.format("dddd{1} :{0}","123","rrr");
        WeakHashMap<String,String> weakHashMap = new WeakHashMap();
        weakHashMap.put("r","1");
        System.out.println(res);

    }
}
