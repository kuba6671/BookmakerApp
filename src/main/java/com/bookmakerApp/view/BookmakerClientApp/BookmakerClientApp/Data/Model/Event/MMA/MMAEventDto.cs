using System;

namespace BookmakerClientApp.Data.Model.Event.MMA
{
    public class MMAEventDto
    {
        private long firstFighterWinId;
        private long secondFighterWinId;
        private string fightersNames;
        private DateTime date;
        private string sportName = "MMA";
        private double firstFigherWinOdds;
        private double secondFighterWinOdds;
        private string mmaFightResult;
        private int numberOfPages;
        
        public long FirstFighterWinId { get => firstFighterWinId; set => firstFighterWinId = value; }
        public long SecondFighterWinId { get => secondFighterWinId; set => secondFighterWinId = value; }
        public string FightersNames { get => fightersNames; set => fightersNames = value; }
        public DateTime Date { get => date; set => date = value; }
        public string SportName { get => sportName; set => sportName = value; }
        public double FirstFigherWinOdds { get => firstFigherWinOdds; set => firstFigherWinOdds = value; }
        public double SecondFighterWinOdds { get => secondFighterWinOdds; set => secondFighterWinOdds = value; }
        public string MmaFightResult { get => mmaFightResult; set => mmaFightResult = value; }
        public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
    }    
}
