
#include <iostream>
#include <fstream>
#include <cstdlib>

using namespace std;

class node {
public:
    int ID = 9999;
    node* next = NULL;

public:
    node()
    {
        this->next = NULL;
    }

    node(int id) {
        this->ID = id;
        this->next = NULL;
    }
};

class coloring {
public:
    int numNodes = 0;
    int numUncolor = 0;
    node** hashTable = NULL;
    int* colorARY = 0;

public:

    coloring(ifstream &inFile)
    {
        if (inFile.is_open())
        {
            inFile >> this->numNodes;
        }

        this->numUncolor = this->numNodes;

        int hashTableSize = this->numNodes + 1;
        this->hashTable = new node* [hashTableSize];
        for (int i = 0; i < hashTableSize; i++)
        {
            hashTable[i] = NULL;
        }

        int colorArySize = hashTableSize;
        this->colorARY = new int[colorArySize];
        for (int i = 0; i < colorArySize; i++)
        {
            this->colorARY[i] = 0;
        }

    }

    void loadGraph(ifstream &inFile)
    {
        inFile.ignore(1, '\n');
        int i = 0;
        int j = 0;
        
        while (inFile >> i && inFile >> j)
        {
            this->hashInsert(i, j);
            this->hashInsert(j, i);
        }
        
    }

    void hashInsert(int id1, int id2)
    {
        node* newNode = new node(id2);
        if (this->hashTable[id1] == NULL)
        {
            this->hashTable[id1] = newNode;
        }
        else
        {
            newNode->next = this->hashTable[id1];
            this->hashTable[id1] = newNode;
            
        }
    }

    void printHashTable(ofstream &File)
    {
        if (File.is_open())
        {
            File << "********Printing HashTable*******" << endl;
            for (int i = 1; i <= this->numNodes; i++)
            {
                node* temp = this->hashTable[i];
                File << "hashTable [" << i << "]";
                while (temp != NULL)
                {
                    File << "->" << temp->ID;
                    temp = temp->next;
                }
                File << endl;
            }
        }
        
    }

    void method1(ofstream &outFile1, ofstream &deBugFile)
    {
        if (deBugFile.is_open())
        {
            deBugFile << "*******entering method1***********" << endl;
            int newColor = 64;
           
            while (this->numUncolor > 0)
            {
                newColor++;
                
                int nodeID = 1;
                while (nodeID <= this->numNodes)
                {
                    if (this->colorARY[nodeID] == 0)
                    {
                        if (this->checkNeighbors(nodeID, newColor) == true)
                        {
                            this->colorARY[nodeID] = newColor;
                            this->numUncolor--;
                            this->printAry(deBugFile);
                        }
                    }
                    nodeID++;
                }
                this->printAry(deBugFile);
            }
            if (outFile1.is_open())
            {
                this->printAry(outFile1);
            }
            deBugFile << "*********leaving Method1*************" << endl;
        }
       
    }

    void method2(ofstream &outFile1, ofstream &deBugFile)
    {
        if (deBugFile.is_open())
        {
            deBugFile << "************Entering Method2******************" << endl;
            int lastUsedColor = 64;
            int nextNodeID = 0;

            while (nextNodeID < this->numNodes)
            {
                nextNodeID++;
                int nextUsedColor = 1 + 64;
                bool coloredFlag = false;
                while (coloredFlag == false && nextUsedColor <= lastUsedColor)
                {
                    if (lastUsedColor > 64 && this->checkNeighbors(nextNodeID, nextUsedColor) == true)
                    {
                        this->colorARY[nextNodeID] = nextUsedColor;
                        coloredFlag = true;
                    }
                    else
                    {
                        nextUsedColor++;
                    }
                }
                if (coloredFlag == false)
                {
                    lastUsedColor++;
                    this->colorARY[nextNodeID] = lastUsedColor;
                    deBugFile << "lastUsedColor: " << lastUsedColor << endl;
                }
                this->printAry(deBugFile);
            }
            
            if (outFile1.is_open())
            {
                this->printAry(outFile1);
            }

            deBugFile << "**********Leaving Method2************" << endl;
            
        }
    }

    bool checkNeighbors(int nodeID, int color)
    {
        node* nextNode = this->hashTable[nodeID];
        
        while (nextNode != NULL)
        {
            if (nextNode == NULL)
            {
                return true;
            }
            if (this->colorARY[nextNode->ID] == color)
            {
                return false;
            }
            nextNode = nextNode->next;
        }
        
        return true;
    }

    void printAry(ofstream &File)
    {
        char color[10] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int colorNum;
        if (File.is_open())
        {
            File << "*************Printing colorAry************" << endl;
            File << "ColorAry : ";
            int* temp = this->colorARY;
            for (int i = 1; i <= this->numNodes; i++)
            {
                colorNum = temp[i];
                File << color[colorNum - 65] << " ";
                if (colorNum < 65)
                {
                    File << "0" << " ";
                }
            }
            File << endl;
        }
        
    }
};
int main(int argc, char** argv)
{
    ifstream inFile(argv[1]);
    int whichMethod = atoi(argv[2]);
    ofstream outFile(argv[3]);
    ofstream deBugFile(argv[4]);

    coloring CG(inFile);
    CG.loadGraph(inFile);
    CG.printHashTable(deBugFile);
    
    
    switch (whichMethod)
    {
    case 0:
        deBugFile << " ERROR " << endl;
        outFile << " ERROR " << endl;
        break;

    case 1:
        CG.method1(outFile, deBugFile);
        break;

    case 2:
        CG.method2(outFile, deBugFile);
    }


    inFile.close();
    outFile.close();
    deBugFile.close();

}

