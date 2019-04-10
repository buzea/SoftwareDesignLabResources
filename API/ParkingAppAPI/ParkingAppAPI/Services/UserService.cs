using ParkingAppAPI.DTOModels;
using ParkingAppAPI.Interfaces;
using ParkingAppAPI.Models;
using ParkingAppAPI.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ParkingAppAPI.Services
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
            var user = _parkingDbContext.Users.Single(u => u.Email == credentials.Email);
            if (user != null) return false;
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
            if (_parkingDbContext.Users.Where(u=>u.Email == credentials.Email) != null) return;
            _parkingDbContext.Users.Add(user);
            _parkingDbContext.SaveChanges();
        }
    }
}
