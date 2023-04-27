import java.io.*; 
import java.util.*;
public class ThawornjaroenpongC_Project3_Main {

	public static void main(String[] args) {
		try {
			Scanner inFile = new Scanner(new FileReader(args[0]));
			BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));
		    BufferedWriter deBugFile = new BufferedWriter(new FileWriter(args[2]));
	
		    HNode listHead = new HNode("dummy", 0, "", 0, 0, null, null, null);
		    Huffman HM = new Huffman();
		    HM.constructHuffmanLList(inFile, deBugFile, listHead);
		    HM.printList(listHead, outFile);
		    
		    HNode Root = HM.constructHuffmanBinTree(listHead, deBugFile);
		    outFile.write("printing the entropy Table \n");
		    double totalEntropy = 0;
		    HM.printEntropyTable(Root, "", outFile, totalEntropy);
		    outFile.write("The Huffman Coding scheme's entropy = " + HM.totalEntropy + "\n");
		    outFile.write("*******PreOrder Traversal*********** \n");
		    HM.preOrder(Root, outFile);
		    outFile.write("*******InOrder Traversal*********** \n");
		    HM.inOrder(Root, outFile);
		    outFile.write("*******PostOrder Traversal*********** \n");
		    HM.postOrder(Root, outFile);
		    
		    inFile.close();
            outFile.close();
            deBugFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	static class HNode
	{
		private String chStr;
		private int prob;
		private String code;
		private int numBits;// string length of code
		private double entropy;// numBits * prob
		private HNode left;
		private HNode right;
		private HNode next;
		
		HNode(String ch, int prob, String code,int bits, double entropy, HNode left, HNode right, HNode next)
		{
			this.chStr = ch;
			this.prob = prob;
			this.code = code;
			this.numBits = bits;
			this.entropy = entropy;
			this.left = left;
			this.right = right;
			this.next = next;
		}
		
		public void printNode(HNode T, BufferedWriter outFile)
		{
			try {
				
					outFile.write("T's chStr is " + T.chStr + " T's prob is " + T.prob +
							" T's code is " + T.code + " T's numBits is " + T.numBits + "\n" +
							" T's entropy is " + T.entropy);
					if(T.next == null)
					{
						outFile.write(" next's chStr is null " );
					}
					else
					{
						outFile.write(" next's chStr is " + T.next.chStr );
					}
					if(T.left == null)
					{
						outFile.write(" left's chStr is null ");
					}
					else
					{
						outFile.write(" left's chStr is " + T.left.chStr);
					}
					if(T.right == null)
					{
						outFile.write(" right's chStr is null ");
					}
					else
					{
						outFile.write(" right's chStr is " + T.right.chStr);
					}
							
				outFile.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static class Huffman
	{
		private HNode listHead;
		private HNode Root;
		private int totalEntropy;
		
		Huffman()
		{
			this.listHead = null;
			this.Root = null;
			this.totalEntropy = 0;
		}
		public HNode findSpot(HNode listHead, HNode newNode)
		{
			HNode spot = listHead;
			
			while(spot.next != null && spot.next.prob < newNode.prob)
			{
				spot = spot.next;
			}
			
			return spot;
		}
		
		public void listInsert(HNode listHead, HNode newNode, BufferedWriter deBugFile)
		{
			try {
				deBugFile.write("In listInsert method");
				deBugFile.write("\n");
				HNode spot = this.findSpot(listHead, newNode);
				deBugFile.write("Returns from findSpot where Spot.data is " + spot.chStr);
				deBugFile.write("\n");
				deBugFile.write("newNode.data is " + newNode.chStr);
				deBugFile.write("\n");	
				
				newNode.next = spot.next;
				spot.next = newNode;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void constructHuffmanLList(Scanner inFile, BufferedWriter deBugFile, HNode listHead)
		{
			try {
				deBugFile.write("Entering constructHuffmanLList method \n");
				String chr = "";
				int prob = 0;
				while(inFile.hasNext())
				{
					chr = String.valueOf(inFile.next().charAt(0)) ;
			    	prob = Integer.parseInt(inFile.next());
					HNode newNode = new HNode(chr, prob, "", 0, 0.0, null, null, null);
					
					this.listInsert(listHead, newNode, deBugFile);
					
					this.printList(listHead, deBugFile);
				}
				
				deBugFile.write("Exit constructHuffmanLList method");
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public HNode constructHuffmanBinTree(HNode listHead, BufferedWriter deBugFile)
		{
			HNode tmp = listHead;
				try {
					deBugFile.write("Entering constructHuffmanBinTree method \n");
					
					while(tmp.next.next != null)
					{
						HNode newNode = new HNode("", 0, "", 0, 0, null, null, null);
						newNode.prob = tmp.next.prob + tmp.next.next.prob;
						newNode.chStr = tmp.next.chStr + tmp.next.next.chStr;
						newNode.left = tmp.next;
						newNode.right = tmp.next.next;
						newNode.next = null;
						this.listInsert(listHead, newNode, deBugFile);
						tmp.next = tmp.next.next.next;
						this.printList(this.listHead, deBugFile);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return tmp.next;
		}
		
		public void printEntropyTable(HNode T, String code, BufferedWriter outFile, double totalEntropy)
		{
			
			try {
					
					if(T.left == null && T.right == null)
					{
						T.code = code;
						T.numBits = code.length();
						T.entropy = T.prob * T.numBits;
						this.totalEntropy += T.entropy;
						outFile.write("output T.chStr " + T.chStr + " T.prob " + T.prob + " T.code " +
								T.code + " T.numBits " + T.numBits + " T.entropy" + T.entropy + "\n");
						
					}
					else
					{
						printEntropyTable(T.left, code + "0", outFile, totalEntropy+=T.entropy);
						printEntropyTable(T.right, code + "1", outFile, totalEntropy+=T.entropy);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
						
		}
		
		public void preOrder(HNode T, BufferedWriter outFile)
		{
			if(this.isLeaf(T))
			{
				T.printNode(T, outFile);
			}
			else
			{
				T.printNode(T, outFile);
				preOrder(T.left, outFile);
				preOrder(T.right, outFile);
			}
		}
		
		public void inOrder(HNode T, BufferedWriter outFile)
		{
			if(this.isLeaf(T))
			{
				T.printNode(T, outFile);
			}
			else
			{
				inOrder(T.left, outFile);
				T.printNode(T, outFile);
				inOrder(T.right, outFile);
			}
		}
		
		public void postOrder(HNode T, BufferedWriter outFile)
		{
			if(this.isLeaf(T))
			{
				T.printNode(T, outFile);
			}
			else
			{
				postOrder(T.left, outFile);
				postOrder(T.right, outFile);
				T.printNode(T, outFile);
			}
		}
		
		public boolean isLeaf(HNode T)
		{
			if(T.left == null && T.right == null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public void printList(HNode listHead, BufferedWriter File) {
			try {
				int count = 0;
				HNode tmp = listHead;
				File.write("listHead" + "->" );
				while(tmp != null)
				{
					File.write("(");
					tmp.printNode(tmp, File);
					File.write(") -> " + "\n");
					tmp = tmp.next;
					count++;
					if(count >= 3)
					{
						File.write("\n");
					}
				}
				File.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
