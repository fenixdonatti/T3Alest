package binaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
