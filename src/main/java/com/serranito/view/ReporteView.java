package com.serranito.view;

import com.serranito.controller.VentaController;
import com.serranito.model.Venta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReporteView {
    private VentaController ventaController = new VentaController();
    private ObservableList<Venta> ventas = FXCollections.observableArrayList();

    public void start(Stage stage) {
        TableView<Venta> tabla = new TableView<>();
        TableColumn<Venta, Integer> colId = new TableColumn<>("ID Venta");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Venta, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
        TableColumn<Venta, Double> colTotal = new TableColumn<>("Total S/.");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalVenta"));

        tabla.getColumns().addAll(colId, colFecha, colTotal);
        ventas.addAll(ventaController.listarVentas());
        tabla.setItems(ventas);

        Button btnRefresh = new Button("Actualizar");
        btnRefresh.setOnAction(e -> {
            ventas.clear();
            ventas.addAll(ventaController.listarVentas());
        });

        BorderPane root = new BorderPane();
        root.setTop(btnRefresh);
        root.setCenter(tabla);
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Reporte de Ventas");
        stage.show();
    }
}
