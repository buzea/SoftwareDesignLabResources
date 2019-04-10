using Server.DTOModels;
using Server.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Server.Interfaces
{
    public interface ICommonServices
    {
        void AddCar( CarDTO car);
        void RemoveCar(CarDTO car);
        IList<Request> GetRequests(UserDTO user);
        void AddRequest(RequestDTO request);
        void UpdateRequest(RequestDTO request);
        void RemoveRequest(RequestDTO request);
    }
}
