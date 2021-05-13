using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MySearchAlgo
{
    class Program
    {
        static void Main(string[] args)
        {

            ArrayList arr = new ArrayList();
            Random rnd = new Random();
            
            for (int i = 0;i < 100;i++)
            {
                
               
                uint j = rnd.NextUInt32(0, 100);


                arr.Insert(i, j);
            }
            
            for(int k=0;k<arr.Count;k++)
            {
                Console.Write(arr.IndexOf(k) + "\t");
            }

            Console.ReadKey();


        }
    }
}
