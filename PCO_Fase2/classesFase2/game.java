import java.util.Random;
public class Game {

    private static final int SIZE_OF_PIECE = 3;
    private static final int PLAY_SCORE = 10;
    private static final int BASE_ELIM_POINTS = 200;
    private static final int EXTRA_ELIM_POINTS = 50;

    private Symbol [][] initgrid;
    private Random g;
    private int nRows;
    private int nCols;
    private int diffic;

    public Game(Symbol [][] initGrid, Random g) {
        this.initgrid = initGrid;
        this.g = g;
        for(int i = 0; i < SIZE_OF_PIECE; i++) {
            int valor = g.nextInt((7)+1);
            if(valor == 7) {
                valor = g.nextInt((7)+1);
            }
        }
    }

    public Game(int nRows, int nCols, int diffic, Random g) {
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
        Piece z = new Piece(g, SIZE_OF_PIECE);
        Symbol[] simbolos = Symbol.values();
        for(int i = 0; i < SIZE_OF_PIECE; i++) {
            z.symbols()[i] = simbolos[g.nextInt(simbolos.length-1)];
        }
    }

    public void permutatePiece(int n) {
        //erro aqui? why tho??? 
        Piece b = new Piece(g, SIZE_OF_PIECE);
            b.permutation(n);
    }

    public void placePiece(int column) {
        //WIP
        // resolver imports e ver pq ntão bem
        generatePiece();

        Piece c = new Piece(g, SIZE_OF_PIECE);

        for(int i = 0;i<initgrid[column].length;i++){
            if(initgrid[column][i]==Symbol.EMPTY && initgrid[column][i-1]!=Symbol.EMPTY){
                for(int e =c.symbols().length;e>=0;e--){
                    initgrid[column][i] = c.symbols()[e];

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

    public int currentScore = 0;

    //Por terminar
    public int score() {
        // if(permutatePiece()) {
        //     currentScore +=  PLAY_SCORE;
        // }
        // if(Transformer.eliminateSequence == 3) {
            //currentScore += BASE_ELIM_POINTS;
        // }
        // if(Transformer.eliminateSequence > 3)
        // currentScore = currentScore + BASE_ELIM_POINTS + (EXTRA_ELIM_POINTS * (Transformer.eliminateSequence - SIZE_OF_PIECE));
        //}
        return currentScore;
        
    }
    //Não está completo
    public String currentPiece() {

        Piece a = new Piece(g, SIZE_OF_PIECE);

        return "(" + a + ")";
    }
    //Não está completo
    public String toString() {
        return "(" + this.nRows + "," + this.nCols + ")";
    }

    public static void main(String[] args){
        

    }
}
