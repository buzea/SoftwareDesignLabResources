using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using ParkingAppAPI.Models;

namespace ParkingAppAPI.Controllers
{
    [Route("api/Requests")]
    [ApiController]
    public class RequestsController : ControllerBase
    {
        // GET: api/Request
        [HttpGet]
        public IActionResult Get()
        {
            return NotFound();
        }

        // POST: api/Request
        [HttpPut]
        public IActionResult Create(Request request)
        {
            return NotFound();
        }

        // PUT: api/Request/5
        [HttpPost]
        public IActionResult Update()
        {
            return NotFound();
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete]
        public IActionResult Delete(Request request)
        {
            return NotFound();
        }
    }
}
