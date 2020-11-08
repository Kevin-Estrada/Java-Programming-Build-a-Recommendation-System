
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings fRatings = new FirstRatings();
        myRaters = fRatings.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        ArrayList<Double> listOfRatings = new ArrayList<Double>();
        for (Rater r : myRaters) {
            for (String movieID : r.getItemsRated()) {
                if (movieID.equals(id)) {
                    listOfRatings.add(r.getRating(id));
                    average = average + r.getRating(id);
                }
            }
        }
        if (listOfRatings.size() >= minimalRaters) {
            average = average/listOfRatings.size();
        } else {
            average = 0.0;
        }

        return average;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for (String m : movies) {
            double average = getAverageByID(m, minimalRaters);

            if(average != 0.0) {
                Rating r = new Rating(m, average);
                averageRatings.add(r);
            }
        }
        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> movieIDs =  MovieDatabase.filterBy(filterCriteria);
        
        for(String id : movieIDs) {
            double average = getAverageByID(id, minimalRaters);
            if (average != 0.0) {
                Rating r = new Rating (id,average);
                list.add(r);
            }
        }
        return list;
    }

}
