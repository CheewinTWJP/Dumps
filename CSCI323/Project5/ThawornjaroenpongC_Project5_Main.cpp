
#include <iostream>
#include <fstream>

using namespace std;

class edge
{
public:
    int nU;
    int nW;
    int cost;
    edge* next;

    edge()
    {
        this->nU = 0;
        this->nW = 0;
        this->cost = 0;
        this->next = NULL;
    }

    edge(int n1, int n2, int cost)
    {
        this->nU = n1;
        this->nW = n2;
        this->cost = cost;
        this->next = NULL;
    }

public:
    void printEdge(ofstream &File)
    {
        if (File.is_open() && this != NULL)
        {
            File << "(" << this->nU << ", " << this->nW << ", "
                << this->cost << ")" << " -> ";
        }
    }
};

class KruskalMST
{
public:
    int N;
    int* whichSet;
    int numSets;
    int totalMSTcost;
    edge* edgeList;
    edge* msList;

    KruskalMST()
    {
        this->N = 0;
        this->whichSet = NULL;
        this->numSets = 0;
        this->totalMSTcost = NULL;
        this->edgeList = NULL;
        this->msList = NULL;
    }

    KruskalMST(int N, int* whichSet, int numSets, int totalMSTcost, edge* edgeList, edge* msList)
    {
        this->N = N;
        this->whichSet = whichSet;
        this->numSets = numSets;
        this->totalMSTcost = totalMSTcost;
        this->edgeList = edgeList;
        this->msList = msList;
    }

public:

    edge* findSpot(edge* listHead, edge* newEdge, ofstream &File)
    {
        edge* Spot = listHead;

        if (File.is_open())
        {
            File << "***In findSpot()*****" << endl;
            File << "newEdge Cost: " << newEdge->cost << " ";
            newEdge->printEdge(File);
            File << endl;
        }

        while (Spot->next != NULL)
        {
            if (newEdge->cost < Spot->next->cost)
            {
                break;
            }
            else
            {
                Spot = Spot->next;
            }
            
        }

        if (File.is_open())
        {
            File << "Spot Cost: " << Spot->cost << " ";
            Spot->printEdge(File);
            File << endl;
        }

        if (File.is_open())
        {
            File << "***Leaving findSpot()*****" << endl;
        }
        

        return Spot;
    }
    
    void insertEdgeList(edge* newEdge, ofstream &File)
    {

        if (File.is_open())
        {
            File << "***In insertEdgeList()*****" << endl;
        }
        edge* Spot = this->findSpot(this->edgeList, newEdge, File);
        if (Spot != NULL)
        {
            newEdge->next = Spot->next;
            Spot->next = newEdge;
        }
        else
        {
            if (File.is_open())
            {
                File << "Spot = NULL" << endl;
            }
        }
        
        if (File.is_open())
        {
            File << "***Leaving insertEdgeList()*****" << endl;
        }
       
    }

    void insertMSTList(edge* newEdge, ofstream& File)
    {
        if (File.is_open())
        {
            File << "***In insertMSTList()*****" << endl;
        }
        edge* Spot = this->findSpot(this->msList, newEdge, File);
        if (Spot != NULL)
        {
            newEdge->next = Spot->next;
            Spot->next = newEdge;
        }
        else
        {
            if (File.is_open())
            {
                File << "Spot = NULL" << endl;
            }
        }

        if (File.is_open())
        {
            File << "***Leaving insertMSTList()*****" << endl;
        }
    }

    edge* removeEdge(ofstream &File)
    {
        if (File.is_open())
        {
            File << "*****In removeEdge()*****" << endl;
        }
        edge* tmp = this->edgeList->next;
        this->edgeList->next = this->edgeList->next->next;
        tmp->next = NULL;
        

        if (File.is_open() && tmp != NULL)
        {
            File << "tmp: " << tmp->nU << ", " << tmp->nW << ", " << tmp->cost << endl;
            if (tmp->next == NULL)
            {
                File << "tmp.next is Null" << endl;
            }
            else
            {
                File << "Something wrong in removeEdge()" << endl;
            }
        }

        if (File.is_open())
        {
            File << "*****Leaving removeEdge()*****" << endl;
        }

        return tmp;
    }


    void merge2Sets(int Ni, int Nj)
    {
        if (this->whichSet[Ni] < this->whichSet[Nj])
        {
            this->updateWhichSet(this->whichSet[Nj], this->whichSet[Ni]);
        }
        else
        {
            this->updateWhichSet(this->whichSet[Ni], this->whichSet[Nj]);
        }
    }

