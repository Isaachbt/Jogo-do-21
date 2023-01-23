package com.isaac.jogodo21.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.isaac.jogodo21.R;
import com.isaac.jogodo21.random.RandomClass;

public class DoisPlayerActivity extends AppCompatActivity {

    private Button btnParaPlay1,btnParaPlay2,btnMasCartaPlay1,btnMasCartaPlay2,btnSairPosJogo;
    private ImageButton btnVoltar;
    private TextView txtNomePlay1,txtNomePlay2,txtNumeroGeradoPlay1,txtNumeroGeradoPlay2,txtTotalPlay1,txtTotalPlay2;
    private LinearLayout corPlay2;
    private LinearLayoutCompat corPlay1;
    private int valorTotalPlay1 = 0;
    private int play1Parou = 0;

    private int min = 2,max = 10;

    private int play2Parou = 0;
    private int valorTotalPlay2 = 0;
    private  RandomClass rnd = new RandomClass();

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dois_player);
        
        findVIew();

        recuperandoNomes();
        desabilitarBtns(2);

        btns();
    }


    private void recuperandoNomes(){
        Bundle dados = getIntent().getExtras();
        String nomePlay1 = dados.getString("play1");
        String nomePlay2 = dados.getString("play2");

        txtNomePlay1.setVisibility(View.VISIBLE);
        txtNomePlay2.setVisibility(View.VISIBLE);

        txtNomePlay1.setText(nomePlay1);
        txtNomePlay2.setText(nomePlay2);

    }

    public void findVIew(){

        btnParaPlay1 = findViewById(R.id.btnParaPlay1);
        btnMasCartaPlay1 = findViewById(R.id.btnMasCartaPlay1);

        btnParaPlay2 = findViewById(R.id.btnParaCartaPlay2);
        btnMasCartaPlay2 = findViewById(R.id.btnMasCartaPlay2);

        txtNomePlay1 = findViewById(R.id.txtNomePlay1);
        txtNomePlay2 = findViewById(R.id.txtNomePlay2);

        txtNumeroGeradoPlay1 = findViewById(R.id.txtNGeradoPlay1);
        txtNumeroGeradoPlay2 = findViewById(R.id.txtNGeradoPlay2);

        txtTotalPlay1 = findViewById(R.id.txtTotalPlay1);
        txtTotalPlay2 = findViewById(R.id.txtTotalPlay2);

        corPlay1 = findViewById(R.id.linearPlay1);
        corPlay2 = findViewById(R.id.linearPlay2);

        btnVoltar = findViewById(R.id.imgBtnFinalizar);
        btnSairPosJogo = findViewById(R.id.btnNovoJogo);
    }

    private void btns(){

        btnMasCartaPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masCartaPlay1();
            }
        });

        btnParaPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play1Parou = 1;
                masCartaPlay1();
            }
        });

        btnMasCartaPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                masCartaPlay2();
            }
        });

        btnParaPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play2Parou = 1;
                masCartaPlay2();
            }
        });

        btnSairPosJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    private void masCartaPlay1(){

        if (valorTotalPlay1 >= 22){
            verificarGanhador();
        }else {

            if (!(play1Parou == 1)){
                int valorGeradoPlay1 = rnd.random(min, max);
                valorTotalPlay1 += valorGeradoPlay1;
                txtNumeroGeradoPlay1.setText(String.valueOf(valorGeradoPlay1));
                txtTotalPlay1.setText(String.valueOf(valorTotalPlay1));
                if (!(play2Parou == 1)){
                    desabilitarBtns(1);
                    habilitarBtns(2);
                }
                if (valorTotalPlay1 >= 22){
                    desabilitarBtns(1);
                }
            }else{
                desabilitarBtns(1);
                habilitarBtns(2);
            }
        }
        verificarGanhador();
       jogadorParou();
    }

    private void masCartaPlay2(){

        if (valorTotalPlay2 >= 22){
           verificarGanhador();
        }else {

            if (!(play2Parou == 1)){
                int valorGeradoPlay2 = rnd.random(min, max);
                valorTotalPlay2 += valorGeradoPlay2;
                txtNumeroGeradoPlay2.setText(String.valueOf(valorGeradoPlay2));
                txtTotalPlay2.setText(String.valueOf(valorTotalPlay2));
                if (!(play1Parou == 1)){
                    desabilitarBtns(2);
                    habilitarBtns(1);
                }

                if (valorTotalPlay2 >= 22){
                    verificarGanhador();
                }
            }else{
             desabilitarBtns(2);
             habilitarBtns(1);
            }
        }
        verificarGanhador();
        jogadorParou();
    }

    private void verificarGanhador(){
        int colorDerrota = ResourcesCompat.getColor(getResources(),R.color.derota,null);
        int colorVitoria = ResourcesCompat.getColor(getResources(),R.color.vitoria,null);
        int colorEmpate = ResourcesCompat.getColor(getResources(),R.color.empate,null);

        if (valorTotalPlay1 >= 22){
          desabilitarBtns(0);
          corPlay1.setBackgroundColor(colorDerrota);
          corPlay2.setBackgroundColor(colorVitoria);
        }else if (valorTotalPlay2 >= 22){
            desabilitarBtns(0);
            corPlay1.setBackgroundColor(colorVitoria);
            corPlay2.setBackgroundColor(colorDerrota);
        }else if (play1Parou == 1 && play2Parou == 1){
            if (valorTotalPlay1 > valorTotalPlay2 && valorTotalPlay1 <= 21){
                desabilitarBtns(0);
                corPlay1.setBackgroundColor(colorVitoria);
                corPlay2.setBackgroundColor(colorDerrota);
            }else if (valorTotalPlay2 > valorTotalPlay1 && valorTotalPlay2 <= 21){
                desabilitarBtns(0);
                corPlay1.setBackgroundColor(colorDerrota);
                corPlay2.setBackgroundColor(colorVitoria);
            }else if (valorTotalPlay1 == valorTotalPlay2){
                desabilitarBtns(0);
                corPlay1.setBackgroundColor(colorEmpate);
                corPlay2.setBackgroundColor(colorEmpate);
            }
        }
    }

    private void desabilitarBtns(int quais){
        if (quais == 1){
            btnParaPlay1.setEnabled(false);
            btnMasCartaPlay1.setEnabled(false);
        }else if (quais == 2){
            btnMasCartaPlay2.setEnabled(false);
            btnParaPlay2.setEnabled(false);
        }else if (quais == 0){
            btnMasCartaPlay2.setEnabled(false);
            btnParaPlay2.setEnabled(false);
            btnParaPlay1.setEnabled(false);
            btnMasCartaPlay1.setEnabled(false);
            btnSairPosJogo.setVisibility(View.VISIBLE);
        }
    }

    private void habilitarBtns(int quais){
        if (quais == 1){
            btnParaPlay1.setEnabled(true);
            btnMasCartaPlay1.setEnabled(true);
        }else if (quais == 2){
            btnMasCartaPlay2.setEnabled(true);
            btnParaPlay2.setEnabled(true);
        }
    }

    private void jogadorParou(){
        if (play1Parou == 1){
            desabilitarBtns(1);
        }else if (play2Parou == 1){
            desabilitarBtns(2);
        }
    }
}