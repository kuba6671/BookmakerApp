using BookmakerClientApp.Data.Constant;
using BookmakerClientApp.Data.Extension;
using BookmakerClientApp.Data.Model;
using System.Collections.Generic;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Service
{
	public class PaymentService
	{

		private readonly HttpClient httpClient;

		private readonly AuthService authService;

		public PaymentService()
		{
			this.httpClient = new HttpClient();
			this.authService = new AuthService();
		}

		public async Task<HttpResponseMessage> MakePayment(MakePaymentDto payment)
		{
			var token = authService.getToken();
			if (httpClient.DefaultRequestHeaders.Authorization == null)
			{
				httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
			}
			return await HttpClientExtensions.PostAsJsonAsync(httpClient, BookmakerApiConstant.MAKE_PAYMENT_URL, payment);

		}

		public async Task<List<PaymentDto>> GetPayments()
		{
			var token = authService.getToken();
			if (httpClient.DefaultRequestHeaders.Authorization == null)
			{
				httpClient.DefaultRequestHeaders.TryAddWithoutValidation("Authorization", "Bearer " + token);
			}
			return await HttpClientExtensions.GetAsJsonAsync <List<PaymentDto>>(httpClient,
				BookmakerApiConstant.GET_PAYMENTS_URL);
		}

	}
}
