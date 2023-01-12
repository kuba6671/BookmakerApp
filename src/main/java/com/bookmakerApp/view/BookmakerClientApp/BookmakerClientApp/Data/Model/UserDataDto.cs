using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Model
{
    public class UserDataDto
    {
        private String name;
        private String surname;
        private int age;
        private String mail;
        private double bankBalance;

        public string Name { get => name; set => name = value; }
        public string Surname { get => surname; set => surname = value; }
        public int Age { get => age; set => age = value; }
        public string Mail { get => mail; set => mail = value; }
        public double BankBalance { get => bankBalance; set => bankBalance = value; }
    }
}
