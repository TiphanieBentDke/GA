package cage;

import java.util.ArrayList;
import java.util.Random;

public class GA {

    /* METHOD #1: CUSTOMIZED CROSS_OVER
     * spilt the vector into k integer part
     * k = 5 alternatively inherited from P1 or P2
     * cut points: where ; random or fixed ?
     *
     * */
    public ArrayList <WeightVector> CycleCrossOver(ArrayList <WeightVector> BestInGeneration){
        ArrayList <WeightVector> NextGeneration = new ArrayList <WeightVector>();

        while(BestInGeneration.IsEmpty() == false){//repeat action for each element of the population
            /* We opted for an evenly distributed fertility Rate
            *   In other words,the chance for each of the top 20 parent to be selected is equal
            *   Otherwise, the risk of overfiting would drastically increase
            */
            int P1 = Random.nextDouble()*10;
            int P2 = 1+ Random.nextDouble()*10;

            WeightVector P1 = BestInGeneration.get(P1); //selecting Parents for next Generation
            Weightvector P2 = BestInGeneration.get(P2);

            /*NB
            * We solved the check for duplicates by using a mutation Rate
            * */
            WeightVector Child1 = new WeightVector(P1[0], P2[1],P1[2], P2[3],P1[4]);//cut at every position alternating P1 to P2
            WeightVector Child2 = new WeightVector(P2[0], P1[1],P2[2], P1[3],P2[4]);//cut at every position alternating P2 to P1

            WeightVector Child3 = new WeightVector(P1[0], P1[1],P2[2], P2[3],P2[4]);// cut at 2nd position P1--> P2
            WeightVector Child4 = new WeightVector(P2[0], P2[1],P1[2], P1[3],P1[4]);// cut at 2nd position P2--> P1

            WeightVector Child5 = new WeightVector(P1[0], P1[1],P1[2], P2[3],P2[4]);// cut at 3rd position P1--> P2
            WeightVector Child6 = new WeightVector(P2[0], P2[1],P2[2], P1[3],P1[4]);// cut at 3rd position P2--> P1

            WeightVector Child7 = new WeightVector(P1[0], P2[1],P2[2], P1[3],P1[4]);// insert P2 in 2nd and 3rd position
            WeightVector Child8 = new WeightVector(P2[0], P1[1],P1[2], P2[3],P2[4]);// insert P1 in 2nd and 3rd position

            WeightVector Child9 = new WeightVector(P1[0], P2[1],P1[2], P2[3],P2[4]);// insert P2 in 2nd, 4rth and 5th position
            WeightVector Child10 = new WeightVector(P2[0], P1[1],P2[2], P1[3],P1[4]);// insert P1 in 2nd, 4rth and 5th position

            NextGeneration.add(Child1); //add it to the nextGeneration
            NextGeneration.add(Child2);
            NextGeneration.add(Child3);
            NextGeneration.add(Child4);
            NextGeneration.add(Child5);
            NextGeneration.add(Child6);
            NextGeneration.add(Child7);
            NextGeneration.add(Child8);
            NextGeneration.add(Child9);
            NextGeneration.add(Child10);

            /*  Deleting the elements from the ArrayList :
            *   Thus no chance for a parent to reproduce twice or to form another couple: NO POLYGAMY ALLOWED here !
            */
            BestInGeneration.remove(P1);
            BestInGeneration.remove(P2);
        }
        return NextGeneration;
    }

    /* METHOD #2: SELECTING BEST IN GENERATION
    * ORDER THE Element of the Pop based on the winning Rate obtained by the AI
    * by playing with these weights against:  the random Ai or itself (which version?)
     * select the best P% percent of a population
     * TO DO, FIND optimal P value
     * */
    public ArrayList <WeightVector> SelectingBest(ArrayList <WeightVector> Generation){
        int P = (Generation.size() * 20)/100; //  we select the P best Elements, here 20 percents
        int CompensationValue = 100 - P; //insures that pop_Size is maintained !

        ArrayList <double []> Ranking = new ArrayList <double []>();

        // SELECT AN ARRAY SORTING ALGO
        for (i = 0; i < Generation.size(); i++){
            int CurrentScore = Generation.get(i).getWinningRate();
            if( CurrentScore > CurrentMax){

                    //ORDERING : PICK AN ALGO

                CurrentMax = CurrentScore;
            }
        }
    }


    /* METHOD #3: FIRST GENERATION
     * CREATES THE ELEMENTS WEIGHT VECTORS OF THE INITIAL GENERATION
     * RANDOM GENERATION UNDER 2 CONSTRAINTS
     * Depth is comprised between 2 and 10
     * any of the four wA to wD are comprise between zero and one: they must sum to ONE !
     * */
    public ArrayList <WeightVector> firstGeneration(){
        ArrayList <WeightVector> Population = new ArrayList <WeightVector>();
        int PopSize = 100;  /*the population size must be an even number& must be maintained constant throughout the experiment*/

        for(int i=0; i < PopSize; i++) {
            /* LIMITS
            *   random assignment of values
            *   constraint, wA to wD should sum to One
            *   depth [2;10]  TO DO EXPERIMENT OBSERVE IMPACT ON INCREASE OVER QUALITY !!!!!
            */
            double wA = 0;
            double wB = 0;
            double wC = 0;
            double wD = 0;

            double [] Constraints = AdjustConstraint(wA, wB, wC, wD);

            double depth = (int)(Math.random() * ((10 - 2) + 1)) + 2; // depth varies from 2 to 10

            WeightVector PopElement = new WeightVector(/*Depth*/Constraints[0], /*wA*/Constraints[1], /*wB*/Constraints[2], /*wC*/Constraints[3], /*wD*/Constraints[4]);
            Population.add(PopElement);
        }//end of loop creating the population
        return Population;
    }

/* METHOD #4: ADJUST CONSTRAINT
* INSURES THAT THE RANDOM VALUES GENERATED OF wA to wB SUM UP to 1
* */
    public double [] AdjustConstraint(double wA, double wB, double wC, double wD){
        double []ConstrainedWeights = new double[4];

        double sumWeight = wA + wB + wC+ wD +2 ; //artificially insures that it enters the while loop
        while(sumWeight != 1) {
            wA = Random.nextDouble();
            wB = Random.nextDouble();
            wC = Random.nextDouble();
            wD = Random.nextDouble();
            sumWeight - 2;

            if (sumWeight != 1) {
                AdjustConstraint(wA, wB, wC, wD);
            }
        }//end of while loop

        ConstrainedWeights [0] = wA;
        ConstrainedWeights [1] = wB;
        ConstrainedWeights [2] = wC;
        ConstrainedWeights [3] = wD;

        return  ConstrainedWeights;
    }

}//end of GA
