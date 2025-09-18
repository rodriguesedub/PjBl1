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
         System.out.println("\n***** ESCOLHER UMA OPCAO *****");
         System.out.println("c: cadastrar tutor+pet(s)");
         System.out.println("i: imprimir cadastro");
         System.out.println("b: buscar pets por codigo tutor");
         System.out.println("e: excluir pets por codigo tutor");
         System.out.println("x: encerrar.");
         System.out.print("Opcao escolhida: ");
         op = sc.next().toLowerCase().charAt(0);
         sc.nextLine();
       
         switch (op) {
            case 'c':
               cadastrarTutorPets(sc);
               break;
            case 'i':
               imprimirCadastro();
               break;
            case 'b':
               buscarPorCodigo(sc);
               break;
            case 'e':
               excluirPorCodigo(sc);
               break;
            case 'x':
               System.out.println("--- Programa de cadastro encerrado ---\r");
               System.exit(1);
               break;
            default:
               System.out.println("Opcao invalida!");
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

   public static void cadastrarTutorPets(Scanner sc) {
      while (true) {
         System.out.println("Digite nome do tutor (vazio encerra cadastro tutor): ");
         String nome = sc.nextLine();
         if (nome.isEmpty()) 
            break;
         System.out.print("Digite dia (dd), mes (mm) e ano (aaaa) de nascimento do tutor (separados por espacos): ");
         int d = sc.nextInt();
         int m = sc.nextInt();
         int a = sc.nextInt();
         sc.nextLine();
         if (!validaData(d, m, a)) {
            System.out.println("Erro! cadastro encerrado: data invalida");
            return;
         }
         LocalDate dataNasc = LocalDate.of(a, m, d);
         System.out.println("Digite endereco do tutor/pet: ");
         String endereco = sc.nextLine();
         Tutor tutor = new Tutor(geraCodTutor(), nome, dataNasc, endereco);
         while (true) {
            System.out.println("Digite nome do pet (vazio encerra cadastro pet): ");
            String nomePet = sc.nextLine().trim();
            if (nomePet.isEmpty()) 
               break;
            System.out.print("Digite o tipo do pet: ");
            String tipo = sc.nextLine().trim();
            Pet pet = new Pet(nomePet, tipo);
            tutor.add(pet);
            System.out.println("Pet cadastrado com sucesso!");
         }
         tutores.add(tutor);
         System.out.println("Tutor cadastrado com sucesso!");
      }
   }

   public static void imprimirCadastro() {
      // percorre lista de tutores, imprime toString()
      System.out.println("\nCadastro De Tutores & Pets:\n");
      for (Tutor tutor : tutores) {
         System.out.println(tutor.toString());
      }
   }

   public static void buscarPorCodigo(Scanner sc) {
      System.out.print("Digite o codigo do tutor para buscar: ");
      int cod = sc.nextInt();
      sc.nextLine();
      boolean encontrado = false;
      for (Tutor tutor : tutores) {
         if (tutor.getCod() == cod) {
            System.out.println("\nTutor encontrado:");
            System.out.println(tutor.toString());
            encontrado = true;
            break;
         }
      }
      if (!encontrado) {
         System.out.println("Tutor com codigo " + cod + " nao encontrado!");
      }
   }


   public static void excluirPorCodigo(Scanner sc) {
      System.out.print("Digite o codigo do tutor para excluir: ");
      int cod = sc.nextInt();
      sc.nextLine();
      boolean excluir = false;
      for (int i = 0; i<tutores.size(); i++) {
         if (tutores.get(i).getCod() == cod) {
            tutores.remove(i);
            System.out.println("Tutor "+cod+" removido com sucesso!");
            excluir = true;
            break;
         }
      }
      if (!excluir) {
         System.out.println("Tutor "+cod+" nao existe!");
      }
   }

   private static boolean validaData(int d, int m, int a) {
      if (d < 1 || m < 1 || m > 12 || a < 1) {
         System.out.println("Erro!, programa encerrado: Data Invalida");
         return false;
      }
      int DiasNoMes;
      if (m == 2) {
         boolean bissexto = (a % 4 == 0 && a % 100 != 0) || (a % 400 == 0);
         if (bissexto) {
            DiasNoMes = 29;
         }
         else {
            DiasNoMes = 28;
         }
      } else if (m == 4 || m == 6 || m == 9 || m == 11) {
         DiasNoMes = 30;
      } else {
         DiasNoMes = 31;
      }
      return d <= DiasNoMes;
   }
}
