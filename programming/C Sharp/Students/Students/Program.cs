using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Students
{
    class Program
    {
        public static string getGrade(int x)
        {
            if(x>85)
            {
                return "A";
            }
            else if(x>70)
            {
                return "B";
            }
            else if(x>60)
            {
                return "C";
            }
            else if(x>50)
            {
                return "D";
            }
            else if(x>=40)
            {
                return "E";
            }
            else
            {
                return "F";
            }
        }
        static void Main(string[] args)
        {
            Console.WriteLine("PRINT RESULTS:");

            List<int> result = new List<int>();
            List<string> student = new List<string>();

            int max = 0;
            int min = 100;
            double sd = 0;
            string maxStud = "";
            string minStud = "";
            int sigma = 0;
            int total = 0;
            string[] lines = System.IO.File.ReadAllLines("results.txt");
            
            foreach (string line in lines)
            {
                string[] strs = line.Split(' ');
                result.Add(Int32.Parse(strs[0]));
                student.Add(strs[1]); 
            }

            for (int i = 0; i < result.Count; i++)
            {
                Console.WriteLine("Name: " + student[i] + "\t | " + "Result: " + result[i]);
                if (result[i] > max)
                {
                    max = result[i]; maxStud = student[i];
                }
                else if (result[i] < min)
                {
                    min = result[i]; minStud = student[i];
                }
                total += result[i];    
            }

            //bottom student
            Console.WriteLine("Lowest Student = " + minStud);

            //top student
            Console.WriteLine("Highest Student = " + maxStud);

            //average
            int avg = total/result.Count;
            Console.WriteLine("Average Mark Overall is " + avg +"%.");

            //standard deviation
            for(int j = 0;j < result.Count;j++)
            {
                int tmp = result[j] - avg;
                tmp = tmp*tmp;
                sigma += tmp;
                
            }
            sigma = sigma / result.Count;
            sd = Math.Sqrt(sigma);
            Console.WriteLine("\nStandard Deviation " + sd );

            //%students failed v passed
            int fail = 0;
            int pass = 0;

            for (int k = 0; k < result.Count; k++)
            {
                if (result[k] > 40)
                {
                    pass++;
                }
                else
                    fail++;
            }
            Console.WriteLine("\nPassed: " + pass + "\nFailed: " + fail);

            //Print out grade(A,B,C,D,E,F)

            for(int l = 0;l<result.Count; l++)
            {
                Console.WriteLine("Name: " + student[l] + "\t\t |> Grade: \t" + getGrade(result[l]));
            }

            //Standard Deviations
            Console.WriteLine("Mean: " + avg);
            Console.WriteLine("SD: " + sd);
            Console.WriteLine("68% \t " + Math.Round((avg-sd)) + "% - " + Math.Round((avg+sd)) + "%");
            Console.WriteLine("95% \t " + Math.Round((avg - sd - sd)) + "% - 100%");
    
    


            System.Console.ReadKey();

        }
    }
}
