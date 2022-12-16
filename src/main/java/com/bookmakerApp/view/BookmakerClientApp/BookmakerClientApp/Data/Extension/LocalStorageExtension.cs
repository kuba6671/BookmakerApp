using Hanssens.Net;
using System;

namespace BookmakerClientApp.Data.Extension
{
    public sealed class LocalStorageExtension
    {
        private static LocalStorage localStorage;

        private static LocalStorageExtension localStorageExtension;

        private LocalStorageExtension()
        {
            if(localStorage == null)
                localStorage = new LocalStorage();
        }

        public static LocalStorageExtension GetInstance()
        {
            if(localStorageExtension == null)
            {
                localStorageExtension = new LocalStorageExtension();
            }
            return localStorageExtension;
        }

        public String Get(string key)
        {
            return localStorage.Get(key).ToString();
        }

        public void Store(string key, string value)
        {
            localStorage.Store(key,value);
        }
    }
}
