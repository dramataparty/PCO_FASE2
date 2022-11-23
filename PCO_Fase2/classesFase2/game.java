import java.util.Random;

import javax.jws.soap.InitParam;
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

    /**
     * Construtor da classe Game
     * @param initGrid
     * @param g
     * @requires initGrid != null, g != null
     * @ensures Início de um Game
     */
    public Game(Symbol [][] initGrid, Random g) {
        if(validGrid(initGrid)){
            initgrid = initGrid;
            this.g = g;
        }
        else{
            initgrid = new Symbol[nCols][nRows];
            this.g = new Random();
        }
    }

    /**
     * Construtor da classe Game
     * @param nRows
     * @param nCols
     * @param diffic
     * @param g
     * @requires nRowns != null, nCols != null, diffic != null, g != null
     * @ensures Início de um Game
     */
    public Game(int nRows, int nCols, int diffic, Random g) {
        Symbol[] symbarr = Symbol.values();
        int rowindex = nRows-1;
        int colindex = nCols-1;
        for(int i = 0; i < colindex ; i++){
            Transformer t = new Transformer(SIZE_OF_PIECE,Symbol.EMPTY);
            while(diffic>rowindex){
                diffic =-1;                
            }
            for(int e = rowindex; e > (rowindex - diffic);e--){            
                initgrid[i][e] = symbarr[g.nextInt(7)-1];
            }
            t.accomodate(initgrid[i]);
        }
    }

    /**
     * Função que devolve as linhas da Grid
     * @return As linhas da Grid
     * @ensures Devolver as linhas da Grid
     */
    int linesInGrid() {
        return this.nRows;
    }
    /**
     * Função que devolve as colunas da Grid
     * @return as colunas da Grid
     * @ensures Devolver as colunas da Grid
     */
    int colsInGrid() {
        return this.nCols;
    }


    /**
     * Função que recebe uma grid e verifica se ela é válida para realizar um "Game"
     * @requires g != null
     * @param g
     * @return True se uma Grid e valida e False se uma Grid não for válida
     * @ensures Devolver True ou False, caso a variável g seja diferente de null
     */
    public static boolean validGrid(Symbol[][] g){
        boolean valgrid = true;
        for(int i = 0; i < g.length - 1;i++){
            for(int e = 0; e < g[i].length - 1;e++){
                if(g[i][e]==null){
                    valgrid=false;
                }
                if(g[i][e]!=Symbol.EMPTY && g[i+1][e]==Symbol.EMPTY){
                    valgrid=false;
                }
            }

        }
        return valgrid;
    }

    /**
     * Função que gera uma peça
     * @ensures A criação de uma peça para o Game
     */
    public void generatePiece() {
        Piece p = new Piece(g,SIZE_OF_PIECE);
        p.copy();
    }
    /**
     * Função que permuta uma peça com um valor n
     * @param n
     * @requires n != null
     * @ensures O movimento da peça no Game, caso a variável n seja diferente de null
     */
    public void permutatePiece(int n) { 
        Piece p = new Piece(g,SIZE_OF_PIECE);
        p.permutation(n);
    }

    /**
     * Função que coloca a peça na Grid/jogo
     * @requires column != null
     * @param column
     * @ensures A introdução da peça no Game, caso a variável column seja diferente de null
     */
    public void placePiece(int column) {
        Transformer traans = new Transformer(SIZE_OF_PIECE,Symbol.EMPTY);
        Piece p = new Piece(g,SIZE_OF_PIECE);
        Symbol [] symb = p.symbols();
        for(int i = 0;i<nRows;i++){
            if(initgrid[column][i]==Symbol.EMPTY && initgrid[column][i-1]!=Symbol.EMPTY){
                for(int e =SIZE_OF_PIECE;e>=0;e--){
                    initgrid[column][i] =  symb[e];

                }
            }

        }
        traans.accomodate(initgrid[column]);
        traans.eliminateSequence(initgrid[column]);
        traans.accomodate(initgrid[column]);
    }
    /**
     * Função que verifica se ainda podem ser realizadas jogadas pelo utilizador
     * @return True se for possível realizar mais jogadas ou False se não for possível realizar mais jogadas
     * @ensures Devolver True ou False
     */
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

    /**
     * Função que verifica se uma dada coluna do Game ainda tem jogadas possíveis
     * @param col
     * @requires col != null
     * @return True se for possível realizar mais jogadas ou False se não for possível realizar mais jogadas
     */
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

     /**
     * Função que calcula o score do Game em curso
     * @return O score do Game
     * @ensures Mostrar a pontuação do Game/jogo e decorrer nesse instante
     */
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
    /**
     * Função que devolve uma representação textual da peça corrente
     * @return Uma representação textual da peça atual
     * @ensures Devolver a representação textual de uma peça
     */
    public String currentPiece() {
        String ts = new Piece(g,SIZE_OF_PIECE).toString();
        return ts;
    }
   /**
     * Função que devolve a representação textual do Game
     * @return A representação textual do Game
     * @ensures Devolver a representação textual do Game/jogo a decorrer
     */
    public String toString() {
        String ts ="";
        for(int i = 0; i < initgrid.length ;i++){
            for(int e = nRows-1;e > 1;e--){
                if(e == nRows-1){
                    ts += "|";
                }
                if(initgrid[i][e] != null){
                ts += initgrid[i][e].toString();
                ts += "|";
                }
            }
            ts+="\n";
        }
        return ts;
        
    }
}

    
