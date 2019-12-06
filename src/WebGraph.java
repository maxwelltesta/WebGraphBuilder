import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Maxwell Testa
 * 111713073
 * CSE214
 * HW #7
 * R01
 */
public class WebGraph {
	public static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	private int[][] edges;
	/**
	 * 
	 */
	public WebGraph() {
		edges = new int[MAX_PAGES][MAX_PAGES];
		pages = new ArrayList<WebPage>();
	}
	/**
	 * @param pagesFile
	 * @param linksFile
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * Builds from present files
	 * Creates ArrayList of WebPages
	 * 2D Array List which represents a graph of links
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException{
		WebGraph web = new WebGraph();
		try {
			Scanner reader = new Scanner(new File(pagesFile));
			while(reader.hasNext()) {
				String[] info = reader.nextLine().trim().split(" ");
				String url = info[0];
				ArrayList<String> keywords = new ArrayList<>();
				for (int i = 1; i < info.length; i++) {
					keywords.add(info[i]);
				}
				WebPage page = new WebPage(url, keywords);
				web.pages.add(page);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		for (int i = 0; i < web.pages.size(); i++) {
			web.pages.get(i).setIndex(i);
			web.pages.get(i).setRank(0);
			web.pages.get(i).getLinks().remove(0);
		}
		for (int i = 0; i < web.pages.size(); i++) {
			for (int j = 0; i < web.pages.size(); i++) {
				web.edges[i][j] = 0;
			}
		}
		try {
			Scanner reader = new Scanner(new File(linksFile));
			while(reader.hasNext()) {
				String[] urls = reader.nextLine().trim().split(" ");
				String first = urls[0];
				String second = urls[1];
				boolean true1 = false;
				int index1 = 0;
				boolean true2 = false;
				int index2 = 0;
				for (int i = 0; i < web.pages.size(); i++) {
					if (web.pages.get(i).getUrl().equals(first)) {
						true1 = true;
						index1 = web.pages.get(i).getIndex();
					}
					else if (web.pages.get(i).getUrl().equals(second)) {
						true2 = true;
						index2 = web.pages.get(i).getIndex();
					}
				}
				if (true1 && true2) {
					web.edges[index1][index2] = 1;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		web.updatePageRanks();
		return web;
	}
	/**
	 * @param url
	 * @param keywords
	 * @throws IllegalArgumentException
	 * 
	 * adds page to graph
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getUrl().equals(url)) {
				throw new IllegalArgumentException();
			}
		}
		WebPage page = new WebPage(url, keywords, pages.size(), 0);
		pages.add(page);
	}
	/**
	 * @param source
	 * @param destination
	 * @throws IllegalArgumentException
	 * 
	 * adds link between two links 
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException {
		boolean true1 = false;
		int index1 = 0;
		boolean true2 = false;
		int index2 = 0;
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getUrl().equals(source)) {
				true1 = true;
				index1 = pages.get(i).getIndex();
			}
			else if (pages.get(i).getUrl().equals(destination)) {
				true2 = true;
				index2 = pages.get(i).getIndex();
			}
		}
		if (!true1 || !true2) {
			throw new IllegalArgumentException();
		}
		if (true1 && true2) {
			edges[index1][index2] = 1;
		}
	}
	/**
	 * @param url
	 * removes page from graph
	 */
	public void removePage(String url) {
		int index = -1;
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getUrl().equals(url)) {
				index = i;
			}
		}
		if (index >= 0) {
			for (int i = index; i < pages.size(); i++) {
				for (int j = index; j < pages.size(); j++) {
					edges[i][j] = edges[i+1][j];
					pages.get(i).setIndex(i-1);
				}
			}
			for (int i = index; i < pages.size(); i++) {
				for (int j = index; j < pages.size(); j++) {
					edges[i][j] = edges[i][j+1];
				}
			}
		}
		pages.remove(index);
	}
	/**
	 * @param source
	 * @param destination
	 * removes link between two links 
	 */
	public void removeLink(String source, String destination) {
		boolean true1 = false;
		int index1 = 0;
		boolean true2 = false;
		int index2 = 0;
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getUrl().equals(source)) {
				true1 = true;
				index1 = pages.get(i).getIndex();
			}
			else if (pages.get(i).getUrl().equals(destination)) {
				true2 = true;
				index2 = pages.get(i).getIndex();
			}
		}
		if (!true1 || !true2 || edges[index1][index2] == 0) {
			throw new IllegalArgumentException();
		}
		if (true1 && true2) {
			edges[index1][index2] = 0;
		}
	}
	/**
	 * updates page ranks and arrayList of links
	 */
	public void updatePageRanks() {
		int rankCounter;
		for (int i = 0; i < pages.size(); i++) {
			rankCounter = 0;
			for (int j = 0; j < pages.size(); j++) {
				rankCounter += edges[j][i];
			}
			pages.get(i).setRank(rankCounter);
		}
		for (int i = 0; i < pages.size(); i++) {
			pages.get(i).getLinks().clear();
			for (int j = 0; j < pages.size(); j++) {
				if (edges[i][j] == 1) {
					pages.get(i).getLinks().add(Integer.toString(j));
				}
			}
		}
			
	}
	/**
	 * Prints table based on index number
	 */
	public void printTableIndex() {
		Collections.sort(pages, new IndexComparator());
		System.out.println(String.format("%-10s%-50s%-10s%-25s%-15s", "Index", "URL", "PageRank", "Links", "Keywords"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < pages.size(); i++) {
			System.out.println(pages.get(i).toString());
		}
	}
	/**
	 * Prints table based on table rank
	 */
	public void printTableRank() {
		Collections.sort(pages, new RankComparator());
		System.out.println(String.format("%-10s%-50s%-10s%-25s%-15s", "Index", "URL", "PageRank", "Links", "Keywords"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < pages.size(); i++) {
			System.out.println(pages.get(i).toString());
		}
	}
	/**
	 * Prints table based on URL
	 */
	public void printTableURL() {
		Collections.sort(pages, new URLComparator());
		System.out.println(String.format("%-10s%-50s%-10s%-25s%-15s", "Index", "URL", "PageRank", "Links", "Keywords"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i = 0; i < pages.size(); i++) {
			System.out.println(pages.get(i).toString());
		}
	}
	/**
	 * @param keyword
	 * searches pages for any page with the given keyword
	 */
	public void search(String keyword) {
		boolean found = false;
		ArrayList<WebPage> search = new ArrayList<>();
		for (int i = 0; i < pages.size(); i++) {
			if (pages.get(i).getKeywords().contains(keyword)) {
				search.add(pages.get(i));
				found = true;
			}
		}
		if (!found) {
			throw new IllegalArgumentException();
		}
		Collections.sort(search, new RankComparator());
		System.out.println(String.format("%-10s%-10s%-50s", "Rank", "PageRank", "URL"));
		for (int i = 0; i < search.size(); i++) {
			System.out.println(String.format("%-10s%-10s%-50s", i+1, search.get(i).getRank(), search.get(i).getUrl()));
		}
	}
}
