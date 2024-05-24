import java.util.ArrayList;

public class Notebook {
  protected ArrayList<NotebookPage> pageList = new ArrayList<NotebookPage>();
  public int openPage = 0;

  public Notebook() {
    pageList.add(new NotebookPage());
  }

  public NotebookPage getPage(int index) {
    return pageList.get(index);
  }
   
  public void addPage(NotebookPage page) {
      // checks if it's a duplicate
    boolean contained = false;
    for (int i = 0; i < pageList.size(); i++) {
      if (pageList.get(i).imageID.equals(page.imageID)) {
        contained = true;
      }
    }
    if (!contained) {
      if (pageList.size() == 1 && pageList.get(0).imageID.equals("transparent")) {
        pageList.remove(0);
        pageList.add(page);
      } else {
        pageList.add(page);
      }
    }
  }
}
