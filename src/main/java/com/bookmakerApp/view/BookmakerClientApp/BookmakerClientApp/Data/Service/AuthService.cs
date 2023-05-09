using BookmakerClientApp.Data.Extension;
using System;

namespace BookmakerClientApp.Data.Service
{
    public class AuthService
    {
        public String GetToken()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get<string>("Token");
        }

        public String GetUserId()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get<string>("id-user");
        }
    }
}