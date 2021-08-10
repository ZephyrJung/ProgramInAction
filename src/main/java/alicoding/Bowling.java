package alicoding;

// Please don't modify the class name.
public class Bowling {
    public static final int TOTAL_PLAY_NUM = 21;
    public static final int TOTAL_PLAY_ROUND = 10;
    public static final int MAX_SCORE = 10;
    public static final int ZERO_NUM = 0;

    int score;
    int rolls[] = new int[TOTAL_PLAY_NUM];
    int rollsIndex = ZERO_NUM;
    int num = ZERO_NUM;

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;
    private static final int MAX_ROUND = 10;

    // Please don't modify the signature of this method.
    public void roll(int n) {
        this.rolls[rollsIndex] = n;
        this.rollsIndex++;
    }

    // Please don't modify the signature of this method.
    public int getScore() {
        int score = 0;
        int index = 0;
        int count = 0;
        while (index < this.rollsIndex && count++ < MAX_ROUND) {
            if (this.rolls[index] == MAX_SCORE) {
                score += MAX_SCORE + this.rolls[index + 1] + this.rolls[index + 2];
                index += ONE_STEP;
                continue;
            }
            if (this.rolls[index] + this.rolls[index + 1] == MAX_SCORE) {
                score += MAX_SCORE + this.rolls[index + 2];
                index += TWO_STEP;
                continue;
            }
            score += this.rolls[index] + this.rolls[index + 1];
            index += TWO_STEP;
        }
        return score;
    }

    public int getScore2() {
        int totalScore = ZERO_NUM;
        int num = 0;
        boolean checkIsSecond = false;
        for (int i = ZERO_NUM; i < this.rollsIndex && num < TOTAL_PLAY_ROUND; i++) {
            totalScore += rolls[i];
            //第一球非10分
            if (this.rolls[i] != MAX_SCORE && !checkIsSecond) {
                checkIsSecond = true;
                continue;
            }
            //第一球10分
            if (this.rolls[i] == MAX_SCORE && !checkIsSecond) {
                totalScore += rolls[i + 1] + rolls[i + 2];
                checkIsSecond = false;
                num++;
                continue;
            }
            //第二球10分
            if (checkIsSecond && this.rolls[i - 1] + this.rolls[i] == MAX_SCORE) {
                totalScore += rolls[i + 1];
                checkIsSecond = false;
                num++;
                continue;
            }
            //第二球
            if (checkIsSecond) {
                checkIsSecond = false;
                num++;
            }
        }
        return totalScore;
    }
}
