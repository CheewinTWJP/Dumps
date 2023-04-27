package Project6;
import java.io.*;
import java.util.Scanner;

public class ThawornjaroenpongC_Project6_Main {
    public static class DijktraSSS {
        public int numNodes = 0;
        public int sourceNode = 0;
        public int minNode = 0;
        public int currentNode = 0;
        public int newCost = 0;
        public int[][] costMatrix;
        public int[] fatherAry;
        public int[] ToDoAry;
        public int[] BestAry;

        public DijktraSSS(int numNodes) {
            this.numNodes = numNodes;
            this.costMatrix = new int [numNodes + 1][numNodes + 1];
            for(int i = 0; i < numNodes + 1; i++)
            {
            	for(int j = 0; j < numNodes + 1; j++)
            	{
            		this.costMatrix[i][j] = 9999;
            	}
            }

            this.ToDoAry = new int [numNodes + 1];
            for(int i = 0; i < numNodes + 1; i++)
            {
                this.ToDoAry[i] = 9999;
            }

            this.BestAry = new int[numNodes + 1];
            for(int i = 0; i < numNodes + 1; i++)
            {
                this.BestAry[i] = 9999;
            }

            this.fatherAry = new int[numNodes + 1];
            for(int i = 0; i < numNodes + 1; i++)
            {
                this.fatherAry[i] = 9999;
            }
        }

