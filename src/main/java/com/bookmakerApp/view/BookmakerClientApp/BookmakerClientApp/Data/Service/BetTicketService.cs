using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Service
{
    public class BetTicketService
    {
        private readonly HttpClient httpClient;

        private readonly AuthService authService;

        public BetTicketService()
        {
            this.httpClient = new HttpClient();
            this.authService = new AuthService();
        }


        public async Task<List<BetTicketDto>> GetAllBetTicketForUser()
        {
            return await getBetTickets(BookmakerApiConstant.ALL_BET_TICKET_FOR_USER);
        }

        public async Task<List<BetTicketDto>> GetWonBetTicketForUser()
        {
            return await getBetTickets(BookmakerApiConstant.WON_BET_TICKET_FOR_USER);
        }

        public async Task<List<BetTicketDto>> GetLostBetTicketForUser()
        {
            return await getBetTickets(BookmakerApiConstant.LOST_BET_TICKET_FOR_USER);
        }

        public async Task<List<BetTicketDto>> GetUnfinishedBetTicketForUser()
        {
            return await getBetTickets(BookmakerApiConstant.UNFINISHED_BET_TICKET_FOR_USER);
        }

        private async Task<List<BetTicketDto>> getBetTickets(string URL)
        {
            var token = authService.getToken();
            var idUser = authService.getUserId();
            httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
            return await HttpClientExtensions.GetAsJsonAsync<List<BetTicketDto>>(httpClient,
                 URL + idUser);
        }

        public async Task<HttpResponseMessage> AddBetTicket(BetTicketDto betTicket)
        {
            return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.ADD_BET_TICKET_URL, betTicket);
        }

    }
}
