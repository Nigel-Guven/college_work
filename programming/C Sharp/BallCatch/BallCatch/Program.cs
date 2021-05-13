using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallCatch
{
    class Program
    {
        public static void CatchFormula(Ball a, Monster b)
        {
            Random rnd = new Random();
            int n;
            if (a.name == "ultra")
            {
                n = rnd.Next(1, 6);


                if (n > b.catchRate)
                {
                    Console.WriteLine(b.name + " broke free.!");
                    
                }
                else
                {
                    int m = rnd.Next(1, 255);
                    if (n > m)
                    {
                        Console.WriteLine(b.name + " was caught.!");
                        
                    }
                    else if (n < m)
                    {
                        Console.WriteLine(b.name + " broke free.!");
                        
                    }
                }
            }
            



        }
        public static void Main(string[] args)
        {
            Ball b1 = new Ball("ultra");
            Monster m1 = new Monster("bob",120);

            
            for (int i = 0; i < 15; i++)
            {
                CatchFormula(b1, m1);
                
            }
            Console.ReadKey();
        }
    }
}
