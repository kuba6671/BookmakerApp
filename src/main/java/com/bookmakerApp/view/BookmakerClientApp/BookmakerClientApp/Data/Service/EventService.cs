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

        public async Task<List<FootballEventDto>> GetUnfinishedFootballEvents()
        {
            var token = authService.getToken();
            httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            return EventModelDtoMapper(await HttpClientExtensions.GetAsJsonAsync<List<FootballEventModel>>(httpClient,
                BookmakerApiConstant.UNFINISHED_FOOTBALL_EVENTS));
        }

        public async Task<List<FootballEventDto>> GetFiinishedFootballEvents()
        {
            var token = authService.getToken();
            httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            return FinishedEventModelDtoMapper(await HttpClientExtensions.GetAsJsonAsync<List<FootballEventModel>>(httpClient,
                BookmakerApiConstant.FINISHED_FOOTBALL_EVENTS));
        }

        private List<FootballEventDto> EventModelDtoMapper(List<FootballEventModel> footballEventDtos)
        {
            List<FootballEventDto> FootballEventDtos = new List<FootballEventDto>();
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
                    FootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                    continue;
                }
            }
            return FootballEventDtos;
        }

        private List<FootballEventDto> FinishedEventModelDtoMapper(List<FootballEventModel> footballEventDtos)
        {
            List<FootballEventDto> FootballEventDtos = new List<FootballEventDto>();
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
                    FootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                    continue;
                }
            }
            return FootballEventDtos;
        }
    }
}
