using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BallCatch
{
    class Monster
    {
        public  string name;
        public  int catchRate;

        public Monster(string x,int y)
        {
            name = x;
            catchRate = y;
        }
    }
}
