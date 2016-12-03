import java.util.*;

public class QUIZ4BISMONTE {




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> variablesForT = new ArrayList<>();
        ArrayList<String> variablesForW = new ArrayList<>();
        HashSet<ArrayList<String>> combinations = new HashSet<>();

        Map<ArrayList<String>,Double> jointDistributionMap = new HashMap<ArrayList<String>, Double>() ;



        System.out.print("ENTER THE NUMBER OF VARIABLES FOR X: ");
        int numberOfVariablesForT = sc.nextInt();
        System.out.print("ENTER THE NUMBER OF VARIABLES FOR Y: ");
        int numberOfVariablesForW = sc.nextInt();

        System.out.println("");
        for (int index = 0; index < numberOfVariablesForT; index++) {
            System.out.print("\nINPUT THE VARIABLE FOR X NO."+(index+1)+": ");
            variablesForT.add(sc.next().trim());
        }

        for (int index = 0; index < numberOfVariablesForW; index++) {
            System.out.print("\nINPUT THE VARIABLE FOR Y NO.: "+(index+1)+": ");
            variablesForW.add(sc.next().trim());
        }

        System.out.println("");

        for (String t : variablesForT) {
            for(String w:variablesForW){
                ArrayList<String> joint = new ArrayList<>();
                joint.add(t);
                joint.add(w);
                combinations.add(joint);
                System.out.print("PLEASE INPUT JOINT PROBABILITY FOR" + joint + ": ");
                Double value = sc.nextDouble();
                jointDistributionMap.put(joint, value);
            }
        }




       // ArrayList<HashMap<ArrayList<String>,Double>> marginalDistributionTables = new ArrayList<>();
        //para sa W?
        HashMap<String,Double> marginalDistributionTableForW = new HashMap<>();
        for (String t: variablesForT) {

            Double value = 0.0;
            for(ArrayList<String> combo:combinations){
                if(combo.contains(t)){
                    value+=jointDistributionMap.get(combo);
                }
            }
            marginalDistributionTableForW.put(t,value);
        }

        System.out.println("\nP(Y)");
        for(Map.Entry<String,Double> row:marginalDistributionTableForW.entrySet()){
            System.out.println(row.getKey()+"-"+String.format("%.2f",row.getValue()));
        }
        System.out.println("");

        HashMap<String,Double> marginalDistributionTableForT = new HashMap<>();
        for (String w: variablesForW) {

            Double value = 0.0;
            for(ArrayList<String> combo:combinations){
                if(combo.contains(w)){
                    value+=jointDistributionMap.get(combo);
                }
            }
            marginalDistributionTableForT.put(w,value);
        }

        System.out.println("P(X)");
        for(Map.Entry<String,Double> row:marginalDistributionTableForT.entrySet()){
            System.out.println(row.getKey()+"-"+String.format("%.2f",row.getValue()));
        }
    }
}
