package br.com.mariojp.exercise1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView labelMensagem;
    private EditText editNome;
    private Button btnCumprimentar;

    private String editNomeTexto = "";

    private final String FORMATO_MENSAGEM = "Alô, %s!";
    private final String KEY_CACHE_EDIT_NOME = "editNomeCache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelMensagem = findViewById(R.id.labelMensagem);
        editNome = findViewById(R.id.editNome);
        btnCumprimentar = findViewById(R.id.btnCumprimentar);

        labelMensagem.setText(getMensagemInicial(savedInstanceState));

        btnCumprimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNomeTexto = editNome.getText().toString();
                labelMensagem.setText(String.format(FORMATO_MENSAGEM, editNomeTexto));
            }
        });
    }

    private String getMensagemInicial(Bundle savedInstanceState) {
        String mensagemInicial = getResources().getString(R.string.nome_inicial);

        if  (savedInstanceState != null) {
            mensagemInicial = savedInstanceState.getString(KEY_CACHE_EDIT_NOME);
        }

        return String.format(FORMATO_MENSAGEM, mensagemInicial);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_CACHE_EDIT_NOME, editNomeTexto);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

}
