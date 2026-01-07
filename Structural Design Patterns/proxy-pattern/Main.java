interface Image {
    void display();
}

class RealImage implements Image {
    private final String fileName;
    public RealImage (String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk () {
        System.out.println("Loading image from Disk: " + fileName);
    }

    @Override
    public void display () {
        System.out.println("Displaying Image: " + fileName);
    }
}

class ImageProxy implements Image {
    private final String fileName;
    private RealImage realImage;
    public ImageProxy (String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display () {
        if(realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

public class Main {
    public static void main(String[] args) {
        Image image = new ImageProxy("photo.png");

        System.out.println("Image object created");
        image.display(); 
        image.display(); 
    }
}
