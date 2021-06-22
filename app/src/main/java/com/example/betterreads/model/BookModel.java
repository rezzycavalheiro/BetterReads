package com.example.betterreads.model;

import java.util.ArrayList;

public class BookModel {
    private static BookModel instance = new BookModel();

    private BookModel(){
    }
    public static BookModel getInstance(){
        return instance;
    }
    // Singleton. Abaixo criando os itens necessários para a recycler view
    public ArrayList<BookInfo> bookArray = new ArrayList<>();
}
