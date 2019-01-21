package org.b3log.pattern.iterator;

import lombok.Data;

import java.util.Iterator;

/**
 * @author : yu.zhang
 * Date : 2019/1/4 6:23 PM
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class BookShelf implements Aggregate {
    private Book[] books;
    private int last = 0;

    public BookShelf(int maxSize){
        this.books = new Book[maxSize];
    }

    public Book getBookAt(int index){
        return books[index];
    }

    public void appendBook(){}

    @Override
    public Iterator iterator() {
        return null;
    }

}
