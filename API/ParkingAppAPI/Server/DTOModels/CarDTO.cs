using Server.Models;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace Server.DTOModels
{
    public class CarDTO
    {
        [Key]
        public int VIN { get; set; }
        public string PTI { get; set; }

        public int OwnerId { get; set; }
        public IList<Request> Requests { get; set; }
    }
}
