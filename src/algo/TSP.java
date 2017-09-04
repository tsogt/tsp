package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TSP {
	BufferedReader br;
	FileReader fr;
	int n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TSP obj=new TSP();
		double mat[][]=obj.readFile("tsp");
		
		double adjMat[][]=new double[obj.n][obj.n];
		
		for(int i=0;i<obj.n;i++) {
			for(int j=0;j<obj.n;j++) {
				if(i!=j)
					adjMat[i][j]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
					adjMat[j][i]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
			}
		}
		
/*		for(int i=0;i<obj.n;i++) {
			for(int j=0;j<obj.n;j++) {
				System.out.print(adjMat[i][j]+"	");
			}
			System.out.println();
		}
*/		
//		System.out.println(obj.algoTSP(adjMat));
		
/*		System.out.println(obj.n);
		double m=Math.pow(2,25);
		BigDecimal bg=new BigDecimal(m);
		System.out.println(bg);
		System.out.println(Integer.MAX_VALUE);
		
		int S[]=new int[obj.n];
		for(int i=0;i<obj.n;i++) {
			S[i]=i+1;
		}*/
//		ArrayList<Integer> set=new ArrayList<Integer>(Arrays.asList(S));
//		ArrayList<ArrayList<Integer>> retSet=obj.powerSet(set);
		
//		obj.subset(S, S.length);
		int tmp[]= {1,2,3,4,5,6};
//		ArrayList<Integer> set=new ArrayList<Integer>(Arrays.asList(tmp));
//		ArrayList<ArrayList<Integer>> retSet=obj.powerSet(set);
		
		ArrayList<ArrayList<Integer>> tmpList=obj.subset(tmp, tmp.length);
		
		for(ArrayList<Integer> l:tmpList) {
			for(int i=0;i<l.size();i++) {
				System.out.print(l.get(i)+",");
			}
			System.out.println();
		}
/*		Map<ArrayList<Integer>,Integer> hm=new HashMap<ArrayList<Integer>,Integer>();
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		tmp.add(1);
		tmp.add(2);
		tmp.add(3);
		hm.put(tmp, 247);
		ArrayList<Integer> tmp1=new ArrayList<Integer>();
		tmp1.add(1);
		tmp1.add(3);
		tmp1.add(2);
		System.out.println(hm.get(tmp1));*/
	
		
		
	}
	public double algoTSP(double adjMat[][]) {

		int S[]=new int[n];
		for(int i=0;i<n;i++) {
			S[i]=i+1;
		}
		Map<ArrayList<Integer>,Integer> hm=new HashMap<ArrayList<Integer>,Integer>();
		
		ArrayList<ArrayList<Integer>> sets=subset(S,S.length);
		
		System.out.println("subset:"+sets.size());
		int z=0;
		for(ArrayList<Integer> l:sets) {
			hm.put(l, z++);
		}
		System.out.println("hashmap");
/*		for(Map.Entry<ArrayList<Integer>,Integer> l:hm.entrySet()) {
			for(Integer s:l.getKey()) {
				System.out.print(s+",");
			}
			System.out.println(l.getValue());
		}*/
		double A[][]=new double[sets.size()][n];
		System.out.println("A");
		for(Map.Entry<ArrayList<Integer>, Integer> l:hm.entrySet()) {
			if(l.getKey().size()==1)
				A[l.getValue()][0]=0;
			else
				A[l.getValue()][0]=Integer.MAX_VALUE;
		}
		System.out.println("base case");
		
		for(int m=2;m<=n;m++) {
			for(ArrayList<Integer> s:sets) {
				if(s.size()==m) {
					for(int j:s) {
						if(j!=1) {
							double min=Integer.MAX_VALUE;
							ArrayList<Integer> tmpList=new ArrayList<Integer>();
//							tmpList.clear();
							for(int r:s) {
								if(r!=j)
									tmpList.add(r);
							}
							for(int k:s) {
								if(k!=j&&min>A[hm.get(tmpList)][k-1]+adjMat[k-1][j-1]) {
//									System.out.println("babe");
									min=A[hm.get(tmpList)][k-1]+adjMat[k-1][j-1];
								}
							}
//							System.out.println(min);
							A[hm.get(s)][j-1]=min;									
						}
					}
				}
			}
		}
		double min=Integer.MAX_VALUE;
//		System.out.println(min);
		for(int j=1;j<n;j++) {
			
			if(min>A[hm.get(sets.get(sets.size()-1))][j]+adjMat[j][0])
				min=A[hm.get(sets.get(sets.size()-1))][j]+adjMat[j][0];
//			System.out.println(A[hm.get(sets.get(sets.size()-1))][j]);
		}
		
		return min;
	}
	public ArrayList<ArrayList<Integer>> powerSet(ArrayList<Integer> originalSet) {
		ArrayList<ArrayList<Integer>> sets = new ArrayList<ArrayList<Integer>>();
	    if (originalSet.isEmpty()) {
	        sets.add(new ArrayList<Integer>());
	        return sets;
	    }
	    List<Integer> list = new ArrayList<Integer>(originalSet);
	    Integer head = list.get(0);
	    ArrayList<Integer> rest = new ArrayList<Integer>(list.subList(1, list.size())); 
	    for (ArrayList<Integer> set : powerSet(rest)) {
	    	ArrayList<Integer> newSet = new ArrayList<Integer>();
	        newSet.add(head);
	        newSet.addAll(set);
	        	       
	        
	        sets.add(newSet);
	        sets.add(set);
	        
	        
	    }       
	    return sets;		
	}
	public ArrayList<ArrayList<Integer>> subset(int S[], int n) {

		ArrayList<ArrayList<Integer>> subset=new ArrayList<ArrayList<Integer>>();
		
		
		for (int i = 0; i < (1<<n); i++){
			
			ArrayList<Integer> tmp=new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					tmp.add(S[j]);
				}
            }
//            if(tmp.size()==m&&)
            if(!tmp.isEmpty()&&tmp.get(0)==1)
            	subset.add(tmp);
            if(i%1000000==0) {
            	System.out.println(i);
            	System.gc();
            }            	            	
//            System.out.println(i);
        }
		return subset;
	}
	public double[][] readFile(String filename) {
		try {
			fr = new FileReader("C:\\Users\\tsogtbayarn\\Documents\\Workspace\\java\\" + filename + ".txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {		
			String i;
			
			List<String> fileList = new ArrayList<String>();
			
			while(true) {				
				i = br.readLine();
				if (i == null)
					break;
			
				fileList.add(i);
				

			}
			br.close();
			fr.close();
		
			n=Integer.parseInt(fileList.get(0));
			double mat[][]=new double[n][2];
			for(int j=0;j<n;j++) {
				mat[j][0]=Double.parseDouble(fileList.get(j+1).split(" ")[0]);
				mat[j][1]=Double.parseDouble(fileList.get(j+1).split(" ")[1]);
			}
		
		
			return mat;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}		
}
