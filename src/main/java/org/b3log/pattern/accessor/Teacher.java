package org.b3log.pattern.accessor;

/**
 * @author : yu.zhang
 * Date : 2019/1/24 5:22 PM
 * Email : yu.zhang@7fresh.com
 **/
public class Teacher implements Element {

    private String name; // 教师姓名
    private int score; // 评价分数
    private int paperCount; // 论文数

    // 构造器
    public Teacher(String name, int score, int paperCount) {
        this.name = name;
        this.score = score;
        this.paperCount = paperCount;
    }

    // visitor访问本对象的数据结构
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }
}
