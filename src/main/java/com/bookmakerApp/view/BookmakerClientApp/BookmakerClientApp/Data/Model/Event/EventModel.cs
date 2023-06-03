using System;
using Newtonsoft.Json;

namespace BookmakerClientApp.Data.Model.Event;

public class EventModel
{
    [JsonProperty(PropertyName = "idEvent")]
    private long idEvent;
    private string chosenResult;
    private double odds;
    private DateTime date;
    private bool? success;
    private int numberOfPages;
    
    public long IdEvent { get => idEvent; set => idEvent = value; }
    public string ChosenResult { get => chosenResult; set => chosenResult = value; }
    public double Odds { get => odds; set => odds = value; }
    public DateTime Date { get => date; set => date = value; }
    public bool? Success { get => success; set => success = value; }
    public int NumberOfPages { get => numberOfPages; set => numberOfPages = value; }
}