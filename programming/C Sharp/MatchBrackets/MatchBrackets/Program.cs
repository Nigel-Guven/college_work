using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MatchBrackets
{
    class Program
    {
        public static bool Input(string x)
        {
            int uppy = 0;
            //string [] array1 = { "(", "{" };
            //string [] array2 = { ")", "}" };
            for (int i = 0;i < x.Count();i++)
            {
                if ((x.ElementAt(i)).Equals('{') || (x.ElementAt(i)).Equals('(') || (x.ElementAt(i)).Equals('['))
                {
                    uppy++;
                }
                else if((x.ElementAt(i)).Equals('}') || (x.ElementAt(i)).Equals(')') || (x.ElementAt(i)).Equals(']'))
                {
                    uppy--;
                }
            }

            return uppy == 0;
        }
        static void Main(string[] args)
        {
            string word = "{x + y([1][5][3])/34(12){x | -> 5}[2]}";
            string pord = "{{3,65,9},{2,34,2}}";


            if (Input(word) == true)
                Console.WriteLine("GOOD");
            else
                Console.WriteLine("BAD");
            if (Input(pord) == true)
                Console.Write("GOOD");
            else
                Console.WriteLine("BAD");


            Console.ReadKey();
        }
    }
}
