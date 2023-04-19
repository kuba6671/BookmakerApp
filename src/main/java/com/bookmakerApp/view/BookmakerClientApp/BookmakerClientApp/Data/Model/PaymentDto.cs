using System;
using System.Numerics;

namespace BookmakerClientApp.Data.Model
{
	public class PaymentDto
	{
		private string statusCode;
		private string redirectUri;
		private string orderId;
		private string currencyCode;
		private Decimal totalAmount;

		public string StatusCode { get => statusCode; set => statusCode = value; }
		public string RedirectUri { get => redirectUri; set => redirectUri = value; }
		public string OrderId { get => orderId; set => orderId = value; }
		public string CurrencyCode { get => currencyCode; set => currencyCode = value; }
		public decimal TotalAmount { get => totalAmount; set => totalAmount = value; }
	}
}
