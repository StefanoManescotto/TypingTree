package com.example.typingtree_java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class GeneratePhrase {

    /**
     * @param wordsNum number of words to generate
     * @return string of random num words
     */
    public static String getRandomWords(int wordsNum){
        if(wordsNum <= 0){
            return "";
        }

        String s = "";

        for(int i = 0; i < wordsNum; i++){
            s += getWord() + " ";
        }

        return s;
    }

    private static String getWord(){
        try {
            List<String> l = Files.readAllLines(Paths.get(GeneratePhrase.class.getResource("MostUsedWords").toURI()));
            Random rand = new Random();
            return l.get(rand.nextInt(l.size()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
