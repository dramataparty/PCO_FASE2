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
        if(validGrid(initGrid)){
            this.initgrid = initGrid;
            this.g = new Random();
        }
    }

    public Game(int nRows, int nCols, int diffic, Random g) {
        Symbol[] symbarr = Symbol.values();
        Symbol [][] game = new Symbol[nCols][nRows];
        this.initgrid = game;
        this.nCols=nCols;
        this.nRows=nRows;
        this.diffic=diffic;
        int rowindex = nRows-1;
        int colindex = nCols-1;
        for(int i = 0; i < colindex ; i++){
            Transformer t = new Transformer(SIZE_OF_PIECE,Symbol.EMPTY);
            Random gen = new Random();
            while(diffic>rowindex){
                diffic =-1;                
            }
            for(int e = rowindex; e > (rowindex - diffic);e--){            
                game[i][e] = symbarr[gen.nextInt(6)];
            }
            t.accomodate(game[i]);
        }
    }

    int linesInGrid() {
        return this.nRows;
    }

    int colsInGrid() {
        return this.nCols;
    }



    public static boolean validGrid(Symbol[][] g){
        boolean valgrid = true;
        for(int i = 0; i < g.length;i++){
            for(int e = 0; e < g[i].length - 1;e++){
                if(g[i][e]==null){
                    valgrid=false;
                }
                if(g[i][e]!= Symbol.EMPTY && g[i][e+1]==Symbol.EMPTY){
                  valgrid=false;  
                }
            }
        }
        return valgrid;
    }

    public void generatePiece() {
        Piece p = new Piece(g,SIZE_OF_PIECE);
        p.copy();
    }

    public void permutatePiece(int n) { 
        Piece p = new Piece(g,SIZE_OF_PIECE);
        p.permutation(n);
    }

    public void placePiece(int column) {
        Symbol [][] game = new Symbol[nCols][nRows];
        Transformer traans = new Transformer(SIZE_OF_PIECE,Symbol.EMPTY);
        Piece p = new Piece(g,SIZE_OF_PIECE);
        Symbol [] symb = p.symbols();
        for(int i = 0;i<game[column].length;i++){
            if(game[column][i]==Symbol.EMPTY && game[column][i-1]!=Symbol.EMPTY){
                for(int e =SIZE_OF_PIECE;e>=0;e--){
                    game[column][i] =  symb[e];

                }
            }

        }
        traans.accomodate(game[column]);
        traans.eliminateSequence(game[column]);
        traans.accomodate(game[column]);
        this.initgrid = game;
    }

    public boolean canPlay() {
        for(int i = 0 ; i< nCols; i++){
            for(int e = 0; e < nRows - 1;e++){
                if(initgrid[i][e]==null || ( (initgrid[i][e] != Symbol.EMPTY) && initgrid[i][e+1]== Symbol.EMPTY)){
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
            //int currentScore = 0;
            //if(permutatePiece()) {
            //    currentScore +=  PLAY_SCORE;
            //    }
            //    if(Transformer.eliminateSequence == 3) {
            //    currentScore += BASE_ELIM_POINTS;
            //    }
            //    if(Transformer.eliminateSequence > 3){
            //    currentScore = currentScore + BASE_ELIM_POINTS + (EXTRA_ELIM_POINTS * (Transformer.eliminateSequence - SIZE_OF_PIECE));
            //}
        return currentScore;
}
    
    public String currentPiece() {
        Piece p = new Piece(g,SIZE_OF_PIECE);
        Symbol [] symb = p.symbols();
        String ts ="";
        for(int i = 0; i < SIZE_OF_PIECE ;i++){
             ts = (symb[i]).toString() + "\n";
        }
        return ts;
    }
   
    public String toString() {
        return "( " + this.nRows + " , " + this.nCols + " )";
    }

}
