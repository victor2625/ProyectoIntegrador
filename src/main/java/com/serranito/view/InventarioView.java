package com.serranito.view;

import com.serranito.controller.InventarioController;
import com.serranito.model.Envase;
import com.serranito.view.VentaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventarioView {
    private InventarioController controller = new InventarioController();
    private TableView<Envase> table = new TableView<>();
    private ObservableList<Envase> data = FXCollections.observableArrayList();

    public void start(Stage stage) {
    // Columnas
    TableColumn<Envase, Integer> colId = new TableColumn<>("ID");
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    TableColumn<Envase, String> colTipo = new TableColumn<>("Tipo");
    colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    TableColumn<Envase, String> colCapacidad = new TableColumn<>("Capacidad");
    colCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
    TableColumn<Envase, Integer> colStock = new TableColumn<>("Stock");
    colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    TableColumn<Envase, Double> colPrecio = new TableColumn<>("Precio S/.");
    colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));

    table.getColumns().addAll(colId, colTipo, colCapacidad, colStock, colPrecio);
    table.setItems(data);

    // Crear botones
    Button btnRefresh = new Button("Actualizar");
    Button btnAgregar = new Button("Agregar");
    Button btnEliminar = new Button("Eliminar");
    Button btnVentas = new Button("Registrar Venta");

    // Acciones
    btnRefresh.setOnAction(e -> cargarDatos());
    btnAgregar.setOnAction(e -> mostrarFormularioAgregar());
    btnEliminar.setOnAction(e -> {
        Envase selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.eliminarEnvase(selected.getId());
            cargarDatos();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione un envase");
            alert.show();
        }
    });
    btnVentas.setOnAction(e -> {
        VentaView ventaView = new VentaView();
        ventaView.start(new Stage());
    });

    // Barra de herramientas (toolbar) con todos los botones
    HBox toolbar = new HBox(10, btnRefresh, btnAgregar, btnEliminar, btnVentas);
    toolbar.setPadding(new Insets(10));

    BorderPane root = new BorderPane();
    root.setTop(toolbar);
    root.setCenter(table);

    Scene scene = new Scene(root, 800, 500);
    stage.setScene(scene);
    stage.setTitle("Gestión de Inventario - El Serranito del Norte");
    stage.show();

    cargarDatos();
    }

    private void cargarDatos() {
        data.clear();
        data.addAll(controller.listarEnvases());
    }

    private void mostrarFormularioAgregar() {
        Stage formStage = new Stage();
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        TextField txtTipo = new TextField();
        TextField txtCapacidad = new TextField();
        TextField txtMaterial = new TextField();
        TextField txtStock = new TextField();
        TextField txtPrecio = new TextField();

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            try {
                String tipo = txtTipo.getText();
                String capacidad = txtCapacidad.getText();
                String material = txtMaterial.getText();
                int stock = Integer.parseInt(txtStock.getText());
                double precio = Double.parseDouble(txtPrecio.getText());
                Envase nuevo = new Envase(tipo, capacidad, material, stock, precio);
                controller.agregarEnvase(nuevo);
                cargarDatos();
                formStage.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Stock y precio deben ser números");
                alert.show();
            }
        });

        form.getChildren().addAll(
                new Label("Tipo:"), txtTipo,
                new Label("Capacidad:"), txtCapacidad,
                new Label("Material:"), txtMaterial,
                new Label("Stock:"), txtStock,
                new Label("Precio unidad:"), txtPrecio,
                btnGuardar
        );
        Scene scene = new Scene(form, 300, 400);
        formStage.setScene(scene);
        formStage.setTitle("Agregar Envase");
        formStage.show();
    }
}