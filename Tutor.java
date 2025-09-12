public class Tutor{
    private int cod;
    private String nome;
    private String endereco;
    private LocalDate dataNasc;
    private ArrayList<Pet> pets;

    public Tutor(int cod, String nome, LocalDate dataNasc, String endereco){
        this.cod = cod;
        this.nome = nome;
        this.dateNasc = dateNasc;
        this.endereco = endereco;
    }

    public String getCod(){
        return cod;
    }

    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public String getDataNasc(){
        return dataNasc;
    }

    public String getIdade(){
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    public void add(Pet p){
        this.pets.add(p);
    }

    public void removePet(Pet p){
        this.pets.remove(p);
    }

    public String toString(){
        String text = "Cod. do tutor: " + getCod() + "\n" +
                   "Nome..........: " + getNome() + "\n" +
                   "Data nascimento: " + getDataNasc() + " (" + getIdade() + " anos)\n" +
                   "Endereço......: " + getEndereco() + "\n" +
                   "Relação de Pets:\n";
        if (pets.isEmpty()){
            text += "  Nenhum pet cadastrado.";
        } else {
            for (Pet pet : pets){
                text += "  - Nome do pet: " + pet.getNome() + "; Tipo: " + pet.getTipo() + ".\n";
            }
        }
        return text;
    }
}
