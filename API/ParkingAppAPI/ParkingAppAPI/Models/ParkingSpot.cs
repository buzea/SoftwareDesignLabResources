using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace ParkingAppAPI.Models
{
    public class ParkingSpot
    {
        [Key]
        public int SpotNumber { get; set; }
        [Required]
        public bool IsFree { get; set; }

        public IList<Request> Requests { get; set; }
        public ParkingLot ParkingLot { get; set; }
    }
}
