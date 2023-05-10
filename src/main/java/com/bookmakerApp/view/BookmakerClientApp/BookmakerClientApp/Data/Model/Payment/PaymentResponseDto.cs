namespace BookmakerClientApp.Data.Model.Payment
{
    public class PaymentResponseDto
    {
        private string statusCode;
        private string redirectUri;
        private string orderId;

        public string StatusCode { get => statusCode; set => statusCode = value; }
        public string RedirectUri { get => redirectUri; set => redirectUri = value; }
        public string OrderId { get => orderId; set => orderId = value; }
    }
}
