package controller;

import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.List;
import mock.MockModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marek
 */
public class MockControllerTest {

    private static MockController controllerInstance;

    public MockControllerTest() {
        controllerInstance = MockController.getInstance();
    }

    /**
     * Test of getInstance method, of class MockController.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        MockController expResult = controllerInstance;
        MockController result = MockController.getInstance();

        assertEquals(expResult, result);
    }

    /**
     * Test of getAllLocations method, of class MockController.
     */
    @Test
    public void testGetAllLocations() {
        System.out.println("getAllLocations");
        List<LocationDTO> expResult = MockModel.getLocations();
        List<LocationDTO> result = controllerInstance.getLocations();

        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getRoutes method, of class MockController.
     */
    @Test
    public void testGetRoutes() {
        System.out.println("getRoutes");
        Integer locationId = 1;
        List<RouteDTO> expResult = MockModel.getRouteByLocationId(locationId);
        List<RouteDTO> result = controllerInstance.getRoutes(locationId);

        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getJourney method, of class MockController.
     */
    @Test
    public void testGetJourney() {
        System.out.println("getJourney");
        Integer routeId = 1;
        JourneysDTO expResult = new MockModel().getJourneysByRouteId(routeId);
        JourneysDTO result = controllerInstance.getJourney(routeId);

        assertEquals(expResult.getDepartureLocation(), result.getDepartureLocation());
        assertEquals(expResult.getDestinationLocation(), result.getDestinationLocation());
        assertEquals(expResult.getJourneysList().size(), result.getJourneysList().size());
    }

    /**
     * Test of makeReservation method, of class MockController.
     */
    @Test
    public void testMakeReservation() {
        System.out.println("makeReservation");
        String jsonQuery = "{journeyId:1,numberOfPeople:2,vehicleType:car}";
        int journeyId = 1;
        int numOfPeople = 2;
        String vehicleType = "car";
        ReservationSummaryDTO expResult = new MockModel().createReservation(journeyId, numOfPeople, vehicleType);
        ReservationSummaryDTO result = controllerInstance.makeReservation(journeyId, numOfPeople, vehicleType);
        System.out.println("arrival" + expResult.getArrivalDate() + " arrival res" + result.getArrivalDate());
        System.out.println("departure" + expResult.getDepartureDate() + " departure res" + result.getDepartureDate());

//        arrival date and departure date are random so they will not be equal 100% of the time
//        assertEquals(expResult.getArrivalDate(), result.getArrivalDate());
//        assertEquals(expResult.getDepartureDate(), result.getDepartureDate());
//        assertEquals(expResult.getReferenceNumber(), result.getReferenceNumber());
        assertEquals(expResult.getDepartureLocation(), result.getDepartureLocation());
        assertEquals(expResult.getDestinationLocation(), result.getDestinationLocation());
        assertEquals(expResult.getFerryName(), result.getFerryName());
        assertEquals(expResult.getNumberOfPeople(), result.getNumberOfPeople());
        assertEquals(expResult.getVehicleType(), result.getVehicleType());
    }

}
