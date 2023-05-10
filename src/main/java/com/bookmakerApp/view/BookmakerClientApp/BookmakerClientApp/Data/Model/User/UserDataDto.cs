using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.User
{
    public class UserDataDto
    {
        [JsonProperty(PropertyName = "name")]
        private String name;
        [JsonProperty(PropertyName = "surname")]
        private String surname;
        [JsonProperty(PropertyName = "age")]
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
