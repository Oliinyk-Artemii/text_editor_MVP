package sample.presenter;

public interface IPresenter {
    public void bind();

    public void openFile();

    public void saveFile();

    public void exit();

    public void copyText(String s);

    public void pasteText();

    void newFile();
}
