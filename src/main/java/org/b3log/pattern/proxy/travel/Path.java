package org.b3log.pattern.proxy.travel;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class Path {
    private String start;
    private String end;

    public Path(){

    }
    public Path(String start,String end){
        this.start=start;
        this.end=end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
