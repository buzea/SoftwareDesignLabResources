using Microsoft.VisualStudio.TestTools.UnitTesting;
using Server.DTOModels;
using Server.Repositories;
using Server.Services;
using System.Linq;

namespace TestServer
{
    [TestClass]
    class CommonServiceTests
    {
        private ParkingDbContext context;
        private CommonServices commonService;

        public CommonServiceTests()
        {
            context = new ParkingDbContext();
            commonService = new CommonServices(context);
        }

        [TestMethod]
        public void AddCarTest()
        {
            var carToAdd = new CarDTO
            {
                OwnerId = 1,
                PTI = "it works",
                VIN = 1938128312,
            };
            commonService.AddCar(carToAdd);
            var dbCar = context.Cars.FirstOrDefault(c => c.VIN  == carToAdd.VIN);
            Assert.IsNotNull(dbCar);
            context.Cars.Remove(dbCar);
            context.SaveChanges();
        }

        [TestMethod]
        public void RemoveCarTest()
        {
            var car = new CarDTO
            {
                OwnerId = 1,
                PTI = "it works",
                VIN = 1938128312,
            };
            commonService.AddCar(car);
            commonService.RemoveCar(car);
            var dbCar = context.Cars.FirstOrDefault(c => c.VIN == car.VIN);
            Assert.IsNull(dbCar);

        }
        
        [TestMethod]
        public void AddRequestTest()
        {
            var car = new CarDTO
            {
                OwnerId = 1,
                PTI = "it works",
                VIN = 1938128312,
            };
            commonService.AddCar(car);

            var request = new RequestDTO
            {
                OwnerId = 1,
                CarVin = car.VIN,
                ParkingLotsId = { 1, 2, 3, 4 }
            };
            commonService.AddRequest(request);

            var dbRequest = context.Requests.FirstOrDefault(r => r.Car.VIN == car.VIN);
            var dbCar = context.Cars.FirstOrDefault(c => c.VIN == car.VIN);

            Assert.IsNotNull(dbRequest);
            Assert.AreEqual(request.OwnerId, dbRequest.Car.User.UserId);
            Assert.AreEqual(context.ParkingLots.Where(lot => request.ParkingLotsId.Contains(lot.ParkingLotId)),dbRequest.ParkingLots);
            context.Requests.Remove(dbRequest);
            context.Cars.Remove(dbCar);
        }
    }
}
