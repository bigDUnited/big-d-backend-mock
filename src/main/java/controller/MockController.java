package controller;

import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import java.util.ArrayList;
import java.util.List;
import mock.MockModel;
import utilities.HttpServerGeneralUtils;

public class MockController {

    private static MockController instance = null;

    public static MockController getInstance() {
        if (instance == null) {
            instance = new MockController();
        }
        return instance;
    }

    public List<LocationDTO> getAllLocations() {
        return MockModel.getLocations();
    }

    public List<RouteDTO> getRoutes(String locationId) {
        if (HttpServerGeneralUtils.isNumeric(locationId)) {
            return MockModel.getRouteByLocationId(Integer.parseInt(locationId));
        }
        return new ArrayList();
    }

    public JourneysDTO getJourney(String routeId) {
        if (HttpServerGeneralUtils.isNumeric(routeId)) {
            return MockModel.getJourneysByRouteId(Integer.parseInt(routeId));
        }
        return null;
    }

    public ReservationSummaryDTO makeReservation(String jsonQuery) {
        return MockModel.createReservation(jsonQuery);
    }
}
