using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using BookmakerClientApp.Data.Model.Event;

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
            return EventModelDtoMapper(
                await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventModel>>(
                    BookmakerApiConstant.UNFINISHED_FOOTBALL_EVENTS, PAGE, pages));
        }

        public async Task<List<FootballEventDto>> GetFinishedFootballEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return FinishedEventModelDtoMapper(
                await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventModel>>(
                    BookmakerApiConstant.FINISHED_FOOTBALL_EVENTS, PAGE, pages));
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

        private List<FootballEventDto> EventModelDtoMapper(List<FootballEventModel> footballEventDtos)
        {
            List<FootballEventDto> mappedFootballEventDtos = new List<FootballEventDto>();
            FootballEventDto footballEventDto = new FootballEventDto();
            int counter = 1;
            foreach (FootballEventModel eventModel in footballEventDtos)
            {
                if (counter == 1)
                {
                    footballEventDto.FirstTeamWinEventId = eventModel.IdEvent;
                    footballEventDto.FootballTeams = eventModel.HomeTeamName + "-" + eventModel.VisitingTeamName;
                    footballEventDto.Date = eventModel.Date;
                    footballEventDto.FirstTeamWinOdds = eventModel.Odds;
                    footballEventDto.SportName = "Piłka nożna";
                    counter++;
                    continue;
                }
                else if (counter == 2)
                {
                    footballEventDto.SecondTeamWinEventId = eventModel.IdEvent;
                    footballEventDto.SecondTeamWinOdds = eventModel.Odds;
                    counter++;
                    continue;
                }
                else if (counter == 3)
                {
                    footballEventDto.DraftEventId = eventModel.IdEvent;
                    footballEventDto.DraftOdds = eventModel.Odds;
                    counter = 1;
                    mappedFootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                    continue;
                }
            }

            return mappedFootballEventDtos;
        }

        private List<FootballEventDto> FinishedEventModelDtoMapper(List<FootballEventModel> footballEventDtos)
        {
            List<FootballEventDto> mappedFootballEventDtos = new List<FootballEventDto>();
            FootballEventDto footballEventDto = new FootballEventDto();
            int counter = 1;
            foreach (FootballEventModel eventModel in footballEventDtos)
            {
                if (counter == 1)
                {
                    footballEventDto.FirstTeamWinEventId = eventModel.IdEvent;
                    footballEventDto.FootballTeams = eventModel.HomeTeamName + "-" + eventModel.VisitingTeamName;
                    footballEventDto.Date = eventModel.Date;
                    footballEventDto.FirstTeamWinOdds = eventModel.Odds;
                    footballEventDto.SportName = "Piłka nożna";
                    footballEventDto.HomeTeamGoals = eventModel.HomeTeamGoals;
                    footballEventDto.VisitingTeamGoals = eventModel.VisitingTeamGoals;
                    counter++;
                    continue;
                }
                else if (counter == 2)
                {
                    footballEventDto.SecondTeamWinEventId = eventModel.IdEvent;
                    footballEventDto.SecondTeamWinOdds = eventModel.Odds;
                    counter++;
                    continue;
                }
                else if (counter == 3)
                {
                    footballEventDto.DraftEventId = eventModel.IdEvent;
                    footballEventDto.DraftOdds = eventModel.Odds;
                    counter = 1;
                    mappedFootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                    continue;
                }
            }

            return mappedFootballEventDtos;
        }
    }
}