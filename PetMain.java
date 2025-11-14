import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*; // Importa classes para persistência de dados

public class PetMain {

   private static ArrayList<Tutor> tutores = new ArrayList<>();
   private static int contadorCod = 1; 
   private static final String ARQUIVO_DADOS = "pets.dat"; // Arquivo para salvar

   public static void main(String[] args) {
   
      recuperarDados(); // 1. Recupera dados ao iniciar
      
      // Atualiza o contador de código para evitar duplicatas
      if (!tutores.isEmpty()) {
         contadorCod = tutores.get(tutores.size() - 1).getCod() + 1;
      }
   
      Scanner sc = new Scanner(System.in);
      char op;
   
      do {
         System.out.println("\n***** CADASTRO DE PETS 2.0 *****");
         System.out.println("c: cadastrar tutor+pet(s)");
         System.out.println("i: imprimir cadastro");
         System.out.println("b: buscar pets por codigo tutor");
         System.out.println("e: excluir pets por codigo tutor");
         System.out.println("x: encerrar.");
         System.out.print("Opcao escolhida: ");
         
         try {
            op = sc.next().toLowerCase().charAt(0);
         } catch (Exception e) {
            op = ' '; // Força opção inválida
         }
         sc.nextLine(); // Limpa o buffer
       
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
               System.out.println("--- Programa de cadastro encerrado ---");
               break;
            default:
               System.out.println("Opcao invalida!");
         }
      } while (op != 'x');
   
      sc.close();
   }

   // Gera código único (agora baseado no contador atualizado)
   public static int geraCodTutor() {
      return contadorCod++;
   }

   // 2. Método para cadastrar com TRATAMENTO DE EXCEÇÕES
   public static void cadastrarTutorPets(Scanner sc) {
      try {
         System.out.print("Digite nome do tutor: ");
         String nome = sc.nextLine();
         if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
         }

         System.out.print("Digite data de nasc. do tutor (dd/MM/yyyy): ");
         LocalDate dataNasc = parseData(sc.nextLine());

         System.out.print("Digite endereco do tutor/pet: ");
         String endereco = sc.nextLine();

         Tutor tutor = new Tutor(geraCodTutor(), nome, dataNasc, endereco);

         while (true) {
            System.out.print("Digite nome do pet (ou 'fim' para encerrar): ");
            String nomePet = sc.nextLine();
            if (nomePet.equalsIgnoreCase("fim")) 
               break;

            System.out.print("Digite o tipo do pet (ex: Cachorro, Gato): ");
            String tipo = sc.nextLine();
            
            System.out.print("Digite data de nasc. do pet (dd/MM/yyyy): ");
            LocalDate dataNascPet = parseData(sc.nextLine());

            Pet pet = new Pet(nomePet, tipo, dataNascPet);
            tutor.add(pet);
            System.out.println("Pet cadastrado com sucesso!");
         }
         
         tutores.add(tutor);
         gravarDados(); // 3. Salva os dados após o cadastro
         System.out.println("Tutor cadastrado com sucesso! (Cod: " + tutor.getCod() + ")");
         
      } catch (InputMismatchException e) {
         System.out.println("Erro: Entrada inválida. Use o formato correto.");
      } catch (DateTimeException e) {
         System.out.println("Erro: Data inválida. " + e.getMessage());
      } catch (Exception e) {
         System.out.println("Erro inesperado no cadastro: " + e.getMessage());
      }
   }

   // Helper para converter String em LocalDate com tratamento de exceção
   public static LocalDate parseData(String dataStr) throws DateTimeException {
      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return LocalDate.parse(dataStr, fmt);
   }

   public static void imprimirCadastro() {
      System.out.println("\nCadastro De Tutores & Pets:\n");
      if (tutores.isEmpty()) {
          System.out.println("Nenhum tutor cadastrado.");
          return;
      }
      for (Tutor tutor : tutores) {
         System.out.println(tutor.toString());
         System.out.println("--------------------");
      }
   }

   // 4. Método de busca com TRATAMENTO DE EXCEÇÕES
   public static void buscarPorCodigo(Scanner sc) {
      try {
         System.out.print("Digite o codigo do tutor para buscar: ");
         int cod = sc.nextInt();
         sc.nextLine(); // Limpa buffer
         
         Tutor tutorEncontrado = null;
         for (Tutor tutor : tutores) {
            if (tutor.getCod() == cod) {
               tutorEncontrado = tutor;
               break;
            }
         }
         
         if (tutorEncontrado != null) {
            System.out.println("\nTutor encontrado:");
            System.out.println(tutorEncontrado.toString());
         } else {
            // Mensagem de "inexistente" obrigatória
            System.out.println("Tutor com codigo " + cod + " nao encontrado!");
         }
      } catch (InputMismatchException e) {
         System.out.println("Erro: O código deve ser um número.");
         sc.nextLine(); // Limpa o buffer
      }
   }

   // 5. Método de exclusão com TRATAMENTO DE EXCEÇÕES e GRAVAÇÃO
   public static void excluirPorCodigo(Scanner sc) {
      try {
         System.out.print("Digite o codigo do tutor para excluir: ");
         int cod = sc.nextInt();
         sc.nextLine(); // Limpa buffer
         
         Tutor tutorParaRemover = null;
         for (Tutor tutor : tutores) {
            if (tutor.getCod() == cod) {
               tutorParaRemover = tutor;
               break;
            }
         }

         if (tutorParaRemover != null) {
            tutores.remove(tutorParaRemover);
            gravarDados(); // 6. Salva os dados após a exclusão
            System.out.println("Tutor " + cod + " removido com sucesso!");
         } else {
            // Mensagem de "inexistente" obrigatória
            System.out.println("Tutor " + cod + " nao existe!");
         }
      } catch (InputMismatchException e) {
         System.out.println("Erro: O código deve ser um número.");
         sc.nextLine(); // Limpa o buffer
      }
   }

   // 7. NOVO MÉTODO: Gravar dados no arquivo
   private static void gravarDados() {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
         for (Tutor tutor : tutores) {
            oos.writeObject(tutor);
         }
         System.out.println("Dados salvos com sucesso em " + ARQUIVO_DADOS);
      } catch (IOException e) {
         System.err.println("Erro ao salvar dados: " + e.getMessage());
      }
   }

   // 8. NOVO MÉTODO: Recuperar dados do arquivo
   private static void recuperarDados() {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {
         while (true) {
            Tutor tutor = (Tutor) ois.readObject();
            tutores.add(tutor);
         }
      } catch (FileNotFoundException e) {
         //
         System.out.println("Arquivo de dados não encontrado. Iniciando novo cadastro."); 
      } catch (EOFException e) {
         System.out.println("Dados recuperados com sucesso."); // Fim do arquivo, normal
      } catch (IOException | ClassNotFoundException e) {
         System.err.println("Erro ao ler dados: " + e.getMessage());
      }
   }
}