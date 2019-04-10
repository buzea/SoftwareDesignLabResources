using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingAppAPI.DTOModels;
using ParkingAppAPI.Interfaces;

namespace ParkingAppAPI.Controllers
{
    [Route("api/authentication")]
    [ApiController]
    public class AuthenticationController : ControllerBase
    {
        private IUserService _userService;

        public AuthenticationController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpPost]
        [Route("login")]
        public IActionResult Login(AuthenticationDTO credentials)
        {
            try
            {
                if (_userService.Login(credentials)) return Ok();
                return NotFound("Username or password incorrect");
            }
            catch (Exception e)
            {
                return StatusCode(500, e.Message);
            }
        }

        [HttpPost]
        [Route("register")]
        public IActionResult Register(AuthenticationDTO credentials)
        {
            try
            {
                _userService.Register(credentials);
                return Ok();
            }
            catch (Exception e)
            {
               return StatusCode(500,e.Message);
            }
        }

        [HttpDelete]
        public IActionResult Delete(string user)
        {
            return NotFound();
        }
    }
}
