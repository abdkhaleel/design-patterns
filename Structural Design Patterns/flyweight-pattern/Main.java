
import java.util.HashMap;
import java.util.Map;

interface Tree {
    void draw(int x, int y);
}
class TreeType implements Tree {
    private final String type;
    private final String color;
    private final String texture;

    public TreeType (String type, String color, String texture) {
        this.type = type;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void draw (int x, int y) {
        System.out.println(
            "Drawing " + type + " tree (" + color + ", " + texture + 
            ") at (" + x + "," + y + ")"
        );
    }
}

class TreeFactory {
    private static final Map<String, Tree> treeMap = new HashMap<>();
    
    public static Tree getTree (String type, String color, String texture) {
        String key = type + "-" + color + "-" + texture;
        if(!treeMap.containsKey(key)){
            treeMap.put(key, new TreeType(type, color, texture));
        }
        return treeMap.get(key);
    }
}

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Tree tree = TreeFactory.getTree("Oak", "Green", "Rough");
            tree.draw(i, i);
        }
    }
}