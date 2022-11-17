import java.util.Random;
public class game {

    private static final int SIZE_OF_PIECE = 3;
    private static final int PLAY_SCORE = 3;
    private static final int BASE_ELIM_POINTS = 3;
    private static final int EXTRA_ELIM_POINTS = 3;

    private Symbol [][] initgrid;
    private Random g;
    private int nRows;
    private int nCols;
    private int diffic;

    public game(Symbol [][] initGrid, Random g) {
        this.initgrid = initGrid;
        this.g = g;
    }

    public game(int nRows, int nCols, int diffic, Random g) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.diffic = diffic;
        this.g = g;
    }

    public int linesInGrid() {
        return this.nRows;
    }

    public int colsInGrid() {
        return this.nCols;
    }



    public static boolean validGrid(Symbol[][] g){
        boolean valgrid = true;
        //int colcount = 0;
        for(Symbol [] i:g){
            //colcount++;
            //if(i.length != linesInGrid()){
            //    valgrid = false;
            //}
            for(int e = 0; e < i.length - 1;e++){
                if(i[e]==null || ( (i[e] != Symbol.EMPTY) && i[e+1]== Symbol.EMPTY)){
                    valgrid = false;
                }
        }
        /** 
        if(colcount!= colsInGrid()){
            valgrid = false;
        }

        }
        */
        }
        return valgrid;
    }

    public void generatePiece() {
        // gera uma piece nova, chama o Piece do piece.java
        // usa o size of piece pra determinar
    }

    public void permutatePiece(int n) {
        //erro aqui? why tho??? 
            piece.permutation(n);
    }

    public void placePiece(int column) {
        //WIP
        // resolver imports e ver pq ntão bem
        generatePiece();
        for(int i = 0;i<g[column].length;i++){
            if(g[column[i]==Symbol.EMPTY] &&g[column][i-1]!=Symbol.EMPTY){
                for(int e =piece.p.length;e>=0;e--){
                    g[column][i] = this.piece.p[e];

                }
            }

        }
        Transformer.eliminateSequence(g[column]);
        Transformer.accomodate(g[column]);
    }

    public boolean canPlay() {
        for(Symbol [] i:initgrid){
            for(int e = 0; e < i.length - 1;e++){
                if(i[e]==null || ( (i[e] != Symbol.EMPTY) && i[e+1]== Symbol.EMPTY)){
                    return false;
                } 
            }
        }
        return true;
    }

    public boolean canPlayInColumn(int col) {
        int colb = col - 1;
        boolean jogar = false;
        Symbol [] p = this.initgrid[colb];

        for(int i = 0; i < p.length; i++) {
            if(p[i] != Symbol.EMPTY && i >= SIZE_OF_PIECE) {
                jogar = true;
            }
        }
        return jogar;
    }
    //Por terminar
    public int score() {
        
        
    }
    //Não está completo
    public String currentPiece() {
        
        return "(" + piece.piece;
    }
    //Não está completo
    public String toString() {
        return "(" + this.nRowns + "," + this.nCols + ")";
    }

    public static void main(String[] args){
        

    }
}
