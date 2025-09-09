import java.util.ArrayList;
import java.util.Scanner;

public class PetShop {
    
    private static ArrayList<Tutor> tutores;

    public static void main(String[] args) {
        System.out.println("***** ESCOLHER UMA OPÇÃO *****\n");
        System.out.println("c: cadastrar tutor+pet(s)\r");
        System.out.println("i: imprimir cadastro\r");
        System.out.println("b: buscar pets por codigo tutor");
        System.out.println("e: excluir pets por codigo tutor\r");
        System.out.println("x: encerrar.\r");
        System.out.println("Opção escolhida:");

    }

    public static void popularCadastro() {
        // insere tutores e pets iniciais
    }

    public static int geraCodTutor() {
        // gera código sequencial automático
        return 0;
    }

    public static void cadastrarTutorPets() {
        // cadastra tutor + seus pets
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
