using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;


namespace Server.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        public string Password { get; set; }

        [Required]
        public bool IsAdmin { get; set; }

        public IList<Car> Cars { get; set; }
    }
}
