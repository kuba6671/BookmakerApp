using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Event.MMA
{
    public class MMAEventModel : EventModel
    {
        private string firstFighterName;
        private string firstFighterCountry;
        private string firstFighterRecord;
        private string secondFighterName;
        private string secondFighterCountry;
        private string secondFighterRecord;
        private string mmaFightResult;


        public string FirstFighterName { get => firstFighterName; set => firstFighterName = value; }
        public string FirstFighterCountry { get => firstFighterCountry; set => firstFighterCountry = value; }
        public string FirstFighterRecord { get => firstFighterRecord; set => firstFighterRecord = value; }
        public string SecondFighterName { get => secondFighterName; set => secondFighterName = value; }
        public string SecondFighterCountry { get => secondFighterCountry; set => secondFighterCountry = value; }
        public string SecondFighterRecord { get => secondFighterRecord; set => secondFighterRecord = value; }
        public string MmaFightResult { get => mmaFightResult; set => mmaFightResult = value; }
    }
}