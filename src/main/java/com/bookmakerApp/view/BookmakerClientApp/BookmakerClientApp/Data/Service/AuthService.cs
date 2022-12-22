using BookmakerClientApp.Data.Extension;
using System;

namespace BookmakerClientApp.Data.Service
{
    public class AuthService
    {


        public String getToken()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get("Token");
        }
    }
}
