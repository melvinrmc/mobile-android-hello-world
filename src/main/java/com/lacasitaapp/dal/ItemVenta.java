package com.lacasitaapp.dal;

public class ItemVenta{
        private Producto producto;
        private float cantidad;
        
        
        public ItemVenta(Producto p, float c){
            this.producto = p;
            this.cantidad = c;
        }
        
        public Producto getProducto(){
            return producto;
        }
        
        public float getCantidad(){
            return cantidad;
        }
        
        public float getMontoItem(){
            return producto.getPrecio() * cantidad;
        }
    }