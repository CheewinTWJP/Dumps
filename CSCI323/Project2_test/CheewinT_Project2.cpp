#include <fstream>

std::string data;
int total = 0;
int count = 0;

void processString(std::ifstream& inFile2, std::ofstream& outFile2)
{
    if (outFile2.is_open())
    {
        outFile2 << "in processString method \n";
    }
    total = 0;
    count = 0;

    while (inFile2 >> data)
    {
        outFile2 << data << " ";
        total++;
        count++;
        if (count >= 5)
        {
            outFile2 << "\n";
            count = 0;
        }

    }

    outFile2 << "\n";
    outFile2 << "the total string count is " << total;


}


int main(int argc, char** argv)
{
    
    std::ifstream inFile2(argv[1]);
    std::ofstream outFile2(argv[2]);

    processString(inFile2, outFile2);

    inFile2.close();
    outFile2.close();
}





