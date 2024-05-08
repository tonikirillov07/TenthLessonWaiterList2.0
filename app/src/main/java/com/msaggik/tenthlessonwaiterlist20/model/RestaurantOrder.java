package com.msaggik.tenthlessonwaiterlist20.model;

public class RestaurantOrder {

    // поля
    private String id; // идентификатор записи в базе данных
    private String name; // имя клиента
    private String listOrder; // наименование списка заказа еды и напитков одного клиента

    // конструктор
    public RestaurantOrder(String id, String name, String listOrder) {
        this.id = id;
        this.name = name;
        this.listOrder = listOrder;
    }

    // геттеры и сеттеры
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getListOrder() {
        return listOrder;
    }
    public void setListOrder(String listOrder) {
        this.listOrder = listOrder;
    }
}
