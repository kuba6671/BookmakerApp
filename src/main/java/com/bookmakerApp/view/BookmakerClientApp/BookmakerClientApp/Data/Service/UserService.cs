using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using Hanssens.Net;
using System.Net.Http;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Service
{
    public class UserService
    {
        private readonly HttpClient httpClient;

        public UserService()
        {
            this.httpClient = new HttpClient();
        }

        public async Task<HttpResponseMessage> PostUser(UserModel user)
        {
            var storage = new LocalStorage();


            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.LOGIN_URL, user);
        }
    }
}
