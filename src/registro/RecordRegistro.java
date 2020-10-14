package registro;

import java.io.Serializable;

public class RecordRegistro implements Serializable {

    public RecordRegistro(String nome, String indirizzo){

        this.nome = nome;
        this.indirizzo = indirizzo;

    }

    public RecordRegistro(String nome){

        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    private String nome;
    private String indirizzo;
    private static final long serialVersionUID = 1L;

}
