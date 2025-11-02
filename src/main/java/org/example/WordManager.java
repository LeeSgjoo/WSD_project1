package org.example;
import java.util.*;

public class WordManager {

    Scanner s = new Scanner(System.in);

    WordCRUD wordCRUD;
    public WordManager() {
        wordCRUD = new WordCRUD(s);
    }

    public int selectMenu(){

        System.out.print("---------------------------------\n" +
                "[English Word Manager]\n" +
                "1. List   2. List(level)   3. Search\n" +
                "4. Add    5. Edit          6. Delete\n" +
                "7. Save file    0. Exit\n" +
                "---------------------------------\n" +
                "Select menu: ");
        return s.nextInt();
    }
    public void start(){
        while(true) {
            int menu = selectMenu();
            if(menu == 0) break;
            else if(menu == 1) { // 1. List
                wordCRUD.listAll();
            }
            else if(menu == 2) { // 2. List(level) - 미구현 기능
                System.out.print("=> 원하는 난이도는? (1~3): ");
                int level = s.nextInt();
                wordCRUD.listAll(level);
            }
            else if(menu == 3) { // 3. Search - 미구현 기능
                System.out.print("=> 검색할 단어 입력: ");
                // nextInt() 이후에 남아있는 개행 문자 소비 (Scanner 객체를 WordManager와 WordCRUD가 공유하므로, nextInt 후 nextLine을 사용하지 않는 경우 sc.nextLine()을 한 번 호출해주는 것이 안전할 수 있습니다. 여기서는 사용자 입력이 String이므로 nextLine()을 사용합니다.)
                s.nextLine();
                String keyword = s.nextLine();
                wordCRUD.listAll(keyword); // 기존에 구현된 검색 메서드 활용
            }
            else if(menu == 4) { // 4. Add
                wordCRUD.addWord();
            }
            else if(menu == 5) { // 5. Edit
                wordCRUD.updateItem();
            }
            else if(menu == 6) { // 6. Delete
                wordCRUD.deleteItem();
            }
            else if(menu == 7) { // 7. Save file - 미구현 기능
                wordCRUD.saveFile();
            }
        }
        System.out.println("단어장 프로그램을 종료합니다.");
    }
}