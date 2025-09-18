import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Tutor {
    private int cod;                // código único do tutor
    private String nome;
    private String endereco;
    private LocalDate dataNasc;     // data de nascimento do tutor
    private ArrayList<Pet> pets;    // lista de pets associados ao tutor

    // construtor
    public Tutor(int cod, String nome, LocalDate dataNasc, String endereco) {
        this.cod = cod;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.pets = new ArrayList<>();
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    // calcula idade do tutor
    public int getIdade() {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    // adiciona pet à lista
    public void add(Pet p) {
        this.pets.add(p);
    }

    // remove pet da lista
    public void removePet(Pet p) {
        this.pets.remove(p);
    }

    // representação em string do tutor e seus pets
    public String toString() {
        DateTimeFormatter dataBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = "Cod. do tutor: " + getCod() + "\n" +
                   "Nome...........: " + getNome() + "\n" +
                   "Data nascimento: " + getDataNasc().format(dataBR) + " (" + getIdade() + " anos)\n" +
                   "Endereço.......: " + getEndereco() + "\n" +
                   "Relação de Pets:\n";

        if (pets.isEmpty()) {
            text += "  Nenhum pet cadastrado.";
        } else {
            for (Pet pet : pets) {
                text += "- Nome do pet: " + pet.getNome() + "; Tipo: " + pet.getTipo() + ".\n";
            }
        }
        return text;
    }
}
