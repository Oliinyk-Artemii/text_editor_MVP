package sample.models;

import sample.interfaces.IWord;

public class Word implements IWord {
    private String text;
    private int beginInd;
    private int endInd;
    private int row;

    public Word(String word) {
        this.text = word;
        endInd = word.length();
    }

    public Word(String word, int beginInd) {
        this.text = word;
        this.beginInd = beginInd;
        endInd = beginInd + word.length();
    }

    public Word(String word, int beginInd, int row) {
        this.text = word;
        this.beginInd = beginInd;
        endInd = beginInd + word.length();
        this.row = row;
    }

    @Override
    public int getBeginInd() {
        return beginInd;
    }

    @Override
    public void setBeginInd(int ind) {
        beginInd = ind;
    }

    @Override
    public int getEndInd() {
        return endInd;
    }

    @Override
    public void setEndInd(int ind) {
        endInd = ind;
    }

    @Override
    public int getLength() {
        return text.length();
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public void setWord(IWord word) {
        this.text = word.toString();
        this.beginInd = word.getBeginInd();
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }
}
