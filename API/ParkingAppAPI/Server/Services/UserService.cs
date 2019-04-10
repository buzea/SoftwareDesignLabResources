using Server.DTOModels;
using Server.Interfaces;
using Server.Models;
using Server.Repositories;
using System.Linq;

namespace Server.Services
{
    public class UserService : IUserService
    {
        private ParkingDbContext _parkingDbContext;

        public UserService(ParkingDbContext parkingDbContext)
        {
            _parkingDbContext = parkingDbContext;
        }

        public bool Login(AuthenticationDTO credentials)
        {
            var user = _parkingDbContext.Users.FirstOrDefault(u => u.Email == credentials.Email);
            if (user == null) return false;
            if (user.Password == credentials.Password) return true;

            return false;
        }

        public void Register(AuthenticationDTO credentials)
        {
            var user = new User
            {
                Email = credentials.Email,
                Password = credentials.Password,
                IsAdmin = false
            };
            var dbUSer = _parkingDbContext.Users.FirstOrDefault(u => u.Email == credentials.Email);
            if (dbUSer != null) return;

            _parkingDbContext.Users.Add(user);
            _parkingDbContext.SaveChanges();
        }
    }
}
