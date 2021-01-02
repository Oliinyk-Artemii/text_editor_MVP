package sample.models;

import sample.interfaces.IText;
import sample.interfaces.IWord;

import java.util.LinkedList;
import java.util.List;

public class Text implements IText {
    private IWord pasteBuffer = new Word("");
    private String[] text;

    public Text(String[] text) {
        this.text = text;
    }

    public String getBuffer(){
        return pasteBuffer.toString();
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        for(String s : text) {
          builder.append(s);
          builder.append('\n');
        }
        String str = builder.toString();
        return str;

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String tempS : text){
            s.append(tempS);
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public void setText(String[] text) {
        this.text = text;
    }

    @Override
    public List<IWord> findWords(IWord word) {
        LinkedList<IWord> searchResult = new LinkedList<>();
        int lastIndex = 0;
        int row = 0;

        for(String s : text){
            while(lastIndex != -1){
                lastIndex = s.indexOf(word.toString(),lastIndex);

                if(lastIndex != -1){
                    searchResult.add(new Word(word.toString(), lastIndex, row));
                    lastIndex += 1;
                }
            }
            row++;
            lastIndex = 0;
        }

        return searchResult;
    }

    @Override
    public void cutWord(IWord word, int row) {
        pasteBuffer = word;
        text[row] = text[row].substring(0, word.getBeginInd()) + text[row].substring(word.getEndInd());
    }

    @Override
    public void copyWord(IWord word) {
        pasteBuffer = word;
    }

    @Override
    public void pasteWord(int ind) {
        int row = 0;
        while (ind > text[row].length()) {
            ind -= text[row].length()+1;
            row++;
        }
        text[row] = text[row].substring(0, ind) + pasteBuffer + text[row].substring(ind);
    }
}
