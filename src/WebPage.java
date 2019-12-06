import java.util.ArrayList;
/**
 * Maxwell Testa
 * 111713073
 * CSE214
 * HW #7
 * R01
 */
public class WebPage {
	private String url;
	private int index, rank;
	private ArrayList<String> keywords;
	private ArrayList<String> links;
	/**
	 * @param url
	 * @param keywords
	 * WebPage constructor
	 * makes an array list of links
	 */
	public WebPage(String url, ArrayList<String> keywords) {
		this.url = url;
		this.keywords = keywords;
		links = new ArrayList<>();
		links.add("***");
	}
	/**
	 * @param url
	 * @param keywords
	 * @param index
	 * @param rank
	 * WebPage constructor for adding a new page
	 */
	public WebPage(String url, ArrayList<String> keywords, int index, int rank) {
		this.url = url;
		this.keywords = keywords;
		this.index = index;
		this.rank = rank;
		links = new ArrayList<>();
		links.add("***");
	}
	/**
	 * @returns url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url
	 * sets url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @returns index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index
	 * sets index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @returns rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank
	 * sets rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * @returns links
	 */
	public ArrayList<String> getLinks() {
		return links;
	}
	/**
	 * @param links
	 * sets links
	 */
	public void setLinks(ArrayList<String> links) {
		this.links = links;
	}
	/**
	 * @returns keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords
	 * sets keywords
	 */
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	/* 
	 * toString method for WebPages
	 */
	public String toString() {
		return String.format("%-10s%-50s%-10s%-25s%-15s", index, url, rank, links.toString().replaceAll("\\[", "").replaceAll("\\]", ""), 
				keywords.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
	}
}
