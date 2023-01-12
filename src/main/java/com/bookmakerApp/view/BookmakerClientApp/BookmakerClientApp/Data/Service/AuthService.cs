using BookmakerClientApp.Data.Extension;
using System;

namespace BookmakerClientApp.Data.Service
{
    public class AuthService
    {

        public String getToken()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get<String>("Token");
        }

        public String getUserId()
        {
            LocalStorageExtension localStorage = LocalStorageExtension.GetInstance();
            return localStorage.Get<String>("id-user");
        }
    }
}