    void updateWhichSet(int a, int b)
    {
        for (int i = 1; i <= this->N; i++)
        {
            if (this->whichSet[i] == a)
            {
                this->whichSet[i] = b;
            }
        }
    }

    void printAry(ofstream &File)
    {
        int data = 0;
        for (int i = 1; i <= N; i++)
        {
            data = this->whichSet[i];
            File << "whichSet[" << i << "]: " << data << endl;
        }

    }

    void printEdgeList(ofstream &File)
    {
        edge* temp = this->edgeList;
   
        if (File.is_open())
        {
            File << "listHead -> ";
        }
        if (temp == NULL)
        {
            if (File.is_open())
            {
                File << "The list is Empty." << endl;
            }
        }
        while (temp != NULL)
        {
            File << " < " << temp->nU << ", " << temp->nW << ", " << temp->cost << " > " << " -> ";
            temp = temp->next;
        }
        if (temp == NULL)
        {
            File << "NULL" << endl;
        }
        
    }

    void printMSTList(ofstream& File)
    {
        edge* temp = this->msList;

        if (File.is_open())
        {
            File << "listHead -> ";
        }
        if (temp == NULL)
        {
            if (File.is_open())
            {
                File << "The list is Empty." << endl;
            }
        }
        while (temp != NULL)
        {
            File << " < " << temp->nU << ", " << temp->nW << ", " << temp->cost << " > " << " -> ";
            temp = temp->next;
        }
        if (temp == NULL)
        {
            File << "NULL" << endl;
        }
    }
};

int main(int argc, char** argv)
{
    ifstream inFile(argv[1]);
    ofstream outFile(argv[2]);
    ofstream deBugFile(argv[3]);

    int N = 0;
    int numSets = 0;
    int* whichSet = NULL;
    edge* edgeList;
    edge* msList;
    int totalMSTCost = 0;

    inFile >> N;
    numSets = N;
   
    whichSet = new int[N + 1];
    for (int i = 1; i < N + 1; i++)
    {
        whichSet[i] = i;
    }
    edgeList = new edge(0, 0, 0);
    msList = new edge(0, 0, 0);

    KruskalMST KKMST(N, whichSet, numSets, totalMSTCost, edgeList, msList);

    int u; int w;  int cost;
    u = w = cost = 0;
    edge* newEdge = NULL;
    while (inFile >> u && inFile >> w && inFile >> cost)
    {
        newEdge = new edge(u, w, cost);
        if (deBugFile.is_open() && newEdge != NULL)
        {
            deBugFile << "newEdge from inFile is: ";
            newEdge->printEdge(deBugFile);
            deBugFile << endl;
        }
        KKMST.insertEdgeList(newEdge, deBugFile);
        KKMST.printEdgeList(deBugFile);

    }
    if (deBugFile.is_open())
        deBugFile << "*** At the end of printing all edges of the input graph.***" << endl;
    

    while (KKMST.numSets > 1)
    {
        edge* nextEdge = KKMST.removeEdge(deBugFile);
        //ME
        if (deBugFile.is_open())
        {
            deBugFile << "nU: " << nextEdge->nU << " nW: " << nextEdge->nW << endl;
        }
        while (KKMST.whichSet[nextEdge->nU] == KKMST.whichSet[nextEdge->nW])
        {
            nextEdge = KKMST.removeEdge(deBugFile);
            //ME
            if(KKMST.whichSet[nextEdge->nU] == KKMST.whichSet[nextEdge->nW])
            if (deBugFile.is_open())
            {
                deBugFile << "*****whichSet[U] == whichSet[W]******" << endl;
                deBugFile << "nU: " << nextEdge->nU << " nW: " << nextEdge->nW << endl;
            }
        }
        if (deBugFile.is_open())
        {
            deBugFile << "The nextEdge is ";
            nextEdge->printEdge(deBugFile);
            deBugFile << endl;
        }
        KKMST.insertMSTList(nextEdge, deBugFile);
        KKMST.totalMSTcost += nextEdge->cost;
        KKMST.merge2Sets(nextEdge->nU, nextEdge->nW);
        KKMST.numSets--;
        if (deBugFile.is_open())
        {
            deBugFile << "numSets is " << KKMST.numSets << endl;
        }

        if (deBugFile.is_open())
        {
            deBugFile << "*****Printing whichSet Array*****" << endl;
            KKMST.printAry(deBugFile);
            deBugFile << "***Printing the remaining of edgeList***";
            KKMST.printEdgeList(deBugFile);
            deBugFile << "***Printing the growing MST List***" << endl;
            KKMST.printMSTList(deBugFile);
        }
    }

    KKMST.printMSTList(outFile);

   
}

