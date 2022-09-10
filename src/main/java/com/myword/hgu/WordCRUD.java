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

    private Word getWordFromId(String id){
        for(Word word: list){
            if(word.getId().equals(id)) return word;
        }
        return (Word) add();
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
        System.out.println("수정할 단어 검색 : ");
        s.nextLine();
        String keyword = s.nextLine();
        ArrayList<String> wordIdList = this.listAll(keyword);
        if(wordIdList.isEmpty()){
            System.out.println("검색된 항목이 없습니다.");
            return;
        } else{
            System.out.println("=> 수정할 번호 선택 : ");
            int indexOfWordIdList = s.nextInt()-1;
            s.nextLine();
            System.out.println("=> 뜻 입력 : ");
            String meaning = s.nextLine();
            getWordFromId(wordIdList.get(indexOfWordIdList)).setMeaning(meaning);
            System.out.println("단어가 수정 되었습니다.");
        }

    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(Object obj) {

    }

    public ArrayList<String> listAll(String keyword){
        ArrayList<String> wordIdList = new ArrayList<>();

        System.out.println("----------------------------");
        if(keyword == null){
            for(int i =0 ; i < list.size(); i++){
                System.out.print((i+1) + " ");
                System.out.println(list.get(i).toString());
            }
        } else{
            int j = 1;
            for(Word word: list){
                if(word.getWord().contains(keyword)){
                    System.out.print((j) + " ");
                    System.out.println(word.toString());
                    wordIdList.add(word.getId());
                    j++;
                }
            }
        }
        System.out.println("----------------------------");
        return wordIdList.isEmpty() ? wordIdList : getWordIdAll();
    }

    private ArrayList<String> getWordIdAll(){
        ArrayList<String> wordIdList = new ArrayList<>();
        for(Word word: list){
            wordIdList.add(word.getId());
        }
        return wordIdList;
    }
}
