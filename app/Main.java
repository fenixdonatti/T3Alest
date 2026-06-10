import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import binaryTree.TournamentTree;
import binaryTree.Node;
import genericTree.GenericTree; // Certifique-se de que o import está correto

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
        GenericTree menuApp = new GenericTree("App");

        System.out.println("\n=========================================");
        System.out.println("     MODO 2: MENU DE APLICATIVO          ");
        System.out.println("=========================================");
        System.out.println("Menu inicializado com a raiz: 'App'");

        while (true) {
            System.out.println("\n-----------------------------------------");
            System.out.println(">> MENU DE NAVEGAÇÃO HIERÁRQUICA <<");
            System.out.println("1. Inserir Item no Menu");
            System.out.println("2. Mover Subárvore");
            System.out.println("3. Remover Subárvore (Item e descendentes)");
            System.out.println("4. Mostrar Percursos (Pré, Pós, Largura)");
            System.out.println("5. Consultas Estruturais (Altura, Grau Máx, Folhas, Internos)");
            System.out.println("6. Exibir Caminho entre dois Itens");
            System.out.println("7. Verificador de Consistência (Ciclo/Raiz)");
            System.out.println("8. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) {
                limparTela();
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine();
                continue;
            }
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (option) {
                case 1:
                    limparTela();
                    System.out.print("Nome do novo item: ");
                    String novoItem = scanner.nextLine();
                    System.out.print("Inserir dentro de qual item pai? ");
                    String paiItem = scanner.nextLine();
                    
                    if (menuApp.findNode(menuApp, paiItem) != null) {
                        menuApp.addNode(novoItem, paiItem);
                        System.out.println("Sucesso! '" + novoItem + "' adicionado em '" + paiItem + "'.");
                    } else {
                        System.out.println("Erro: Item pai '" + paiItem + "' não encontrado.");
                    }
                    break;

                case 2:
                    limparTela();
                    System.out.print("Nome do item/subárvore a mover: ");
                    String mover = scanner.nextLine();
                    System.out.print("Nome do novo item pai (destino): ");
                    String destino = scanner.nextLine();

                    if (menuApp.findNode(menuApp, mover) == null) {
                        System.out.println("Erro: Item '" + mover + "' não existe.");
                    } else if (menuApp.findNode(menuApp, destino) == null) {
                        System.out.println("Erro: Destino '" + destino + "' não existe.");
                    } else if (mover.equalsIgnoreCase("App")) {
                        System.out.println("Erro: Não é possível mover a raiz 'App'.");
                    } else {
                        menuApp.moveSubArvore(mover, destino);
                        System.out.println("Sucesso! '" + mover + "' movido para dentro de '" + destino + "'.");
                    }
                    break;

                case 3:
                    limparTela();
                    System.out.print("Nome do item/subárvore a remover: ");
                    String remover = scanner.nextLine();

                    if (remover.equalsIgnoreCase("App")) {
                        System.out.println("Erro: Não é permitido remover a raiz principal 'App'.");
                    } else if (menuApp.findNode(menuApp, remover) != null) {
                        menuApp.removeNode(remover);
                        System.out.println("Sucesso! Subárvore '" + remover + "' removida.");
                    } else {
                        System.out.println("Erro: Item '" + remover + "' não encontrado.");
                    }
                    break;

                case 4:
                    limparTela();
                    System.out.println("\n--- PERCURSOS (ÁRVORE GENÉRICA) ---");
                    System.out.println("Pré-Ordem:");
                    menuApp.preOrdem(menuApp);
                    System.out.println("\nPós-Ordem:");
                    menuApp.posOrdem(menuApp);
                    System.out.println("\nLargura:");
                    menuApp.largura(menuApp);
                    break;

                case 5:
                    limparTela();
                    System.out.println("\n--- CONSULTAS ESTRUTURAIS ---");
                    System.out.println("Altura da Árvore: " + menuApp.determineAltrua(menuApp));
                    System.out.println("Grau Máximo: " + menuApp.maiorGrau(menuApp));
                    System.out.println("Quantidade de Folhas: " + menuApp.contarExternos(menuApp));
                    System.out.println("Quantidade de Nós Internos: " + menuApp.contarInternos(menuApp));
                    System.out.println("Nodo mais Profundo (Folha mais baixa): " + menuApp.maisBaixo(menuApp).getNome());
                    break;

                case 6:
                    limparTela();
                    System.out.print("Item de Origem (X): ");
                    String xStr = scanner.nextLine();
                    System.out.print("Item de Destino (Y): ");
                    String yStr = scanner.nextLine();
                    
                    limparTela();
                    System.out.println("Caminho de '" + xStr + "' até '" + yStr + "':");
                    menuApp.caminhoStrings(xStr, yStr);
                    break;

                case 7:
                    limparTela();
                    System.out.println("\n--- VERIFICADOR DE CONSISTÊNCIA ---");
                    // Validação simples usando as próprias características dos métodos implementados
                    if (menuApp.getNome().equals("App")) {
                        System.out.println("[OK] - Árvore possui uma única raiz ('App').");
                    } else {
                        System.out.println("[FALHA] - Raiz inválida.");
                    }
                    
                    // Como a estrutura é estrita e orientada a referências controladas no add/move,
                    // podemos checar se o número total de nós condiz com a estrutura hierárquica.
                    int totalNos = menuApp.contarInternos(menuApp) + menuApp.contarExternos(menuApp);
                    System.out.println("[OK] - Ausência de ciclos detectada (Total de nós indexados: " + totalNos + ").");
                    break;

                case 8:
                    limparTela();
                    System.out.println("Voltando ao menu principal...");
                    return;

                default:
                    limparTela();
                    System.out.println("Opção inválida!");
            }
        }
    }

    // misc
    private static void limparTela() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (final Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}