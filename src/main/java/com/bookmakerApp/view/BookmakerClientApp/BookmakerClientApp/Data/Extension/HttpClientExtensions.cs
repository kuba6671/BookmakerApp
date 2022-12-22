using Newtonsoft.Json;
using System.Collections.Generic;
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

        public static async Task<T> ReadAsJsonAsync<T>(this HttpContent content)
        {
            var dataAsString = await content.ReadAsStringAsync();
            return JsonConvert.DeserializeObject<T>(dataAsString);
        }

        public static async Task<T> GetAsJsonAsync<T>(this HttpClient httpClient, string url)
        {
            HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get,
                url);
            HttpResponseMessage response = await httpClient.SendAsync(request);
            string statusCode = response.StatusCode.ToString();
            if (statusCode.Equals("OK"))
            {
                HttpContent httpContent = response.Content;
                return await HttpClientExtensions.ReadAsJsonAsync<T>(httpContent);
            }
            else
            {
                List<TType> list = new List<TType>();
                return emptyList;
            }
        }


    }
}
