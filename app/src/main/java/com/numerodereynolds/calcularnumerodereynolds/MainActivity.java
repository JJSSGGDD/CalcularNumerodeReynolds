package com.numerodereynolds.calcularnumerodereynolds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText densidaddelfluido, diametrodelatuberia, velocidaddelfluido, velocidaddinamica;
    private Button calcularnumerodereynolds, eliminardatos;
    private TextView numerodereynolds, tipodeflujo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cuando ingresa los datos de usuario
        densidaddelfluido = findViewById(R.id.txtdensidaddelfluido);
        diametrodelatuberia = findViewById(R.id.txtdiametrodelatuberia);
        velocidaddelfluido = findViewById(R.id.txtvelocidaddelfluido);
        velocidaddinamica = findViewById(R.id.txtvelocidaddinamica);
        calcularnumerodereynolds = findViewById(R.id.btncalcularnumerodereynolds);
        eliminardatos = findViewById(R.id.btneliminardatos);
        numerodereynolds = findViewById(R.id.txtnumerodereynolds);
        tipodeflujo = findViewById(R.id.txttipodeflujo);
    }

    //haciendo las operaciones
    public void operar(View view) {
        double ndensidaddelfluido = Double.parseDouble(densidaddelfluido.getText().toString());
        double ndiametrodelatuberia = Double.parseDouble(diametrodelatuberia.getText().toString());
        double nvelocidaddelfluido = Double.parseDouble(velocidaddelfluido.getText().toString());
        double nvelocidaddinamica = Double.parseDouble(velocidaddinamica.getText().toString());
        double resultadonumerodereynolds = ((ndensidaddelfluido * ndiametrodelatuberia * nvelocidaddelfluido) / (nvelocidaddinamica));

        //mostrar el resultado
        numerodereynolds.setText("  Numero de Reynolds : " + String.format("%.2f", resultadonumerodereynolds)); //"%2.f" = para solo dos decimales

        if (resultadonumerodereynolds <= 2300 )
            tipodeflujo.setText("  Tipo de Flujo : Laminar");
        else if (resultadonumerodereynolds > 2300 && resultadonumerodereynolds <= 4000)  //&& = funcion logica "y"
            tipodeflujo.setText("  Tipo de Flujo : de Transicion");
        else if (resultadonumerodereynolds > 4000)
            tipodeflujo.setText("  Tipo de Flujo : Turbulento");
    }

    public void borrar(View view){
        densidaddelfluido.setText("");
        diametrodelatuberia.setText("");
        velocidaddelfluido.setText("");
        velocidaddinamica.setText("");
        numerodereynolds.setText("  Numero de Reynolds :");
        tipodeflujo.setText("  Tipo de Flujo :");
    }
}