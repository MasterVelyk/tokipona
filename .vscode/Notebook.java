import java.util.ArrayList;

public class Notebook {
    protected ArrayList<NotebookPage> pageList = new ArrayList<NotebookPage>();
    public int openPage = 0;

    public Notebook() {
      pageList.add(new NotebookPage());
    }

    // B is the page number. Remember.
    public NotebookPage getPage(int B) {
        return pageList.get(B);
    }
    
    public void addPage(NotebookPage page) {
      pageList.add(page);
    }
}