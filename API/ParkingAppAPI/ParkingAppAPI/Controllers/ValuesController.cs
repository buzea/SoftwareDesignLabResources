using System;
using System.Collections.Generic;
using ParkingAppAPI.Models;
using Microsoft.AspNetCore.Mvc;
using ParkingAppAPI.Interfaces;
using ParkingAppAPI.Repositories;

namespace ParkingAppAPI.Controllers
{
    [Route("api/[Controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        ParkingDbContext _userRepository;

        public ValuesController(ParkingDbContext userRepository)
        {
            _userRepository = userRepository;
        }

        // GET api/values
        [HttpGet]
        public IActionResult Get()
        {
            _userRepository.Users.Add(new User {
                Email = "me@imeil.com",
                Password = "1234",
                IsAdmin = true,
                Cars = null
            });
            _userRepository.SaveChanges();

            var users = _userRepository.Users;
            return Ok(users);
        }

        // GET api/values/5
        [HttpGet("{id}")]
        public ActionResult<string> Get(int id)
        {
            return "value";
        }

        // POST api/values
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
