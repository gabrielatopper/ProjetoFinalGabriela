package br.com.gabriela.applivros2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etDescricao;
    private Spinner spNota;
    private Button btnSalvar;
    private String acao;
    private Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etDescricao = findViewById(R.id.etDescricao);
        spNota = findViewById(R.id.spNota);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void carregarFormulario(){
        int idLivro = getIntent().getIntExtra("idLivro", 0);
        if (idLivro != 0 ){
            livro = LivroDAO.getLivroById(this, idLivro);
            etNome.setText(livro.nome);
            etDescricao.setText(livro.descricao);

            String[] arrayNota = getResources().getStringArray(R.array.arrayNota);
            for (int i = 1; i < arrayNota.length; i++){
                if (Integer.valueOf(arrayNota[i] ) == livro.getNota()){
                    spNota.setSelection( i );
                }
            }
        }
    }

        private void salvar() {
            if (spNota.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty()) {

                Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

            } else {
                if (acao.equals("novo")) {
                    livro = new Livro();
                }

                livro.nome = etNome.getText().toString();
                livro.descricao = etDescricao.getText().toString();
                livro.setNota(Integer.valueOf(spNota.getSelectedItem().toString()));

                if (acao.equals("editar")) {
                    LivroDAO.editar(livro, this);
                    finish();
                } else {
                    LivroDAO.inserir(livro, this);
                    etNome.setText("");
                    etDescricao.setText("");
                    spNota.setSelection(0);
                }
            }
        }


}