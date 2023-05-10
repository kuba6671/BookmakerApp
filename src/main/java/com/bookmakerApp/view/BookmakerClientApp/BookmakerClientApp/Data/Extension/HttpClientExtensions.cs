using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Extension
{
    public static class HttpClientExtensions
    {
        public static Task<HttpResponseMessage> PostAsJsonAsync<T>(
            this HttpClient httpClient, string url, T data)
        {
            var dataAsString = JsonConvert.SerializeObject(data);
            var content = new StringContent(dataAsString);
            content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
            return httpClient.PostAsync(url, content);
        }

        public static Task<HttpResponseMessage> PutAsJsonAsync<T>(
            this HttpClient httpClient, string url, T data)
        {
            var dataAsString = JsonConvert.SerializeObject(data);
            var content = new StringContent(dataAsString);
            content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
            return httpClient.PutAsync(url, content);
        }

        private static async Task<T> ReadAsJsonAsync<T>(this HttpContent content)
        {
            var dataAsString = await content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<T>(dataAsString);
        }

        public static async Task<T> GetAsJsonAsync<T>(this HttpClient httpClient, string url)
        {
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string statusCode = response.StatusCode.ToString();
            if (statusCode.Equals("OK"))
            {
                HttpContent httpContent = response.Content;
                return await ReadAsJsonAsync<T>(httpContent);
            }
            else
            {
                IList<T> list = new List<T>();
                return (T)list;
            }
        }

        public static async Task<T> GetAsJsonAsyncWithListParameter<T>(this HttpClient httpClient, string url,
            string key, JArray values)
        {
            string queryString = BuildUrl(key, values);
            string newUrl = url + queryString;
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, newUrl);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string statusCode = response.StatusCode.ToString();
            if (statusCode.Equals("OK"))
            {
                HttpContent httpContent = response.Content;
                return await ReadAsJsonAsync<T>(httpContent);
            }
            else
            {
                IList<T> list = new List<T>();
                return (T)list;
            }
        }

        private static string BuildUrl(string key, JArray values)
        {
            NameValueCollection queryString = System.Web.HttpUtility.ParseQueryString(string.Empty);

            foreach (long value in values)
            {
                queryString.Add(key, value.ToString());
            }

            return queryString.ToString() != string.Empty ? "?" + queryString : string.Empty;
        }
    }
}