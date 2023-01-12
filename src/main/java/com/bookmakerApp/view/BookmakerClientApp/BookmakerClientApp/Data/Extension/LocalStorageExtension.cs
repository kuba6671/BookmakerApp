using Hanssens.Net;
using System;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;
using Quartz.Util;

namespace BookmakerClientApp.Data.Extension
{
    public sealed class LocalStorageExtension
    {
        private static LocalStorage localStorage;

        private static LocalStorageExtension localStorageExtension;

        private LocalStorageExtension()
        {
            if (localStorage == null)
                localStorage = new LocalStorage();
        }

        public static LocalStorageExtension GetInstance()
        {
            if (localStorageExtension == null)
            {
                localStorageExtension = new LocalStorageExtension();
            }
            return localStorageExtension;
        }

        public T Get<T>(string key)
        {
            return (T)localStorage.Get(key);
        }

        public void Store<T>(string key, T value)
        {
            localStorage.Store(key, value);
        }
    }
}
