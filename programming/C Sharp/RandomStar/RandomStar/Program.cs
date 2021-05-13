using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RandomStar
{
    class Program
    {
        static void printArr(string [] myArray)
        {
            for(int c = 0;c < myArray.Count();c++)
            {
                Console.Write(myArray[c] + "," );
                
            }
        }
        static void Main(string[] args)
        {
            Random rnd = new Random();
            

            string[] myArray = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
            

            for (int i =0;i<myArray.Count();i++)
            {
                string [] newArray = myArray;
                int a = rnd.Next(0, myArray.Count());
                newArray[a] = "*";
                printArr(newArray);
                Console.WriteLine();
            }
            Console.ReadKey();
        }
    }
}
