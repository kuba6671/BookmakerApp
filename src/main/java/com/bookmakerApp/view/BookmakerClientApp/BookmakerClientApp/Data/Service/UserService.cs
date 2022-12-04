using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
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

        public async Task<HttpResponseMessage> UserLogin(UserAuthModel user)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.LOGIN_URL, user);
        }

        public async Task<HttpResponseMessage> UserRegistration(UserModel user)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.REGISTRATION_URL, user);
        }
    }
}
