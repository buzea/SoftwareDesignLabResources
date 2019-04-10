using Server.DTOModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Server.Interfaces
{
    public interface IUserService
    {
        void Register(AuthenticationDTO credentials);
        bool Login(AuthenticationDTO credentials);
    }
}
