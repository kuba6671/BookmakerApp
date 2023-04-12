using Newtonsoft.Json;
using System;
using System.Numerics;

namespace BookmakerClientApp.Data.Model
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
