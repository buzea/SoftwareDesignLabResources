using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Server.DTOModels
{
    public class ParkingLotDTO
    {
        [Key]
        public int ParkingLotId { get; set; }
        public string Address { get; set; }

    }
}
