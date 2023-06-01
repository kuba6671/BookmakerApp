namespace BookmakerClientApp.Data.Constant
{
    public static class BookmakerApiConstant
    {
        private const string BASE_URL = "http://localhost:8080/";
        public const string LOGIN_URL = BASE_URL + "api/auth/login";
        public const string REGISTRATION_URL = BASE_URL + "api/auth/registration";
        public const string CHANGE_PASSWORD_URL = BASE_URL + "api/auth/changePassword";
        public const string UNFINISHED_FOOTBALL_EVENTS = BASE_URL + "events/unfinishedFootballEvents";
        public const string FINISHED_FOOTBALL_EVENTS = BASE_URL + "events/finishedFootballEvents";
        public const string FOOTBALL_EVENTS_BY_IDS = BASE_URL + "events/footballEventsByIds";
        public const string ALL_BET_TICKET_FOR_USER = BASE_URL + "betTicketsUser/";
        public const string WON_BET_TICKET_FOR_USER = BASE_URL + "betTicketsWon/";
        public const string LOST_BET_TICKET_FOR_USER = BASE_URL + "betTicketsLost/";
        public const string UNFINISHED_BET_TICKET_FOR_USER = BASE_URL + "betTicketsUnfinished/";
        public const string ADD_BET_TICKET_URL = BASE_URL + "betTickets";
        public const string USER_DATA_URL = BASE_URL + "user/";
        public const string CHANGE_DATA_URL = BASE_URL + "user/changeUserData";
        public const string MAKE_PAYMENT_URL = BASE_URL + "makePayment";
        public const string GET_PAYMENTS_URL = BASE_URL + "payments";
        public const string UNFINISHED_MMA_EVENTS = BASE_URL + "events/unfinishedMMAEvents";
        public const string FINISHED_MMA_EVENTS = BASE_URL + "events/finishedMMAEvents";
    }
}