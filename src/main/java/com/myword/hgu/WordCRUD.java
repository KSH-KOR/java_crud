package com.myword.hgu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    final Scanner s;
    private final String fileName = "dictionary.txt";
    private final String regex = "\\|";

    public void loadFile(){
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while(true){
                String line = bufferedReader.readLine();
                if(line == null) break;
                String[] dictionaryComponents = line.split(regex);
                list.add(new Word(dictionaryComponents));
                count++;
            }
            bufferedReader.close();
            System.out.println("==> " + count + "개 로딩 완료!!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFile() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
            for(Word word: list){
                printWriter.write(word.toFileFormatString() + "\n");
            }
            printWriter.close();
            System.out.println("파일 저장 완료!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WordCRUD(Scanner s) {
        this.list = new ArrayList<Word>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.println("=> 난이도(1,2,3) & 새 단어 입력 : ");
        //System.out.println("=> Enter difficulty(1,2,3) & new vocab : ");
        int level = s.nextInt();
        String word = s.nextLine().trim();
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
//        System.out.println("=> 뜻 입력 : ");
//        String meaning = s.nextLine();
//        obj.setMeaning(meaning);
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
//            update(getWordFromId(wordIdList.get(indexOfWordIdList)));
            getWordFromId(wordIdList.get(indexOfWordIdList)).setMeaning(meaning);
            System.out.println("단어가 수정 되었습니다.");
        }

    }

    @Override
    public int delete(Object obj) {
        list.remove(obj);
        return 0;
    }

    public void deleteWord(){
        System.out.println("삭제할 단어 검색 : ");
        s.nextLine();
        String keyword = s.nextLine();
        ArrayList<String> wordIdList = this.listAll(keyword);
        if(wordIdList.isEmpty()){
            System.out.println("검색된 항목이 없습니다.");
            return;
        } else {
            System.out.println("=> 삭제할 번호 선택 : ");
            int indexOfWordIdList = s.nextInt() - 1;
            s.nextLine();
            System.out.println("=> 정말로 삭제하실래요?(Y/n) : ");
            String answer = s.next();
            s.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                delete(getWordFromId(wordIdList.get(indexOfWordIdList)));
                System.out.println("단어가 삭제 되었습니다.");
            } else {
                System.out.println("취소되었습니다.");
            }
        }
    }

    public void searchLevel() {
        int level;
        System.out.println("=> 레벨(1:초급, 2:중급, 3:고급) 선택 : ");
        while(true){
            level = s.nextInt();
            if(level < 1 || level > 3){
                System.out.println("1, 2, 3 중에 입력해 주세요.");
            } else{
                break;
            }
        }
        listAll(level);
    }

    public void searchWord() {
        System.out.println("=> 검색할 단어는? : ");
        String keyword = s.next();
        s.nextLine();
        listAll(keyword);
    }

    @Override
    public void selectOne(Object obj) {

    }

    private ArrayList<String> printWordList(ArrayList<Word> wordList){
        ArrayList<String> wordIdList = new ArrayList<>();
        System.out.println("----------------------------");
        int i=1;
        for(Word word: wordList){
            System.out.print((i) + " ");
            System.out.println(word.toString());
            wordIdList.add(word.getId());
            i++;
        }
        System.out.println("----------------------------");
        return wordIdList;
    }

    public ArrayList<String> listAll(int level){
        ArrayList<Word> wordList = getWordListByLevel(level);
        return printWordList(wordList);
    }

    public ArrayList<String> listAll(String keyword){
        ArrayList<Word> wordList = getWordListByKeyword(keyword);
        return printWordList(wordList);
    }

    private ArrayList<String> getWordIdAll(){
        ArrayList<String> wordIdList = new ArrayList<>();
        for(Word word: list){
            wordIdList.add(word.getId());
        }
        return wordIdList;
    }

    private ArrayList<Word> getWordListByKeyword(String keyword){
        keyword = keyword == null ? "" : keyword;
        ArrayList<Word> wordList = new ArrayList<>();
        for(Word word: list){
            if(word.getWord().contains(keyword)){
                wordList.add(word);
            }
        }
        return wordList;
    }

    private Word getWordFromId(String id){
        for(Word word: list){
            if(word.getId().equals(id)) return word;
        }
        return (Word) add();
    }

    private ArrayList<Word> getWordListByLevel(int level){
        ArrayList<Word> wordList = new ArrayList<>();
        for(Word word: list){
            if(word.getLevel() == level){
                wordList.add(word);
            }
        }
        return wordList;
    }


}
