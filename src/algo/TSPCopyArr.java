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

public class TSPCopyArr {
	BufferedReader br;
	FileReader fr;
	int n;
	double[][] dp;
	
	double adjMat[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TSPCopyArr obj=new TSPCopyArr();
		double mat[][]=obj.readFile("tsp_test");
		
		obj.adjMat=new double[obj.n][obj.n];
		
		for(int i=0;i<obj.n;i++) {
			for(int j=0;j<obj.n;j++) {
				if(i!=j)
					obj.adjMat[i][j]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
					obj.adjMat[j][i]=Math.sqrt(Math.pow((mat[i][0]-mat[j][0]), 2)+Math.pow((mat[i][1]-mat[j][1]), 2));
			}
		}
		
		obj.dp=new double[1<<obj.n][obj.n];
		
		for(int i=0;i<1<<obj.n;i++) {
			for(int j=0;j<obj.n;j++) {
				obj.dp[i][j]=-1;
			}
		}

		
		for(int i=0;i<obj.n;i++) {
			obj.dp[1<<i][i]=obj.adjMat[1][i];
		}
		
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
		if(dp[status][x]!=-1) {
			return dp[status][x];			
		}
		int mask=1<<x;
		double tmp[];
		dp[status][x]=Integer.MAX_VALUE;
		for(int i=0;i<n;i++) {
			if(i!=x&&((status&(1<<i))>0)) {
				
				dp[status][x]=Math.min(dp[status][x], algoTSP(status-mask,i)+adjMat[i][x]);
				System.out.println(dp[status][x]);
			}
		}
		return dp[status][x];
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
