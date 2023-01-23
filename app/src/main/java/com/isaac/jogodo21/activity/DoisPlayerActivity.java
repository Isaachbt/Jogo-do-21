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

import com.isaac.jogodo21.R;
import com.isaac.jogodo21.databinding.ActivityDoisPlayerBinding;
import com.isaac.jogodo21.random.RandomClass;

public class DoisPlayerActivity extends AppCompatActivity {

    private int valorTotalPlay1 = 0;
    private int play1Parou = 0;
    private int min = 2,max = 10;
    private int play2Parou = 0;
    private int valorTotalPlay2 = 0;
    private  RandomClass rnd = new RandomClass();
    private ActivityDoisPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoisPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recuperandoNomes();
        desabilitarBtns(2);

        btns();
    }

    private void recuperandoNomes(){
        Bundle dados = getIntent().getExtras();
        String nomePlay1 = dados.getString("play1");
        String nomePlay2 = dados.getString("play2");

        binding.txtNomePlay1.setVisibility(View.VISIBLE);
        binding.txtNomePlay2.setVisibility(View.VISIBLE);

        binding.txtNomePlay1.setText(nomePlay1);
        binding.txtNomePlay2.setText(nomePlay2);

    }

    private void btns(){

        binding.btnMasCartaPlay1.setOnClickListener(view -> masCartaPlay1());

        binding.btnParaPlay1.setOnClickListener(view -> {
            play1Parou = 1;
            masCartaPlay1();
        });

        binding.btnMasCartaPlay2.setOnClickListener(view -> masCartaPlay2());

        binding.btnParaCartaPlay2.setOnClickListener(view -> {
            play2Parou = 1;
            masCartaPlay2();
        });

        binding.btnNovoJogo.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        binding.imgBtnFinalizar.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });
    }

    private void masCartaPlay1(){

        if (valorTotalPlay1 >= 22){
            verificarGanhador();
        }else {

            if (!(play1Parou == 1)){
                int valorGeradoPlay1 = rnd.random(min, max);
                valorTotalPlay1 += valorGeradoPlay1;
                binding.txtNGeradoPlay1.setText(String.valueOf(valorGeradoPlay1));
                binding.txtTotalPlay1.setText(String.valueOf(valorTotalPlay1));
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
                binding.txtNGeradoPlay2.setText(String.valueOf(valorGeradoPlay2));
                binding.txtTotalPlay2.setText(String.valueOf(valorTotalPlay2));
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
          binding.linearPlay1Cor.setBackgroundColor(colorDerrota);
          binding.linearPlay2Cor.setBackgroundColor(colorVitoria);
        }else if (valorTotalPlay2 >= 22){
            desabilitarBtns(0);
            binding.linearPlay1Cor.setBackgroundColor(colorVitoria);
            binding.linearPlay2Cor.setBackgroundColor(colorDerrota);
        }else if (play1Parou == 1 && play2Parou == 1){
            if (valorTotalPlay1 > valorTotalPlay2 && valorTotalPlay1 <= 21){
                desabilitarBtns(0);
                binding.linearPlay1Cor.setBackgroundColor(colorVitoria);
                binding.linearPlay2Cor.setBackgroundColor(colorDerrota);
            }else if (valorTotalPlay2 > valorTotalPlay1 && valorTotalPlay2 <= 21){
                desabilitarBtns(0);
                binding.linearPlay1Cor.setBackgroundColor(colorDerrota);
                binding.linearPlay2Cor.setBackgroundColor(colorVitoria);
            }else if (valorTotalPlay1 == valorTotalPlay2){
                desabilitarBtns(0);
                binding.linearPlay1Cor.setBackgroundColor(colorEmpate);
                binding.linearPlay2Cor.setBackgroundColor(colorEmpate);
            }
        }
    }

    private void desabilitarBtns(int quais){
        if (quais == 1){
            binding.btnParaPlay1.setEnabled(false);
            binding.btnMasCartaPlay1.setEnabled(false);
        }else if (quais == 2){
            binding.btnMasCartaPlay2.setEnabled(false);
            binding.btnParaCartaPlay2.setEnabled(false);
        }else if (quais == 0){
            binding.btnMasCartaPlay2.setEnabled(false);
            binding.btnParaCartaPlay2.setEnabled(false);
            binding.btnParaPlay1.setEnabled(false);
            binding.btnMasCartaPlay1.setEnabled(false);
            binding.imgBtnFinalizar.setVisibility(View.VISIBLE);
        }
    }

    private void habilitarBtns(int quais){
        if (quais == 1){
            binding.btnParaPlay1.setEnabled(true);
            binding.btnMasCartaPlay1.setEnabled(true);
        }else if (quais == 2){
            binding.btnMasCartaPlay2.setEnabled(true);
            binding.btnParaCartaPlay2.setEnabled(true);
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