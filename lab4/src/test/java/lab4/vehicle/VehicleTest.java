package lab4.vehicle;

import lab4.person.Firefighter;
import lab4.person.Human;
import lab4.person.Policeman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    @Test
    void taxiAcceptsAnyPassengers() {
        Taxi taxi = new Taxi();

        taxi.board(new Human("Анна"));
        taxi.board(new Firefighter("Іван"));
        taxi.board(new Policeman("Петро"));

        assertEquals(3, taxi.getOccupiedSeats());
    }

    @Test
    void busAcceptsAnyPassengers() {
        Bus bus = new Bus();

        bus.board(new Human("Анна"));
        bus.board(new Firefighter("Іван"));
        bus.board(new Policeman("Петро"));

        assertEquals(3, bus.getOccupiedSeats());
        assertEquals(Bus.DEFAULT_SEATS, bus.getMaxSeats());
    }

    @Test
    void fireTruckAcceptsFirefighters() {
        FireTruck truck = new FireTruck();

        truck.board(new Firefighter("Іван"));
        truck.board(new Firefighter("Сергій"));

        assertEquals(2, truck.getOccupiedSeats());
    }

    @Test
    void policeCarAcceptsPolicemen() {
        PoliceCar car = new PoliceCar();

        car.board(new Policeman("Петро"));
        car.board(new Policeman("Олег"));

        assertEquals(2, car.getOccupiedSeats());
    }

    @Test
    void reportsMaxAndOccupiedSeats() {
        Taxi taxi = new Taxi(3);
        assertEquals(3, taxi.getMaxSeats());
        assertEquals(0, taxi.getOccupiedSeats());

        taxi.board(new Human("Анна"));
        taxi.board(new Human("Марія"));

        assertEquals(3, taxi.getMaxSeats());
        assertEquals(2, taxi.getOccupiedSeats());
    }

    @Test
    void boardingFullVehicleThrows() {
        Taxi taxi = new Taxi(1);
        taxi.board(new Human("Анна"));

        assertThrows(VehicleFullException.class, () -> taxi.board(new Human("Марія")));
        assertEquals(1, taxi.getOccupiedSeats());
    }

    @Test
    void disembarkingFreesSeat() {
        Taxi taxi = new Taxi(1);
        Human anna = new Human("Анна");
        taxi.board(anna);

        taxi.disembark(anna);

        assertEquals(0, taxi.getOccupiedSeats());
        taxi.board(new Human("Марія"));
        assertEquals(1, taxi.getOccupiedSeats());
    }

    @Test
    void disembarkingRemovesOnlyThatPassenger() {
        Bus bus = new Bus();
        Human anna = new Human("Анна");
        Human maria = new Human("Марія");
        bus.board(anna);
        bus.board(maria);

        bus.disembark(anna);

        assertEquals(1, bus.getOccupiedSeats());
        assertTrue(bus.getPassengers().contains(maria));
    }

    @Test
    void disembarkingAbsentPassengerThrows() {
        PoliceCar car = new PoliceCar();
        Policeman outsider = new Policeman("Петро");

        assertThrows(PassengerNotFoundException.class, () -> car.disembark(outsider));
    }

    @Test
    void disembarkingTwiceThrows() {
        FireTruck truck = new FireTruck();
        Firefighter ivan = new Firefighter("Іван");
        truck.board(ivan);
        truck.disembark(ivan);

        assertThrows(PassengerNotFoundException.class, () -> truck.disembark(ivan));
    }

    @Test
    void samePassengerCannotBoardTwice() {
        Taxi taxi = new Taxi();
        Human anna = new Human("Анна");
        taxi.board(anna);

        assertThrows(IllegalStateException.class, () -> taxi.board(anna));
        assertEquals(1, taxi.getOccupiedSeats());
    }

    @Test
    void seatCountMustBePositive() {
        assertThrows(IllegalArgumentException.class, () -> new Taxi(0));
        assertThrows(IllegalArgumentException.class, () -> new Bus(-5));
    }

    @Test
    void passengerListIsReadOnly() {
        Taxi taxi = new Taxi();

        assertThrows(UnsupportedOperationException.class,
                () -> taxi.getPassengers().add(new Human("Анна")));
    }
}
