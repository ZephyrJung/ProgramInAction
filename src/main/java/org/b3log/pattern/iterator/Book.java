package org.b3log.pattern.iterator;

import lombok.Data;

/**
 * @author : yu.zhang
 * Date : 2019/1/4 6:22 PM
 * Email : yu.zhang@7fresh.com
 **/
@Data
public class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
