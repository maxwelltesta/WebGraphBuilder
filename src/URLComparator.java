import java.util.Comparator;
public class URLComparator implements Comparator {
	/* 
	 * Compares pages based on url (alphabetically)
	 */
	public int compare(Object o1, Object o2) {
        WebPage e1 = (WebPage) o1;
        WebPage e2 = (WebPage) o2;
        return (e1.getUrl().compareTo(e2.getUrl()));
}
}
