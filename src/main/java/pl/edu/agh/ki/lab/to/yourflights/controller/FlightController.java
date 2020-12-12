package pl.edu.agh.ki.lab.to.yourflights.controller;

import java.io.IOException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.edu.agh.ki.lab.to.yourflights.model.Customer;
import pl.edu.agh.ki.lab.to.yourflights.model.Flight;
import pl.edu.agh.ki.lab.to.yourflights.repository.FlightRepository;
import pl.edu.agh.ki.lab.to.yourflights.service.FlightService;
import pl.edu.agh.ki.lab.to.yourflights.utils.ResourceNotFoundException;




@RestController
@RequestMapping("/api/v1")
public class FlightController {

    private final Resource mainView;
    private final Resource customersView;
    private final Resource airlinesView;
    private final Resource reservationListView;

    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Kontekst aplikacji Springa
     */
    private final ApplicationContext applicationContext;

    /**
     * Kolumny tabeli
     */
    @FXML
    private TreeTableColumn<Customer, String> departure;
    @FXML
    private TreeTableColumn<Customer, String> destination;
    @FXML
    private TreeTableColumn<Customer, String> departureTime;
    @FXML
    private TreeTableColumn<Customer, String> arrivalTime;

    /**
     * Metoda która wczytuje dane do tabeli przwoźników
     */
    public void setModel() {
        //Ustawienie kolumn
        departure.setCellValueFactory(data -> data.getValue().getValue().getFirstNameProperty());
        destination.setCellValueFactory(data -> data.getValue().getValue().getSecondNameProperty());
        departureTime.setCellValueFactory(data -> data.getValue().getValue().getCountryProperty());
        arrivalTime.setCellValueFactory(data -> data.getValue().getValue().getCityProperty());

        //Pobranie klientów z serwisu
        //ObservableList<Customer> customers = customerService.getMockData();

        //Przekazanie danych do tabeli
        //final TreeItem<Customer> root = new RecursiveTreeItem<>(customers, RecursiveTreeObject::getChildren);
        //customersTableView.setRoot(root);
        //customersTableView.setShowRoot(false);
    }
    public FlightController(FlightService flightService, ApplicationContext applicationContext,
                            @Value("classpath:/view/AirlinesView.fxml") Resource airlinesView,
                            @Value("classpath:/view/CustomersView.fxml") Resource customersView,
                            @Value("classpath:/view/MainView.fxml") Resource mainView,
                            @Value("classpath:/view/ReservationListView.fxml") Resource reservationListView) {
        this.applicationContext = applicationContext;
        this.airlinesView = airlinesView;
        this.customersView = customersView;
        this.mainView = mainView;
        this.flightService = flightService;
        this.reservationListView = reservationListView;
    }
    /**
     * Metoda wywoływana po inicjalizacji widoku
     */
    @FXML
    public void initialize() {
        this.setModel();
    }

    public void showMainView(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(mainView.getURL());
            fxmlloader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlloader.load();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAirlinesView(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(airlinesView.getURL());
            fxmlloader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlloader.load();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metoda służąca do przejścia do widoku tabeli klientów
     * @param actionEvent event emitowany przez przycisk
     */
    public void showCustomersView(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(customersView.getURL());
            fxmlloader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlloader.load();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReservation(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(reservationListView.getURL());
            fxmlloader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlloader.load();
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}