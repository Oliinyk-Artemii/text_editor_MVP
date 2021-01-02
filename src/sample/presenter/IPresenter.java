package sample.presenter;

import java.io.File;

public interface IPresenter {
    public void bind();

    public void openFile();

    public void saveFile(File file);

    public void copyText(String s);

    public void pasteText();

    void newFile();
}
