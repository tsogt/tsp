package algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSPCopy {
	BufferedReader br;
	FileReader fr;
	int n;
	int z=0;
	int z1=0;
	Map<Integer,Integer> hm=new HashMap<Integer,Integer>();
	Map<Integer,double[]> dp;
	
	double adjMat[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TSPCopy obj=new TSPCopy();
		double mat[][]=obj.readFile("tsp");
		
		obj.adjMat=new double[obj.n][obj.n];
		
		for(int i=0;i<obj.n;i++) {
			for(int j=0;j<obj.n;j++) {
				if(i!=j)
					obj.adjMat[i][j]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
					obj.adjMat[j][i]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
			}
		}
		
		obj.dp=new HashMap<Integer,double[]>();
/*		double tmp[];
		for(int i=0;i<1<<obj.n;i++) {
			tmp=new double[obj.n];
			for(int j=0;j<obj.n;j++) {
				tmp[j]=-1;
			}
			obj.dp.put(i, tmp);
		}*/

		
/*		for(int i=0;i<obj.n;i++) {
			obj.dp.get(1<<i)[i]=obj.adjMat[1][i];
		}*/
		double tmp[]=new double[obj.n];
		Arrays.fill(tmp, -1);
		obj.dp.put((1<<obj.n)-1, tmp);
		obj.dp.get((1<<obj.n)-1)[obj.n-1]=obj.adjMat[1][obj.n-1];
		System.out.println(obj.algoTSP((1<<obj.n)-1, 1));
		
		
/*		Map<Integer,int[]> test=new HashMap<Integer,int[]>();
		int test1[]=new int[3];
		test1[0]=12;
		test.put(1, test1);
		System.out.println(test.get(1)[0]+","+test.get(1)[1]+","+test.get(1)[2]);
		test.get(1)[1]=2;
		test.get(1)[2]=122;
		System.out.println(test.get(1)[0]+","+test.get(1)[1]+","+test.get(1)[2]);*/
		
	}
	public double algoTSP(int status, int x) {
		if(dp.get(status)[x]!=-1) {
			
			return dp.get(status)[x];			
			
		}
		
		int mask=1<<x;
		double tmp[];
		dp.get(status)[x]=Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			if(i!=x&&((status&(1<<i))>0)) {				
				if(!dp.containsKey(status-mask)) {
					tmp=new double[n];
					Arrays.fill(tmp, -1);
					dp.put(status-mask, tmp);
/*					if(!hm.containsKey(status-mask)) {
						hm.put(status-mask, 1);
						z++;
						System.out.println(z);
					}*/
					
//					System.out.println(x);
				}
				if(dp.containsKey(1<<i)) {
					dp.get(1<<i)[i]=adjMat[1][i];
//					System.out.println(i);
/*					if(z%(1<<8)==0) {
						System.out.println(z);
					}*/
					
					if(!hm.containsKey(1<<i)) {
						hm.put(1<<i, 1);
						z++;
						System.out.println(z);
					}
					
				}
				
//				dp.remove(status-mask-2);
				dp.get(status)[x]=Math.min(dp.get(status)[x], algoTSP(status-mask,i)+adjMat[i][x]);
				dp.remove(status-mask);
				
/*				if(i==n-1) {
					System.out.println(x);	
				}*/
//				System.out.println(dp.get(status)[x]);
//				System.out.println(dp.size());
			}
/*			if(z%(1<<n)==0) {
				z1++;
				System.out.println(z1);
			}*/			

		}


			
//		System.out.println(dp.size());
		
		return dp.get(status)[x];
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
