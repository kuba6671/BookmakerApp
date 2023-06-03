using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Event.Football
{
    public class FootballEventModel : EventModel
    {
        private string footballMatchType;
        private string homeTeamName;
        private string homeTeamCountry;
        private int homeTeamGoals;
        private string visitingTeamName;
        private string visitingTeamCountry;
        private int visitingTeamGoals;

        public string FootballMatchType { get => footballMatchType; set => footballMatchType = value; }
        public string HomeTeamName { get => homeTeamName; set => homeTeamName = value; }
        public string HomeTeamCountry { get => homeTeamCountry; set => homeTeamCountry = value; }
        public int HomeTeamGoals { get => homeTeamGoals; set => homeTeamGoals = value; }
        public string VisitingTeamName { get => visitingTeamName; set => visitingTeamName = value; }
        public string VisitingTeamCountry { get => visitingTeamCountry; set => visitingTeamCountry = value; }
        public int VisitingTeamGoals { get => visitingTeamGoals; set => visitingTeamGoals = value; }
    }
}
