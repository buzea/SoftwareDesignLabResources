
using System.ComponentModel.DataAnnotations;

namespace ParkingAppAPI.Models
{
    public class RequestParkingLot
    {
        public int ParkingLotId { get; set; }
        public int RequestId { get; set; }
        public ParkingLot ParkingLot { get; set;}
        public Request Request { get; set; }
    }
}
