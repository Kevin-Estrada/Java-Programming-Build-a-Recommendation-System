/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fRatings = new FirstRatings();
        myMovies = fRatings.loadMovies(moviefile);
        myRaters = fRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
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

        for (Movie m : myMovies) {
            double average = getAverageByID(m.getID(), minimalRaters);

            if(average != 0.0) {
                Rating r = new Rating(m.getID(), average);
                averageRatings.add(r);
            }
        }
        return averageRatings;
    }

    public String getTitle(String id) {
        String title = "ID was not found.";
        for (Movie m : myMovies) {
            if(m.getID().equals(id)) {
                return title = m.getTitle();
            }
        }
        return title;
    }

    public String getID(String title) {
        String id = "Movie title was not found.";
        for (Movie m : myMovies) {
            if(m.getTitle().equals(title)) {
                return id = m.getID();
            }
        }
        return id;
    }
    
    
}