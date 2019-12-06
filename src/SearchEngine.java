import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Maxwell Testa
 * 111713073
 * CSE214
 * HW #7
 * R01
 */
public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private WebGraph web;
	/**
	 * @param args
	 * Menu for building files 
	 * and using menu
	 */
	public static void main(String[] args) {
		boolean on = true;
		Scanner sc = new Scanner(System.in);
		SearchEngine engine = new SearchEngine();
		System.out.println("Loading Web Graph Data!");
		System.out.println("Success!");
		engine.web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		while (on = true) {
			System.out.println("\nMenu: ");
			System.out.println("\t(AP) - Add a new page to the graph.");
			System.out.println("\t(RP) - Remove a page from the graph.");
			System.out.println("\t(AL) - Add a link between pages in the graph.");
			System.out.println("\t(RL) - Remove a link between pages in the graph.");
			System.out.println("\t(P) - Print the graph.");
			System.out.println("\t(S) - Search for pages with a keyword.");
			System.out.println("\t(Q) - Quit\n");
			System.out.print("Enter a selection: ");
			String selection = sc.nextLine().toUpperCase();
			if (selection.equals("AP")) {
				try {
					System.out.println("Enter a URL");
					String url = sc.nextLine();
					System.out.println("Enter keywords (seperated by spaces)");
					String keywords = sc.nextLine();
					ArrayList<String> words = new ArrayList<>(Arrays.asList(keywords.split(" ")));
					engine.web.addPage(url, words);
					engine.web.updatePageRanks();
					System.out.println(url + " successfully added to the WebGraph");
				}
				catch(IllegalArgumentException ex) {
					System.out.println("This is already a part of the WebGraph");
				}
			}
			else if(selection.equals("RP")) {
				try {
					System.out.println("Enter a URL: ");
					String url = sc.nextLine();
					engine.web.removePage(url);
					System.out.println(url + " removed from the WebGraph");
					engine.web.updatePageRanks();
				}
				catch (IllegalArgumentException ex) {
					System.out.println("URL non-existant");
				}
			}
			else if(selection.equals("AL")) {
				try {
					System.out.println("Enter a source url: ");
					String source = sc.nextLine();
					System.out.println("Enter a destination url: ");
					String destination = sc.nextLine();
					engine.web.addLink(source, destination);
					engine.web.updatePageRanks();
					System.out.println("New link added between " + source + " and " + destination);
				}
				catch(IllegalArgumentException ex) {
					System.out.println("Cannot create a new link! Non-existent");
				}
				
			}
			else if(selection.equals("RL")) {
				try {
					System.out.println("Enter a source url: ");
					String source = sc.nextLine();
					System.out.println("Enter a destination url: ");
					String destination = sc.nextLine();
					engine.web.removeLink(source, destination);
					engine.web.updatePageRanks();
					System.out.println("Link removed between " + source + " and " + destination);
				}
				catch (IllegalArgumentException ex) {
					System.out.println("Cannot remove link");
				}
			}
			else if(selection.equals("P")) {
				System.out.println("\t\t(I) Sort based on index (ASC)");
				System.out.println("\t\t(U) Sort based on URL (ASC)");
				System.out.println("\t\t(R) Sort based on rank (DSC)");
				System.out.print("Enter a selection: ");
				String selected = sc.nextLine().toUpperCase();
				if (selected.equals("I")) {
					engine.web.printTableIndex();
				}
				else if (selected.equals("U")) {
					engine.web.printTableURL();
				}
				else if (selected.equals("R")) {
					engine.web.printTableRank();
				}
				else {
					System.out.println("Invalid input");
				}
			}
			else if(selection.equals("S")) {
				try {
					System.out.println("Enter a keyword: ");
					engine.web.search(sc.next());
				}
				catch(IllegalArgumentException ex) {
					System.out.println("keyword not found");
					sc.nextLine();
				}
			}
			else if(selection.equals("Q")) {
				on = false;
				System.out.println("Goodbye");
				break;
			}
			else {
				System.out.println("Invalid Input");
			}
		}
	}
}
