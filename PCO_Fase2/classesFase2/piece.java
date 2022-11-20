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
        this.size = size;
        this.generator = generator;
        for(int i = 0; i < size; i++) {
            int valor = generator.nextInt((7));
            this.p[i]=symbarr[valor];
            if(valor == 7) {
                valor = generator.nextInt((7));
            }

        }
    }

    public void permutation(int n) {
        for(int i=0;i<this.p.length;i++){
            int currind = i + n;
            while(currind>this.p.length-1){
                currind = currind-this.p.length;
            }
            this.p[i] = this.p[currind];
        }
    }

    public Symbol[] symbols() {
        return this.p;
    }

    public Piece copy() {
        return new Piece(this.generator, this.size);
    }

    public String toString() {
        return "(" + this.p + ")";
    }
}