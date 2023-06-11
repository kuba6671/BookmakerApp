using System;

namespace BookmakerClientApp.Data.Model.Event.Football
{
    public class FootballEventDto
    {
        private long firstTeamWinEventId;
        private long secondTeamWinEventId;
        private long draftEventId;
        private string firstTeamName;
        private String secondTeamName;
        private string sportName = "Piłka nożna";
        private DateTime date;
        private double firstTeamWinOdds;
        private double secondTeamWinOdds;
        private double draftOdds;
        private int homeTeamGoals;
        private int visitingTeamGoals;
        private int numberOfPages;
        

        public long FirstTeamWinEventId { get => firstTeamWinEventId; set => firstTeamWinEventId = value; }
        public long SecondTeamWinEventId { get => secondTeamWinEventId; set => secondTeamWinEventId = value; }
        public long DraftEventId { get => draftEventId; set => draftEventId = value; }
        public string FirstTeamName { get => firstTeamName; set => firstTeamName = value; }
        public string SecondTeamName { get => secondTeamName; set => secondTeamName = value; }
        public string SportName { get => sportName; set => sportName = value; }
        public DateTime Date { get => date; set => date = value; }
        public double FirstTeamWinOdds { get => firstTeamWinOdds; set => firstTeamWinOdds = value; }
        public double SecondTeamWinOdds { get => secondTeamWinOdds; set => secondTeamWinOdds = value; }
        public double DraftOdds { get => draftOdds; set => draftOdds = value; }
        public int HomeTeamGoals { get => homeTeamGoals; set => homeTeamGoals = value; }
        public int VisitingTeamGoals { get => visitingTeamGoals; set => visitingTeamGoals = value; }
        public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
    }
}
