using Server.Interfaces;
using Server.Models;
using Server.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Server.Services
{
    public class AdminServices : IAdminServices
    {
        private ParkingDbContext _parkingDbContext;

        public AdminServices(ParkingDbContext parkingDbContext)
        {
            _parkingDbContext = parkingDbContext;
        }

        public void AssignParkingSpot(int requestId, int parkingSpotId)
        {
            var request = _parkingDbContext.Requests.FirstOrDefault(r => r.RequestId == requestId);
            if (request == null) return;

            var spot = _parkingDbContext.ParkingSpots.FirstOrDefault(r => r.SpotNumber == parkingSpotId);
            if (spot == null) return;
            if (!spot.IsFree) return;

            request.ParkingSpot = spot;
            request.Status = Status.Accepted;
            spot.IsFree = false;

            _parkingDbContext.ParkingSpots.Update(spot);
            _parkingDbContext.Requests.Update(request);

            _parkingDbContext.SaveChanges();

        }

        public IList<ParkingLot> GetParkingLots()
        {
            return _parkingDbContext.ParkingLots.ToList();
        }

        public IList<Request> GetRequests(int parkingLotId)
        {
            var connections = _parkingDbContext.RequestParkingLot.Where(rp => rp.ParkingLotId == parkingLotId).ToList();
            var requests = new List<Request>();
            foreach (var item in connections)
            {
                requests.Add(item.Request);
            }

            return requests;
        }

        public void RetractParkingSpot(int parkingSpot)
        { 
            var spot = _parkingDbContext.ParkingSpots.FirstOrDefault(r => r.SpotNumber == parkingSpot);
            if (spot == null) return;
            if (spot.IsFree) return;

            var request = _parkingDbContext.Requests.FirstOrDefault(r => r.ParkingSpot == spot);
            if (request == null) return;

            spot.IsFree = true;
            request.Status = Status.Refused;
            request.ParkingSpot = null;


            _parkingDbContext.ParkingSpots.Update(spot);
            _parkingDbContext.Requests.Update(request);

            _parkingDbContext.SaveChanges();
        }
    }
}
