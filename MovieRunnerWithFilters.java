
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        String moviefile = "ratedmovies_short.csv";
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatings(minimalRaters);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }

    public void printAverageRatingsByYear() {
        String moviefile = "ratedmovies_short.csv";
        int year = 2000;
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");
        Filter yearFilter = new YearAfterFilter(year);
        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,yearFilter);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem ()) + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }

    public void printAverageRatingsByGenre() {
        String moviefile = "ratedmovies_short.csv";
        String genre = "Crime";
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");
        Filter genreFilter = new GenreFilter(genre);
        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,genreFilter);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem ()));
        }
    }

    public void printAverageRatingsByMinutes() {
        String moviefile = "ratedmovies_short.csv";
        int minMinutes = 110;
        int maxMinutes = 170;
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");
        Filter minutesFilter = new MinutesFilter(minMinutes,maxMinutes);
        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,minutesFilter);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + "Time: " + MovieDatabase.getMinutes(r.getItem ()) + "\t" + MovieDatabase.getTitle(r.getItem ()));
        }
    }

    public void printAverageRatingsByDirectors() {
        String moviefile = "ratedmovies_short.csv";
        String listOfDirectors = "Charles Chaplin, Michael Mann, Spike Jonze";
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");
        Filter directorsFilter = new DirectorsFilter(listOfDirectors);
        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,directorsFilter);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem ()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        String moviefile = "ratedmovies_short.csv";
        int year = 1980;
        String genre = "Romance";
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");

        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(yearFilter);
        allFilters.addFilter(genreFilter);

        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem ()) + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem ()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        String moviefile = "ratedmovies_short.csv";
        int minMinutes = 30;
        int maxMinutes = 170;
        String listOfDirectors = "Spike Jonze, Michael Mann, Charles Chaplin, Francis Ford Coppola";
        ThirdRatings tRatings = new ThirdRatings("ratings_short.csv");

        Filter minutesFilter = new MinutesFilter(minMinutes,maxMinutes);
        Filter directorFilter = new DirectorsFilter(listOfDirectors);
        AllFilters allFilters = new AllFilters();

        allFilters.addFilter(directorFilter);
        allFilters.addFilter(minutesFilter);

        System.out.println("Number of Raters read in: " + tRatings.getRaterSize());
        MovieDatabase.initialize(moviefile);

        System.out.println("Number of Movies read in: " + MovieDatabase.size());

        int minimalRaters = 1;
        ArrayList<Rating> listOfRatings = tRatings.getAverageRatingsByFilter(minimalRaters,allFilters);

        System.out.println("Number of Movies with minimum raters of " + minimalRaters + " is: " + listOfRatings.size());
        Collections.sort(listOfRatings);

        for (Rating r : listOfRatings) {
            System.out.println(r.getValue() + "\t" + "Time: " + MovieDatabase.getMinutes(r.getItem ()) + "\t" + MovieDatabase.getTitle(r.getItem ()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem ()));
        }
    }
}
