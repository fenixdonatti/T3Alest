
package genericTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class Node {

    private String nome;
    private ArrayList<Node> subArvores;



    public String getNome() {
        return nome;
    }

    //node handling

    public void moveSubArvore(String mover, String ondeColocar) {

        Node parent = findParent(this, mover);
        Node mover1 = findNode(this, mover);
        Node destino = findNode(this, ondeColocar);


        parent.subArvores.remove(mover1);
        destino.subArvores.add(mover1);


    }

    public void addNode(String novo, String onde) {
        Node pai = findNode(this, onde);
        if (pai != null) {
            pai.subArvores.add(new Node(novo));
        }
    }

    public void removeNode(String removerEssa) {
        Node remover = findNode(this, removerEssa);
        Node parent = findParent(this, removerEssa);

         parent.subArvores.remove(remover);
        remover.subArvores.clear();//desnecessario

    }
    /// find nodes
    private Node findParent(Node atual, String target) {
        for (Node filho : atual.getSubArvores()) {
            if (filho.getNome().equals(target)) return atual;
            Node resultado = findParent(filho, target);
            if (resultado != null) return resultado;
        }
        return null;
    }

    public Node findNode (Node atual, String target){

        if (atual.getNome().equals(target)){
            return atual;
        }
        for (Node filho : atual.getSubArvores()){

            Node result = findNode(filho, target);
            if (result  != null) return result;
        }


        return null;
    }

    ////consultas
    public int determineAltrua(Node atual) {
        int maiorAltura = 0;

        if (atual.getSubArvores().isEmpty()) {
            return 0;
        }
        for (Node filho : atual.getSubArvores()){
            if (filho != null) {

                int alturaFilho = determineAltrua(filho);
                //determineAltrua(filho);
                if (alturaFilho > maiorAltura) {
                    maiorAltura = alturaFilho;
                }

            }

    }
        return maiorAltura + 1;
    }


    public int contarFilhos(Node pai){

        int cont = 0;

        if (pai.getSubArvores() != null)
        {
            for (Node filho : pai.getSubArvores()){

                cont++;

            }

        }
        return cont;
    }

    public int maiorGrau(Node atual) {
        int maiorGrau =  contarFilhos(atual);

        for (Node filho : atual.getSubArvores()){
            if (filho != null) {


                int newba = maiorGrau(filho);

                if (newba> maiorGrau){

                    maiorGrau = newba;
                }


            }

        }
        return maiorGrau;
    }

    public int contarExternos(Node atual) {

        int cont = 0;

        if (atual.getSubArvores().isEmpty()) {
            return 1;
        }
        for (Node filho : atual.getSubArvores()){
            if (filho != null) {

                  cont = cont + contarExternos(filho);


            }

        }
        return  cont;
    }

    public int contarInternos(Node atual) {

        int cont = 0;

        if ( atual.getSubArvores().isEmpty()) {
            return 0;
        }
        cont = 1;
        for (Node filho : atual.getSubArvores()){
            if (filho != null) {

                cont = cont + contarInternos(filho);


            }

        }
        return  cont;
    }

    public Node maisBaixo(Node atual) {
        if (atual.getSubArvores().isEmpty()) {
            return atual;
        }

        Node maior = null;
        int maiorAltura = 0;

        for (Node filho : atual.getSubArvores()){
            if (filho != null) {
                int oi = determineAltrua(filho);
                if (oi > maiorAltura) {

                    maiorAltura  = oi;
                    maior = filho;
                }
            }

        }
        return maisBaixo(maior);
    }

    public boolean caminho (Node x, Node y){

        if (x== y){
            System.out.println(y.getNome());
            return true;
        }
        System.out.println(x.getNome());

        for (Node i : x.getSubArvores()){

            if (i== y){
                System.out.println(y.getNome());
                return true;
            }
            if (caminho (i, y)){return true;}

        }
        return false;
    }


    public void caminhoStrings (String nome1, String nome2){

        Node n1 = findNode(this, nome1);
        Node n2 = findNode(this, nome2);

        caminho(n1, n2);

    }

    /// PreOrdem PosOrdem e Larguraaaaaaaaaaaaaaaaaa


    public void preOrdem(Node x) {
        if (x == null) return;

        System.out.println(x.getNome());

        for (Node filho : x.getSubArvores()) {
            preOrdem(filho);
        }
    }

    public void posOrdem(Node x) {
        if (x == null) return;

        for (Node filho : x.getSubArvores()) {
            posOrdem(filho);
        }

        System.out.println(x.getNome());
    }


    //getters setters etc

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Node> getSubArvores() {
        return subArvores;
    }

    public void setSubArvores(ArrayList<Node> subArvores) {
        this.subArvores = subArvores;
    }

    public Node (String nome){

        this.nome = nome;

        this.subArvores = new ArrayList<>();


    }



}
