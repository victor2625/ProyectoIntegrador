package com.serranito.view;

import java.util.ArrayList;

import com.serranito.controller.InventarioController;
import com.serranito.controller.VentaController;
import com.serranito.model.DetalleVenta;
import com.serranito.model.Envase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentaView {
    private InventarioController inventarioController = new InventarioController();
    private VentaController ventaController = new VentaController();
    private ObservableList<Envase> productosDisponibles = FXCollections.observableArrayList();
    private ObservableList<DetalleVenta> carrito = FXCollections.observableArrayList();
    private TableView<DetalleVenta> tablaCarrito = new TableView<>();
    private Label lblTotal = new Label("Total: S/ 0.00");

    public void start(Stage stage) {
        // Tabla de productos disponibles
        TableView<Envase> tablaProductos = new TableView<>();
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
        tablaProductos.getColumns().addAll(colId, colTipo, colCapacidad, colStock, colPrecio);
        productosDisponibles.addAll(inventarioController.listarEnvases());
        tablaProductos.setItems(productosDisponibles);
        tablaProductos.setPrefWidth(500);

        // Carrito
        TableColumn<DetalleVenta, String> colProdCarro = new TableColumn<>("Producto");
        colProdCarro.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEnvase().getTipo()));
        TableColumn<DetalleVenta, Integer> colCantCarro = new TableColumn<>("Cantidad");
        colCantCarro.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        TableColumn<DetalleVenta, Double> colSubCarro = new TableColumn<>("Subtotal");
        colSubCarro.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tablaCarrito.getColumns().addAll(colProdCarro, colCantCarro, colSubCarro);
        tablaCarrito.setItems(carrito);
        tablaCarrito.setPrefWidth(300);

        // Controles
        Spinner<Integer> spinnerCantidad = new Spinner<>(1, 100, 1);
        Button btnAgregar = new Button("Agregar →");
        btnAgregar.setOnAction(e -> {
            Envase seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
            if (seleccionado != null && seleccionado.getStock() >= spinnerCantidad.getValue()) {
                int cant = spinnerCantidad.getValue();
                double subtotal = cant * seleccionado.getPrecioUnidad();
                DetalleVenta detalle = new DetalleVenta(seleccionado, cant, subtotal);
                carrito.add(detalle);
                actualizarTotal();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Stock insuficiente o no seleccionó producto");
                alert.show();
            }
        });

        Button btnEliminar = new Button("← Eliminar");
        btnEliminar.setOnAction(e -> {
            DetalleVenta seleccionado = tablaCarrito.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                carrito.remove(seleccionado);
                actualizarTotal();
            }
        });

        Button btnConfirmar = new Button("✅ Confirmar Venta");
        btnConfirmar.setOnAction(e -> {
            if (carrito.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Carrito vacío");
                alert.show();
                return;
            }
            ventaController.registrarVenta(new ArrayList<>(carrito));
            carrito.clear();
            actualizarTotal();
            // Actualizar inventario
            productosDisponibles.clear();
            productosDisponibles.addAll(inventarioController.listarEnvases());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Venta registrada exitosamente");
            alert.show();
        });

        HBox controles = new HBox(10, new Label("Cantidad:"), spinnerCantidad, btnAgregar, btnEliminar, btnConfirmar);
        controles.setPadding(new Insets(10));

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(controles);
        root.setLeft(new VBox(10, new Label("📦 Productos disponibles:"), tablaProductos));
        root.setRight(new VBox(10, new Label("🛒 Carrito de compras:"), tablaCarrito, lblTotal));
        Scene scene = new Scene(root, 900, 550);
        stage.setScene(scene);
        stage.setTitle("Registro de Ventas - El Serranito del Norte");
        stage.show();
    }

    private void actualizarTotal() {
        double total = carrito.stream().mapToDouble(DetalleVenta::getSubtotal).sum();
        lblTotal.setText(String.format("💰 Total: S/ %.2f", total));
    }
}