import java.util.Comparator;
public class IndexComparator implements Comparator {
	/* 
	 * Compares pages based on index
	 */
	public int compare(Object o1, Object o2) {
        WebPage e1 = (WebPage) o1;
        WebPage e2 = (WebPage) o2;
        if (e1.getIndex() == e2.getIndex())
            return 0;
        else if (e1.getIndex() > e2.getIndex())
            return 1;
        else
            return -1;
    }
}
