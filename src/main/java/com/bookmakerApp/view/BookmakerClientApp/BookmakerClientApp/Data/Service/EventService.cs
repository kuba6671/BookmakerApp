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
            return FootballEventModelDtoMapper(
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
            return FinishedFootballEventModelDtoMapper(
                await httpClient.GetAsJsonAsyncWithListParameter<List<FootballEventModel>>(
                    BookmakerApiConstant.FINISHED_FOOTBALL_EVENTS, PAGE, pages));
        }

        public async Task<List<MMAEventDto>> GetUnfinishedMMAEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return MMAEventModelDtoMapper(
                await httpClient.GetAsJsonAsyncWithListParameter<List<MMAEventModel>>(
                    BookmakerApiConstant.UNFINISHED_MMA_EVENTS, PAGE, pages));
        }

        public async Task<List<MMAEventDto>> GetFinishedMMAEvents(int pageNumber)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);
            return MMAEventModelDtoMapper(
                await httpClient.GetAsJsonAsyncWithListParameter<List<MMAEventModel>>(
                    BookmakerApiConstant.FINISHED_MMA_EVENTS, PAGE, pages));
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

        private List<FootballEventDto> FootballEventModelDtoMapper(List<FootballEventModel> footballEvents)
        {
            List<FootballEventDto> mappedFootballEventDtos = new List<FootballEventDto>();
            FootballEventDto footballEventDto = new FootballEventDto();
            int counter = 1;
            footballEvents.ForEach(footballEvent =>
            {
                if (counter == 1)
                {
                    footballEventDto.FirstTeamWinEventId = footballEvent.IdEvent;
                    footballEventDto.FootballTeams = footballEvent.HomeTeamName + "-" + footballEvent.VisitingTeamName;
                    footballEventDto.Date = footballEvent.Date;
                    footballEventDto.FirstTeamWinOdds = footballEvent.Odds;
                    footballEventDto.SportName = "Piłka nożna";
                    footballEventDto.NumberOfPages = footballEvent.NumberOfPages;
                    counter++;
                }
                else if (counter == 2)
                {
                    footballEventDto.SecondTeamWinEventId = footballEvent.IdEvent;
                    footballEventDto.SecondTeamWinOdds = footballEvent.Odds;
                    counter++;
                }
                else if (counter == 3)
                {
                    footballEventDto.DraftEventId = footballEvent.IdEvent;
                    footballEventDto.DraftOdds = footballEvent.Odds;
                    counter = 1;
                    mappedFootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                }
            });

            return mappedFootballEventDtos;
        }

        private List<FootballEventDto> FinishedFootballEventModelDtoMapper(List<FootballEventModel> footballEvents)
        {
            List<FootballEventDto> mappedFootballEventDtos = new List<FootballEventDto>();
            FootballEventDto footballEventDto = new FootballEventDto();
            int counter = 1;
            footballEvents.ForEach(footballEvent =>
            {
                if (counter == 1)
                {
                    footballEventDto.FirstTeamWinEventId = footballEvent.IdEvent;
                    footballEventDto.FootballTeams = footballEvent.HomeTeamName + "-" + footballEvent.VisitingTeamName;
                    footballEventDto.Date = footballEvent.Date;
                    footballEventDto.FirstTeamWinOdds = footballEvent.Odds;
                    footballEventDto.SportName = "Piłka nożna";
                    footballEventDto.HomeTeamGoals = footballEvent.HomeTeamGoals;
                    footballEventDto.VisitingTeamGoals = footballEvent.VisitingTeamGoals;
                    counter++;
                }
                else if (counter == 2)
                {
                    footballEventDto.SecondTeamWinEventId = footballEvent.IdEvent;
                    footballEventDto.SecondTeamWinOdds = footballEvent.Odds;
                    counter++;
                }
                else if (counter == 3)
                {
                    footballEventDto.DraftEventId = footballEvent.IdEvent;
                    footballEventDto.DraftOdds = footballEvent.Odds;
                    counter = 1;
                    mappedFootballEventDtos.Add(footballEventDto);
                    footballEventDto = new FootballEventDto();
                }
            });

            return mappedFootballEventDtos;
        }

        private List<MMAEventDto> MMAEventModelDtoMapper(List<MMAEventModel> mmaEvents)
        {
            List<MMAEventDto> mappedMmaEventDtos = new();
            MMAEventDto mmaEventDto = new MMAEventDto();
            int counter = 1;
            mmaEvents.ForEach(mmaEvent =>
            {
                if (counter == 1)
                {
                    mmaEventDto.FirstFighterWinId = mmaEvent.IdEvent;
                    mmaEventDto.FightersNames = mmaEvent.FirstFighterName + "-" + mmaEvent.SecondFighterName;
                    mmaEventDto.Date = mmaEvent.Date;
                    mmaEventDto.FirstFigherWinOdds = mmaEvent.Odds;
                    mmaEventDto.MmaFightResult = mmaEvent.MmaFightResult;
                    mmaEventDto.NumberOfPages = mmaEvent.NumberOfPages;
                    counter++;
                }
                else if (counter == 2)
                {
                    mmaEventDto.SecondFighterWinId = mmaEvent.IdEvent;
                    mmaEventDto.SecondFighterWinOdds = mmaEvent.Odds;
                    counter++;
                    mappedMmaEventDtos.Add(mmaEventDto);
                    mmaEventDto = new();
                }
            });
            return mappedMmaEventDtos;
        }
    }
}