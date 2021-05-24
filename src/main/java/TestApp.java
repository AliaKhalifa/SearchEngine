/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shorouk
 */

    
    

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;




public class TestApp {
     public static void main(String[] args)
{
//      ArrayList<String> stopWords = new ArrayList<>();  
       ArrayList<String> words = new ArrayList<>();  
       words.add("i");
        words.add("he");
         words.add("nice"); words.add("nice");
         
//            for (int i=0;i<words.size();i++){
//          System.out.println(words.get(i));
//      }
//    String [] stopWords;
//     try {
//                File myObj = new File("stopwords.txt");
//                Scanner myReader = new Scanner(myObj);
//                while (myReader.hasNextLine()) {
//                 stopWords.add (myReader.nextLine());
////                  System.out.println(data);
//                }
//                myReader.close();
//              } catch (FileNotFoundException e) {
//                System.out.println("An error occurred.");
//             for (int i=0;i<words.size();i++){
//          System.out.println(words.get(i));
//      }      e.printStackTrace();
//              }
//      for (int i=0;i<stopWords.size();i++){
//          System.out.println(stopWords.get(i));
//      }

//      words.removeAll(stopWords);
//        for (int i=0;i<words.size();i++){
//          System.out.println(words.get(i));
//      }
//    SnowballStemmer snowballStemmer = new englishStemmer();
//    snowballStemmer.setCurrent("you've");
//    snowballStemmer.stem();
//    String result = snowballStemmer.getCurrent();
//    System.out.println(result);

    
//    String str = "Hello?? : ; !meh-.";
//      str = str.replaceAll("[^0-9a-zA-Z @!]", "");
//       System.out.println(str);
//  words.remove("sweet");
//   words.removeIf( word -> word.equals("sweet"));
   System.out.println(words);
 
}
        }
    

