package br.com.alura.loja.adapters.http;

import br.com.alura.loja.exceptions.ErrorException;
import br.com.alura.loja.model.Orcamento;

import java.util.Map;

public class RegistroDeOrcamento {

    private HttpAdapter http;

    public RegistroDeOrcamento(HttpAdapter http) {
        this.http = http;
    }

    public void registrar(Orcamento orcamento){
        verificaStatus(orcamento);

        String url = "http://api.externa.orcamento/";
        Map<String, Object> dados = Map.of(
                "valor", orcamento.getValor(),
                "quantidadeItens", orcamento.getQtdItens()
        );

        http.post(url, dados);
    }

    public void verificaStatus(Orcamento orcamento){
        if(!orcamento.isFinalizado()){
            throw new ErrorException("Não é possível realizar o registro sem que o orçamento esteja finalizado");
        }
    }
}
