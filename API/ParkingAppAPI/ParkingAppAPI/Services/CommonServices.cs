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
    public class CommonServices : ICommonServices
    {
        private ParkingDbContext _parkingDbContext;

        public CommonServices(ParkingDbContext parkingDbContext)
        {
            _parkingDbContext = parkingDbContext;
        }
        public void AddCar(UserDTO owner, CarDTO car)
        {
            if (car == null) return;

            var user = _parkingDbContext.Users.Single(u => u.UserId == owner.UserId);
            if (user == null) return;

            var dbCar = _parkingDbContext.Cars.Single(c => c.VIN == car.VIN);
            if (dbCar != null) return;

            var carToAdd = new Car
            {
                PTI = car.PTI,
                VIN = car.VIN,
                //User = car.User,
                Requests = null
            };

            _parkingDbContext.Cars.Add(carToAdd);
            _parkingDbContext.SaveChanges();

        }

        public void AddRequest(UserDTO owner, RequestDTO request)
        {
            if (request == null) return;//The request is empty

            var user = _parkingDbContext.Users.Single(u => u.UserId == request.OwnerId);
            if (user == null) return;//the user does not exist

            var car = _parkingDbContext.Cars.Single(c => c.VIN == request.CarVin);
            if (car == null) return; //car is not in the db
            if (car.User != user) return; //not his car


            var lots = _parkingDbContext.ParkingLots.Where(l => request.ParkingLotsId.Contains(l.ParkingLotId) ).ToList();
            var connections = new List<RequestParkingLot>();

            var requestToAdd = new Request
            {
                Car = car,
                Date = DateTime.UtcNow,
                ParkingLots = null,
                ParkingSpot = null
            };

            foreach (var lot in lots)
            {
                connections.Add(new RequestParkingLot
                {
                    ParkingLot = lot
                });
            }
            //_parkingDbContext.Cars.Add(requestToAdd);
            _parkingDbContext.SaveChanges();

        }

        public void AddRequest(RequestDTO request)
        {
            throw new NotImplementedException();
        }

        public IList<RequestDTO> GetRequests(UserDTO user)
        {
            throw new NotImplementedException();
        }

        public void RemoveCar(UserDTO owner, CarDTO car)
        {
            throw new NotImplementedException();
        }

        public void RemoveRequest(UserDTO owner, RequestDTO request)
        {
            throw new NotImplementedException();
        }

        public void UpdateRequest(UserDTO owner, RequestDTO request)
        {
            throw new NotImplementedException();
        }
    }
}
