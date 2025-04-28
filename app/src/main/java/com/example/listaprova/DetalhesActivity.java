package com.example.listaprova;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    // Declaração dos elementos da interface (TextViews e Buttons)
    private TextView txtNome, txtTelefone, txtEmail, txtLinkedIn;
    private Button btnLigar, btnEnviarEmail, btnAbrirLinkedIn;

    // Declaração do objeto Contato que será exibido
    private Contato contato;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);  // Define o layout da activity

        // Inicializa os componentes da interface com findViewById
        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEmail = findViewById(R.id.txtEmail);
        txtLinkedIn = findViewById(R.id.txtLinkedIn);
        btnLigar = findViewById(R.id.btnLigar);
        btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
        btnAbrirLinkedIn = findViewById(R.id.btnAbrirLinkedIn);

        // Recupera o objeto Contato enviado pela Intent da Activity anterior
        contato = (Contato) getIntent().getSerializableExtra("contato");

        // Preenche os TextViews com as informações do contato
        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtLinkedIn.setText(contato.getLinkedIn());

        // Ação do botão "Ligar" - Inicia a discagem telefônica
        btnLigar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);  // Ação de discagem telefônica
            intent.setData(Uri.parse("tel:" + contato.getTelefone()));  // Define o número para discar
            startActivity(intent);  // Inicia a atividade de discagem
        });

        // Ação do botão "Enviar Email" - Abre o cliente de e-mail com o endereço do contato
        btnEnviarEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);  // Ação para enviar email
            intent.setData(Uri.parse("mailto:" + contato.getEmail())); // Define o endereço de email
            startActivity(intent);  // Inicia a atividade de envio de email
        });

        // Ação do botão "Abrir LinkedIn" - Abre o perfil do LinkedIn no navegador
        btnAbrirLinkedIn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);  // Ação para abrir URL
            intent.setData(Uri.parse(contato.getLinkedIn()));  // Define o URL do LinkedIn
            startActivity(intent);  // Inicia a atividade para abrir o LinkedIn
        });
    }
}
