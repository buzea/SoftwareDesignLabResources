using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Server.Interfaces
{
    public interface IAdminServices
    {
        IList<ParkingLot> GetParkingLots();
        IList<Request> GetRequests(int parkingLotId);
        void AssignParkingSpot(int requestId, int parkingSpotId);
        void RetractParkingSpot( int parkingSpot);
    }
}
