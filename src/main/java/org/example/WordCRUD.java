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
    public void addWord(){
        Word one = (Word)add();
        list.add(one); // ArrayList의 add이고 WordCRUD의 add와는 다른 메서드임
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }
    public void deleteItem(){
        System.out.print ("=> 삭제할 단어 검색: ");
        String keyword = sc.next();
        ArrayList<Integer> idList = this.listAll(keyword); // 실제 단어 List의 아이디들이 담겨있음;
        System.out.print("=> 삭제할 번호 선택: ");
        int id = sc.nextInt(); // 실제 단어 리스트의 아이디가 아니라 아이디 리스트의 아이디 번호이다.
        sc.nextLine(); // 개행 문자 소비하기
        System.out.print("=> 정말로 삭제하시겠습니까?(Y/N): ");
        String answer = sc.next();
        if(answer.equalsIgnoreCase("Y")) {
            list.remove((int)idList.get(id - 1));
            System.out.println("삭제되었습니다.");
        }

        else System.out.println("취소되었습니다.");
    }
}