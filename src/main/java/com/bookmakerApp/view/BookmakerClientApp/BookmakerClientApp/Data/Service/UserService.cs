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
        private readonly AuthService authService;

        public UserService()
        {
            this.httpClient = new HttpClient();
            this.authService = new AuthService();
        }

        public async Task<HttpResponseMessage> UserLogin(UserAuthModel user)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.LOGIN_URL, user);
        }

        public async Task<HttpResponseMessage> UserRegistration(UserModel user)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.REGISTRATION_URL, user);
        }

        public async Task<HttpResponseMessage> ChangePassword(UserPasswordDto user)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.CHANGE_PASSWORD_URL, user);
        }

        public async Task<UserDataDto> GetUserById()
        {
            var token = authService.getToken();
            var idUser = authService.getUserId();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }
            return await HttpClientExtensions.GetAsJsonAsync<UserDataDto>(httpClient,
                 BookmakerApiConstant.USER_DATA_URL + idUser);
        }
    }
}
