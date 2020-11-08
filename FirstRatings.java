
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        String filePath = "D:/BlueJ/StepOneStarterProgram/data/";
        filename = filePath + filename;
        ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();

        for(CSVRecord record : parser) {
            Movie m = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"), record.get("director"), record.get("country"), record.get("poster"), 
                    Integer.parseInt(record.get("minutes")));
            listOfMovies.add(m);
        }

        return listOfMovies;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        String filePath = "D:/BlueJ/StepOneStarterProgram/data/";
        filename = filePath + filename;
        ArrayList<Rater> listOfRaters = new ArrayList<Rater>();
        ArrayList<String> IDOfRaters = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        String ID = "";

        for(CSVRecord record : parser) {
            Rater r = new EfficientRater(record.get("rater_id"));
            ID = r.getID();
            if (!IDOfRaters.contains(ID)){
                //System.out.println("Adding rater " + ID + " to list");
                r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                listOfRaters.add(r);
                IDOfRaters.add(ID);
            } else {
                for (int j= 0; j < IDOfRaters.size(); j++){
                    for(int i = 0; i < listOfRaters.size(); i++) {
                        if(listOfRaters.get(i).getID().equals(ID) && !(listOfRaters.get(i).getItemsRated().contains(record.get("movie_id")))) {
                            //System.out.println("Adding a rating to user ID: " + listOfRaters.get(i).getID());
                            listOfRaters.get(i).addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                        }
                    }
                }
            }
        }

        return listOfRaters;
    }

    public void testLoadMovies() {
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> listOfMovies = loadMovies(filename);
        System.out.println("The number of movies in file are: " + listOfMovies.size());
        int comedyGenreMovies = 0;
        int moviesLongerThan150Mins = 0;
        HashMap<String, Integer> directorMap = new HashMap<String, Integer>();
        int maxNumOfMoviesDirected = 0;
        for (Movie m : listOfMovies) {
            System.out.println(m);
            if (m.getGenres().contains("Comedy")) {
                comedyGenreMovies++;
            }

            if (m.getMinutes() > 150) {
                moviesLongerThan150Mins++;
            }

            if(!directorMap.containsKey(m.getDirector())) {
                directorMap.put(m.getDirector(), 1);
            } else {
                directorMap.put(m.getDirector(), directorMap.get(m.getDirector()) + 1);
            }
        }
        //Movies that include Comedy Genre
        System.out.println("Number of movies that are considered comedy genre: " + comedyGenreMovies);
        //Movies that are longer than 150 minutes
        System.out.println("Number of movies that are longer than 150 minutes: " + moviesLongerThan150Mins);
        //Movie directors
        for (String director : directorMap.keySet()) {
            int temp = directorMap.get(director);
            if (temp > maxNumOfMoviesDirected) {
                maxNumOfMoviesDirected = temp;
            }
        }

        for (String director : directorMap.keySet()) {
            if (directorMap.get(director) == maxNumOfMoviesDirected) {
                System.out.println("Director Name: " + director + "   Number of movies directed: " + directorMap.get(director));
            }
        }
    }

    public void testLoadRaters() {
        String filename = "ratings.csv";
        ArrayList<Rater> listOfRaters = loadRaters(filename);
        System.out.println("The number of raters in file are: " + listOfRaters.size());
        int numberOfRatingsPerID = 0;
        String ID = "193";
        HashMap<String, Integer> raterMap = new HashMap<String, Integer>();
        int maxNumOfRatings = 0;

        String movieID = "1798709";
        ArrayList<String> IDOfRaters = new ArrayList<String>();
        ArrayList<String> uniqueMovieIDs = new ArrayList<String>();

        for (Rater r : listOfRaters) {
            System.out.println("Rater ID: " + r.getID() + "\t" + "Number of ratings: " + r.numRatings());
            for (String movie : r.getItemsRated()) {
                System.out.println("\t" + "Movie ID: " + movie + "\t" + "Movie Rating: " + r.getRating(movie));
            }
        }

        for (Rater r : listOfRaters) {
            if(r.getID().equals(ID)){
                ArrayList<String> ratingList = r.getItemsRated();
                System.out.println("Rater ID: " + r.getID() + " has " + ratingList.size() + " total ratings.");
            }
        }

        for (Rater r : listOfRaters) {
            if(!raterMap.containsKey(r.getID())) {
                raterMap.put(r.getID(), r.getItemsRated().size());
            }
        }

        for (String id : raterMap.keySet()) {
            int temp = raterMap.get(id);
            if (temp > maxNumOfRatings) {
                maxNumOfRatings = temp;
            }
        }

        for (String id : raterMap.keySet()) {
            if (raterMap.get(id) == maxNumOfRatings) {
                System.out.println("Rater ID: " + id + " Max number of ratings " + raterMap.get(id));
            }
        }

        for (Rater r : listOfRaters) {
            if(r.hasRating(movieID)){
                IDOfRaters.add(r.getID());
            }
        }

        System.out.println("Movie ID: " + movieID + " has " + IDOfRaters.size() + " ratings");

        for (Rater r : listOfRaters) {
            for (String movID : r.getItemsRated()) {
                if(!uniqueMovieIDs.contains(movID)) {
                    uniqueMovieIDs.add(movID);
                }
            }
        }

        System.out.println("Total rated movies amongst raters is: " + uniqueMovieIDs.size());
    }
}
