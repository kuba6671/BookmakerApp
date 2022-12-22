using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Constant
{
    public static class BookmakerApiConstant
    {
        public const string BASE_URL = "http://localhost:8080/";
        public const string LOGIN_URL = BASE_URL+"api/auth/login";
        public const string REGISTRATION_URL = BASE_URL + "registration";
        public const string UNFINISHED_FOOTBALL_EVENTS = BASE_URL + "events/unfinishedFootballEvents";

    }
}
