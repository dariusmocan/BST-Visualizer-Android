package com.example.proiect3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BSTVisualizer";
    private BST bst;
    private BSTView bstView;
    private EditText inputKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bst = new BST();
        bstView = findViewById(R.id.bstView);
        inputKey = findViewById(R.id.inputKey);
        Button insertButton = findViewById(R.id.insertButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button clearButton = findViewById(R.id.clearButton);
        Button helpButton = findViewById(R.id.helpButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyText = inputKey.getText().toString();
                if (!TextUtils.isEmpty(keyText)) {
                    try {
                        int key = Integer.parseInt(keyText);

                        if (bst.contains(key)){
                            showAlert("Please insert another value!");
                    }else {
                            Log.d(TAG, "Inserting key: " + key);
                            bst.insert(key);
                            bstView.setBST(bst);
                            inputKey.setText("");
                        }

                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Invalid input", e);
                    }
                }else{
                    showAlert("Please insert a number!");
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyText = inputKey.getText().toString();
                if (!TextUtils.isEmpty(keyText)) {
                    try {
                        int key = Integer.parseInt(keyText);
                        if (bst.contains(key)) {
                            Log.d(TAG, "Deleting key: " + key);
                            bst.delete(key);
                            bstView.setBST(bst);
                            inputKey.setText("");
                        } else {
                            showAlert("The node does not exist!");
                        }
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Invalid input", e);
                    }
                }else{
                    showAlert("Please insert a number!");
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bst = new BST();
                bstView.setBST(bst);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
