import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import binaryTree.TournamentTree;
import binaryTree.Node;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=================================================");
            System.out.println("                  TRABALHO 3          ");
            System.out.println("=================================================");
            System.out.println("1. Modo 1: Torneio Eliminatório (Árvore Binária)");
            System.out.println("2. Modo 2: Menu de Aplicativo (Árvore Genérica)");
            System.out.println("3. Sair do Programa");
            System.out.print("Escolha o modo que deseja testar: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    runTournamentMode(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void runTournamentMode(Scanner scanner) {
        TournamentTree tournament = new TournamentTree();

        System.out.println("\n=========================================");
        System.out.println("   MODO 1: TORNEIO ELIMINATÓRIO          ");
        System.out.println("=========================================");

        // jogadores padroes
        List<String> defaultPlayers = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank");
        tournament.createTournament(defaultPlayers);
        System.out.println("Torneio inicializado com 8 participantes padrão:");
        System.out.println(defaultPlayers);

        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println(">> MENU DO TORNEIO <<");
            System.out.println("1. Criar Novo Torneio (Digitar Participantes)");
            System.out.println("2. Registrar Vencedor de Partida");
            System.out.println("3. Mostrar Percursos (Pré, Pós, Largura)");
            System.out.println("4. Consultas Estruturais (Altura, Folhas, Internos)");
            System.out.println("5. Encontrar LCA (Partida comum entre dois)");
            System.out.println("6. Exibir Caminho entre dois Nós (printPath)");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Quantos participantes terá o torneio? ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine();
                    List<String> players = new ArrayList<>();
                    for (int i = 1; i <= qtd; i++) {
                        System.out.print("Nome do participante " + i + ": ");
                        players.add(scanner.nextLine());
                    }
                    tournament.createTournament(players);
                    System.out.println("Novo torneio criado!");
                    break;
                case 2:
                    System.out.print("Nome do jogador que venceu a partida: ");
                    String winner = scanner.nextLine();
                    if (tournament.registerWinner(winner)) {
                        System.out.println("Sucesso! " + winner + " avançou.");
                    } else {
                        System.out.println("Erro: Jogador não encontrado ou já é o Campeão.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- PERCURSOS ---");
                    System.out.println("Pré-Ordem:");
                    tournament.preOrder(tournament.root);
                    System.out.println("\nPós-Ordem:");
                    tournament.postOrder(tournament.root);
                    System.out.println("\nLargura:");
                    tournament.lengthSearch();
                    break;
                case 4:
                    System.out.println("\n--- ESTATÍSTICAS ---");
                    System.out.println("Altura: " + tournament.getHeight(tournament.root));
                    System.out.println("Folhas (Participantes): " + tournament.countLeaves(tournament.root));
                    System.out.println("Nós Internos (Partidas): " + tournament.countInternalNodes(tournament.root));
                    break;
                case 5:
                    System.out.print("Jogador 1: ");
                    String j1 = scanner.nextLine();
                    System.out.print("Jogador 2: ");
                    String j2 = scanner.nextLine();
                    Node lca = tournament.findLCA(j1, j2);
                    if (lca != null) {
                        System.out.println("LCA: [" + (lca.data.isEmpty() ? "Não definido" : lca.data) + "]");
                    } else {
                        System.out.println("Erro: Jogadores não encontrados.");
                    }
                    break;
                case 6:
                    System.out.print("Origem (From): ");
                    String from = scanner.nextLine();
                    System.out.print("Destino (To): ");
                    String to = scanner.nextLine();
                    tournament.printPath(from, to);
                    break;
                case 7:
                    System.out.println("Saindo do modo Torneio...");
                    return; // sai do método e volta para o menu principal do main
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void runMenuMode(Scanner scanner) {
        System.out.println("\n=========================================");
        System.out.println("     MODO 2: MENU DE APLICATIVO      ");
        System.out.println("=========================================");
        
        // TODO: Menu de aplicação (Árvore genérica)
    }
}
