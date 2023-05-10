using System;

namespace BookmakerClientApp.Data.Model.Payment
{
	public class PaymentDto
	{
		private string statusCode;
		private string redirectUri;
		private string orderId;
		private string currencyCode;
		private Decimal totalAmount;
		private int numberOfPages;

		public string StatusCode { get => statusCode; set => statusCode = value; }
		public string RedirectUri { get => redirectUri; set => redirectUri = value; }
		public string OrderId { get => orderId; set => orderId = value; }
		public string CurrencyCode { get => currencyCode; set => currencyCode = value; }
		public decimal TotalAmount { get => totalAmount; set => totalAmount = value; }
		public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
	}
}
