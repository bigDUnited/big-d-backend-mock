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
        List<LocationDTO> result = controllerInstance.getAllLocations();
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
        List<RouteDTO> result = controllerInstance.getRoutes(locationId.toString());
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getJourney method, of class MockController.
     */
    @Test
    public void testGetJourney() {
        System.out.println("getJourney");
        Integer routeId = 1;
        JourneysDTO expResult = MockModel.getJourneysByRouteId(routeId);
        JourneysDTO result = controllerInstance.getJourney(routeId.toString());
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
        String jsonQuery = "{not really working}";
        ReservationSummaryDTO expResult = MockModel.createReservation(jsonQuery);
        ReservationSummaryDTO result = controllerInstance.makeReservation(jsonQuery);        
        assertEquals(expResult.toString(), result.toString());
    }

}
