package com.example.listaprova;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    // Contexto da aplicação e lista de contatos que vamos mostrar
    private Context context;
    private List<Contato> listaContatos;

    // Construtor da classe que recebe o contexto e a lista de contatos
    public ContatoAdapter(Context context, List<Contato> listaContatos) {
        this.context = context;
        this.listaContatos = listaContatos;
    }

    // Criação da view para cada item da lista
    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item de contato (item_contato.xml) para ser exibido na lista
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false);
        return new ContatoViewHolder(itemView); // Retorna o ViewHolder com a view inflada
    }

    // Preenche os dados da view com informações do contato
    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, int position) {
        Contato contato = listaContatos.get(position);
        // Define os valores dos campos (nome e telefone) com base no contato da lista
        holder.txtNome.setText(contato.getNome());
        holder.txtTelefone.setText(contato.getTelefone());

        // Configura o que acontece quando o item da lista é clicado
        holder.itemView.setOnClickListener(v -> {
            // Cria uma nova Intent para abrir a tela de detalhes do contato
            Intent intent = new Intent(context, DetalhesActivity.class);
            // Passa o objeto Contato para a próxima tela (DetalhesActivity)
            intent.putExtra("contato", contato);
            context.startActivity(intent); // Inicia a nova activity
        });
    }

    // Retorna o número de itens na lista
    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    // ViewHolder que segura as views de cada item na lista
    public static class ContatoViewHolder extends RecyclerView.ViewHolder {
        // Variáveis para os elementos de cada item da lista
        TextView txtNome, txtTelefone;

        // Construtor que recebe a view e inicializa os campos
        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome); // Referência para o TextView de nome
            txtTelefone = itemView.findViewById(R.id.txtTelefone); // Referência para o TextView de telefone
        }
    }
}
