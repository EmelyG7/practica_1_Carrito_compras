package org.pucmm.practica_1;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    // Setters
    public void setProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.cantidad = cantidad;
    }


    public void incrementarCantidad(int incremento) {
        if (incremento <= 0) {
            throw new IllegalArgumentException("El incremento debe ser mayor a 0");
        }
        this.cantidad += incremento;
    }

    public void decrementarCantidad(int decremento) {
        if (decremento <= 0) {
            throw new IllegalArgumentException("El decremento debe ser mayor a 0");
        }
        if (decremento >= cantidad) {
            throw new IllegalArgumentException("El decremento no puede ser mayor o igual a la cantidad actual");
        }
        this.cantidad -= decremento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemCarrito item = (ItemCarrito) obj;
        return cantidad == item.cantidad && producto.equals(item.producto);
    }

    @Override
    public int hashCode() {
        return producto.hashCode() + Integer.hashCode(cantidad);
    }

    @Override
    public String toString() {
        return String.format("ItemCarrito{producto=%s, cantidad=%d, subtotal=%.2f}",
                producto, cantidad, getSubtotal());
    }
}