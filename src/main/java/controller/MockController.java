package controller;

import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.RouteDTO;
import java.util.List;
import mock.MockModel;

public class MockController {

    private static MockController instance = null;
    private MockModel model;

    private MockController() {
        // Exists only to defeat instantiation.
        model = new MockModel();
    }

    public static MockController getInstance() {
        if (instance == null) {
            instance = new MockController();
        }
        return instance;
    }

    public List<LocationDTO> getAllLocations() {
        return model.getLocations();
    }

    public List<RouteDTO> getRoutes(String locationId) {
        return model.getRouteByLocationId(locationId);
    }

    public JourneysDTO getJourney(String routeId) {
        return model.getJourneysByRouteId(routeId);
    }
}
