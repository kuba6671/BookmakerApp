using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
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
            this.httpClient = new HttpClient();
            this.authService = new AuthService();
        }


        public async Task<List<BetTicketDto>> GetAllBetTicketForUser(int pageNumber)
        {
            return await getBetTickets(BookmakerApiConstant.ALL_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetWonBetTicketForUser(int pageNumber)
        {
            return await getBetTickets(BookmakerApiConstant.WON_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetLostBetTicketForUser(int pageNumber)
        {
            return await getBetTickets(BookmakerApiConstant.LOST_BET_TICKET_FOR_USER, pageNumber);
        }

        public async Task<List<BetTicketDto>> GetUnfinishedBetTicketForUser(int pageNumber)
        {
            return await getBetTickets(BookmakerApiConstant.UNFINISHED_BET_TICKET_FOR_USER, pageNumber);
        }

        private async Task<List<BetTicketDto>> getBetTickets(string URL, int pageNumber)
        {
            var token = authService.getToken();
            var idUser = authService.getUserId();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }
            JArray pages = new JArray(pageNumber);

            return await HttpClientExtensions.GetAsJsonAsyncWithListParameter<List<BetTicketDto>>(httpClient,
                URL + idUser, PAGE, pages);
        }

        public async Task<HttpResponseMessage> AddBetTicket(BetTicketDto newBetTicket)
        {
            var token = authService.getToken();
            if (httpClient.DefaultRequestHeaders.Authorization == null)
            {
                httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            }

            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.ADD_BET_TICKET_URL,
                newBetTicket);
        }
    }
}