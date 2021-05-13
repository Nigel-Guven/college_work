using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GuessTheNumber
{
    class Program
    {
        static void Main(string[] args)
        {
            Random rnd = new Random();
            int max = 100;
            int x = rnd.Next(1,max);
            x = Math.Abs(x);
            int myNum = 0;
            int count = 5;
            int guesslo;
            int guesshi;
            while (count != 0)
            {
                myNum = Convert.ToInt32(Console.ReadLine());

                if(myNum == x)
                {
                    Console.WriteLine("You have won!");
                } 
                else
                {
                    guesslo = rnd.Next(1,15);
                    guesshi = x + guesslo;
                    guesslo = x - guesslo;
                    Console.WriteLine(guesslo + " < x < " + guesshi );
                    count--; 
                    Console.WriteLine(count + " tries left.");


                }
                
            }

            Console.ReadKey();
        }
    }
}
