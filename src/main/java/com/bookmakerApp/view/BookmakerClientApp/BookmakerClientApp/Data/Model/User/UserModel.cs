using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.User
{
    public class UserModel
    {
        [JsonProperty(PropertyName = "mail")]
        private string mail;
        [JsonProperty(PropertyName = "password")]
        private string password;
        [JsonProperty(PropertyName = "name")]
        private string name;
        [JsonProperty(PropertyName = "surname")]
        private string surname;
        [JsonProperty(PropertyName = "age")]
        private int age;
        [JsonProperty(PropertyName = "idUser")]
        private long idUser;

        public string Mail { get => mail; set => mail = value; }
        public string Password { get => password; set => password = value; }
        public string Name { get => name; set => name = value; }
        public string Surname { get => surname; set => surname = value; }
        public int Age { get => age; set => age = value; }
        public long IdUser { get => idUser; set => idUser = value; }
    }
}
