package logic;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import Controller.CreateTST;
import Controller.Main;
import Controller.QuickSelect;

public class countmatch {
	
	
	public static int findoccurences(String searched) {
		int countofword=0;
		for(int i=0;i<CreateTST.myfile.length;i++) {
		countofword+=CreateTST.table[i+1][CreateTST.st.get(searched)];
		}
		System.out.println(countofword);
		return countofword;
		}
	
	public int searchOccurance(String cname) {
		
		//File[] myFile=new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
		if(CreateTST.st !=null) {
		int indexN=CreateTST.st.get(cname);
		System.out.println("occ::"+indexN);
		ArrayList<Integer> occArray=CreateTST.index.get(indexN);
		
		  int con=0; 
		  for(Integer x: occArray) 
		  { con=con+x; }
		  System.out.println("COUNT==="+con);
		  return con;
		}else {
			return 0;
		}
		 
	}
	
	public File[] quickSelect(String word) {
		System.out.println("Quick Select");
		String[] search= {word};
		
		File[] myfile=new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
		File[] result=new File[myfile.length];
		//	CreateTST.createTST();
		Integer[] list=new Integer[myfile.length]; 
		
		for(int n=0;n<myfile.length;n++) {
			list[n]=0;
		}
		System.out.println(search);
		for(int s=0;s<search.length;s++) {
			if(CreateTST.st == null) {
				System.out.println("st Null");
			}
			
			System.out.println(CreateTST.st.get(search[s]));
			int indextohash=CreateTST.st.get(search[s]);
			for(int n=0;n<myfile.length;n++) {
				list[n]=list[n]+CreateTST.index.get(indextohash).get(n);				
			}
		}
		ArrayList<Integer> oldlist = new ArrayList<Integer>(Arrays.asList(list));
	
		for (int i = list.length-1; i >= 0; i--) {
			int rank=QuickSelect.select(list, i);
			System.out.println("Meera:::"+myfile[oldlist.indexOf(rank)]);
			result[i]=myfile[oldlist.indexOf(rank)];
			oldlist.set(oldlist.indexOf(rank), null);	
		}
		return result;
	}
	

	public void countsearch(String cname) throws IOException {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		int counter = 0;
		ArrayList<String> arrayList = new ArrayList<String>();
		File folder = new File("C:\\Users\\MEERA\\Documents\\MAC\\W3C Web Pages\\W3C Web Pages\\Text\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				arrayList.add("C:\\Users\\MEERA\\Documents\\MAC\\W3C Web Pages\\W3C Web Pages\\Text\\\\"
						+ listOfFiles[i].getName());
				// System.out.println( listOfFiles[i].getName());
			}
		}
		for (int i = 0; i < arrayList.size(); i++) {
			org.jsoup.nodes.Document doc1 = Jsoup.parse(new File(arrayList.get(i)), "UTF-8", "www.w3sfjj.com");
			String text = doc1.text();
			String[] matchingword = text.split(" ");
			Arrays.sort(matchingword);
			for (int j = 0; j < matchingword.length; j++) {
				if (matchingword[j].equals(cname)) {
					counter++;
				}
			}
			// System.out.println(listOfFiles[i].getName()+"file
			// contain**********"+counter+"**********words");
			hashMap.put(listOfFiles[i].getName(), counter);

			matchingword = null;
			counter = 0;
		}

		System.out.println("\n------------------------Search Results------------------------");
		Map<Integer, String> map = sortByValues(hashMap);
		Set set2 = map.entrySet();
		Iterator iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry) iterator2.next();
			if (!me2.getValue().equals(0)) {
				System.out.println(me2.getKey() + " --> '" + cname + "' occures " + me2.getValue() + " times.\n");

			}
		}
	}

	public void pluralSearch(String cname) throws IOException {
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();

		int counter = 0;
		ArrayList<String> arrayList = new ArrayList<String>();
		File folder = new File("C:\\Users\\MEERA\\Documents\\MAC\\W3C Web Pages\\W3C Web Pages\\Text\\\\");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				arrayList.add("C:\\Users\\MEERA\\Documents\\MAC\\W3C Web Pages\\W3C Web Pages\\Text\\\\"
						+ listOfFiles[i].getName());
			}
		}
		System.out.println("--------------------------Search Results--------------------------\n");
		for (int i = 0; i < arrayList.size(); i++) {
			org.jsoup.nodes.Document doc1 = Jsoup.parse(new File(arrayList.get(i)), "UTF-8", "www.w3sfjj.com");
			String text = doc1.text();
			// String pattern = cname + "(ing|ed|er|s)?";
			String pattern = "\\b" + cname + "([a-zA-Z]+)?";

			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(text);

			while (m.find()) {
				counter++;
				System.out.println(m.group());
			}

			System.out.println(listOfFiles[i].getName() + " --> contains " + counter + " words.\n");
			System.out.println("*********************************************************\n");

			hmap.put(listOfFiles[i].getName(), counter);

			counter = 0;

		}
		System.out.println("--------------------------Before Sorting--------------------------\n");
		Set set = hmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			System.out.print(me.getKey() + " --> ");
			System.out.println(me.getValue() + " count \n");
		}

		Map<Integer, String> map = sortByValues(hmap);
		System.out.println("--------------------------After Sorting--------------------------\n");
		Set set2 = map.entrySet();
		Iterator iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry me2 = (Map.Entry) iterator2.next();
			System.out.print(me2.getKey() + " --> ");
			System.out.println(me2.getValue() + " count \n");
		}
	}

	private static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		Collections.reverse(list);
		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		System.out.println();
		return sortedHashMap;
	}

	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();

		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}

		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}

		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);

				// if last two chars equal
				if (c1 == c2) {
					// update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;

					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
		return dp[len1][len2];
	}

}
