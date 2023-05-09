using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Payment
{
	public class MakePaymentDto
	{
		[JsonProperty(PropertyName = "totalAmount")]
		private Decimal totalAmount;
		[JsonProperty(PropertyName = "currencyCode")]
		private string currencyCode;

		public string CurrencyCode { get => currencyCode; set => currencyCode = value; }
		public Decimal TotalAmount { get => totalAmount; set => totalAmount = value; }
	}
}
