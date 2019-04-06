using ParkingAppAPI.Models;
using System;
using System.Collections.Generic;


namespace ParkingAppAPI.DTOModels
{
    public class RequestDTO
    {
        public int RequestId { get; set; }
        public DateTime Date { get; set; }
        public Status Status { get; set; }
        public int OwnerId { get; set; }
        public int CarVin { get; set; }
        public IList<int> ParkingLotsId { get; set; }
        public int ParkingSpotId { get; set; }

    }
}
