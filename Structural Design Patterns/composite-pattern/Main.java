
import java.util.*;

interface FileSystemComponent {
    void showDetails();
    int getSize();
}

class File implements FileSystemComponent {
    private final String name;
    private final int size;

    public File (String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize () {
        return this.size;
    }

    @Override
    public void showDetails () {
        System.out.println("File: " + name + ", Size: " + size + "KB");
    }
}

class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Folder (String name) {
        this.name = name;
    }

    public void add (FileSystemComponent component) {
        children.add(component);
    }

    public void remove (FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails () {
        System.err.println("Folder: " + name);
        for (FileSystemComponent component: children) {
            component.showDetails();
        }
    }

    @Override
    public int getSize () {
        int totalSize = 0;
        for (FileSystemComponent component: children) {
            totalSize += component.getSize();
        }

        return totalSize;
    }
}

public class Main {
    public static void main(String[] args) {
        File file1 = new File("resume.pdf", 120);
        File file2 = new File("photo.jpg", 500);

        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");

        documents.add(file1);
        pictures.add(file2);

        Folder root = new Folder("root");
        root.add(documents);
        root.add(pictures);

        root.showDetails();
        System.out.println("Total size: " + root.getSize() + "KB");
    }
}