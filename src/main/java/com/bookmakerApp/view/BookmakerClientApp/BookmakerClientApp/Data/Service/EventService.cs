using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Service
{
    public class EventService
    {
        private readonly HttpClient httpClient;

        private readonly AuthService authService;

        public EventService()
        {
            this.httpClient = new HttpClient();
            this.authService = new AuthService();
        }

        public async Task<List<FootballEventModelDto>> GetUnfinishedFootballEvents()
        {
            var token = authService.getToken();
            httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization","Bearer "+token);
            return await HttpClientExtensions.GetAsJsonAsync<List<FootballEventModelDto>>(httpClient, 
                BookmakerApiConstant.UNFINISHED_FOOTBALL_EVENTS);
        }


    }
}
