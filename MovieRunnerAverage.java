
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sRatings = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        System.out.println("Number of movies read in: " + sRatings.getMovieSize());
        System.out.println("Number of Raters read in: " + sRatings.getRaterSize());
        
        int minimalRaters = 3;
        ArrayList<Rating> listOfRatings = sRatings.getAverageRatings(minimalRaters);
        
        Collections.sort(listOfRatings);
        
        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + sRatings.getTitle(r.getItem ()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sRatings = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        String movieTitle = "The Godfather";
        String movieID = sRatings.getID(movieTitle);
        int minimalRaters = 1;
        
        ArrayList<Rating> listOfRatings = sRatings.getAverageRatings(minimalRaters);
        for (Rating r : listOfRatings) {
            if (r.getItem().equals(movieID)) {
                System.out.println("Average for " + movieTitle + " is: " +  r.getValue());
            }
        }
        
    }
}
