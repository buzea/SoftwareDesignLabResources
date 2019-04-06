using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace ParkingAppAPI.Models
{
    public class Car
    {
        [Key]
        public int VIN { get; set; }
        public string PTI { get; set; }

        public User User { get; set; }
        public IList<Request> Requests { get; set; }
    }
}
