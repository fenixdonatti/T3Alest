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
            System.out.println("                  TRABALHO 3                  ");
            System.out.println("=================================================");
            System.out.println("1. Modo 1: Torneio Eliminatório (Árvore Binária)");
            System.out.println("2. Modo 2: Menu de Aplicativo (Árvore Genérica)");
            System.out.println("3. Sair do Programa");
            System.out.print("Escolha o modo que deseja testar: ");

            if (!scanner.hasNextInt()) {
                limparTela();
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do ENTER

            switch (choice) {
                case 1:
                    limparTela();
                    runTournamentMode(scanner);
                    break;
                case 2:
                    limparTela();
                    runMenuMode(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    return;
                default:
                    limparTela();
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void runTournamentMode(Scanner scanner) {
        TournamentTree tournament = new TournamentTree();

        System.out.println("\n=========================================");
        System.out.println("   MODO 1: TORNEIO ELIMINATÓRIO          ");
        System.out.println("=========================================");

        // Jogadores padrões
        List<String> defaultPlayers = Arrays.asList("Fulano", "Fulana", "Beltrano", "Beltrana");
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

            if (!scanner.hasNextInt()) {
                limparTela();
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine();
                continue;
            }
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do ENTER

            switch (option) {
                case 1:
                    limparTela();
                    System.out.print("Quantos participantes terá o torneio? ");
                    int qtd = scanner.nextInt();
                    scanner.nextLine(); // Limpa buffer
                    List<String> players = new ArrayList<>();
                    for (int i = 1; i <= qtd; i++) {
                        System.out.print("Nome do participante " + i + ": ");
                        players.add(scanner.next());
                    }
                    scanner.nextLine(); // Limpa o resto da linha
                    limparTela();
                    tournament.createTournament(players);
                    System.out.println("Novo torneio criado!");
                    break;
                case 2:
                    limparTela();
                    System.out.print("Nome do jogador que venceu a partida: ");
                    String winner = scanner.next();
                    scanner.nextLine(); // Limpa buffer
                    limparTela();
                    if (tournament.registerWinner(winner)) {
                        System.out.println("Sucesso! " + winner + " avançou.");
                    } else {
                        System.out.println("Erro: Jogador não encontrado ou já é o Campeão.");
                    }
                    break;
                case 3:
                    limparTela();
                    System.out.println("\n--- PERCURSOS ---");
                    System.out.println("Pré-Ordem:");
                    tournament.preOrder(tournament.root);
                    System.out.println("\nPós-Ordem:");
                    tournament.postOrder(tournament.root);
                    System.out.println("\nLargura:");
                    tournament.lengthSearch();
                    break;
                case 4:
                    limparTela();
                    System.out.println("\n--- ESTATÍSTICAS ---");
                    System.out.println("Altura: " + tournament.getHeight(tournament.root));
                    System.out.println("Folhas (Participantes): " + tournament.countLeaves(tournament.root));
                    System.out.println("Nós Internos (Partidas): " + tournament.countInternalNodes(tournament.root));
                    break;
                case 5:
                    limparTela();
                    System.out.print("Jogador 1: ");
                    String j1 = scanner.next();
                    System.out.print("Jogador 2: ");
                    String j2 = scanner.next();
                    scanner.nextLine(); // Limpa buffer
                    limparTela();
                    Node lca = tournament.findLCA(j1, j2);
                    if (lca != null) {
                        System.out.println("LCA: [" + (lca.data.isEmpty() ? "Não definido" : lca.data) + "]");
                    } else {
                        System.out.println("Erro: Jogadores não encontrados.");
                    }
                    break;
                case 6:
                    limparTela();
                    System.out.print("Origem (From): ");
                    String from = scanner.next();
                    System.out.print("Destino (To): ");
                    String to = scanner.next();
                    scanner.nextLine(); // Limpa buffer
                    limparTela();
                    tournament.printPath(from, to);
                    break;
                case 7:
                    limparTela();
                    System.out.println("Saindo do modo Torneio...");
                    return; 
                default:
                    limparTela();
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void runMenuMode(Scanner scanner) {
        System.out.println("\n=========================================");
        System.out.println("     MODO 2: MENU DE APLICATIVO      ");
        System.out.println("=========================================");
        System.out.println("Espaço reservado para a árvore genérica do seu colega.");
        System.out.println("Pressione ENTER para voltar...");
        scanner.nextLine();
        limparTela();
    }


    // misc
    private static void limparTela() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Executa o comando 'cls' do Windows em um processo do terminal externo
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Executa o comando 'clear' no Linux/Mac
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}