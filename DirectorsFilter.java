
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String listOfDirectors;

    public DirectorsFilter(String list) {
        listOfDirectors = list;
    }

    @Override
    public boolean satisfies(String id) {
        String[] directorListSplit = listOfDirectors.split(", ");
        for(int i = 0; i < directorListSplit.length; i++) {
            if(MovieDatabase.getDirector(id).indexOf(directorListSplit[i]) != -1) {
                return true;
            }
        }
        return false;
    }
}
