package controller;

import com.google.gson.Gson;
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
    private MockModel model;
    private HttpServerGeneralUtils utils;

    private MockController() {
        // Exists only to defeat instantiation.
        model = new MockModel();
        utils = new HttpServerGeneralUtils();
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
        if (utils.isNumeric(locationId)) {
            return model.getRouteByLocationId(Integer.parseInt(locationId));
        }
        return new ArrayList();
    }

    public JourneysDTO getJourney(String routeId) {
        if (utils.isNumeric(routeId)) {
            return model.getJourneysByRouteId(Integer.parseInt(routeId));
        }
        return null;
    }

    public ReservationSummaryDTO makeReservation(String jsonQuery) {
        return model.createReservation(jsonQuery);
    }
}
