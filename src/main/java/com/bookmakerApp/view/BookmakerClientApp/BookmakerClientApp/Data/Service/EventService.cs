using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using BookmakerClientApp.Data.Model.Event;
using BookmakerClientApp.Data.Model.Event.Football;
using BookmakerClientApp.Data.Model.Event.MMA;

namespace BookmakerClientApp.Data.Service
{
    public class EventService
    {
        private readonly HttpClient httpClient;
        private readonly AuthService authService;

        private const string PAGE = "pageNumber";

        public EventService()
        {
            httpClient = new HttpClient();
            authService = new AuthService();
        }

        public async Task<List<FootballEventDto>> GetUnfinishedFootballEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventDto>>(
                BookmakerApiConstant.UNFINISHED_FOOTBALL_EVENTS, PAGE, pages);
        }

        public async Task<List<FootballEventDto>> GetFinishedFootballEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventDto>>(
                BookmakerApiConstant.FINISHED_FOOTBALL_EVENTS, PAGE, pages);
        }

        public async Task<List<MMAEventDto>> GetUnfinishedMMAEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return await httpClient.GetAsJsonAsyncWithListParameter<List<MMAEventDto>>(
                BookmakerApiConstant.UNFINISHED_MMA_EVENTS, PAGE, pages);
        }

        public async Task<List<MMAEventDto>> GetFinishedMMAEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return await httpClient.GetAsJsonAsyncWithListParameter<List<MMAEventDto>>(
                BookmakerApiConstant.FINISHED_MMA_EVENTS, PAGE, pages);
        }

        public async Task<List<FootballEventModel>> GetFootballEventsByIds(JArray idEvents)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            return await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventModel>>(
                BookmakerApiConstant.FOOTBALL_EVENTS_BY_IDS, "idEvents", idEvents);
        }

        public async Task<List<MMAEventModel>> GetMMAEventsByIds(JArray idEvents)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            return await httpClient.GetAsJsonAsyncWithListParameter<List<MMAEventModel>>(
                BookmakerApiConstant.MMA_EVENTS_BY_IDS, "idEvents", idEvents);
        }
    }
}