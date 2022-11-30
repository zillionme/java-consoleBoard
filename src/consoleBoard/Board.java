package consoleBoard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Board {
    //HashMap<String, Object> board 이 게시글 객체 역할.

    //ArrayList 만들기
    ArrayList<HashMap<String, Object>> table
            = new ArrayList<>();
    //날짜
    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");


    public static void main(String[] args) {
        new Board().start();
    }

    private void start() {
        while(true) {
            System.out.println("=================================");
            System.out.println("번호\t제목\t작성자\t작성일");
            System.out.println("---------------------------------");
            for (int i = table.size()-1 ; i>=0 ; i--) {
                HashMap<String, Object> board = table.get(i);
                System.out.println(board.get("BOARD_NO")
                        + "\t" + board.get("TITLE")
                        + "\t" + board.get("USER_NAME")
                        + "\t" + format.format(board.get("REG_DATE")) );
            }
            System.out.println("=================================");


            System.out.print("1.조회  2.등록  0.종료>");
            //ScanUtil 클래스 생성해서 스캐너를 그때그때 만들지 않아도 됨
            //예) 이름과 나이 입력받고 출력하는 프로그램
            //nextInt() 메서드는 정수를 입력받기.
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            switch(input) {
                case 1:
                    read();
                    break;
                case 2:
                    insert();
                    break;
                case 0:
                    System.out.println("프로그램이 종료되었습니다");
                    System.exit(0);  //프로그램 종료(System.exit(0:정상종료))
                    break;
            }


        }
    }

    //게시글 읽기 (상세 페이지)
    private void read() {
        System.out.print("조회할 게시물 번호>");
        int boardNo = new Scanner(System.in).nextInt();

        HashMap<String, Object> board = null;      //???????????
        for (HashMap<String, Object> stringObjectHashMap : table) {
            if (boardNo == (int) stringObjectHashMap.get("BOARD_NO")) {
                board = stringObjectHashMap;
            }
        }
        //board없는 경우 null
        System.out.println("=======================");
        System.out.println("번호\t: " + board.get("BOARD_NO"));
        System.out.println("-----------------------");
        System.out.println("작성자\t: " + board.get("USER_NAME"));
        System.out.println("-----------------------");
        System.out.println("작성일\t: " + format.format(board.get("REG_DATE")));
        System.out.println("-----------------------");
        System.out.println("제목\t: " + board.get("TITLE"));
        System.out.println("-----------------------");
        System.out.println("내용\t: " + board.get("CONTENT"));
        System.out.println("=======================");


        //게시글 기능 : 수정, 삭제, 목록으로 돌아가기
        System.out.print("1.수정  2.삭제  0.목록>");
        int input = new Scanner(System.in).nextInt();

        switch (input) {
            case 1:
                update(board);
                break;
            case 2:
                delete(board);
                break;
            case 0:

                break;
        }
    }

    // 게시글 등록
    private void insert() {
        HashMap<String, Object> board = new HashMap<>();

        // 게시판 넘버 정하기 (게시글이 삭제되면 게시글의 총 개수와 게시글 번호 다를 수 있음)
        int max = 0;
        for (HashMap<String, Object> stringObjectHashMap : table) {
            if (max < (int) stringObjectHashMap.get("BOARD_NO")) {
                max = (int) stringObjectHashMap.get("BOARD_NO");
            }
        }


        //key와 value
        board.put("BOARD_NO", max + 1);
        System.out.print("제목>");
        board.put("TITLE", new Scanner(System.in).nextLine());
        System.out.print("내용>");
        board.put("CONTENT", new Scanner(System.in).nextLine());
        System.out.print("작성자>");
        board.put("USER_NAME", new Scanner(System.in).nextLine());
        board.put("REG_DATE", new Date());


        table.add(board);
        System.out.println("작성하신 게시글이 등록되었습니다.");

    }

    //게시글 수정 (제목은 아무것도 안쓰면 안바뀌는 걸로)
    private void update(HashMap<String, Object> board) {
        System.out.print("제목>");
        String s = new Scanner(System.in).nextLine();
        if (s !=null) {
            board.replace("TITLE", s);
        }

        System.out.print("내용>");
        board.replace("CONTENT", new Scanner(System.in).nextLine());

        System.out.println("게시글이 수정되었습니다.");
    }

    //게시글 삭제
    private void delete(HashMap<String, Object> board) {
        System.out.print("정말 삭제 하시겠습니까?(Y/N)>");
        String input = new Scanner(System.in).nextLine();

        if ("Y".equals(input)) {
            for (int i = 0; i < table.size(); i++) {
                if (board.get("BOARD_NO") == table.get(i).get("BOARD_NO")) {
                    table.remove(i);
                    System.out.println("게시글이 삭제되었습니다.");
                    break;
                }
            }
        }

    }


}
