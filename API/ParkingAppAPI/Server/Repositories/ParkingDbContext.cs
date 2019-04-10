using Microsoft.EntityFrameworkCore;
using Server.Models;

namespace Server.Repositories
{
    public class ParkingDbContext : DbContext
    {
        private string _connectionString;

        public ParkingDbContext()
        {
            _connectionString = "Server=localhost;DataBase=ParkingAppDb;Uid=dev;Pwd=mysql";
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .Property(u => u.IsAdmin)
                .HasConversion(v => v ? 1 : 0, v => v != 0);

            modelBuilder.Entity<ParkingSpot>()
                .Property(p => p.IsFree)
                .HasConversion(v => v ? 1 : 0, v => v != 0);

            modelBuilder.Entity<Car>()
                .HasOne(c => c.User)
                .WithMany(u => u.Cars)
                .IsRequired();

            modelBuilder.Entity<Car>()
                .HasMany(c => c.Requests)
                .WithOne(r => r.Car)
                .IsRequired();

            modelBuilder.Entity<Request>()
                .HasOne(c => c.ParkingSpot)
                .WithMany(s => s.Requests)
                .IsRequired(false);

            modelBuilder.Entity<ParkingLot>()
                .HasMany(l => l.ParkingSpots)
                .WithOne(s => s.ParkingLot)
                .IsRequired();

            modelBuilder.Entity<RequestParkingLot>()
                .HasKey(rp => new { rp.ParkingLotId, rp.RequestId });

            modelBuilder.Entity<RequestParkingLot>()
                .HasOne(rp => rp.ParkingLot)
                .WithMany(p => p.Requests)
                .IsRequired();

            modelBuilder.Entity<RequestParkingLot>()
                .HasOne(rp => rp.Request)
                .WithMany(r => r.ParkingLots)
                .IsRequired();

        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySQL(_connectionString);
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Car> Cars { get; set; }
        public DbSet<Request> Requests { get; set; }
        public DbSet<ParkingLot> ParkingLots { get; set; }
        public DbSet<ParkingSpot> ParkingSpots { get; set; }
        public DbSet<RequestParkingLot> RequestParkingLot { get; set; }
    }
}
