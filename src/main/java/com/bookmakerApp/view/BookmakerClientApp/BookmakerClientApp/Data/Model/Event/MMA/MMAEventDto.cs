using System;

namespace BookmakerClientApp.Data.Model.Event.MMA
{
    public class MMAEventDto
    {
        private long firstFighterWinId;
        private long secondFighterWinId;
        private string firstFighterName;
        private string secondFighterName;
        private DateTime date;
        private string sportName = "MMA";
        private double firstFighterWinOdds;
        private double secondFighterWinOdds;
        private string mmaFightResult;
        private int numberOfPages;
        
        public long FirstFighterWinId { get => firstFighterWinId; set => firstFighterWinId = value; }
        public long SecondFighterWinId { get => secondFighterWinId; set => secondFighterWinId = value; }
        public string FirstFighterName { get => firstFighterName; set => firstFighterName = value; }
        public string SecondFighterName { get => secondFighterName; set => secondFighterName = value; }
        public DateTime Date { get => date; set => date = value; }
        public string SportName { get => sportName; set => sportName = value; }
        public double FirstFighterWinOdds { get => firstFighterWinOdds; set => firstFighterWinOdds = value; }
        public double SecondFighterWinOdds { get => secondFighterWinOdds; set => secondFighterWinOdds = value; }
        public string MmaFightResult { get => mmaFightResult; set => mmaFightResult = value; }
        public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
    }    
}
