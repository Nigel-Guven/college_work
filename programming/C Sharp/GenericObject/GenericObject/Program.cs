using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenericObject
{
    public class GenericObject<T>
    {
        T item;

        public GenericObject(T x)
        {
            item = x;
        }
        public void PrintObject()
        {
            Console.WriteLine(item);
        }
        
    }
    public static class Run
    {
        public static void Main(string[] args)
        {
            GenericObject<string> obj1 = new GenericObject<string>("B");
            GenericObject<int> obj2 = new GenericObject<int>(5);
            obj1.PrintObject();
            obj2.PrintObject();
            Console.ReadKey();
        }
    }
}
