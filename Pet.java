import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

// 1. Implementa Serializable para permitir a gravação do objeto em arquivo
public class Pet implements Serializable {
    
    // Atributo serialVersionUID é recomendado para classes Serializable
    private static final long serialVersionUID = 1L; 

    private String nome;
    private String tipo;
    private LocalDate dataNasc; // 2. Novo atributo obrigatório

    public Pet(String nome, String tipo, LocalDate dataNasc) {
        this.nome = nome;
        this.tipo = tipo;
        this.dataNasc = dataNasc; // 3. Construtor atualizado
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    // 4. Novo método para calcular a idade do pet
    public int getIdade() {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    // toString() original mantido, a classe Tutor fará a formatação completa
    public String toString() {
        return " - Nome do pet: " + nome + "; Tipo: " + tipo + ".";
    }
}