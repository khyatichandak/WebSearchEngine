package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTST {
public static TST<Integer> st;	
public static HashMap<Integer,ArrayList<Integer>> index;
		public static int[][] arr;
		public static int[][] table;
		public static File[] myfile;
		public static void createTST() {
			st = new TST<Integer>();
			myfile = new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
			int i=1;
			for (File f : myfile) {
				if (f.isFile()) {
					In file = new In(f);
					String text = file.readAll();
					
					text=text.replaceAll("\\W"," ");
					
					String domain = "\\w+";
					Pattern r = Pattern.compile(domain);
					Matcher a = r.matcher(text);
					
					while (a.find( )) {
						String token = a.group(0).toLowerCase();
						if (st.get(token) == null) {
							st.put(token, i);
							i++;
						}
					}
				}
			}
			table=new int[myfile.length+1][st.size()+1];			
			int pagenumber=1;
			for (File f : myfile) {
				HashMap<String,Integer> freq = new HashMap<String,Integer>( );
				
				arr=new int[myfile.length+1][st.size()+1];
				for(int c1=0;c1<myfile.length+1;c1++) {
					arr[c1][0]=c1;
				}
				for(int r1=0;r1<st.size()+1;r1++) {
					arr[0][r1]=r1+1;
				}
				for(int c1=0;c1<myfile.length+1;c1++) {
					table[c1][0]=c1;
				}
				for(int r1=0;r1<st.size()+1;r1++) {
					table[0][r1]=r1+1;
				}
				if (f.isFile()) {
					In file = new In(f);
					
					String text = file.readAll();
					text=text.replaceAll("\\W"," ");
					String domain = "\\w+";
					Pattern r = Pattern.compile(domain);
					Matcher a = r.matcher(text);
					
					while (a.find( )) {
						String token = a.group(0).toLowerCase();
						Integer count = freq.get(token.toLowerCase()); // get the previous count for this word
						if (count == null)
							count = 0; // if not in map, previous count is zero
						
						freq.put(token, 1 + count);
					}
				//	System.out.println(freq);
					for(int rows=1;rows<=st.size();rows++) {
					//	StdOut.println("Hi");
						arr[pagenumber][rows]=0;
						for (Entry<String, Integer> ent : freq.entrySet( )) {
							String key=ent.getKey();
							int index=st.get(key);
							arr[pagenumber][index]=ent.getValue();
						}
					table[pagenumber][rows]=arr[pagenumber][rows];	
					}
				}
				pagenumber++;
			}
		
	}
		/*File[] myfile=new File("C:\\Users\\MEERA\\Documents\\MAC\\ACC\\workspace\\WebSearchEngine\\TEXT").listFiles();
		st = new TST<Integer>();
		index = new HashMap<Integer, ArrayList<Integer>>();
		int i=0; 
		 FileReader fr;
		 BufferedReader br;
		for(File f:myfile) {
			 if(f.isFile()) { 
				 fr = new FileReader(f);
			    	br = new BufferedReader(fr);
			    	
				
				  In file=new In(f); 
				  String text=file.readAll();
				 
				
				 StringTokenizer s=new StringTokenizer(text.toString()," -.;, ()\n\t");
			
				 while(s.hasMoreTokens()) {
					ArrayList<Integer> list=new ArrayList<Integer>(); 
					String token=s.nextToken();
					int c=0;
					
					for(File allfiles: myfile) { 
						if(allfiles.isFile()) { 
							
							  In allfile=new In(allfiles); 
							  String txt=allfile.readAll();
							 
							 
							 list.add(c,TST.findoccurrences(token, txt.toString()));						
							 c++;
						 }
					}
					//System.out.println(token+" "+st.get(token));
					if(st.get(token)==null) {
						st.put(token, i);
						index.put(i,list);	
					}
					i++;	
				
				 }
			}
		 }
		
		 for (Integer name: index.keySet()){ 
			  System.out.print(" \n" +name + " Val: " );
			  for(int j=0;j<myfile.length;j++) {
				  System.out.print(" "+index.get(name).get(j));
			  }
		 }
		 for (String key : st.keys()) {
				StdOut.println("TST:::"+key + " " + st.get(key));
			}
		 
		
	}*/
		
	
}
