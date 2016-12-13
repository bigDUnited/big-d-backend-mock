package controller;

import contractinterface.ContractInterface;
import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.List;
import mock.MockModel;

public class MockController implements ContractInterface {

    private static MockController instance = null;
    private MockModel mockModel;

    public static MockController getInstance() {
        if (instance == null) {
            instance = new MockController();
        }
        return instance;
    }

    public MockController() {
        mockModel = new MockModel();
    }

    @Override
    public List<LocationDTO> getLocations() {
        return MockModel.getLocations();
    }

    @Override
    public List<RouteDTO> getRoutes(int locationId) {
        return MockModel.getRouteByLocationId(locationId);
    }

    @Override
    public JourneysDTO getJourney(int routeId) {
        return mockModel.getJourneysByRouteId(routeId);
    }

    @Override
    public ReservationSummaryDTO makeReservation(int journeyId, int numOfPeople, String vehicleType) {
        return mockModel.createReservation(journeyId, numOfPeople, vehicleType);
    }

    @Override
    public ReservationSummaryDTO getReservation(int reservationId) {
        return mockModel.getReservationById(reservationId);
    }

    @Override
    public List<ReservationSummaryDTO> getAllReservations() {
        return mockModel.getReservations();
    }
}
