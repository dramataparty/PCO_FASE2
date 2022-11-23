/*
 * @author Diogo Forte nº 56931, Tiago Pereira nº55854
 */
import java.util.Random;
public class Piece {

    private Symbol[] p;
    private Random generator;
    private int size;

    public Piece(Symbol[] p) {
        this.p = p;
    }

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

    public void permutation(int n) {
        for(int i=0;i<size;i++){
            int currind = i + n;
            while(currind>size-1){
                currind = currind-size;
            }
            this.p[i] = this.p[currind];
        }
    }

    public Symbol[] symbols() {
        return p;
    }

    public Piece copy() {
        return new Piece(p); 
    }
    public String toString() {
        return "(" + p.toString() + ")";
    }
}