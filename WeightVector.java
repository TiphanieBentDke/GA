public class WeightVector {
    /*@WEIGHT VECTORS stores the weight Depth to wD*/
    private double [] WeightVector = new double[5];

    /*@WINNING RATE corresponds to the number of game Won/ Total number of simulation
     *  it should be updated as a counter (increase or not)
     *  each time the Ai called with a evaluation function using these weights wins a game
     */
    private int WinningRate;//fitness
    /* RELATION BETWEEN FERTILITY RATE and WINNING RATE
    *   @FERTILITY RATE should be distributed accross the population , based on the players winning rate
    *   chance to be selected, random among top 20, basic chance is 1/20
    *   taking into account the fertility advantage; 1/20  + WinningRate*0,1 : HOW TO CORRECTLY RESIZE WinningRate ?
    */
    public WeightVector (double depth, double wA, double wB, double wC, double wD){
        WeightVector[0] = depth;
        WeightVector[1] = wA; //@wa weight associated to CRITERIA #1 DENSITY
        WeightVector[2] = wB; //@wB weight associated to CRITERIA #2  OPPORTUNITY
        WeightVector[3] = wC; //@wC weight associated to CRITERIA #3 IMMEDIATE GAIN
        WeightVector[4] = wD; //@wD weight associated to CRITERIA #4 DELTA AGENT
    }
    public getWeightVector(){
        return this.WeightVector;
    }
    public getWinningRate(){
        return this.WinningRate;
    }
}// end of WeightVector
