package br.com.alura.loja.orcamento;

import br.com.alura.loja.adapters.http.JavaHttpClient;
import br.com.alura.loja.adapters.http.RegistroDeOrcamento;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.situacao.Aprovado;
import br.com.alura.loja.orcamento.situacao.Finalizado;

import java.math.BigDecimal;

public class TesteAdaptadorHttp {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(BigDecimal.TEN, 1);
        orcamento.setSituacao(new Aprovado());
        orcamento.setSituacao(new Finalizado());

        RegistroDeOrcamento registro = new RegistroDeOrcamento(new JavaHttpClient());
        registro.registrar(orcamento);
    }
}
