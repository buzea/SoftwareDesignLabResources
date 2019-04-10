using Microsoft.VisualStudio.TestTools.UnitTesting;
using Server.DTOModels;
using Server.Repositories;
using Server.Services;
using System;
using System.Linq;

namespace TestServer
{
    [TestClass]
    public class UserTests
    {
        private ParkingDbContext context;
        private UserService userService;

        public UserTests()
        {
            context = new ParkingDbContext();
            userService = new UserService(context);
        }

        [TestMethod]
        public void RegisterTest()
        {
            var credentials = new AuthenticationDTO
            {
                Email = "me@imeil.com",
                Password = "1234"
            };
            userService.Register(credentials);
            var user = context.Users.FirstOrDefault(u => u.Email == credentials.Email);
            Assert.IsNotNull(user);
            context.Users.Remove(user);
        }

        [TestMethod]
        public void LoginTest()
        {
            var credentials = new AuthenticationDTO
            {
                Email = "me@imeil.com",
                Password = "1234"
            };
            userService.Register(credentials);
            Assert.IsTrue(userService.Login(credentials));
            var user = context.Users.FirstOrDefault(u => u.Email == credentials.Email);
            context.Users.Remove(user);
        }
    }
}
