package TimeTableStuff;

import java.util.Random;

public class Timepoint {

    /**
     * Zwischen 0 und 23
     */
    private final int hour;

    /**
     * Zwischen 0 und 60
     */
    private final int minute;

    /**
     * Zwischen 1 und 7 (?)
     */
    private final int day;

    public Timepoint(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Day: " + day + " Time: " + hour + ":" + minute + "";
    }

    public static Timepoint[] getRandomTimepointPair() {
        int day = getRandomValueInRange(1, 5);
        int h = getRandomValueInRange(7, 15);

        return new Timepoint[]{new Timepoint(day, h, 0), new Timepoint(day, h + 1, 0)};
    }

    private static int getRandomValueInRange(int min, int max) {
        Random random = new Random();
        if (min == max) {
            return min;
        }
        return random.nextInt(max - min + 1) + min;
    }

}
