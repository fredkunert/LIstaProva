package com.example.listaprova;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Declaração dos componentes da interface
    private RecyclerView recyclerView;
    private List<Contato> listaContatos;
    private SensorManager sensorManager;  // Gerenciador de sensores
    private Sensor sensorProximidade;  // Sensor de proximidade
    private Contato contatoFavorito;  // Contato favorito
    private Button btnAdicionarContato;  // Botão para adicionar novo contato

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Define o layout da activity

        listaContatos = new ArrayList<>();
        popularContatos();  // Popula a lista de contatos

        inicializarContatoFavorito();  // Inicializa o contato favorito

        // Inicializa o RecyclerView com a lista de contatos
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContatoAdapter(this, listaContatos));

        // Inicializa o SensorManager e o Sensor de Proximidade
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            sensorProximidade = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);  // Obtem o sensor de proximidade
        }

        // Caso o sensor de proximidade não esteja disponível
        if (sensorProximidade == null) {
            System.out.println("Sensor de proximidade não disponível.");
            Toast.makeText(this, "Sensor de proximidade não disponível.", Toast.LENGTH_SHORT).show();
        }

        // Configura o botão de adicionar contato
        btnAdicionarContato = findViewById(R.id.btnAdicionarContato);
        btnAdicionarContato.setOnClickListener(v -> {
            // Ao clicar no botão, abre a Activity para adicionar um novo contato
            Intent intent = new Intent(MainActivity.this, AdicionarContato.class);
            startActivityForResult(intent, 1);  // 1 é o código de solicitação
        });
    }

    // Inicializa o contato favorito a partir da lista
    private void inicializarContatoFavorito() {
        for (Contato contato : listaContatos) {
            if (contato.isFavorito()) {
                contatoFavorito = contato;
                break;
            }
        }
    }

    // Método que lida com o retorno da Activity de adicionar contato
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o resultado é do tipo esperado (adicionar contato)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String nome = data.getStringExtra("nome");
            String telefone = data.getStringExtra("telefone");
            String email = data.getStringExtra("email");
            String linkedIn = data.getStringExtra("linkedIn");

            // Cria um novo contato com os dados recebidos
            Contato novoContato = new Contato(nome, telefone, email, linkedIn, false);
            listaContatos.add(novoContato);  // Adiciona o novo contato à lista
            recyclerView.getAdapter().notifyDataSetChanged();  // Notifica o RecyclerView sobre a alteração
        }
    }

    // Método para popular a lista de contatos com dados iniciais
    private void popularContatos() {
        listaContatos.add(new Contato("teste", "999999999", "t@email.com", "https://linkedin.com/in/t", true));
        listaContatos.add(new Contato("teste01", "977777777", "t1@email.com", "https://linkedin.com/in/t1", false));
    }

    // Registra o sensor de proximidade quando a Activity está em primeiro plano
    @Override
    protected void onResume() {
        super.onResume();
        if (sensorProximidade != null) {
            sensorManager.registerListener(this, sensorProximidade, SensorManager.SENSOR_DELAY_UI);
        }
    }

    // Desregistra o sensor de proximidade quando a Activity sai de primeiro plano
    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    // Método que lida com as mudanças do sensor de proximidade
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d("SensorProximidade", "Valor do sensor: " + event.values[0]);

            // Se o sensor detectar proximidade (valor abaixo do máximo), liga para o contato favorito
            if (event.values[0] < sensorProximidade.getMaximumRange()) {
                if (contatoFavorito != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);  // Ação de discagem telefônica
                    intent.setData(Uri.parse("tel:" + contatoFavorito.getTelefone()));  // Define o número para discar
                    startActivity(intent);  // Inicia a discagem
                } else {
                    Log.d("SensorProximidade", "Contato favorito não encontrado.");
                }
            }
        }
    }

    // Método necessário para a interface SensorEventListener, mas não utilizado
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
