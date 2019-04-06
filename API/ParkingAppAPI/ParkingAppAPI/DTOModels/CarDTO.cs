using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace ParkingAppAPI.DTOModels
{
    public class CarDTO
    {
        [Key]
        public int VIN { get; set; }
        public string PTI { get; set; }

        public User User { get; set; }
        public IList<Request> Requests { get; set; }
    }
}
