using Server.DTOModels;
using Server.Interfaces;
using Server.Models;
using Server.Repositories;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Server.Services
{
    public class CommonServices : ICommonServices
    {
        private ParkingDbContext _parkingDbContext;

        public CommonServices(ParkingDbContext parkingDbContext)
        {
            _parkingDbContext = parkingDbContext;
        }
        public void AddCar( CarDTO car)
        {
            if (car == null) return;

            var user = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == car.OwnerId);
            if (user == null) return;

            var dbCar = _parkingDbContext.Cars.FirstOrDefault(c => c.VIN == car.VIN);
            if (dbCar != null) return;

            var carToAdd = new Car
            {
                PTI = car.PTI,
                VIN = car.VIN,
                User = user,
                Requests = null
            };

            _parkingDbContext.Cars.Add(carToAdd);
            _parkingDbContext.SaveChanges();

        }

        public void AddRequest(RequestDTO request)
        {
            if (request == null) return;//The request is empty

            var user = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == request.OwnerId);
            if (user == null) return;//the user does not exist

            var car = _parkingDbContext.Cars.FirstOrDefault(c => c.VIN == request.CarVin);
            if (car == null) return; //car is not in the db
            if (car.User != user) return; //not his car


            var lots = _parkingDbContext.ParkingLots.Where(l => request.ParkingLotsId.Contains(l.ParkingLotId) ).ToList();
            var date = DateTime.UtcNow;

            var requestToAdd = new Request
            {
                Car = car,
                Date = date,
                Status = Status.Pending,
                ParkingLots = null,
                ParkingSpot = null
            };
            _parkingDbContext.Requests.Add(requestToAdd);
            _parkingDbContext.SaveChanges();

            var dbRequest = _parkingDbContext.Requests.FirstOrDefault(r => (r.Car == car) && (r.Date == date));
            foreach (var lot in lots)
            {
                _parkingDbContext.RequestParkingLot.Add(new RequestParkingLot
                {
                    ParkingLot = lot,
                    Request = dbRequest
                });
            }

            _parkingDbContext.SaveChanges();

        }

        public IList<Request> GetRequests(UserDTO user)
        {
            var dbUser = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == user.UserId);
            if (dbUser == null) return null;//the user does not exist

            var cars = _parkingDbContext.Cars.Where(c => c.User == dbUser);
            if (cars == null) return null; //car is not in the db

            return _parkingDbContext.Requests.Where(r => cars.Contains(r.Car)).ToList();
        }

        public void RemoveCar( CarDTO car)
        {
            var dbUser = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == car.OwnerId);
            if (dbUser == null) return ;//the user does not exist

            var dbCar = _parkingDbContext.Cars.FirstOrDefault(c => c.User == dbUser);
            if (dbCar == null) return; //car is not in the db

            if ((dbCar.User != dbUser)&&(!dbUser.IsAdmin)) return;

            _parkingDbContext.Cars.Remove(dbCar);
            _parkingDbContext.SaveChanges();
        }

        public void RemoveRequest( RequestDTO request)
        {
            var dbUser = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == request.OwnerId);
            if (dbUser == null) return;//the user does not exist

            var car = _parkingDbContext.Cars.FirstOrDefault(c => c.VIN == request.CarVin);
            if (car == null) return; //car is not in the db

            if ((car.User != dbUser) && (!dbUser.IsAdmin)) return;

            var dbRequest = _parkingDbContext.Requests.FirstOrDefault(r => r.Car == car);
            if (dbRequest == null) return;

            _parkingDbContext.Requests.Remove(dbRequest);
            _parkingDbContext.SaveChanges();
        }

        public void UpdateRequest(RequestDTO request)
        {
            var dbUser = _parkingDbContext.Users.FirstOrDefault(u => u.UserId == request.OwnerId);
            if (dbUser == null) return;//the user does not exist

            var car = _parkingDbContext.Cars.FirstOrDefault(c => c.VIN == request.CarVin);
            if (car == null) return; //car is not in the db

            if ((car.User != dbUser) && (!dbUser.IsAdmin)) return;

            var dbRequest = _parkingDbContext.Requests.FirstOrDefault(r => r.Car == car);
            if (dbRequest == null) return;

            _parkingDbContext.Requests.Remove(dbRequest);
            var lots = _parkingDbContext.ParkingLots.Where(l => request.ParkingLotsId.Contains(l.ParkingLotId)).ToList();

            foreach (var lot in lots)
            {
                _parkingDbContext.RequestParkingLot.Add(new RequestParkingLot
                {
                    ParkingLotId = lot.ParkingLotId,
                    RequestId = dbRequest.RequestId
                });
            }

            _parkingDbContext.SaveChanges();
        }
    }
}
