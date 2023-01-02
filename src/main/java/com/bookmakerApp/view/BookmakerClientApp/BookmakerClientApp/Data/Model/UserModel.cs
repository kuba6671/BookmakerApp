namespace BookmakerClientApp.Data.Model
{
    public class UserModel
    {
        private string mail;
        private string password;
        private string name;
        private string surname;
        private int age;
        private long idUser;

        public string Mail { get => mail; set => mail = value; }
        public string Password { get => password; set => password = value; }
        public string Name { get => name; set => name = value; }
        public string Surname { get => surname; set => surname = value; }
        public int Age { get => age; set => age = value; }
        public long IdUser { get => idUser; set => idUser = value; }
    }
}
