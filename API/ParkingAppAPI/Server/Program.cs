using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Server.Repositories;
using System;
using System.Linq;

namespace Server
{
    class Program
    {
        static void Main(string[] args)
        {

            var Controller = new StartupController();
        }
    }
}
