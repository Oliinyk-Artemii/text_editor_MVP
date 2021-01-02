package sample.interfaces;

import java.util.List;

public interface IText {
    
    
    void setText(String[] text);

    List<IWord> findWords(IWord word);

    void cutWord(IWord word, int row);

    void copyWord(IWord word);

    void pasteWord(int ind);

    String getBuffer();

    String getText();
}
