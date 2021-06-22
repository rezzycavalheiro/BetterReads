package com.example.betterreads.model;

import java.util.ArrayList;

public class PhotoModel {
    private static PhotoModel instance = new PhotoModel();
    private PhotoModel(){

    }
    public static PhotoModel getInstance(){
        return instance;
    }

    // Singleton. Abaixo criando os itens necessários para a recycler view
    public ArrayList<Pictures> picturesArrayList = new ArrayList<>();
}
