package com.msaggik.tenthlessonwaiterlist20.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.msaggik.tenthlessonwaiterlist20.R;
import com.msaggik.tenthlessonwaiterlist20.model.RestaurantOrder;

import java.util.List;

public class RestaurantOrderAdapter extends RecyclerView.Adapter<RestaurantOrderAdapter.ViewHolder> {

    // поля адаптера
    private final LayoutInflater inflater; // поле для трансформации layout-файла во View-элемент
    private final List<RestaurantOrder> restaurantOrders; // поле коллекции контейнера для хранения данных (объектов класса RestaurantOrder)

    // конструктор адаптера
    public RestaurantOrderAdapter(Context context, List<RestaurantOrder> restaurantOrders) {
        this.inflater = LayoutInflater.from(context);
        this.restaurantOrders = restaurantOrders;
    }

    // метод onCreateViewHolder() возвращает объект ViewHolder(), который будет хранить данные по одному объекту RestaurantOrder
    @Override
    public RestaurantOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_order, parent, false); // трансформация layout-файла во View-элемент
        return new ViewHolder(view);
    }

    // метод onBindViewHolder() выполняет привязку объекта ViewHolder к объекту RestaurantOrder по определенной позиции
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RestaurantOrderAdapter.ViewHolder holder, int position) {
        RestaurantOrder restaurantOrder = restaurantOrders.get(position);
        holder.nameClient.setText(restaurantOrder.getName());
        holder.listOrder.setText(restaurantOrder.getListOrder());
    }

    // метод getItemCount() возвращает количество объектов в списке
    @Override
    public int getItemCount() {
        return restaurantOrders.size();
    }

    // созданный статический класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // неизменяемые поля представления
        final ConstraintLayout orderLayout;
        final TextView nameClient, listOrder;

        // конструктор класса ViewHolder с помощью которого мы связываем поля и представление item_order.xml
        ViewHolder(View view) {
            super(view);
            orderLayout = view.findViewById(R.id.order_layout);
            nameClient = view.findViewById(R.id.name_client);
            listOrder = view.findViewById(R.id.list_order);
        }
    }
}
