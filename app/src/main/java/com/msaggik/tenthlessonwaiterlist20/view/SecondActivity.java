package com.msaggik.tenthlessonwaiterlist20.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.msaggik.tenthlessonwaiterlist20.R;
import com.msaggik.tenthlessonwaiterlist20.model.RestaurantOrder;
import com.msaggik.tenthlessonwaiterlist20.viewmodel.DataBaseHelper;
import com.msaggik.tenthlessonwaiterlist20.viewmodel.RestaurantOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    // поля
    private EditText inputClient, inputItemOrder;
    private Button button, buttonDeleteAll;
    private List<RestaurantOrder> orderList = new ArrayList(); // коллекция для списка заказа
    private RestaurantOrderAdapter adapter; // кастомизированный адаптер
    private RecyclerView recyclerView; // список
    private DataBaseHelper dataBaseHelper; // поле класса работы с БД

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // привязка разметки к полям
        inputClient = findViewById(R.id.inputClient);
        inputItemOrder = findViewById(R.id.inputItemOrder);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.output);
        buttonDeleteAll = findViewById(R.id.buttonDeleteAll);

        // создание объекта работы с БД
        dataBaseHelper = new DataBaseHelper(SecondActivity.this);

        // инициализация списка заказов из БД
        fetchAllOrders(orderList);

        // создадим объект адаптера
        adapter = new RestaurantOrderAdapter(SecondActivity.this, orderList);
        recyclerView.setAdapter(adapter);

        // обработка нажатия кнопки
        button.setOnClickListener(listener);
        buttonDeleteAll.setOnClickListener(v -> {
            dataBaseHelper.deleteAllOrders();

            orderList.clear();
            adapter.notifyDataSetChanged();
        });
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onClick(View view) {
            String inputClientString = inputClient.getText().toString(); // считывание введённых данных
            String inputItemOrderString = inputItemOrder.getText().toString(); // считывание введённых данных
            inputItemOrder.setText(""); // обнуление введённой информации заказа
            // запись данных в базу данных SQLite
            // если введённый текст не пустой, то добавление записи в БД
            if (!TextUtils.isEmpty(inputClientString) && !TextUtils.isEmpty(inputItemOrderString)){
                boolean newClient = true; // флаг нового клиента
                for(RestaurantOrder order: orderList) {
                    if(order.getName().equals(inputClientString)) { // если данный клиент уже обслуживается, то к ранее добавленному заказу добавляется ещё позиция
                        dataBaseHelper.updateOrder(order.getName(), order.getListOrder() + ", " + inputItemOrderString, order.getId());
                        newClient = false;
                    }
                }
                if(newClient) { // если новый клиент, то
                    dataBaseHelper.addOrder(inputClientString, inputItemOrderString); // создание новой записи в БД
                }
            } else { // иначе сообщение о необходимости заполнения обоих полей
                Toast.makeText(getApplicationContext(), "Необходимо заполнить оба поля", Toast.LENGTH_SHORT).show();
            }
            // вывод данных в RecyclerView из базы данных SQLite
            orderList.clear(); // очистка коллекции заказов
            fetchAllOrders(orderList); // загрузка данных из БД
            adapter.notifyDataSetChanged(); // обновление данных адаптера
        }
    };

    // метод считывания из базы данных всех записей
    public void fetchAllOrders(List<RestaurantOrder> restaurantOrders){
        // чтение БД и запись данных в курсор
        Cursor cursor = dataBaseHelper.readOrders();

        if (cursor.getCount() == 0) { // если данные отсутствую, то вывод на экран об этом тоста
            Toast.makeText(this, "Задачи отсутствуют", Toast.LENGTH_SHORT).show();
        } else { // иначе помещение их в контейнер данных restaurantOrders
            while (cursor.moveToNext()){
                // помещение в контейнер restaurantOrders из курсора данных
                restaurantOrders.add(new RestaurantOrder(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}