package controller;

import dtos.LocationDTO;
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
}
