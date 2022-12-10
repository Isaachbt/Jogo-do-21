package com.isaac.jogodo21.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.isaac.jogodo21.R;
import com.isaac.jogodo21.random.RandomClass;

public class SoloActivity extends AppCompatActivity {

    private TextView txtValorGeradoEu,txtValorGeragoMaquina,txtContaTotalEu,txtContaTotalMaquina,txtMsg;
    private Button btnMasCartaSolo,btnParar;
    private ImageButton imgBtnVoltar;
    private LinearLayout telaJogo;
    private RandomClass rnd;
    private int pararJogo = 0;
    private int maquinaParou = 0;
    private int somaPlayer,somaMaquina;
    private int maquinaRetorna = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);
        findView();
        rnd  = new RandomClass();

        btnParar.setVisibility(View.INVISIBLE);
        if (pararJogo != 1){
            validarBtns();
        }

    }

    public void findView(){
        txtContaTotalEu = findViewById(R.id.txtContagemEuSolo);
        txtValorGeradoEu = findViewById(R.id.txtValorQSaouEuSolo);
        txtValorGeragoMaquina = findViewById(R.id.txtValorQSaiuMaquinaSolo);
        txtContaTotalMaquina = findViewById(R.id.txtContagemMaquinaSolo);
        txtMsg = findViewById(R.id.msgAvisoSolo);
        telaJogo = findViewById(R.id.IncludeJogoSolo);
        btnParar = findViewById(R.id.btnPararSolo);
        btnMasCartaSolo = findViewById(R.id.btnMasCartaSolo);
        imgBtnVoltar = findViewById(R.id.imgBtnVoltarSolo);

        telaJogo.setVisibility(View.INVISIBLE);
        txtMsg.setVisibility(View.INVISIBLE);
    }

    public void validarBtns(){

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pararJogo = 1;
                validarGanhador();
                btnParar.setText("Parou!");
                btnParar.setEnabled(false);

                while(maquinaParou != 1){
                 sortearMaquina();
                }

                //aqui vai ter um while para a maquina jogar até ela querer
            }
        });

        btnMasCartaSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortearMasCartas();
                btnParar.setVisibility(View.VISIBLE);
            }
        });

        imgBtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        }

    private void sortearMasCartas(){

        telaJogo.setVisibility(View.VISIBLE);


        sortearEu();
        //sortearMaquina();
    }

    private void sortearMaquina(){

    if (maquinaContinuar() == 1){
        maquinaParou = 1;
        txtValorGeragoMaquina.setText("Parou");
        validarGanhador();
    }else{
        if (somaMaquina >= 22){
            maquinaParou = 1;
            validarGanhador();
        }else{
            if (!(maquinaParou == 1)){
                int valorGeradoMaquina = rnd.random(2,10);
                somaMaquina += valorGeradoMaquina;
                txtValorGeragoMaquina.setText(String.valueOf(valorGeradoMaquina));
                txtContaTotalMaquina.setText(String.valueOf(somaMaquina));
                btnParar.setEnabled(true);
                btnMasCartaSolo.setEnabled(true);
                if (somaMaquina >= 22){
                    maquinaParou = 1;
                    validarGanhador();
                }
            }else{
                btnParar.setEnabled(true);
                btnMasCartaSolo.setEnabled(true);
            }
        }
    }
    }

    private void sortearEu(){

        if (somaPlayer >= 22){
            validarGanhador();
        }else{
            if (!(pararJogo == 1)){
                int valorGerado = rnd.random(2,10);
                somaPlayer += valorGerado;
                txtValorGeradoEu.setText(String.valueOf(valorGerado));
                txtContaTotalEu.setText(String.valueOf(somaPlayer));
//                btnParar.setEnabled(false);
//                btnMasCartaSolo.setEnabled(false);
                if (somaPlayer >= 22){
                    validarGanhador();
                }else{
                    sortearMaquina();
                }
            }
        }
    //        if (pararJogo == 1){
//            txtValorGeradoEu.setVisibility(View.INVISIBLE);
//            validarGanhador();
//        } else if (somaPlayer >= 22){
//            validarGanhador();
//        }else{
//            somaPlayer += sorteandoNumerosPlayer();
//            txtContaTotalEu.setText(String.valueOf(somaPlayer));
//        }
    }

    private void validarGanhador(){

        if (somaPlayer >= 22){
            msg(2);
            btnParar.setEnabled(false);
            btnMasCartaSolo.setEnabled(false);
        }else if (somaMaquina >= 22){
            msg(1);
            btnParar.setEnabled(false);
            btnMasCartaSolo.setEnabled(false);
        }else if (pararJogo == 1 && maquinaParou == 1){
            if (somaPlayer > somaMaquina && somaPlayer < 22){
                msg(1);
                btnParar.setEnabled(false);
                btnMasCartaSolo.setEnabled(false);
            }else if (somaMaquina > somaPlayer && somaMaquina < 22){
                msg(2);
                btnParar.setEnabled(false);
                btnMasCartaSolo.setEnabled(false);
            }else if (somaMaquina == somaPlayer){
                msg(3);
                btnParar.setEnabled(false);
                btnMasCartaSolo.setEnabled(false);
            }
        }
    }

    private void msg(int ganhador){

        String res = "";
        int qualMsg = rnd.random(0,5);
        if (ganhador == 1){
            String[] vitoria = {"Vc e foda!","Vc venceu!","Vc ganhou","Só não melhor que eu.","Nada mal em","Parábens"};
            res = vitoria[qualMsg];
        }else if (ganhador == 2){
            String[] derrota = {"Vish","Ruim d+","Aprender né","Já sabia que ia perder","Tragico","Lamentavel"};
            res = derrota[qualMsg];
        }else if(ganhador == 3){
            String[] empate =  {"Bom jogo","Jogou bem","Na próxima eu ganho.","Vamos mais uma?","Gostei de vc."};
        }
        txtMsg.setVisibility(View.VISIBLE);
        txtMsg.setText(res);
        btnParar.setEnabled(true);
        btnMasCartaSolo.setEnabled(true);
    }

    private int maquinaContinuar(){

        int valorRetorn = 0;
        if (somaMaquina >= 18 && somaMaquina <= 21){
            valorRetorn = 1;
        }
        return valorRetorn;
    }

}