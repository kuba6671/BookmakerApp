using Hanssens.Net;

namespace BookmakerClientApp.Data.Extension
{
    public sealed class LocalStorageExtension
    {
        private static LocalStorage localStorage;

        private static LocalStorageExtension localStorageExtension;

        private LocalStorageExtension()
        {
            localStorage ??= new LocalStorage();
        }

        public static LocalStorageExtension GetInstance()
        {
            return localStorageExtension ??= new LocalStorageExtension();
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
