using Server.Interfaces;
using Server.Repositories;
using Server.Services;
using System;
using System.Collections.Generic;
using System.Text;

namespace Server
{
    class StartupController
    {
        private readonly ParkingDbContext Context;
        private readonly AdminServices _adminServices;
        private readonly CommonServices _commonServices;
        private readonly UserService _userService;

        public StartupController()
        {
            Context = new ParkingDbContext();
            _adminServices = new AdminServices(Context);
            _commonServices = new CommonServices(Context);
            _userService = new UserService(Context);
        }

    }
}
