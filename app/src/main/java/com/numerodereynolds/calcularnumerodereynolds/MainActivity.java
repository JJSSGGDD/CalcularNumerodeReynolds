package com.numerodereynolds.calcularnumerodereynolds;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText densidaddelfluido, diametrodelatuberia, velocidaddelfluido, viscosidaddinamica;
    private Button calcularnumerodereynolds, eliminardatos;
    private TextView numerodereynolds, tipodeflujo;
    private Spinner spinner;

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
        spinner = findViewById(R.id.spinner1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.diametrotuberia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    //haciendo las operaciones
    public void operar(View view) {

        String densidaddelfluido_string = densidaddelfluido.getText().toString();
        String diametrodelatuberia_string = diametrodelatuberia.getText().toString();
        String velocidaddelfluido_string = velocidaddelfluido.getText().toString();
        String viscosidaddinamica_string = viscosidaddinamica.getText().toString();

        if (TextUtils.isEmpty(densidaddelfluido_string) || TextUtils.isEmpty(diametrodelatuberia_string)  || TextUtils.isEmpty(velocidaddelfluido_string) || TextUtils.isEmpty(viscosidaddinamica_string)) {
            Toast.makeText(this, "Porfavor, ingrese toda la informacion", Toast.LENGTH_SHORT).show();
        } else {
            double ndensidaddelfluido = Double.parseDouble(densidaddelfluido.getText().toString());
            double ndiametrodelatuberia = Double.parseDouble(diametrodelatuberia.getText().toString());
            double nvelocidaddelfluido = Double.parseDouble(velocidaddelfluido.getText().toString());
            double nviscosidaddinamica = Double.parseDouble(viscosidaddinamica.getText().toString());
            double resultadonumerodereynolds = ((ndensidaddelfluido * ndiametrodelatuberia * nvelocidaddelfluido) / (nviscosidaddinamica));

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

    public void borrar(View view){
        densidaddelfluido.setText("");
        diametrodelatuberia.setText("");
        velocidaddelfluido.setText("");
        viscosidaddinamica.setText("");
        numerodereynolds.setText("  Numero de Reynolds :");
        tipodeflujo.setText("  Tipo de Flujo :");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}