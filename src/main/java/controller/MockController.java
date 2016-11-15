package controller;

import com.google.gson.Gson;
import dtos.JourneysDTO;
import dtos.LocationDTO;
import dtos.ReservationSummaryDTO;
import dtos.RouteDTO;
import entity.web.WebReservationHelper;
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

    public ReservationSummaryDTO makeReservation(String jsonQuery) {
        WebReservationHelper wrh = new Gson().fromJson( jsonQuery, WebReservationHelper.class );
        return model.createReservation( wrh );
    }
}
