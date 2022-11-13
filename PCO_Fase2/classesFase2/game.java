public class Game {
    

    int linesInGrid() = ;
        
        

    
    int colsInGrid() = ;




    public static boolean validGrid(Symbol[][] g){
        boolean valgrid = true;
        int colcount = 0;
        for(Symbol [] i:g){
            colcount++;
            if(i.length != linesInGrid){
                valgrid = false;
            }



            for(int e = 0; e < i.length - 1;e++){
                if(i[e]==null || ( (i[e] != Symbol.EMPTY) && i[e+1]== Symbol.EMPTY )){
                    valgrid = false;
                }
            }
        if(colcount!=colsInGrid){
            valgrid = false;
        }

        }
        return valgrid;

    }



    public static void main(String[] args){
        

    }
}