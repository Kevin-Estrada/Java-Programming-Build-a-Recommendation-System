
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        String moviefile = "ratedmovies_short.csv";
        String raterfile = "ratings_short.csv";

        FourthRatings fRatings = new FourthRatings("ratings_short.csv");
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = fRatings.getAverageRatings(minimalRaters);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        String moviefile = "ratedmovies_short.csv";
        int year = 1980;
        String genre = "Romance";
        FourthRatings fRatings = new FourthRatings("ratings_short.csv");

        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = fRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem ()) + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem ()));
        }
    }

    public void printSimilarRatings() {
        String moviefile = "ratedmoviesfull.csv";
        String raterID = "71";
        int minRaters = 5;
        int topSimilarRaters = 20;
        FourthRatings fRatings = new FourthRatings("ratings.csv");

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        ArrayList<Rating> listOfRatings = fRatings.getSimilarRatings(raterID,topSimilarRaters, minRaters);

        System.out.println("Number of Movies with minimum raters of " + minRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));

        }
    }

    public void printSimilarRatingsByGenre() {
        String moviefile = "ratedmoviesfull.csv";
        String raterID = "964";
        String genre = "Mystery";
        int minRaters = 5;
        int topSimilarRaters = 20;
        Filter genreFilter = new GenreFilter(genre);
        FourthRatings fRatings = new FourthRatings("ratings.csv");

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        ArrayList<Rating> listOfRatings = fRatings.getSimilarRatingsByFilter(raterID,topSimilarRaters, minRaters, genreFilter);

        System.out.println("Number of Movies with minimum raters of " + minRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + "Genre: " + MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        String moviefile = "ratedmoviesfull.csv";
        String raterID = "120";
        String directors = "Clint Eastwood, J.J. Abrams, Alfred Hitchcock, Sydney Pollack, David Cronenberg, Oliver Stone, Mike Leigh";
        int minRaters = 2;
        int topSimilarRaters = 10;
        Filter directorFilter = new DirectorsFilter(directors);
        FourthRatings fRatings = new FourthRatings("ratings.csv");

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        ArrayList<Rating> listOfRatings = fRatings.getSimilarRatingsByFilter(raterID,topSimilarRaters, minRaters, directorFilter);

        System.out.println("Number of Movies with minimum raters of " + minRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + "Directed by : " + MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        String moviefile = "ratedmoviesfull.csv";
        String raterID = "168";
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        int minRaters = 3;
        int topSimilarRaters = 10;

        Filter genreFilter = new GenreFilter(genre);
        Filter minutesFilter = new MinutesFilter(minMinutes,maxMinutes);

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);

        FourthRatings fRatings = new FourthRatings("ratings.csv");

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        ArrayList<Rating> listOfRatings = fRatings.getSimilarRatingsByFilter(raterID,topSimilarRaters, minRaters, allFilters);

        System.out.println("Number of Movies with minimum raters of " + minRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        String moviefile = "ratedmoviesfull.csv";
        String raterID = "314";
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        int minRaters = 5;
        int topSimilarRaters = 10;

        Filter yearAfterFilter = new YearAfterFilter(year);
        Filter minutesFilter = new MinutesFilter(minMinutes,maxMinutes);

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(minutesFilter);

        FourthRatings fRatings = new FourthRatings("ratings.csv");

        System.out.println("Number of Raters read in: " + RaterDatabase.size());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        ArrayList<Rating> listOfRatings = fRatings.getSimilarRatingsByFilter(raterID,topSimilarRaters, minRaters, allFilters);

        System.out.println("Number of Movies with minimum raters of " + minRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }
}
