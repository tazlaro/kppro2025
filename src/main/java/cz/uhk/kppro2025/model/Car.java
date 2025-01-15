package cz.uhk.kppro2025.model;

import jakarta.validation.constraints.*;

public class Car {

    @NotEmpty(message = "SPZ is required")
    private String spz;

    @NotEmpty(message = "Color is required")
    private String color;

    @Min(value = 1)
    @Max(value = 5)
    private float tankVolume;

    @Min(value = 2)
    @Max(value = 9)
    private int numberOfSeats;

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(float tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
