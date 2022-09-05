package com.myword.hgu;

import java.util.Scanner;

enum MenuOptions{
    allWords,
    searchWordsDependsOnDifficulty,
    searchWords,
    addWords,
    updateWords,
    deleteWords,
    saveInFiles,
    exit,
}

public class WordManager {

    private final Scanner s = new Scanner(System.in);
    private final WordCRUD wordCRUD;

    WordManager(){
        wordCRUD = new WordCRUD(s);
    }

    public MenuOptions selectMenu(){
        System.out.println(
                        "*** 영단어 마스터 ***\n" +
                        "********************\n" +
                        "1.모든 단어 보기\n" +
                        "2.수준별 단어 보기\n" +
                        "3. 단어 검색\n" +
                        "4. 단어 추가\n" +
                        "5. 단어 수정\n" +
                        "6. 단어 삭제\n" +
                        "7. 파일 저장\n" +
                        "8. 나가기\n" +
                        "********************\n" +
                        "=> 원하는 메뉴는? "
        );
//        System.out.println(
//                "*** vocab master ***\n" +
//                        "********************\n" +
//                        "1. all vocab\n" +
//                        "2. vocab depends on difficulty\n" +
//                        "3. search vocab\n" +
//                        "4. add vocab\n" +
//                        "5. update vocab\n" +
//                        "6. delete vocab\n" +
//                        "7. save in files\n" +
//                        "8. exit\n" +
//                        "********************\n" +
//                        "=> select an index in menu "
//        );
        return MenuOptions.values()[s.nextInt() - 1];
    }
    public void start(){
        while(true){
            switch (selectMenu()){
                case allWords -> {
                    wordCRUD.listAll();
                }
                case searchWordsDependsOnDifficulty -> {

                }
                case searchWords -> {
                    //wordCRUD.selectOne();
                }
                case addWords -> {
                    wordCRUD.addWord();
                }
                case updateWords -> {
                    //wordCRUD.update();
                }
                case deleteWords -> {
                    //wordCRUD.delete();
                }
                case saveInFiles -> {
                }
                case exit -> {
                    break;
                }
            }
        }
    }
}
