package com.company.pellet;

public class SelectedProduct {

    String id;
    String date;
    String productName;
    String wrapping;
    String fr;
    String destination;
    String distance;
    String oneKmCost;
    String weight;
    String buyPrice;
    String margin;
    String expenses;

    SelectedProduct(String _id, String _date, String _productName, String _wrapping, String _fr, String _destination,
                    String _distance, String _oneKmCost, String _weight, String _buyPrice, String _margin,
                    String _expenses) {
        id = _id;
        date = _date;
        productName = _productName;
        wrapping = _wrapping;
        fr = _fr;
        destination = _destination;
        distance = _distance;
        oneKmCost = _oneKmCost;
        weight = _weight;
        buyPrice = _buyPrice;
        margin = _margin;
        expenses = _expenses;
    }
}