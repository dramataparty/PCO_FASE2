/*
 * @author Diogo Forte nº 56931, Tiago Pereira nº55854
 */
import java.util.Random;
public class piece {

    private Symbol[] p;
    private Random generator;
    private int size;

    public piece(Symbol[] p) {
        this.p = p;
    }

    public piece (Random generator, int size) {
        this.generator = generator;
        for(int i = 0; i < size; i++) {
            int valor = generator.nextInt((7)+1);
            if(valor == 7) {
                valor = generator.nextInt((7)+1);
            }
        }
        this.size = size;
    }

    public void permutation( int n) {
        this.size = size + n;
    }

    public Symbol[] symbols() {
        return this.p;
    }

    public piece copy() {
        return new piece(this.generator, this.size);
    }

    public String toString() {
        return "(" + this.p + ")";
    }
}