using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EmailValidation
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter your first name: ");
            string fname = Console.ReadLine();
            Console.WriteLine("Enter your surname: ");
            string sname = Console.ReadLine();

            string localmail = fname + "." + sname;

            Console.WriteLine(localmail);
            Console.WriteLine();
        }
        
    }
}
