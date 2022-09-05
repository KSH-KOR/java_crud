package com.myword.hgu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;

    public WordCRUD(Scanner s) {
        this.list = new ArrayList<Word>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력 : ");
        //System.out.println("=> Enter difficulty(1,2,3) & new vocab : ");
        int level = s.nextInt();
        String word = s.nextLine();
        System.out.println("뜻 입력: ");
        //System.out.println("Enter meaning: ");
        String meaning = s.nextLine();
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        return new Word(uuid, level, word, meaning);
    }

    public void addWord() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
        //System.out.println("New vocab is added in the vocab book.");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    public void updateWord(){

    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(Object obj) {

    }

    public void listAll(){

        System.out.println("----------------------------");
        for(int i =0 ; i < list.size(); i++){
            System.out.print((i+1) + " ");
            System.out.println(list.get(i));
        }
        System.out.println("----------------------------");
    }
}
