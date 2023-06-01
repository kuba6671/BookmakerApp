using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Event.Football
{
    public class FootballEventModel
    {
        [JsonProperty(PropertyName = "idEvent")]
        private long idEvent;
        private String footballMatchType;
        private String homeTeamName;
        private String homeTeamCountry;
        private int homeTeamGoals;
        private String visitingTeamName;
        private String visitingTeamCountry;
        private int visitingTeamGoals;
        private String chosenResult;
        private double odds;
        private DateTime date;
        private bool? success;
        private int numberOfPages;

        public long IdEvent { get => idEvent; set => idEvent = value; }
        public string FootballMatchType { get => footballMatchType; set => footballMatchType = value; }
        public string HomeTeamName { get => homeTeamName; set => homeTeamName = value; }
        public string HomeTeamCountry { get => homeTeamCountry; set => homeTeamCountry = value; }
        public int HomeTeamGoals { get => homeTeamGoals; set => homeTeamGoals = value; }
        public string VisitingTeamName { get => visitingTeamName; set => visitingTeamName = value; }
        public string VisitingTeamCountry { get => visitingTeamCountry; set => visitingTeamCountry = value; }
        public int VisitingTeamGoals { get => visitingTeamGoals; set => visitingTeamGoals = value; }
        public string ChosenResult { get => chosenResult; set => chosenResult = value; }
        public double Odds { get => odds; set => odds = value; }
        public DateTime Date { get => date; set => date = value; }
        public bool? Success { get => success; set => success = value; }
        public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
    }
}
