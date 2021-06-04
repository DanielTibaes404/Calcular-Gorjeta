package com.metaapp.calculadoradegorjeta;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.net.URI;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText valorDaConta;
    private SeekBar seekbarEscala;
    private TextView porcentagemSeekBar;
    private TextView totalGorjeta;
    private TextView totalAPagar;

    private double porcentagem = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.porcentagemSeekBar = findViewById(R.id.porcentagem);
        this.seekbarEscala = findViewById(R.id.seekBar);
        this.totalAPagar = findViewById(R.id.valorTotal);
        this.totalGorjeta = findViewById(R.id.valorGorjeta);
        this.valorDaConta = findViewById(R.id.valorDaConta);

        seekbarEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override  // Função será executada caso deslise a barra
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                porcentagemSeekBar.setText(progress + "%");
                porcentagem = progress;

            }

            @Override  // Função será executada quando clicar na barra
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override  //Função será executada ao soltar a barra
            public void onStopTrackingTouch(SeekBar seekBar) {

                calculo();
            }
        });
    }

        public void calculo() {

            String valorDigitado = valorDaConta.getText().toString();
            if (valorDigitado == null || valorDigitado.equals("")) {

                Toast.makeText(
                        this,
                        "FAVOR PREENCHER O VALOR DA SUA CONTA",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                double valorConta = Double.parseDouble(valorDigitado);
                DecimalFormat df = new DecimalFormat("0.00");

                double calculoDoValorDaGorjeta = valorConta * (porcentagem / 100);
                totalGorjeta.setText("R$ " + df.format(calculoDoValorDaGorjeta));

                double calculoTotalaPagar = valorConta + calculoDoValorDaGorjeta;

                totalAPagar.setText("R$ " + df.format(calculoTotalaPagar));

            }
        }

        public void restaurar (View view){

            // Restaurar todos os campos.
            this.valorDaConta.setText("");
            this.totalGorjeta.setText("R$ 0.00");
            this.totalAPagar.setText("R$ 0.00");
            this.seekbarEscala.setProgress(0);

        }

        public void redesSociais (View view){

            //Esta linha de codigo é responsavel pela ação de abrir uma pagina na internet por exemplo.
            startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://resultadosdigitais.com.br/blog/redes-sociais-mais-usadas-no-brasil/")));

        }
    }
