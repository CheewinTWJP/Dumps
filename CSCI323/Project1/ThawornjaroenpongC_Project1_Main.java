import java.io.*; 
import java.util.*;

class ThawornjaroenpongC_Project1_Main {
    public static void main(String args []){

        try{
            Scanner inFile = new Scanner(new FileReader(args[0]));
            BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));
            BufferedWriter deBugFile = new BufferedWriter(new FileWriter(args[2]));
            
            listNode listHead = new listNode("dummy");
            LList list = new LList().constructLL(listHead, inFile, deBugFile);
            
            list.printList(listHead, outFile);
            
            if(list.findMiddleNode(listHead, deBugFile)!= null)
            {
            	listNode middleNode = list.findMiddleNode(listHead, deBugFile);
            	outFile.write("The word in the middle is: " + middleNode.data);
            }
            
            inFile.close();
            outFile.close();
            deBugFile.close();
            
        }catch (IOException e) {
         // TODO Auto-generated catch block 
            e.printStackTrace();
        }
        
        
        
    }

    static class listNode {
     String data;
     listNode next;
     
     	listNode(){}
        listNode(String d)
        {
            data = d;
            next = null;
        }
    }

    static class LList {
    	
    	
    	
        public LList constructLL(listNode listHead, Scanner inFile, BufferedWriter deBugFile)
        {
           try{
               deBugFile.write("In constructLL method");
               deBugFile.write("\n");
               while (inFile.hasNext())
                   {
                       String data = inFile.next();
                       listNode newNode = new listNode(data);
                       listInsert(listHead, newNode, deBugFile);
                       printList(listHead, deBugFile);
                   }
           }catch (IOException e) {
           // TODO Auto-generated catch block 
            e.printStackTrace();
         }
           return this;
        }

        public void listInsert(listNode listHead, listNode newNode, BufferedWriter deBugFile)
        {
            try {
				deBugFile.write("In listInsert method");
				deBugFile.write("\n");
				listNode spot = findSpot(listHead, newNode);
				deBugFile.write("Returns from findSpot where Spot.data is " + spot.data);
				deBugFile.write("\n");
				deBugFile.write("newNode.data is " + newNode.data);
				deBugFile.write("\n");				
				
				newNode.next = spot.next;
				spot.next = newNode;
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        public listNode findSpot(listNode listHead, listNode newNode)
        {
        	listNode spot = listHead;
        	
        	while(spot.next != null && spot.next.data.toLowerCase().charAt(0)
        			< newNode.data.toLowerCase().charAt(0))
        	{
        		
        		spot = spot.next;
        	}
        	
			return spot;
      
        }
        
        public void printList(listNode listHead, BufferedWriter File)
        {
        	int count = 0;
        	listNode tmp = listHead;
        	
        	try {
        		while(tmp.next != null)
            	{	
    				File.write("(" + tmp.data + ", " + tmp.next.data + ")"
    							+ " -> ");
    				tmp = tmp.next;
    				count++;
    					
    				if(count >= 5)
    	        	{
    	        		File.write("\n");
    	        		count = 0;
    	        	}
            	}
        		
        		File.write("NULL");
        		File.write("\n");
        		
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        public listNode findMiddleNode(listNode listHead, BufferedWriter deBugFile)
        {
        	listNode walker1 = listHead.next;
			listNode walker2 = listHead.next;
        	try {
				deBugFile.write("In findMiddleNode method");
				deBugFile.write("\n");
				
				
				while(walker2 != null && walker2.next != null)
				{
					walker1 = walker1.next;
					walker2 = walker2.next.next;
					
					deBugFile.write("walker1's data is " + walker1.data);
					deBugFile.write("\n");
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        	return walker1;
        }
        
     }
}

