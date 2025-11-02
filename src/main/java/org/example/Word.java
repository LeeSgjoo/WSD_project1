package org.example;

public class Word{
    private int id;
    private int level;
    private String word = "";
    private String meaning = "";

    Word(){

    }
    public Word(int id, int level, String word, String meaning) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    /*
    1. ** (레벨:*개수)         electric  전기의, 전기를 생산하는
    2. *  (레벨:*개수)             pole  기둥, 장대
     */

    @Override
    public String toString() {
        String slevel = "";
        for(int i = 0; i < level; i++) slevel += "*";
        String str = String.format("%-3s", slevel) + "  " + String.format("%15s", word) + "  " + meaning;
        // 세칸에 대해서 왼쪽 정렬을 하기 위해서 String.format을 사용
        return str;
    }
}