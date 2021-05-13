using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OpenPage
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("What are you searching for? ");
            string x = Console.ReadLine();

            string proto = "https://";
            string web = "www.";

            string post = ".ie";
            string myUrl = proto + web + x + post;
            System.Diagnostics.Process.Start(myUrl);
            Console.ReadKey();

        }
    }
}
