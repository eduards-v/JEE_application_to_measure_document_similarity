package ie.gmit.sw;

import java.util.*;

public class TestRunner {

    public static void main(String[] args) {

        Set<String> shingles = new HashSet<>();


        ShingleHandler shingleHandler = new ShingleHandler();

        String[] samples = {"Tíkhon Shcherbáty was one of the most indispensable men in their band.",
                            "He was a peasant from Pokróvsk, near the river Gzhat. When Denísov had",
                            "come to Pokróvsk at the beginning of his operations and had as usual",
                            "summoned the village elder and asked him what he knew about the French,"};



        for (String s : samples){
            shingleHandler.makeShingles(s);
        }

        shingles = shingleHandler.getShingles();

        System.out.println("Shingles ready to be printed!");
        System.out.println("_____________________________");
        shingles.forEach(System.out::println);


    }

    private static class ShingleHandler{

        private Set<String> shingles = new HashSet<>();
        private String reserve = "";

        private void makeShingles(String line){
            if(!reserve.isEmpty()){
                line = reserve + line;

                System.out.println("Prepend left over: " + reserve
                                    + " | New line: " + line);
                reserve = "";
                System.out.println("Is reserve empty after concat: " + reserve.isEmpty());
            }


            String[] splitLine = line.split("(?<=\\G.{15})");

            for (String s : splitLine){
                if(s.length() == 15){
                    shingles.add(s);
                }else {
                    reserve = s;
                }
            }

        } // end of makeShingles()

        public Set<String> getShingles() {
            return shingles;
        }
    } // end of inner class
}


