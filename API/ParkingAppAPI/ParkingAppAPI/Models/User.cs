using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace ParkingAppAPI.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        [Required]
        [EmailAddress]
        public  string Email { get; set; }
        [Required]
        public string Password { get; set; }
        [Required]
        public Boolean IsAdmin { get; set; }

        public IList<Car> Cars { get; set; } 
    }
}
