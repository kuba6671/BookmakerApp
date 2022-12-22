using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace BookmakerClientApp.Data.Model
{
    public class FootballEventModelDto
    {
        private String footballMatchType { get; set; }
        private String homeTeamName { get; set; }
        private String homeTeamCountry { get; set; }
        private int homeTeamGoals { get; set; }
        private String visitingTeamName { get; set; }
        private String visitingTeamCountry { get; set; }
        private int visitingTeamGoals { get; set; }
    }
}
