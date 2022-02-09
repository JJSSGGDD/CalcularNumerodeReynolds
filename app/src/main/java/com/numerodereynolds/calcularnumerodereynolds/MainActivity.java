package com.numerodereynolds.calcularnumerodereynolds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText densidaddelfluido, diametrodelatuberia, velocidaddelfluido, viscosidaddinamica;
    private Button calcularnumerodereynolds, eliminardatos;
    private TextView numerodereynolds, tipodeflujo;
    private Spinner spinner_densidaddelfluido, spinner_diametrodelatuberia, spinner_velocidaddelfluido, spinner_viscosidaddinamica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cuando ingresa los datos de usuario
        densidaddelfluido = findViewById(R.id.txtdensidaddelfluido);
        diametrodelatuberia = findViewById(R.id.txtdiametrodelatuberia);
        velocidaddelfluido = findViewById(R.id.txtvelocidaddelfluido);
        viscosidaddinamica = findViewById(R.id.txtviscosidaddinamica);
        calcularnumerodereynolds = findViewById(R.id.btncalcularnumerodereynolds);
        eliminardatos = findViewById(R.id.btneliminardatos);
        numerodereynolds = findViewById(R.id.txtnumerodereynolds);
        tipodeflujo = findViewById(R.id.txttipodeflujo);

        spinner_densidaddelfluido = (Spinner) findViewById(R.id.spinner1);
        spinner_diametrodelatuberia = (Spinner) findViewById(R.id.spinner2);
        spinner_velocidaddelfluido = (Spinner) findViewById(R.id.spinner3);
        spinner_viscosidaddinamica = (Spinner) findViewById(R.id.spinner4);

        String[] spinner_1 = {"[kg/m^3]", "[lbm/ft^3]", "[lbm/in^3]", };//Arreglo unidimensional
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_1 ); //sirve para poder comunicar el arreglo con la parte grafica, los dos tipos de spinner cambian muy poco, solo en el espacio
        spinner_densidaddelfluido.setAdapter(adapter);

        String[] spinner_2 = {"[centipoise]", "[Pa-s]", "[lbm/s-ft]", };//Arreglo unidimensional
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_2 ); //sirve para poder comunicar el arreglo con la parte grafica, los dos tipos de spinner cambian muy poco, solo en el espacio
        spinner_diametrodelatuberia.setAdapter(adapter1);

        String[] spinner_3 = {"[m]", "[cm]", "[ft]", "[in]"};//Arreglo unidimensional
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_3 ); //sirve para poder comunicar el arreglo con la parte grafica, los dos tipos de spinner cambian muy poco, solo en el espacio
        spinner_velocidaddelfluido.setAdapter(adapter2);

        String[] spinner_4 = {"[m/s]", "[cm/s]", "[ft/s]","[in/s]" };//Arreglo unidimensional
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_4 ); //sirve para poder comunicar el arreglo con la parte grafica, los dos tipos de spinner cambian muy poco, solo en el espacio
        spinner_viscosidaddinamica.setAdapter(adapter3);

    }

    //haciendo las operaciones
    public void operar(View view) {

        String densidaddelfluido_string = densidaddelfluido.getText().toString();
        String diametrodelatuberia_string = diametrodelatuberia.getText().toString();
        String velocidaddelfluido_string = velocidaddelfluido.getText().toString();
        String viscosidaddinamica_string = viscosidaddinamica.getText().toString();

        if (TextUtils.isEmpty(densidaddelfluido_string) || TextUtils.isEmpty(diametrodelatuberia_string) || TextUtils.isEmpty(velocidaddelfluido_string) || TextUtils.isEmpty(viscosidaddinamica_string)) {
            Toast.makeText(this, "Porfavor, ingrese toda la informacion", Toast.LENGTH_SHORT).show();
        } else {

            String seleccion1 = spinner_densidaddelfluido.getSelectedItem().toString();
            String seleccion2 = spinner_diametrodelatuberia.getSelectedItem().toString();
            String seleccion3 = spinner_velocidaddelfluido.getSelectedItem().toString();
            String seleccion4 = spinner_viscosidaddinamica.getSelectedItem().toString();

            double ndensidaddelfluido = Double.parseDouble(densidaddelfluido.getText().toString());
            double ndiametrodelatuberia = Double.parseDouble(diametrodelatuberia.getText().toString());
            double nvelocidaddelfluido = Double.parseDouble(velocidaddelfluido.getText().toString());
            double nvelocidaddinamica = Double.parseDouble(viscosidaddinamica.getText().toString());

            if (nvelocidaddinamica == 0) {
                Toast.makeText(this, "La viscosidad dinamica debe ser diferente de cero", Toast.LENGTH_SHORT).show();
            } else {

                if (seleccion1.equals("[lbm/ft^3]")) {
                    ndensidaddelfluido = ndensidaddelfluido * 16.0185;
                }

                double resultadonumerodereynolds = ((ndensidaddelfluido * ndiametrodelatuberia * nvelocidaddelfluido) / (nvelocidaddinamica));

                //mostrar el resultado
                numerodereynolds.setText("  Numero de Reynolds : " + String.format("%.2f", resultadonumerodereynolds)); //"%2.f" = para solo dos decimales

                if (resultadonumerodereynolds <= 2300)
                    tipodeflujo.setText("  Tipo de Flujo : Laminar");
                else if (resultadonumerodereynolds > 2300 && resultadonumerodereynolds <= 4000)  //&& = funcion logica "y"
                    tipodeflujo.setText("  Tipo de Flujo : de Transicion");
                else if (resultadonumerodereynolds > 4000)
                    tipodeflujo.setText("  Tipo de Flujo : Turbulento");
            }
        }
    }

    public void borrar(View view){

        densidaddelfluido.setText("");
        diametrodelatuberia.setText("");
        velocidaddelfluido.setText("");
        viscosidaddinamica.setText("");
        numerodereynolds.setText("  Numero de Reynolds :");
        tipodeflujo.setText("  Tipo de Flujo :");

        Toast.makeText(this, "Datos eliminados", Toast.LENGTH_SHORT).show();
    }
}