
package genericTree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class GenericTree {

    private String nome;
    private ArrayList<GenericTree> subArvores;



    public String getNome() {
        return nome;
    }

    //node handling

    public void moveSubArvore(String mover, String ondeColocar) {

        GenericTree parent = findParent(this, mover);
        GenericTree mover1 = findNode(this, mover);
        GenericTree destino = findNode(this, ondeColocar);


        parent.subArvores.remove(mover1);
        destino.subArvores.add(mover1);


    }

    public void addNode(String novo, String onde) {
        GenericTree pai = findNode(this, onde);
        if (pai != null) {
            pai.subArvores.add(new GenericTree(novo));
        }
    }

    public void removeNode(String removerEssa) {
        GenericTree remover = findNode(this, removerEssa);
        GenericTree parent = findParent(this, removerEssa);

         parent.subArvores.remove(remover);
        remover.subArvores.clear();//desnecessario

    }
    /// find nodes
    private GenericTree findParent(GenericTree atual, String target) {
        for (GenericTree filho : atual.getSubArvores()) {
            if (filho.getNome().equals(target)) return atual;
            GenericTree resultado = findParent(filho, target);
            if (resultado != null) return resultado;
        }
        return null;
    }

    public GenericTree findNode (GenericTree atual, String target){

        if (atual.getNome().equals(target)){
            return atual;
        }
        for (GenericTree filho : atual.getSubArvores()){

            GenericTree result = findNode(filho, target);
            if (result  != null) return result;
        }


        return null;
    }

    ////consultas
    public int determineAltrua(GenericTree atual) {
        int maiorAltura = 0;

        if (atual.getSubArvores().isEmpty()) {
            return 0;
        }
        for (GenericTree filho : atual.getSubArvores()){
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


    public int contarFilhos(GenericTree pai){

        int cont = 0;

        if (pai.getSubArvores() != null)
        {
            for (GenericTree filho : pai.getSubArvores()){

                cont++;

            }

        }
        return cont;
    }


    public void largura(GenericTree raiz) {
        if (raiz == null) return;

        LinkedList<GenericTree> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            GenericTree atual = fila.poll();
            System.out.println(atual.getNome());

            for (GenericTree filho : atual.getSubArvores()) {
                fila.add(filho);
            }
        }
    }
    public int maiorGrau(GenericTree atual) {
        int maiorGrau =  contarFilhos(atual);

        for (GenericTree filho : atual.getSubArvores()){
            if (filho != null) {


                int newba = maiorGrau(filho);

                if (newba> maiorGrau){

                    maiorGrau = newba;
                }


            }

        }
        return maiorGrau;
    }

    public int contarExternos(GenericTree atual) {

        int cont = 0;

        if (atual.getSubArvores().isEmpty()) {
            return 1;
        }
        for (GenericTree filho : atual.getSubArvores()){
            if (filho != null) {

                  cont = cont + contarExternos(filho);


            }

        }
        return  cont;
    }

    public int contarInternos(GenericTree atual) {

        int cont = 0;

        if ( atual.getSubArvores().isEmpty()) {
            return 0;
        }
        cont = 1;
        for (GenericTree filho : atual.getSubArvores()){
            if (filho != null) {

                cont = cont + contarInternos(filho);


            }

        }
        return  cont;
    }

    public GenericTree maisBaixo(GenericTree atual) {
        if (atual.getSubArvores().isEmpty()) {
            return atual;
        }

        GenericTree maior = null;
        int maiorAltura = 0;

        for (GenericTree filho : atual.getSubArvores()){
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

    public boolean caminho (GenericTree x, GenericTree y){

        if (x== y){
            System.out.println(y.getNome());
            return true;
        }
        System.out.println(x.getNome());

        for (GenericTree i : x.getSubArvores()){

            if (i== y){
                System.out.println(y.getNome());
                return true;
            }
            if (caminho (i, y)){return true;}

        }
        return false;
    }


    public void caminhoStrings (String nome1, String nome2){

        GenericTree n1 = findNode(this, nome1);
        GenericTree n2 = findNode(this, nome2);

        caminho(n1, n2);

    }

    /// PreOrdem PosOrdem e Larguraaaaaaaaaaaaaaaaaa


    public void preOrdem(GenericTree x) {
        if (x == null) return;

        System.out.println(x.getNome());

        for (GenericTree filho : x.getSubArvores()) {
            preOrdem(filho);
        }
    }

    public void posOrdem(GenericTree x) {
        if (x == null) return;

        for (GenericTree filho : x.getSubArvores()) {
            posOrdem(filho);
        }

        System.out.println(x.getNome());
    }


    //getters setters etc

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<GenericTree> getSubArvores() {
        return subArvores;
    }

    public void setSubArvores(ArrayList<GenericTree> subArvores) {
        this.subArvores = subArvores;
    }

    public GenericTree (String nome){

        this.nome = nome;

        this.subArvores = new ArrayList<>();


    }



}
