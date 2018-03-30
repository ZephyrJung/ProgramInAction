package org.b3log.pattern.proxy.ticket;

/**
 * Created by Zephyr on 2017/1/6.
 */
public class Ticket {
    private String start;
    private String end;
    private String date;
    private String price;
    private boolean checked=false;

    public Ticket(){

    }

    public Ticket(String start,String end,String date,String price){
        this.start=start;
        this.end=end;
        this.date=date;
        this.price=price;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
