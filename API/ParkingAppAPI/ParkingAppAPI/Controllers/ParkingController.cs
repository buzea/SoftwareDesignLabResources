using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ParkingAppAPI.Controllers
{
    [Route("api/Parking")]
    [ApiController]
    public class ParkingController : ControllerBase
    {
        // GET: api/Parking
        [HttpGet]
        [Route("lots")]
        public IActionResult GetLots()
        {
            return NotFound();
        }

        [HttpGet]
        [Route("lots/{id}")]
        public IActionResult GetLot(int id)
        {
            return NotFound();
        }
   

        // POST: api/Parking
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT: api/Parking/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE: api/ApiWithActions/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
