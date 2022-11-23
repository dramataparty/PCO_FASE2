/*
 * @author Diogo Forte nº 56931, Tiago Pereira nº55854
 */
import java.util.Random;
public class Piece {

    private Symbol[] p;
    private Random generator;
    private int size;


    /**
     * Construtor da classe Piece
     * @param p
     * @requires p != null
     * @ensures Geração/Formação de uma piece/peça
     */
    public Piece(Symbol[] p) {
        this.p = p;
    }

    /**
     * Construtor da classe Piece
     * @param generator
     * @param size
     * @requires generator != null, size != null
     * @ensures Geração/Formação de uma piece/peça
     */
    public Piece (Random generator, int size) {
        Symbol[] symbarr = Symbol.values();
        Random gen = new Random();
        this.size = size;
        Symbol[] currp = new Symbol[size];
        for(int i = 0; i < size; i++) {
            int valor = gen.nextInt(7);
            if(valor == 7) {
                valor = gen.nextInt(7);    
            }
            currp[i]=symbarr[valor];
        }
        this.p=currp;
        this.generator = gen;
    }

    /**
     * Função que realiza uma permutação com um dado valor n
     * @param n
     * @requires n != null
     * @ensures O movimento da peça no Piece, caso a variável n seja diferente de null
     */
    public void permutation(int n) {
        for(int i=0;i<size;i++){
            int currind = i + n;
            while(currind>size-1){
                currind = currind-size;
            }
            this.p[i] = this.p[currind];
        }
    }

    /**
     * Função que devolve os símbolos disponíveis do Game/jogo
     * @return uma lista com todos os Símbolos
     * @ensures Devolve uma lista com todos os Símbolos caso a variável p seja diferente de null
     */
    public Symbol[] symbols() {
        return p;
    }

    /**
     * Função que devolve uma cópia de uma Piece/peça
     * @return uma cópia de/da Piece/peça
     * @ensures A devolução de uma cópia de uma Piece/peça
     */
    public Piece copy() {
        return new Piece(p); 
    }
    
    /**
     * Função que devolve a representação textual de uma Piece/peça
     * @return Uma representação textual de uma Piece/peça
     * @ensures A representação textual de uma Piece/peça 
     */
    public String toString() {
        return "(" + p.toString() + ")";
    }
}