/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Find the users name.
        EditText name = findViewById(R.id.name_edit_text_view);
        Editable customer = name.getText();

        // Figure out if the user wants whipped cream topping
        CheckBox isWhippedCream = findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = isWhippedCream.isChecked();

        // figure out if the user wants chocolate topping
        CheckBox chocolate = findViewById(R.id.chocolate_checkBox);
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        CharSequence body = createOrderSummary(price, hasWhippedCream, hasChocolate, customer);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping.
     * @param addChocolate is whether or not the user wants chocolate topping.
     * @return total price.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 cup of coffee.
        int basePrice = 5;

        // Add $1 if the user wants whipped cream.
        if (addWhippedCream) {
            basePrice += 1;
        }

        // Add $2 if the user wants chocolate.
        if (addChocolate) {
            basePrice += 2;
        }

        //Calculate the total order price by multiplying by quantity.
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param price of the order.
     * @param hasWhippedCream is whether or not the user wants whipped cream topping.
     * @return order summary.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, Editable customer) {
        String orderSummary = "Name: " + customer;
        orderSummary += "\nAdd whipped cream? " + hasWhippedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity:" + quantity;
        orderSummary += "\nTotal: $" + price;
        orderSummary += "\nThank you!";
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
     * This method increments the quantity on the screen.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast.
            Toast.makeText(this,"You cannot have more than 100 coffees.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method decrements the quantity on the screen.
     */
    public void decrement(View view) {
        if (quantity == 1){
            // Show an error message as a toast.
            Toast.makeText(this, "You cannot have less than 1 coffee.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
}
