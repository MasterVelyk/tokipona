public class EventHandler {
    // holds all the events
    private Notebook myNotebook;

    public EventHandler(Notebook notebook) {
        notebook = myNotebook;
    }

    public String runEvent(int event, int line) {
        // old man dialogue
        if (event == 0) {
            if (line == 0) {
                return "toki a";
            }
        }
        return "";
    }
}