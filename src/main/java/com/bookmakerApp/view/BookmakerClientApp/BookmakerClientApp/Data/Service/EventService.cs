using BookmakerClientApp.Data.Extension;
using Hanssens.Net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Service
{
    public class EventService
    {
        private readonly HttpClient httpClient;

        public EventService()
        {
            this.httpClient = new HttpClient();
        }

        public String getToken()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get("Token");
        }
    }
}
