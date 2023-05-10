using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using System.Net.Http;
using System.Threading.Tasks;
using BookmakerClientApp.Data.Model.User;

namespace BookmakerClientApp.Data.Service
{
    public class UserService
    {
        private readonly HttpClient httpClient;
        private readonly AuthService authService;

        public UserService()
        {
            httpClient = new HttpClient();
            authService = new AuthService();
        }

        public async Task<HttpResponseMessage> UserLogin(UserAuthModel user)
        {
            return await httpClient.PostAsJsonAsync(BookmakerApiConstant.LOGIN_URL, user);
        }

        public async Task<HttpResponseMessage> UserRegistration(UserModel user)
        {
            return await httpClient.PostAsJsonAsync(BookmakerApiConstant.REGISTRATION_URL, user);
        }

        public async Task<HttpResponseMessage> ChangePassword(UserPasswordDto user)
        {
            return await httpClient.PutAsJsonAsync(BookmakerApiConstant.CHANGE_PASSWORD_URL, user);
        }

        public async Task<HttpResponseMessage> ChangeData(UserDataDto user)
        {
            return await httpClient.PutAsJsonAsync(BookmakerApiConstant.CHANGE_DATA_URL, user);
        }

        public async Task<UserDataDto> GetUserById()
        {
            var token = authService.GetToken();
            var idUser = authService.GetUserId();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }
            return await httpClient.GetAsJsonAsync<UserDataDto>(BookmakerApiConstant.USER_DATA_URL + idUser);
        }
    }
}
