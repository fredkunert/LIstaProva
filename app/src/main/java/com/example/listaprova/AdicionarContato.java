package com.example.listaprova;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarContato extends Activity {

    // Criando as variáveis para os campos de texto e o botão
    private EditText edtNome, edtTelefone, edtEmail, edtLinkedIn;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_contato); // Carrega o layout da tela de adicionar contato

        // Ligando as variáveis aos componentes do layout
        edtNome = findViewById(R.id.edtNome);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEmail = findViewById(R.id.edtEmail);
        edtLinkedIn = findViewById(R.id.edtLinkedIn);
        btnSalvar = findViewById(R.id.btnSalvarContato);

        // Quando clicar no botão de salvar
        btnSalvar.setOnClickListener(v -> {
            // Pegando os textos digitados nos campos
            String nome = edtNome.getText().toString();
            String telefone = edtTelefone.getText().toString();
            String email = edtEmail.getText().toString();
            String linkedIn = edtLinkedIn.getText().toString();

            // Criando uma intent pra mandar os dados de volta pra tela anterior
            Intent intent = new Intent();
            intent.putExtra("nome", nome);
            intent.putExtra("telefone", telefone);
            intent.putExtra("email", email);
            intent.putExtra("linkedIn", linkedIn);

            // Diz que a operação foi concluída com sucesso e devolve os dados
            setResult(RESULT_OK, intent);

            // Fecha essa tela e volta pra anterior
            finish();
        });
    }
}

