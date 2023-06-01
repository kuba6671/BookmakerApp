using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Event.MMA
{
    public class MMAEventModel
    {
        [JsonProperty(PropertyName = "idEvent")]
        private long idEvent;
        private double odds;
        private DateTime date;
        private bool? success;
        private string chosenResult;
        private string firstFighterName;
        private string firstFighterCountry;
        private string firstFighterRecord;
        private string secondFighterName;
        private string secondFighterCountry;
        private string secondFighterRecord;
        private string mmaFightResult;
        private int numberOfPages;
        
        public long IdEvent { get => idEvent; set => idEvent = value; }
        public double Odds { get => odds; set => odds = value; }
        public DateTime Date { get => date; set => date = value; }
        public bool? Success { get => success; set => success = value; }
        public string ChosenResult { get => chosenResult; set => chosenResult = value; }
        public string FirstFighterName { get => firstFighterName; set => firstFighterName = value; }
        public string FirstFighterCountry { get => firstFighterCountry; set => firstFighterCountry = value; }
        public string FirstFighterRecord { get => firstFighterRecord; set => firstFighterRecord = value; }
        public string SecondFighterName { get => secondFighterName; set => secondFighterName = value; }
        public string SecondFighterCountry { get => secondFighterCountry; set => secondFighterCountry = value; }
        public string SecondFighterRecord { get => secondFighterRecord; set => secondFighterRecord = value; }
        public string MmaFightResult { get => mmaFightResult; set => mmaFightResult = value; }
        public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
    }
}