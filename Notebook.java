import java.util.ArrayList;

public class Notebook {
    protected ArrayList<NotebookPage> pageList = new ArrayList<NotebookPage>();

    public Notebook() {
        pageList.add(new NotebookPage());
    }

    // B is the page number. Remember.
    public NotebookPage getPage(int B) {
        return pageList.get(B);
    }
}