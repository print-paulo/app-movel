package br.edu.unicid.exercicio1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSomar, btnDividir, btnSubtrair, btnMultiplicar, btnProximaTela;
    EditText edtValor1, edtValor2;
    TextView txvResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Variaveis
        btnSomar = findViewById(R.id.btnSomar);
        btnDividir = findViewById(R.id.btnDividir);
        btnSubtrair = findViewById(R.id.btnSubtrair);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnProximaTela = findViewById(R.id.btnProximaTela);
        edtValor1 = findViewById(R.id.edtValor1);
        edtValor2 = findViewById(R.id.edtValor2);
        txvResultado = findViewById(R.id.txvResultado);

        cliqueOperador(btnSomar, "soma", edtValor1, edtValor2, txvResultado);
        cliqueOperador(btnDividir, "divisao", edtValor1, edtValor2, txvResultado);
        cliqueOperador(btnSubtrair, "subtracao", edtValor1, edtValor2, txvResultado);
        cliqueOperador(btnMultiplicar, "multiplicacao", edtValor1, edtValor2, txvResultado);
    }

    public void proximaTela(View view) {
        Intent it = new Intent(getApplicationContext(), SistemaDeCompras.class);
        startActivity(it);
    }

    public void cliqueOperador(Button botao, String operacao, EditText valor1, EditText valor2, TextView outputResultado) {
            botao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String valor1str = valor1.getText().toString().trim();
                    String valor2str = valor2.getText().toString().trim();
                    if (TextUtils.isEmpty(valor1str) || TextUtils.isEmpty(valor2str)) {
                        return;
                    }

                    try {
                        float numero1 = Float.parseFloat(valor1str);
                        float numero2 = Float.parseFloat(valor2str);
                        float resultado;

                        switch (operacao) {
                            case "soma": {
                                resultado = numero1 + numero2;
                                break;
                            }
                            case "divisao": {
                                if (numero2 == 0) {
                                    outputResultado.setText("Divisão por Zero");
                                }
                                resultado = numero1 / numero2;
                                break;
                            }
                            case "subtracao": {
                                resultado = numero1 - numero2;
                                break;
                            }
                            case "multiplicacao": {
                                resultado = numero1 * numero2;
                                break;
                            }
                            default: {
                                outputResultado.setText("Operação Inválida");
                                return;
                            }
                        }

                        outputResultado.setText(String.valueOf(resultado));
                    }
                    catch (NumberFormatException e){
                        outputResultado.setText("Número Inválido");
                    }
                }
        });
    }
}
