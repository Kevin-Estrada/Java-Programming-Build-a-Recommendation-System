
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minMins;
    private int maxMins;

    public MinutesFilter(int min, int max) {
        minMins = min;
        maxMins = max;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) <= maxMins  && MovieDatabase.getMinutes(id) >= minMins;
    }
}
