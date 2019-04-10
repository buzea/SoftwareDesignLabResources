using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Server.Models
{
    public class ParkingLot
    {
        [Key]
        public int ParkingLotId { get; set; }
        public string Address { get; set; }

        public IList<ParkingSpot> ParkingSpots { get; set; }
        public IList<RequestParkingLot> Requests { get; set; }
    }
}
