using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CurrencyConvert
{
    class Program
    {
        static double InputNumber()
        {
            Console.WriteLine("Input a number: ");
            double intTemp = Convert.ToDouble(Console.ReadLine());
            return intTemp;
        }
        static void ConvertYen(double num)
        {
            double numEuro = Math.Round(num/130.18,2);
            double numDollar = Math.Round(num/109.77,2);
            double numPound = Math.Round(num/148.61,2);
            Console.WriteLine(num + " YEN is: \n\tEURO: " + numEuro + "\n\tDOLLAR: " + numDollar + "\n\tPOUND: " + numPound);
        }
        static void ConvertEuro(double num)
        {
            double numYen = Math.Round(num / 0.0077,2);
            double numDollar = Math.Round(num / 0.84,2);
            double numPound = Math.Round(num / 1.14,2);
            Console.WriteLine(num + " EURO is: \n\tYEN: " + numYen + "\n\tDOLLAR: " + numDollar + "\n\tPOUND: " + numPound);
        }
        static void ConvertDollar(double num)
        {
            double numYen = Math.Round(num / 0.0091,2);
            double numEuro = Math.Round(num / 1.19,2);
            double numPound = Math.Round(num / 1.35,2);
            Console.WriteLine(num + " EURO is: \n\tYEN: " + numYen + "\n\tEURO: " + numEuro + "\n\tPOUND: " + numPound);
        }
        static void ConvertPound(double num)
        {
            double numYen = Math.Round(num / 0.0067, 2);
            double numEuro = Math.Round(num / 0.88, 2);
            double numDollar = Math.Round(num / 0.74, 2);
            Console.WriteLine(num + " EURO is: \n\tYEN: " + numYen + "\n\tEURO: " + numEuro + "\n\tDOLLAR: " + numDollar);
        }
        static void CheckInput(char input)
        {
            double x;
            Console.WriteLine();
            if (input == 'y')
            {
                Console.WriteLine("Your currency is YEN");
                x = InputNumber();
                ConvertYen(x);
            }
            else if (input == 'e')
            {
                Console.WriteLine("Your currency is EURO");
                x = InputNumber();
                ConvertEuro(x);
            }
            else if (input == 'd')
            {
                Console.WriteLine("Your currency is DOLLAR");
                x = InputNumber();
                ConvertDollar(x); 
            }
            else
            {
                Console.WriteLine("Your currency is POUND");
                x = InputNumber();
                ConvertPound(x);
            }
        }
        static void Main(string[] args)
        {
            Console.WriteLine("What is your currency?");
            Console.WriteLine("YEN = y \nEURO = e \nDOLLAR = d \nPOUND = p");
            char input = Console.ReadKey().KeyChar;

            CheckInput(input);
            Console.ReadKey();
        }
    }
}
