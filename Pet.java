public class Pet{
    private String nome;
    private String tipo;
    public Pet(String nome, String tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        return tipo;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String toString(){
        return " - Nome do pet: "+nome+"; Tipo: "+tipo+".";
    }
}