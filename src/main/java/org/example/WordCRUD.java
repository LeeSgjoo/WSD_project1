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
    public void updateItem() {
        System.out.print ("=> 수정할 단어 검색: ");
        String keyword = sc.next();
        ArrayList<Integer> idList = this.listAll(keyword); // 실제 단어 List의 아이디들이 담겨있음;
        System.out.print("=> 수정할 번호 선택: ");
        int id = sc.nextInt(); // 실제 단어 리스트의 아이디가 아니라 아이디 리스트의 아이디 번호이다.
        sc.nextLine(); // 개행 문자 소비하기
        System.out.print("=> 뜻 입력: ");
        String meaning = sc.nextLine();
        Word word = list.get(idList.get(id-1));
        // idList.get으로 진짜 단어 리스트의 아이디를 얻어온다.
        // 첫번째 listAll()을 1부터 출력되게 만들었기에 1이면 실제 아이디는 0이므로 id-1으로 얻어온다.
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
    }
    // 파일 저장 기능 (메뉴 7)
    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("words.txt"));
            for (Word one : list) {
                // level|word|meaning 형태로 파일에 저장
                pr.write(one.getLevel() + "|" + one.getWord() + "|" + one.getMeaning() + "\n");
            }
            pr.close();
            System.out.println("=> 단어 목록이 words.txt 파일로 저장되었습니다. "); // 기획서 예시 참고 [cite: 93]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listAll(int level){
        int j = 0;
        System.out.println("-----------------------------------------------");
        for(int i = 0; i < list.size(); i++){
            Word one = list.get(i);
            if(level == one.getLevel()) {
                j++;
                System.out.print(j + " ");
                System.out.println(one.toString());
            }
        }
        if (j == 0) {
            System.out.println("해당 난이도의 단어가 없습니다.");
        }
        System.out.println("-----------------------------------------------");
    }

    // 파일 로드 기능 (프로그램 시작 시)
    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String line;
            int count = 0; // 로드된 단어의 id는 0으로 처리 (Word 객체 내부에서 처리되도록)

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // 빈 줄 건너뛰기

                String[] data = line.split("\\|"); // '|' 기준으로 분리
                if (data.length != 3) continue; // 데이터 형식이 맞지 않으면 건너뛰기

                int level = Integer.parseInt(data[0]);
                String word = data[1].trim();
                String meaning = data[2].trim();

                // 파일에서 ID는 저장하지 않았으므로 0으로 넣고 Word 객체 생성
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("단어 파일 로드 완료! 총 " + count + "개의 단어를 로드했습니다.");
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 (첫 실행 시) 무시하고 진행
            System.out.println("단어 파일(words.txt)을 찾을 수 없습니다. 새로운 단어장으로 시작합니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}