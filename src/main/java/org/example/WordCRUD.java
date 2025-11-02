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
    public void listAll(){
        System.out.println("-----------------------------------------------");
        for(int i = 1; i <= list.size(); i++){
            System.out.print(i + " ");
            System.out.println(list.get(i-1).toString());
        }
        System.out.println("-----------------------------------------------");
    }
    public ArrayList<Integer> listAll(String keyword){ // 검색을 위해 오버로드
        ArrayList<Integer> idList = new ArrayList<>();
        int j = 1;
        System.out.println("-----------------------------------------------");
        for(int i = 1; i <= list.size(); i++){
            String word = list.get(i-1).getWord();
            if(word.contains(keyword)) {
                System.out.print(j + " ");
                System.out.println(list.get(i-1).toString());
                idList.add(i-1);
                j++;
            }
        }
        System.out.println("-----------------------------------------------");
        return idList;
    }
    public Object add(){
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력: ");
        // 1 driveway
        int level = sc.nextInt();
        String word = sc.next();
        sc.nextLine();
        // next로 하면 입력버퍼가 남아있어서 meaning의 입력에 지장을 주므로 nextLine을 사용한다

        System.out.print("뜻 입력: ");
        // 차고 진입로
        String meaning = sc.nextLine();

        return new Word(0,level,word,meaning);
    }
}