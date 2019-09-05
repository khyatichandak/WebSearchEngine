package Controller;

import Controller.QuickSelect;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	//public static TST<Integer> st;
//	public static int[][] arr;
//	public static int[][] table;
//	public static File[] myfile;
	/*
	 * public static void createTST() { st = new TST<Integer>(); myfile = new File(
	 * "C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").
	 * listFiles(); int i=1; for (File f : myfile) { if (f.isFile()) { In file = new
	 * In(f); String text = file.readAll();
	 * 
	 * text=text.replaceAll("\\W"," ");
	 * 
	 * String domain = "\\w+"; Pattern r = Pattern.compile(domain); Matcher a =
	 * r.matcher(text);
	 * 
	 * while (a.find( )) { String token = a.group(0).toLowerCase(); if
	 * (st.get(token) == null) { st.put(token, i); i++; } } } } table=new
	 * int[myfile.length+1][st.size()+1]; int pagenumber=1; for (File f : myfile) {
	 * HashMap<String,Integer> freq = new HashMap<String,Integer>( );
	 * 
	 * arr=new int[myfile.length+1][st.size()+1]; for(int
	 * c1=0;c1<myfile.length+1;c1++) { arr[c1][0]=c1; } for(int
	 * r1=0;r1<st.size()+1;r1++) { arr[0][r1]=r1+1; } for(int
	 * c1=0;c1<myfile.length+1;c1++) { table[c1][0]=c1; } for(int
	 * r1=0;r1<st.size()+1;r1++) { table[0][r1]=r1+1; } if (f.isFile()) { In file =
	 * new In(f);
	 * 
	 * String text = file.readAll(); text=text.replaceAll("\\W"," "); String domain
	 * = "\\w+"; Pattern r = Pattern.compile(domain); Matcher a = r.matcher(text);
	 * 
	 * while (a.find( )) { String token = a.group(0).toLowerCase(); Integer count =
	 * freq.get(token.toLowerCase()); // get the previous count for this word if
	 * (count == null) count = 0; // if not in map, previous count is zero
	 * 
	 * freq.put(token, 1 + count); } // System.out.println(freq); for(int
	 * rows=1;rows<=st.size();rows++) { // StdOut.println("Hi");
	 * arr[pagenumber][rows]=0; for (Entry<String, Integer> ent : freq.entrySet( ))
	 * { String key=ent.getKey(); int index=st.get(key);
	 * arr[pagenumber][index]=ent.getValue(); }
	 * table[pagenumber][rows]=arr[pagenumber][rows]; } } pagenumber++; }
	 * 
	 * 
	 * 
	 * StdOut.println(st.size()); for (String key : st.keys()) StdOut.println(key +
	 * " " + st.get(key));
	 * 
	 * 
	 * for(int i1=0;i1< arr.length;i++) { System.out.println(" "); for(int
	 * j1=0;j1<arr.length;j1++) System.out.print(arr[1][j1]); }
	 * 
	 * 
	 * }
	 */

	public static ArrayList<File> selecttoptenpages(String[] searched) {
		// File[] myfile = new File("C:\\Users\\general\\Desktop\\ACC
		// T1\\Test").listFiles();
		File[] myfile = new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
		Integer[] list = new Integer[myfile.length];
		for (int n = 0; n < myfile.length; n++) {
			list[n] = 0;
		}
		for (int s = 0; s < searched.length; s++) {

			if (CreateTST.st.get(searched[s]) == null) {
				System.out.print(" Not present");
			} else {
				int indextotable = CreateTST.st.get(searched[s]);

				// System.out.println(indextotable);
				for (int n = 0; n < myfile.length; n++) {
					list[n] = CreateTST.table[n + 1][indextotable];
					// System.out.println(table[n+1][indextotable]);
				}
			}
		}

		ArrayList<Integer> oldlist = new ArrayList<Integer>(Arrays.asList(list));

		int i = list.length - 10;
		
		ArrayList<File> files=new ArrayList<File>();
		/*
		 * for (int j = list.length-1; j >= i; j--) { rank = QuickSelect.select(list,
		 * j); rankArray.add(rank); }
		 * 
		 * int o = oldlist.indexOf(rankArray.get(x));
		 * oldlist.set(oldlist.indexOf(rankArray.get(x)), null); return myfile[o];
		 */
			
			for (int j =list.length-1;j> i; j--) { 
				 int rank=QuickSelect.select(list,j); 
				 files.add(myfile[oldlist.indexOf(rank)]);

				 oldlist.set(oldlist.indexOf(rank), null);
				 //return myfile[o];
				} 
				 System.out.println(files);
				 return files;
		

		
	}

	public static void main(String[] args) {

		/*
		 * String sea = args[0]; String[] search={}; StringTokenizer stringt=new
		 * StringTokenizer(sea," -.;, ()\n\t"); int w=0; while(stringt.hasMoreTokens())
		 * { search[w]=stringt.nextToken(); w++; }
		 */
		String[] searched = { "had" };
		/*
		 * createTST(); selecttoptenpages(searched);
		 */

	}
}
