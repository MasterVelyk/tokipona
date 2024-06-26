import java.util.ArrayList;

public class Masterlist {
   // words accessable via binary search
   protected static Word[] masterlist = {
        new Word("a"),
        new Word("akesi"),
        new Word("ala"),
        new Word("alasa"),
        new Word("ale"),
        new Word("anpa"),
        new Word("ante"),
        new Word("anu"),
        new Word("awen"),
        new Word("e"),
        new Word("en"),
        new Word("esun"),
        new Word("ijo"),
        new Word("ike"),
        new Word("ilo"),
        new Word("insa"),
        new Word("jaki"),
        new Word("jan"),
        new Word("jelo"),
        new Word("jo"),
        new Word("kala"),
        new Word("kalama"),
        new Word("kama"),
        new Word("kasi"),
        new Word("ken"),
        new Word("kepeken"),
        new Word("kijetesantakalu"),
        new Word("kili"),
        new Word("kin"),
        new Word("kipisi"),
        new Word("kiwen"),
        new Word("ko"),
        new Word("kon"),
        new Word("kule"),
        new Word("kulupu"),
        new Word("kute"),
        new Word("la"),
        new Word("lanpan"),
        new Word("lape"),
        new Word("laso"),
        new Word("lawa"),
        new Word("leko"),
        new Word("len"),
        new Word("lete"),
        new Word("li"),
        new Word("lili"),
        new Word("linja"),
        new Word("lipu"),
        new Word("loje"),
        new Word("lon"),
        new Word("luka"),
        new Word("lukin"),
        new Word("lupa"),
        new Word("ma"),
        new Word("mama"),
        new Word("mani"),
        new Word("meli"),
        new Word("meso"),
        new Word("mi"),
        new Word("mije"),
        new Word("misikeke"),
        new Word("moku"),
        new Word("moli"),
        new Word("monsi"),
        new Word("monsuta"),
        new Word("mu"),
        new Word("mun"),
        new Word("musi"),
        new Word("mute"),
        new Word("n"),
        new Word("namako"),
        new Word("nanpa"),
        new Word("nasa"),
        new Word("nasin"),
        new Word("nena"),
        new Word("ni"),
        new Word("nimi"),
        new Word("noka"),
        new Word("o"),
        new Word("oko"),
        new Word("olin"),
        new Word("ona"),
        new Word("open"),
        new Word("pakala"),
        new Word("pali"),
        new Word("palisa"),
        new Word("pan"),
        new Word("pana"),
        new Word("pi"),
        new Word("pilin"),
        new Word("pimeja"),
        new Word("pini"),
        new Word("pipi"),
        new Word("poka"),
        new Word("poki"),
        new Word("pona"),
        new Word("sama"),
        new Word("seli"),
        new Word("selo"),
        new Word("seme"),
        new Word("sewi"),
        new Word("sijelo"),
        new Word("sike"),
        new Word("sin"),
        new Word("sina"),
        new Word("sinpin"),
        new Word("sitelen"),
        new Word("soko"),
        new Word("sona"),
        new Word("soweli"),
        new Word("suli"),
        new Word("suno"),
        new Word("supa"),
        new Word("suwi"),
        new Word("tan"),
        new Word("taso"),
        new Word("tawa"),
        new Word("telo"),
        new Word("tenpo"),
        new Word("toki"),
        new Word("tomo"),
        new Word("tu"),
        new Word("unpa"),
        new Word("uta"),
        new Word("utala"),
        new Word("walo"),
        new Word("wan"),
        new Word("waso"),
        new Word("wawa"),
        new Word("weka"),
        new Word("wile")
    };
   protected ArrayList<Word> seenlist = new ArrayList<Word>();

   public Masterlist() {
   }

      // adds the Word in masterlist that coresponds to the inputted string to seenlist
   public void seeWord(String word) {
      word = word.toLowerCase();
      
         // finds the index of word in masterlist
      int low = 0;
      int high = masterlist.length - 1;
      
      while (low <= high) {
         int mid = low + (high - low) / 2;
      
            // check if x is present at mid
         if (masterlist[mid].getToki().compareTo(word) == 0)
               // if it is, add word to seenlist
            seeWord(mid);
      
            // if x greater, ignore left half
         if (masterlist[mid].getToki().compareTo(word) < 0)
            low = mid + 1;
            // if x is smaller, ignore right half
         else
            high = mid - 1;
      }
   }

      // inserts the element at masterlist[wordNum] to seenlist if word is not already in seenlist
      // sorts seenlist
   public void seeWord(int wordNum) {
         // if wordNum is out of range
         // returns void, breaks out of all method
      if (wordNum >  masterlist.length) {
         return; 
      }
      
         // avoides duplicates
      if (!seenlist.contains(masterlist[wordNum])) {
         seenlist.add(masterlist[wordNum]);
      }
      
         // sorts seenlist to be alphabetical
      seenlist.sort((o1, o2) -> o1.compareTo(o2));
   }
   }
