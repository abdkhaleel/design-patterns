
import java.util.Stack;

class EditorMemento {
    private final String content;
    public EditorMemento (String content) {
        this.content = content;
    }

    String getContent () {
        return content;
    }
}

class TextEditor {
    private String content;

    public void setContent (String content) {
        this.content = content;
    }

    public String getContent () {
        return content;
    }

    public EditorMemento save () {
        return new EditorMemento(content);
    }

    public void restore (EditorMemento memento) {
        this.content = memento.getContent();
    }
}

class History {
    private final Stack<EditorMemento> stack = new Stack<>();

    public void save (EditorMemento memento) {
        stack.push(memento);
    }

    public EditorMemento undo () {
        return stack.pop();
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setContent("Hello");
        history.save(editor.save());

        editor.setContent("Hello World");
        history.save(editor.save());

        editor.setContent("Hello World!!!");

        editor.restore(history.undo());
        System.out.println(editor.getContent());

        editor.restore(history.undo());
        System.out.println(editor.getContent());
    }
}

