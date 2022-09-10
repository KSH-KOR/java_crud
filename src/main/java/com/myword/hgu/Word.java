package com.myword.hgu;

import java.util.UUID;

public class Word {
    private final String id;
    private int level;
    private String word;
    private String meaning;


    public Word(String id, int level, String word, String meaning) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    public Word(String[] dictionaryComponents){
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.level = Integer.parseInt(dictionaryComponents[0]);
        this.word = dictionaryComponents[1];
        this.meaning = dictionaryComponents[2];
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {

        return meaning;
    }


    @Override
    public String toString() {
        String slevel = "";
        for(int i = 0; i < getLevel(); i++) slevel += '*';
        String str = String.format("%-3s", slevel) +
                String.format("%15s", word) +
                "  " +
                meaning;
        return str;
    }
}
