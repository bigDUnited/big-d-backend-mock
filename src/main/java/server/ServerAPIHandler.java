package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import controller.MockController;
import dtos.LocationDTO;
import java.util.List;
import utilities.HttpServerGeneralUtils;

public class ServerAPIHandler implements HttpHandler {

    private MockController controller;
    private HttpServerGeneralUtils utilities;

    public ServerAPIHandler(MockController controller) {
        this.controller = controller;
        utilities = new HttpServerGeneralUtils();
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        //ALL
        String response = "";
        int status = 200;
        String method = he.getRequestMethod().toUpperCase();
        String path = he.getRequestURI().getPath();
        String[] parts = path.split("/");
        String ipport = he.getRemoteAddress().toString();
        String address = ipport.substring(0, ipport.indexOf(":"));

        //Debug START
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateFormatted = formatter.format(date);
        System.out.println("BackendServerAPIHandler DEBUG: # " + dateFormatted + " # #Request method: " + method + ", Request path : " + path + ", path Length: " + parts.length + " / 2nd elem : " + (parts.length > 0 ? parts[1] : "NO"));
        //Debug END

        //POST, PUT, (DELETE?)
        InputStreamReader isr;
        BufferedReader br;
        String jsonQuery;

        switch (method) {
            case "GET":
                /*
                 * Get all locations
                 * URL : http://localhost:8084/api/getLocations
                 */
                if (parts.length == 3 && parts[2] != null && "getLocations".equals(parts[2])) {
                    List<LocationDTO> stuff = controller.getAllLocations();

                    response = new Gson().toJson(stuff);
                    status = 201;
                    
                } else {
                    status = 500;
                    response = "not supported";
                }
                //else if ( parts.length > 2 && parts[ 2 ] != null && "registerServerId".equals( parts[ 2 ] ) ) {
//
//                    int curServerId = random.nextInt( 10 - 1 ) + 1;
//                    if ( controller.createUserIdentifierObj( address, curServerId, "register" ) ) {
//                        response = new Gson().toJson( curServerId );
//                        status = 201;
//                    }
//                }

                break;
            case "POST":
                //use PUT to create resources, or use POST to update resources.

                isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                br = new BufferedReader(isr);
                jsonQuery = br.readLine();

                System.out.println("Hello " + parts[2] + " " + parts[3]);
                /*
                 * Save Client Identifier 
                 * URL : http://localhost:8084/api/getRoutes
                 * JSON : {"clientRN": 8 }
                 */
                if (parts.length == 4 && parts[2] != null && "getRoutes".equals(parts[2])
                        && parts[3] != null && utilities.isNumeric(parts[3])) {
                    //response = new Gson().toJson( controller.createSongAPI( jsonQuery ) );
                    int locationId = Integer.parseInt(parts[3]) + 1;
                    //if ( controller.addClientId( address, curClientId ) ) {
                    response = new Gson().toJson("Location id is : " + locationId);
                    status = 201;
                    //}
                } else {
                    status = 500;
                    response = "not supported";
                }
                break;
            case "PUT":
                status = 500;
                response = "not supported";
                break;
            case "DELETE":
                status = 500;
                response = "not supported";
                break;
            default:
                status = 500;
                response = "not supported";
                break;
        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(status, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
