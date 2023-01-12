﻿using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Model
{
    public class BetTicketDto
    {
        private bool? finish;
        private bool? success;
        [JsonProperty(PropertyName = "deposit")]
        private double deposit;
        private Decimal toWin;
        private Double totalOdds;
        private DateTime date;
        [JsonProperty(PropertyName = "events")]
        private List<FootballEventModel> events;
        [JsonProperty(PropertyName = "user")]
        private UserModel user;


        public double Deposit { get => deposit; set => deposit = value; }
        public decimal ToWin { get => toWin; set => toWin = value; }
        public double TotalOdds { get => totalOdds; set => totalOdds = value; }
        public DateTime Date { get => date; set => date = value; }
        public List<FootballEventModel> Events { get => events; set => events = value; }
        public bool? Finish { get => finish; set => finish = value; }
        public bool? Success { get => success; set => success = value; }
        public UserModel User { get => user; set => user = value; }
    }
}