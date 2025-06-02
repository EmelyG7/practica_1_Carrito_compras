package org.pucmm.practica_1;


// Clase Producto
public class Producto {
    private String nombre;
    private double precio;
    private String descripcion;

    public Producto(String nombre, double precio, String descripcion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion != null ? descripcion : "";
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion : "";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return Double.compare(producto.precio, precio) == 0 &&
                nombre.equals(producto.nombre) &&
                descripcion.equals(producto.descripcion);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode() + Double.hashCode(precio);
    }

    @Override
    public String toString() {
        return String.format("Producto{nombre='%s', precio=%.2f, descripcion='%s'}",
                nombre, precio, descripcion);
    }
}