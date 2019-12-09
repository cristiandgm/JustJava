/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox isWhippedCream = findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = isWhippedCream.isChecked();
        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream));

    }

    /**
     * Calculates the price of the order.
     *
     * @return total price.
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order.
     * @param hasWhippedCream is whether or not the user wants whipped cream topping.
     * @return order summary.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream) {
        String orderSummary = "Name:Kaptain Kunal";
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nQuantity:" + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank you";
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView= (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method increments the quantity on the screen.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method decrements the quantity on the screen.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }
}
