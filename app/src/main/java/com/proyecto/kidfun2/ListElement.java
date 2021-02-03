package com.proyecto.kidfun2;

public class ListElement {
    public String color;
    public String actividad;
    public String Descripcion;
    public String select;
    private String photoURL;

    public ListElement(String color, String actividad, String descripcion, String select, String photoURL) {
        this.color = color;
        this.actividad = actividad;
        this.Descripcion = descripcion;
        this.select = select;
        this.photoURL = photoURL;
     }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }
}
