import java.util.Comparator;
public class RankComparator implements Comparator {
	/*
	 * Compares pages based on rank
	 */
	public int compare(Object o1, Object o2) {
        WebPage e1 = (WebPage) o1;
        WebPage e2 = (WebPage) o2;
        if (e1.getRank() == e2.getRank())
            return 0;
        else if (e1.getRank() > e2.getRank())
            return -1;
        else
            return 1;
    }
}
