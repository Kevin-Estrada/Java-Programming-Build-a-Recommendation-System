import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }

    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }

    private double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        ArrayList<Double> listOfRatings = new ArrayList<Double>();
        for (Rater r :  RaterDatabase.getRaters()) {
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
            double average = Math.round(getAverageByID(m, minimalRaters) * 100.0) / 100.0;

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
            double average = Math.round(getAverageByID(id, minimalRaters) * 100.0) / 100.0;
            if (average != 0.0) {
                Rating r = new Rating (id,average);
                list.add(r);
            }
        }
        return list;
    }

    private double dotProduct(Rater me, Rater r) {
        double dotProductResult = 0.0;
        ArrayList<String> moviesRatedByMe = me.getItemsRated();

        for(String movieRated : moviesRatedByMe) {
            if (r.getItemsRated().contains(movieRated)) {
                double ratingByMe = me.getRating(movieRated);
                double ratingByR = r.getRating(movieRated);

                dotProductResult = dotProductResult + ((ratingByMe - 5.0) * (ratingByR - 5.0));
            }
        }

        return dotProductResult;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> raterList = new ArrayList<Rating>();
        Rater myRatings = RaterDatabase.getRater(id);

        for(Rater r : RaterDatabase.getRaters()) {
            if(!r.getID().equals(id)) {
                double dotProductResult = dotProduct(myRatings, r);
                if (dotProductResult >= 0) {
                    raterList.add(new Rating(r.getID(), dotProductResult));
                }
            }
        }

        Collections.sort(raterList, Collections.reverseOrder());
        return raterList;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRatersList = getSimilarities(id);
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratingsWithWeight = new ArrayList<Rating> ();

        HashMap<String,Double> totalRating = new HashMap<String,Double> ();
        HashMap<String,Integer> totalCount = new HashMap<String,Integer> ();

        for (String movieID : movieIDs) {
            double currentRating = 0.0;
            int count = 0;

            for (int i=0; i < numSimilarRaters; i++) {
                Rating rating = similarRatersList.get(i);
                String ratersID = rating.getItem();
                double weight = rating.getValue();

                Rater rater = RaterDatabase.getRater(ratersID);

                if (rater.hasRating(movieID)) {
                    double ratingValue = rater.getRating(movieID) * weight;
                    currentRating += ratingValue;
                    count += 1;
                }
            }

            if (count >= minimalRaters) {
                totalRating.put(movieID, currentRating);
                totalCount.put(movieID, count);
            }
        }

        for (String movieID : totalRating.keySet()) {
            double weightedRating = Math.round((totalRating.get(movieID) / totalCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            ratingsWithWeight.add(rating);
        }
        
        Collections.sort(ratingsWithWeight, Collections.reverseOrder());

        return ratingsWithWeight;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRatersList = getSimilarities(id);
        ArrayList<String> filteredMovieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratingsWithWeight = new ArrayList<Rating> ();

        HashMap<String,Double> totalRating = new HashMap<String,Double> ();
        HashMap<String,Integer> totalCount = new HashMap<String,Integer> ();

        for (String movieID : filteredMovieIDs) {
            double currentRating = 0.0;
            int count = 0;

            for (int i=0; i < numSimilarRaters; i++) {
                Rating rating = similarRatersList.get(i);
                String ratersID = rating.getItem();
                double weight = rating.getValue();

                Rater rater = RaterDatabase.getRater(ratersID);

                if (rater.hasRating(movieID)) {
                    double ratingValue = rater.getRating(movieID) * weight;
                    currentRating += ratingValue;
                    count += 1;
                }
            }

            if (count >= minimalRaters) {
                totalRating.put(movieID, currentRating);
                totalCount.put(movieID, count);
            }
        }

        for (String movieID : totalRating.keySet()) {
            double weightedRating = Math.round((totalRating.get(movieID) / totalCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            ratingsWithWeight.add(rating);
        }

        Collections.sort(ratingsWithWeight, Collections.reverseOrder());

        return ratingsWithWeight;
    }
}
