package com.isaac.jogodo21.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isaac.jogodo21.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private CardView cardView;

    private EditText editNomePlay1,editNomePlay2;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.cardView_nomes);


    }

    public void validar(View view){
        switch (view.getId()){
            case R.id.btn2Player:
                abrirDialog();
                break;
            case R.id.btnSolo:
                startActivity(new Intent(getApplicationContext(),SoloActivity.class));
                break;
        }

    }



    private void abrirDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View viewAlert = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_alert_dalog,cardView);
        alert.setCancelable(true);

        alert.setView(viewAlert);
        configAlertDiolog(viewAlert);

        alertDialog = alert.create();
        alertDialog.show();
    }

    private void configAlertDiolog(View view ){
        btnSalvar = view.findViewById(R.id.btnSalvar);
        editNomePlay1 =view. findViewById(R.id.editNomeplay1);
        editNomePlay2 = view.findViewById(R.id.editNomePlay2);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomePlay1 = "";
                String nomePlay2 = "";

                try {
                    if (!editNomePlay1.getText().toString().isEmpty()){
                        nomePlay1 = editNomePlay1.getText().toString();
                    }else{
                        nomePlay1 = "Play 1";
                    }
                    if (!editNomePlay2.getText().toString().isEmpty()){
                        nomePlay2 = editNomePlay2.getText().toString();
                    }else{
                        nomePlay2 = "Play 2";
                    }
                    Intent intent = new Intent(getApplicationContext(),DoisPlayerActivity.class);

                    intent.putExtra("play1",nomePlay1);
                    intent.putExtra("play2",nomePlay2);

                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Formato de nome inválido", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}