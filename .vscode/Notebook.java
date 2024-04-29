import java.util.ArrayList;

public class Notebook {
    protected ArrayList<NotebookPage> pageList = new ArrayList<NotebookPage>();

    public Notebook() {
        pageList.add(new NotebookPage());
    }

    public NotebookPage getPage(int page) {
        return pageList.get(page);
    }
}