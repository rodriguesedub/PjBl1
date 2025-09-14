import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class PetShop {
    
    private static ArrayList<Tutor> tutores = new ArrayList<>();
    private static int contadorCod = 1;

    public static void main(String[] args) {

        popularCadastro();

        Scanner sc = new Scanner(System.in);
        char op;

        do {
            System.out.println("\n***** ESCOLHER UMA OPÇÃO *****");
            System.out.println("c: cadastrar tutor+pet(s)");
            System.out.println("i: imprimir cadastro");
            System.out.println("b: buscar pets por codigo tutor");
            System.out.println("e: excluir pets por codigo tutor");
            System.out.println("x: encerrar.");
            System.out.print("Opção escolhida: ");
            
            op = sc.next().charAt(0);

            switch (op) {
                case 'c':
                    cadastrarTutorPets();
                    break;
                case 'i':
                    imprimirCadastro();
                    break;
                case 'b':
                    break;
                case 'e':
                    break;
                case 'x':
                    System.out.println("--- Programa de cadastro encerrado ---\r");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 'x');

        sc.close();
    }

    public static LocalDate parseData(String dataStr) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dataStr, fmt);
    }


    public static void popularCadastro() {
        System.out.println("Populando cadastro...");
        System.out.println("Criando Tutor 1...");
        // Tutor 1
        Tutor t1 = new Tutor(
            geraCodTutor(),
            "Ana Beatriz",
            parseData("15/08/2004"),
            "Rua Guimaroes 184"
        );
        t1.add(new Pet("Rex", "Cachorro"));
        t1.add(new Pet("Mimi", "Gato"));
        tutores.add(t1);

        System.out.println("Criando Tutor 2...");
        // Tutor 2
        Tutor t2 = new Tutor(
            geraCodTutor(),
            "Carlos Eduardo",
            parseData("22/03/1988"),
            "Av. Brasil 1020"
        );
        t2.add(new Pet("Bolt", "Cachorro"));
        tutores.add(t2);

        System.out.println("Criando Tutor 3...");
        // Tutor 3
        Tutor t3 = new Tutor(
            geraCodTutor(),
            "Fernanda Lima",
            parseData("10/11/1995"),
            "Rua das Flores 56"
        );
        t3.add(new Pet("Luna", "Gato"));
        t3.add(new Pet("Nina", "Cachorro"));
        tutores.add(t3);
    }

    public static int geraCodTutor() {
        return contadorCod++;
    }

    public static void cadastrarTutorPets() {
        System.out.println("Digite nome do tutor (vazio encerra cadastro tutor): ");
        System.out.println("Digite dia (dd), mês (mm) e ano (aaaa) de nascimento do tutor (separados por espaços) :\n");
        System.out.println("Digite endereço do tutor/pet: :\n");
        // TODO: Fazer o loop de cadastro
        System.out.println("Digite nome do pet (vazio encerra cadastro pet):\n");
        System.out.println("--- Pet cadastrado ---");


    }

    public static void imprimirCadastro() {
        // percorre lista de tutores, imprime toString()
    }

    public static void buscarPorCodigo(int cod) {
        // procura tutor pelo código e exibe
    }

    public static void excluirPorCodigo(int cod) {
        // remove tutor e seus pets
    }

    private static boolean validaData(int d, int m, int a) {
        // valida datas de nascimento
        return false;
    }
}
