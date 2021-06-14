package com.example.lavacarapp.model;

import java.util.ArrayList;

public class CarModel {
    private static CarModel instance = new CarModel();
    private CarModel(){

    }
    public static CarModel getInstance(){
        return instance;
    }

    // Singleton. Abaixo criando os itens necessários para a recycler view
    public ArrayList<CarPictures> carsArray = new ArrayList<>();
}
