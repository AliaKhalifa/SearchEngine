/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shorouk
 */
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
import java.util.ArrayList;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.*;





public class Indexer {
    
    
    public static class WordDetails
    {
      public  String word;
      public  int occurence;
     ArrayList<String> tags = new ArrayList<>();  
      ArrayList<String> wordTags=new ArrayList<>();

        public void setWord(String w)
           {word=w; }
        public void setOccurence(int o)
           {occurence=o; }
        public void addTag(String t)
           {
               if (wordTags.contains(t)==false) // If the tag isn't already added
               {
                     wordTags.add(t);
               }

           }
       
     }
     public static class MyPair
     {
        private  String word;
        private  String tag;
        public void setPair(String w,String t)
           {word=w; tag=t;}
        public String getWord()
        {return word;}
        
         public String getTag()
        {return tag;}
        
        public void setWord(String w){
            word=w;
        }
//        public void lowerCase()
//        {
//            word.toLowerCase();
//        }
     }
    
    
   public static void main(String[] args) throws IOException
    {
             ArrayList<MyPair> words = new ArrayList<>();  
             ArrayList<String> tags = new ArrayList<>();  
             ArrayList<String> stopWords = new ArrayList<>(); 
             ArrayList<WordDetails> allWords = new ArrayList<>(); 
             int occurrence;
            
             // ------------------------ Reading stop words from the .txt file ------------------------ //
              try {
                File myObj = new File("stopwords.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                 stopWords.add (myReader.nextLine());
                }
                myReader.close();
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
              }

            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Document").get();
            String tag;
            String str;
          
             // --------------- Getting all words in the head of the document --------------- //
              Elements head = doc.head().select("*");
               for (Element element : head) {
                 tag= element.tagName();
                 str= element.ownText();
                 str = str.replaceAll("[^0-9a-zA-Z @!]", ""); // <---------- Not sure if it's accurate

                  // ---------------- Splitting the word in each sentence ---------------- //
                    for (String word : str.split(" ")) {
                       if (word!="" && word!=" ")
                            {
                                MyPair p=new MyPair();
                                p.setPair(word, tag);
//                                System.out.println(p.getWord());
                                words.add(p);
                            }

                        }

                
            }
             
              // --------------- Getting all words in the body of the document --------------- //
               Elements body = doc.body().select("*");
            for (Element element : body) {
                   tag= element.tagName();
                   str= element.ownText();
                    str = str.replaceAll("[^a-zA-Z @!]", ""); // <---------- Not sure if it's accurate
//                  str = str.replaceAll("[~`!@#$%^&*()_-/?\\|]", "");   
                   // ---------------- Splitting the word in each sentence ---------------- //
                    for (String word : str.split(" ")) {
                        
                        if (word!="" && word!=" ")
                            {
                                MyPair p=new MyPair();
                                p.setPair(word, tag);
//                                  System.out.println(p.getWord());
                                words.add(p);
                                
                            }
                        }
               
                
            }
            
            // ------------------------ Stemming the words extracted from the document ------------------------ //
            
                  SnowballStemmer snowballStemmer = new englishStemmer();
                  String stemWord;
                     for (int i=0;i<words.size();i++)
                       {  
                           
                            snowballStemmer.setCurrent(words.get(i).getWord());
                            snowballStemmer.stem();
                            stemWord= snowballStemmer.getCurrent();
                            stemWord= stemWord.toLowerCase();
                            //System.out.println(stemWord);
                            words.get(i).setWord(stemWord);
                            //System.out.println(words.get(i).getWord());
                           

                       }
                   
            // ------------------------ Removing stop words ------------------------ //
//             words.removeIf(s -> s.matches("(,|\\.|\\?|/|\"|\')+"));
//               words.replaceAll(MyPair::lowerCase);
//               words.replaceAll(MyPair::stopWords);
//                 words.removeAll(stopWords);

                      for (int i=0;i<stopWords.size();i++){
                            String s=stopWords.get(i);
                            words.removeIf( word -> word.getWord().equals(s));
                             
                      }
                      
                      
            // ------------------------ Get occurence of each word ------------------------ //
                //Iterator<MyPair> iter= words.iterator();
                WordDetails w=new WordDetails();
                
                for(int i=0;i<words.size();i++)
               {
                     w.occurence=1;
//                      occurrence  = Collections.frequency(words, iter.next());
//                       w.setWord(iter.next());
//                       w.setOccurence(occurrence);
//                       w.addTag()
                   // System.out.println(words.get(i).getWord());
                   w.tags.clear();
                   w.setWord(words.get(i).getWord());
                   w.tags.add(words.get(i).getTag());
                        for(int j=i+1;j<words.size();j++){
//                         System.out.println(w.word);
//                          System.out.println("//////////////////////");
//                           System.out.println(words.get(j).getWord());

                            if(w.word.equals(words.get(j).getWord()))
                            {
                                if(!(w.tags.contains(words.get(j).getTag())))
                                {w.tags.add(words.get(j).getTag());}
                                w.occurence++;
                                
                            }
                        }
                         System.out.println(w.word);
                         
//                           System.out.println(words.get(j).getWord());
                                     System.out.println(w.occurence);
                                     for(String m : w.tags)
                                     {
                                         System.out.print(m+" ");
                                         
                                     }
                                     System.out.println();
                                      System.out.println("//////////////////////");
                          String s=words.get(i).getWord();
                        words.removeIf( word -> word.getWord().equals(s));
                         
                 }
               
                   
                 
                   
                
//                 words.removeIf( word -> word.equals(iter.next()));
//                   System.out.println(iter.next());
//                            }

                    
//                          
//                             //System.out.println(words.get(i).getWord());
//                             System.out.println(words.get(i).getTag());
                                    
                      
//            
      }       
   
    }
//}


//         for (  ) { 
//                    tag = element.tagName();
//                   
//                    for (String word : element.text().split(" ")) {
//                        
//                        if (tag!="#root" && tag!="body" && tag!="html" && tag!="head" )
//                        {
//                             counter++;
//                             words.add(word);
//                            tags.add(tag);
//                              System.out.println(tag);
//                            System.out.println(word);
//                             System.out.println(counter);
//                            System.out.println("----------------");
//                        }
//                       
////                        
//
//                         
//                    }
////                    words.add();
//            if (counter==100)
//                 break;
//            
//           
////                     System.out.println("---------------------------------------");
//
//        }
//  
