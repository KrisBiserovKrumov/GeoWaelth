package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            List<String> allWords = loadAllWords();
            System.out.println("Words loaded successfully: " + allWords.size());
            Set<String> wordSet = new HashSet<>(allWords);
            List<String> nineLetterWords = findNineLetterWords(allWords);
            for (String word : nineLetterWords) {
                List<String> validWords = findValidWords(word, wordSet);
                if (validWords.contains("I") || validWords.contains("A")) {
                    System.out.println(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> loadAllWords() throws IOException {
        URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()))) {
            List<String> ret = br.lines().collect(Collectors.toList());
            return ret;
        }
    }

    private static List<String> findNineLetterWords(List<String> words) {
        List<String> nineLetterWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() == 9) {
                nineLetterWords.add(word);
            }
        }
        return nineLetterWords;
    }

    private static List<String> findValidWords(String word, Set<String> wordSet) {
        List<String> validWords = new ArrayList<>();
        validWords.add(word);
        while (word.length() > 1) {
            word = word.substring(0, word.length() - 1);
            if (wordSet.contains(word)) {
                validWords.add(word);
            } else {
                break;
            }
        }
        Collections.reverse(validWords);
        return validWords;
    }
}
