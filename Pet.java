public class Pet{
    private String nome;
    private String tipo;
    public Pet(String nome, String tipo){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        return tipo;
    }

    public String setNome(String nome){
        this.nome = nome;
    }

    public String setTipo(String tipo){
        this.tipo = tipo
    }

    public String toString(){
        return " - Nome do pet: "+nome+"; Tipo: "+tipo+".";
    }
}