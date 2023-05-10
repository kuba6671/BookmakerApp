using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.User
{
    public class UserPasswordDto
    {
        [JsonProperty(PropertyName = "oldPassword")]
        private String oldPassword;
        [JsonProperty(PropertyName = "newPassword")]
        private String newPassword;

        public string OldPassword { get => oldPassword; set => oldPassword = value; }
        public string NewPassword { get => newPassword; set => newPassword = value; }
    }
}
