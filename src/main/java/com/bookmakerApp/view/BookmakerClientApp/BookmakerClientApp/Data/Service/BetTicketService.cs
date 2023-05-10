using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using BookmakerClientApp.Data.Model.BetTicket;
using Newtonsoft.Json.Linq;

namespace BookmakerClientApp.Data.Service
{
    public class BetTicketService
    {
        private readonly HttpClient httpClient;
        private readonly AuthService authService;

        private const string PAGE = "pageNumber";

        public BetTicketService()
        {
            httpClient = new HttpClient();
            authService = new AuthService();
        }


        public async Task<List<BetTicketDto>> GetAllBetTicketForUser(int pageNumber)
        {
            return await GetBetTickets(BookmakerApiConstant.ALL_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetWonBetTicketForUser(int pageNumber)
        {
            return await GetBetTickets(BookmakerApiConstant.WON_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetLostBetTicketForUser(int pageNumber)
        {
            return await GetBetTickets(BookmakerApiConstant.LOST_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetUnfinishedBetTicketForUser(int pageNumber)
        {
            return await GetBetTickets(BookmakerApiConstant.UNFINISHED_BET_TICKET_FOR_USER, pageNumber);
        }

        private async Task<List<BetTicketDto>> GetBetTickets(string URL, int pageNumber)
        {
            var token = authService.GetToken();
            var idUser = authService.GetUserId();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            JArray pages = new JArray(pageNumber);

            return await httpClient.GetAsJsonAsyncWithListParameter<List<BetTicketDto>>(URL + idUser, PAGE, pages);
        }

        public async Task<HttpResponseMessage> AddBetTicket(BetTicketDto newBetTicket)
        {
            var token = authService.GetToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            return await httpClient.PostAsJsonAsync(BookmakerApiConstant.ADD_BET_TICKET_URL, newBetTicket);
        }
    }
}