        public void loadCostMatrix(Scanner inFile, BufferedWriter deBugFile) throws IOException {
            inFile.nextLine();
            while(inFile.hasNextInt())
            {
            	int row = inFile.nextInt();
            	int col = inFile.nextInt();
            	int cost = inFile.nextInt();
            	this.costMatrix[row][col] = cost;
            }
           
            try {
                this.debugPrintCostMatrix(deBugFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void setBestAry(int sourceNode , BufferedWriter deBugFile) {
        	this.sourceNode = sourceNode;
        	for (int i = 1; i < numNodes + 1; i++)
        	{
        		this.BestAry[i] = this.costMatrix[this.sourceNode][i];
        	}
        	this.deBugPrintBestAry(deBugFile);
        }

        public void setFatherAry(int sourceNode, BufferedWriter deBugFile) {
        	this.sourceNode = sourceNode;
        	for (int i = 1; i < numNodes + 1; i++)
        	{
        		this.fatherAry[i] = this.sourceNode;
        	}
        	this.deBugPrintFatherAry(deBugFile);
        }

        public void setToDoAry(int sourceNode, BufferedWriter deBugFile) {
        	this.sourceNode = sourceNode;
        	for (int i = 1; i < numNodes + 1; i++)
        	{
        		if(i == this.sourceNode)
        		{
        			this.ToDoAry[i] = 0;
        		}
        		else if(this.ToDoAry[i] == 0)
        		{
        			this.ToDoAry[i] = 0;
        		}
        		else
        		{
        			this.ToDoAry[i] = 1;
        		}
        	}
        	this.deBugPrintToDoAry(deBugFile);

        }

        public int findMinNode() {
        	int minCost = 9999;
        	int minNode = 0;
        	int index = 1;
        	
        	while(index <= this.numNodes)
        	{
        		if(this.ToDoAry[index] == 1 && this.BestAry[index] < minCost)
            	{
            		minCost = this.BestAry[index];
            		minNode = index;
            	}
            	index++;
        	}
        	
            return minNode;
        }

        public int computeCost(int minNode, int Node)
        {
            return this.BestAry[minNode] + this.costMatrix[minNode][Node];
        }

        public boolean checkToDoAry()
        {
        	for(int i = 1; i < numNodes + 1; i++)
            {
				if(this.ToDoAry[i] == 1)
					return false;
            }

            return true;
        }

        public void debugPrintCostMatrix(BufferedWriter deBugFile) throws IOException {
            try {
                deBugFile.write("***********2D costMatrix************ \n");
                for(int i = 1; i < this.numNodes + 1; i++)
                {
                    for (int j = 1; j < this.numNodes + 1; j++)
                    {
                        deBugFile.write(this.costMatrix[i][j] + " ");
                    }
                    deBugFile.write("\n");
                }
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        
        public void deBugPrintBestAry(BufferedWriter deBugFile)
        {
        	try {
				deBugFile.write("************ Best Array ********** \n");
				for(int i = 1; i < numNodes + 1; i++)
	            {
					deBugFile.write(this.BestAry[i] + " ");
	            }
				deBugFile.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        public void deBugPrintToDoAry(BufferedWriter deBugFile)
        {
        	try {
				deBugFile.write("************ ToDo Array ********** \n");
				for(int i = 1; i < numNodes + 1; i++)
	            {
					deBugFile.write(this.ToDoAry[i] + " ");
	            }
				deBugFile.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        public void deBugPrintFatherAry(BufferedWriter deBugFile)
        {
        	try {
				deBugFile.write("************ Father Array ********** \n");
				for(int i = 1; i < numNodes + 1; i++)
	            {
					deBugFile.write(this.fatherAry[i] + " ");
	            }
				deBugFile.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        public void printShortestPath(int currentNode, int sourceNode, BufferedWriter SSSfile)
        {
        	this.sourceNode = sourceNode;
        	this.currentNode = currentNode;
        	int totalCost = 0;
        	try {
				
				SSSfile.write("The path from " + this.sourceNode + " to " + this.currentNode + " : " + this.currentNode);
				while(this.fatherAry[currentNode] != sourceNode)
				{
					SSSfile.write(" <- " + this.fatherAry[currentNode]);
					currentNode = this.fatherAry[currentNode];
				}
				SSSfile.write(" <- " + this.sourceNode +  " : cost = " + this.BestAry[this.currentNode] +  "\n");
				
				
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }


    }
    public static void main(String[] args) throws IOException {


        try {
            Scanner inFile = new Scanner(new FileReader(args[0]));
            BufferedWriter SSSfile = new BufferedWriter(new FileWriter(args[1]));
            BufferedWriter deBugFile = new BufferedWriter(new FileWriter(args[2]));

            int numNodes = 0;
            numNodes = inFile.nextInt();
            
            
            DijktraSSS DSSS = new DijktraSSS(numNodes);
            DSSS.debugPrintCostMatrix(deBugFile);
            DSSS.loadCostMatrix(inFile, deBugFile);
            
            int sourceNode = 1;
            while(sourceNode <= DSSS.numNodes)
            {
            	DSSS.setBestAry(sourceNode, deBugFile);
                DSSS.setFatherAry(sourceNode, deBugFile);
                DSSS.setToDoAry(sourceNode, deBugFile);
                
                while(DSSS.checkToDoAry() == false)
                {
                	DSSS.minNode = DSSS.findMinNode();
                    DSSS.ToDoAry[DSSS.minNode] = 0;
                    DSSS.deBugPrintToDoAry(deBugFile);
                    
                    int childNode = 1;
                    while (childNode <= DSSS.numNodes)
                    {
                    	if(DSSS.ToDoAry[childNode] == 1)
                        {
                        	 int newCost = DSSS.computeCost(DSSS.minNode, childNode);
                        	 if(newCost < DSSS.BestAry[childNode])
                        	 {
                        		 DSSS.BestAry[childNode] = newCost;
                        		 DSSS.fatherAry[childNode] = DSSS.minNode;
                        		 DSSS.deBugPrintBestAry(deBugFile);
                        		 DSSS.deBugPrintFatherAry(deBugFile);
                        	 }
                        }
                        
                        childNode++;
                    }
                }
                
                int currentNode = 1;
                while(currentNode <= DSSS.numNodes)
                {
                	SSSfile.write("Source node = " + sourceNode + "\n");
                    DSSS.printShortestPath(currentNode, sourceNode, SSSfile);
                    currentNode++;
                }
                SSSfile.write("\n");
                sourceNode++;
            }
            
            
            inFile.close();
		    SSSfile.close();
		    deBugFile.close();

          } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
