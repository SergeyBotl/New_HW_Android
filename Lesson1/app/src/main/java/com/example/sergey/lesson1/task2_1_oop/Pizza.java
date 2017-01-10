package com.example.sergey.lesson1.task2_1_oop;

public class Pizza {

    private String name;
    private float weight;
    private Thickness thickness;

    public Pizza(String name, float weight, Thickness thickness) {
        this.name = name;
        this.weight = weight;
        this.thickness = thickness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Thickness getThickness() {
        return thickness;
    }

    public void setThickness(Thickness thickness) {
        this.thickness = thickness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (Float.compare(pizza.weight, weight) != 0) return false;
        if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
        return thickness == pizza.thickness;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + (thickness != null ? thickness.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Название: %s, Вес: %s,  Толщина: %s  ", name, weight, thickness);

    }
}
