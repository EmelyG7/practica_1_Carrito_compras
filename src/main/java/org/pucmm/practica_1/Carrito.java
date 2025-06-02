package org.pucmm.practica_1;

import java.util.*;

public class Carrito {
    private final List<ItemCarrito> items;
    
    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        // Buscar si el producto ya existe en el carrito
        ItemCarrito itemExistente = buscarItem(producto);
        if (itemExistente != null) {
            itemExistente.incrementarCantidad(cantidad);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
    }

    public boolean eliminarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        ItemCarrito item = buscarItem(producto);
        if (item != null) {
            items.remove(item);
            return true;
        }
        return false;
    }

    public boolean modificarCantidad(Producto producto, int nuevaCantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La nueva cantidad debe ser mayor a 0");
        }

        ItemCarrito item = buscarItem(producto);
        if (item != null) {
            item.setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    public double calcularTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }

    public int getCantidadTotalProductos() {
        return items.stream()
                .mapToInt(ItemCarrito::getCantidad)
                .sum();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public void vaciarCarrito() {
        items.clear();
    }

    private ItemCarrito buscarItem(Producto producto) {
        return items.stream()
                .filter(item -> item.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public boolean contieneProducto(Producto producto) {
        return buscarItem(producto) != null;
    }

    public int getCantidadProducto(Producto producto) {
        ItemCarrito item = buscarItem(producto);
        return item != null ? item.getCantidad() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrito de Compras:\n");
        if (items.isEmpty()) {
            sb.append("  (Vacío)");
        } else {
            for (ItemCarrito item : items) {
                sb.append(String.format("  - %s\n", item));
            }
            sb.append(String.format("Total: $%.2f", calcularTotal()));
        }
        return sb.toString();
    }
}