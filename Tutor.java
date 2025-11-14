import java.io.Serializable; // 1. Importa Serializable
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// 2. Implementa Serializable
public class Tutor implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private int cod;
    private String nome;
    private String endereco;
    private LocalDate dataNasc;
    private ArrayList<Pet> pets;

    public Tutor(int cod, String nome, LocalDate dataNasc, String endereco) {
        this.cod = cod;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.pets = new ArrayList<>();
    }

    // ... (Getters e Setters permanecem os mesmos) ...
    public int getCod() { return cod; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public LocalDate getDataNasc() { return dataNasc; }
    public int getIdade() {
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }
    public void add(Pet p) { this.pets.add(p); }
    public void removePet(Pet p) { this.pets.remove(p); }


    // 3. Método toString() ATUALIZADO
    @Override
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
            // Loop atualizado para mostrar data de nasc. e idade do Pet
            for (Pet pet : pets) {
                text += String.format("  - Pet: %s (%s) - Nasc: %s (%d anos)\n",
                            pet.getNome(),
                            pet.getTipo(),
                            pet.getDataNasc().format(dataBR),
                            pet.getIdade());
            }
        }
        return text;
    }
}