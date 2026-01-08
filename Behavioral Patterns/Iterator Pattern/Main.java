class Book {
    final String title;
    Book (String title) {
        this.title = title;
    }
}

interface Iterator<T> {
    boolean hasNext();
    T next();
}

interface IterableCollection<T> {
    Iterator<T> createIterator();
}

class BookShelf implements IterableCollection<Book> {
    private final Book[] books = new Book[3];
    private int index = 0;

    public void addBook (Book book) {
        books[index++] = book;
    }

    @Override
    public Iterator<Book> createIterator () {
        return new BookIterator(books);
    }
}

class BookIterator implements Iterator<Book> {
    private final Book[] books;
    private int position = 0;

    public BookIterator (Book[] books)  {
        this.books = books;
    }

    @Override
    public boolean hasNext () {
        return position < books.length && books[position] != null;
    }

    @Override
    public Book next () {
        return books[position++];
    }
}

public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();

        bookShelf.addBook(new Book("Clean Code"));
        bookShelf.addBook(new Book("Design Patterns"));
        bookShelf.addBook(new Book("Data Structures and Algorithms"));

        Iterator<Book> iterator = bookShelf.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().title);
        }
    }
}