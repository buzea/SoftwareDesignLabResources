using System;
using System.Collections.Generic;


namespace ParkingAppAPI.Models
{
    public class Request
    {
        public int RequestId { get; set; }
        public DateTime Date { get; set; }
        public Status Status { get; set; }

        public Car Car { get; set; }
        public IList<RequestParkingLot> ParkingLots { get; set; }
        public ParkingSpot ParkingSpot { get; set; }
    }
}
