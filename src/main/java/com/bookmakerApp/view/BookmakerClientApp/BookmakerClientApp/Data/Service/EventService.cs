using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Net.Http.Headers;

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
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get,
                BookmakerApiConstant.UNFINISHED_FOOTBALL_EVENTS);
            httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization","Bearer "+token);

            HttpResponseMessage response =  await httpClient.SendAsync(request);
            string statusCode = response.StatusCode.ToString();
            if (statusCode.Equals("OK"))
            {
                HttpContent httpContent = response.Content;
                return await HttpClientExtensions.ReadAsJsonAsync<List<FootballEventModelDto>>(httpContent);
            }
            else
            {
                List<FootballEventModelDto> footballEventModelDtos = new List<FootballEventModelDto>();
                return footballEventModelDtos;
            }
        }


    }
}
