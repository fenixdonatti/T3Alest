package binaryTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TournamentTree {
    // raiz da arvore
    public Node root;

    // cria o torneio com a lista dos participantes
    public void createTournament(List<String> participants) {
        // verificações
        if (participants == null || participants.isEmpty())
            return;

        // lista auxiliar para os nodos dos participantes
        Queue<Node> queue = new LinkedList<>();
        for (String p : participants) {
            queue.add(new Node(p));
        }

        // enquanto houver mais de um nodo na fila, 
        // ainda não chegou na raiz
        // o for vai rodar de par em par
        while (queue.size() > 1) {
            int size = queue.size();
            for (int i = 0; i < size / 2; i++) {

                Node left = queue.poll();
                Node right = queue.poll();

                Node parent = new Node(""); // node interno vazio de partida
                parent.left = left;
                parent.right = right;
                left.parent = parent;
                right.parent = parent;

                // coloca o pai na queue auxiliar
                queue.add(parent);
            }

            // se sobrar 1 elemento sozinho na rodada ele passa diretamente para a proxima fase
            // evita o bug de loop infino :p
            if (size % 2 != 0) {
                queue.add(queue.poll());
            }
        }

        // quando sobrar um elemento na fila ele vai ser o do topo
        // da arvore, a final do campeonato
        //              root
        //          /         \
        //     parent         parent
        //    /      \        /     \
        //[nome]  [nome]   [nome]  [nome]
        this.root = queue.poll();
    }

    // encontra node pelo nome do participante
    // coloca a raiz como primeiro argumento
    public Node findNode(Node node, String data) {
        // chegou ao fim
        if (node == null)
            return null;
        // achou
        if (node.data.equalsIgnoreCase(data))
            return node;

        // recursão da parte esquerda e retorna se for diferente de null
        Node found = findNode(node.left, data);
        if (found != null)
            return found;

        // se nn achou na esquerda faz a mesma recursão so que na direita
        return findNode(node.right, data);

        // Raiz
        //  v
        // Esquerda inteira
        //  v
        // Direita inteira
    }

    // define vencedor de uma partida
    public boolean registerWinner(String winnerName) {
        Node node = findNode(root, winnerName);

        // participante ja é o campeão ou nn encontrou
        if (node == null || node.parent == null)
            return false;

        // coloca o nome no nodo pai
        node.parent.data = winnerName;
        return true;
    }

    // pre ordem
    // raiz -> esquerda -> direita
    public void preOrder(Node node) {
        if (node == null)
            return;

        // pega primeiro a raiz, visita a esquerda e depois a direita
        // se for vazio o node retorna a string nao defido.
        System.out.println("[" + (node.data.isEmpty() ? "Não definido" : node.data) + "] ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // pos ordem
    // esquerda -> direita -> raiz
    public void postOrder(Node node) {
        if (node == null)
            return;

        // mesma coisa do pre ordem, so que o contrario. Visita toda a esquerda, depois direita
        // e ai sim a raiz.
        postOrder(node.left);
        postOrder(node.right);
        System.out.println("[" + (node.data.isEmpty() ? "Não definido" : node.data) + "] ");
    }

    // largura
    // visita os nodes nivel por nivel
    public void lengthSearch() {
        if (root == null)
            return;

        // cria uma lista aux e adiciona a raiz nela
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // enquanto a fila nn estiver vazia, retira o proximo nodo e imprime o valor
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("[" + (current.data.isEmpty() ? "Não definido" : current.data) + "] ");

            // se tiver filho na esquerda adiciona na fila aux
            // se tiver filho na direita adiciona na fila aux
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
    }

    // retorna o valor da altura da arvore
    public int getHeight(Node node) {

        // se tiver vazia é -1
        if (node == null)
            return -1;
        
        // pega o maior valor de um dos lados e adiciona 1 (o maior lado + a raiz)
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    // contar nodos sem filhos
    public int countLeaves(Node node) {
        if (node == null)
            return 0;
        // se nao tiver filhos (for a ultima camada) retorna 1
        if (node.left == null && node.right == null)
            return 1;

        // soma recursivamente o valor das folhas da esquerda e o valor das folhas da direita
        // 1 + 1 + 1 + ... + 1
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // conta nodos internos (partidas)
    // (pelo menos um filho)
    public int countInternalNodes(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 0;

        // se tiver filho é uma partida, ent soma +1 + a contagem dos nodos internos (recursão)
        return 1 + countInternalNodes(node.left) + countInternalNodes(node.right);
    }

    // LCA
    // indica a primeira partida onde dois participantes poderiam competir
    public Node findLCA(String x, String y) {

        // localiza os nodos que tiverem os nomes x,y
        Node n1 = findNode(root, x);
        Node n2 = findNode(root, y);
        if (n1 == null || n2 == null)
            return null;

        // cria um conjunto e faz o caminho até o topo e vai armazenando os nodos no caminho

        // começa pelo jogador 1 e vai subindo pelo parent dele até chegar na raiz.
        // enquanto sobe, vai armazenando o node que passou no Set.
        Set<Node> ancestors = new HashSet<>();
        Node current = n1;
        while (current != null) {
            ancestors.add(current);
            current = current.parent;
        }

        // faz a mesma coisa mas agora com o segundo participante
        // A cada subida, verifica se passou pelo mesmo nodo que o n1 passou
        // se ambos passaram por ali retorna o nodo atual
        current = n2;
        while (current != null) {
            if (ancestors.contains(current))
                return current;
            current = current.parent;
        }

        return null;

        // O primeiro nodo que ele encontrar que também esteja guardado no set ancestors
        // é o ancestral comum mais baixo, ai retorna ele
    }

    public void printPath(String from, String to) {
        // encontra os nodes e o LCA (proxima partida)
        Node start = findNode(root, from);
        Node end = findNode(root, to);
        if (start == null || end == null) {
            System.out.println("Participante(s) não encontrado(s).");
            return;
        }
        Node lca = findLCA(from, to);
        if (lca == null)
            return;

        // monta uma lista do caminho subida, vai do nodo inicial e sobe de pai em pai até encontrar
        // o ancestral em comum lca.
        // mais complicado tive que usar ajuda exterior :(((

        // sobe do node start, de pai em pai adicionando os nomes na lista
        List<String> upPath = new ArrayList<>();
        Node current = start;
        while (current != lca) {
            String nomeItem = current.data.isEmpty() ? "Não definido" : current.data;
            upPath.add(nomeItem);
            current = current.parent;
        }

        // monta o caminho descida, sobe do node final até o lca
        // segunda subida, simula descida.
        // começa no node end e vai subindo em direção ao lca.
        List<String> downPath = new ArrayList<>();
        current = end;
        while (current != lca) {
            String nomeItem = current.data.isEmpty() ? "Não definido" : current.data;
            downPath.add(0, nomeItem); // se forçar a colocar na posição 0, os nodes vao sendo colocados para o fim.
            current = current.parent;
        }

        // impressão do resultado formatado no console
        System.out.print("Caminho: ");
        for (String step : upPath) {
            System.out.print(step + " -> ");
        }

        String lcaNome = lca.data.isEmpty() ? "Não definido" : lca.data;
        System.out.print("[" + lcaNome + "]");

        for (String step : downPath) {
            System.out.print(" -> " + step);
        }
        System.out.println();
    }
}
