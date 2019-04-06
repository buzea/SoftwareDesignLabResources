using ParkingAppAPI.DTOModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ParkingAppAPI.Interfaces
{
    public interface ICommonServices
    {
        void AddCar(UserDTO owner, CarDTO car);
        void RemoveCar(UserDTO owner, CarDTO car);
        IList<RequestDTO> GetRequests(UserDTO user);
        void AddRequest(RequestDTO request);
        void UpdateRequest(UserDTO owner, RequestDTO request);
        void RemoveRequest(UserDTO owner, RequestDTO request);
    }
}
