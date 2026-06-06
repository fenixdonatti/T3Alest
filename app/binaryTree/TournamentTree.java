package binaryTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TournamentTree {
    Node root;


    // cria o torneio com a lista dos participantes
    public void createTournament(List<String> participants) {
        // verificações
        if (participants == null || participants.isEmpty()) return;

        Queue<Node> queue = new LinkedList<>();
        for (String p : participants) {
            queue.add(new Node(p));
        }

        // agrupa as folhas em pares criando nodes internos vazios até restar a raiz
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

                queue.add(parent);
            }
        }

        this.root = queue.poll();
    }

    // encontra node pelo nome do participante
    public Node findNode(Node node, String data) {
        if (node == null) return null;
        if (node.data.equalsIgnoreCase(data)) return node;

        Node found = findNode(node.left, data);
        if (found != null) return found;

        return findNode(node.right, data);
    }

    // define vencedor de uma partida
    public boolean registerWinner(String winnerName) {
        Node node = findNode(root, winnerName);

        // participante ja é o campeão ou nn encontrou
        if (node == null || node.parent == null) return false; 

        node.parent.data = winnerName;
        return true;
    }

    // pre ordem
    public void preOrder(Node node) {
        if (node == null) return;

        System.out.println("[" + (node.data.isEmpty() ? "Não definido" : node.data) + "] ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // pos ordem
    public void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println("[" + (node.data.isEmpty() ? "Não definido" : node.data) + "] ");
    }

    // largura
    public void lengthSearch() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("[" + (current.data.isEmpty() ? "Não definido" : current.data) + "] ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public int getHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public int countLeaves(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int countInternalNodes(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0;

        return 1 + countInternalNodes(node.left) + countInternalNodes(node.right);
    }

    // LCA
    public Node findLCA(String x, String y) {
        Node n1 = findNode(root, x);
        Node n2 = findNode(root, y);
        if (n1 == null || n2 == null) return null;

        Set<Node> ancestors = new HashSet<>();

        Node current = n1;
        while (current != null) {
            ancestors.add(current);
            current = current.parent;
        }

        current = n2;
        while (current != null) {
            if (ancestors.contains(current)) return current;
            current = current.parent;
        }

        return null;
    }

public void printPath(String from, String to) {
        Node start = findNode(root, from);
        Node end = findNode(root, to);
        if (start == null || end == null) {
            System.out.println("Participante(s) não encontrado(s).");
            return;
        }

        Node lca = findLCA(from, to);
        if (lca == null) return;

        // caminho subindo do node start até o LCA
        List<String> upPath = new ArrayList<>();
        Node current = start;
        while(current != lca) {
            String nomeItem = current.data.isEmpty() ? "Não definido" : current.data;
            upPath.add(nomeItem);
            current = current.parent;
        }

        // caminho descendo do LCA até o node end
        List<String> downPath = new ArrayList<>();
        current = end;
        while (current != lca) {
            String nomeItem = current.data.isEmpty() ? "Não definido" : current.data;
            downPath.add(0, nomeItem); // insere no início para inverter a ordem na descida
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
