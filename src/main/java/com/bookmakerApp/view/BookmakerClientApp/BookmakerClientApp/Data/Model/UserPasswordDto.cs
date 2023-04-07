using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Model
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
