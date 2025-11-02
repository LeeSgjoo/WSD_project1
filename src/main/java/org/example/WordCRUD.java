package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

// ICRUD 인터페이스를 구현한 구현체이다.
public class WordCRUD implements ICRUD {

    ArrayList<Word> list;
    Scanner sc = new Scanner(System.in);
    /*
     *** 구현 예시 ***
     * => 난이도(1,2,3) & 새 단어 입력: 1 driveway
     * 뜻 입력: 차고 진입로
     * 새 단어가 단어장에 추가되었습니다.
     */
    public WordCRUD(){};
    public WordCRUD(Scanner s){
        list = new ArrayList<Word>();
        s = new Scanner(System.in);
    }

}