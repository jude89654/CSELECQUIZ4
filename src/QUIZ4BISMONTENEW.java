import java.math.BigDecimal;
import java.util.*;

public class QUIZ4BISMONTENEW {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("INPUT NO. OF RANDOM VARIABLES");
        int numOfRandomVariables = sc.nextInt();

        ArrayList<ArrayList<String>> variablesDomains = new ArrayList<>();

        for (int varIndex = 0; varIndex < numOfRandomVariables; varIndex++) {
            System.out.print("INPUT NUMBER OF DOMAIN FOR VARIABLE NO." + varIndex + " :");
            int numOfDomainForCurrentVariable = sc.nextInt();

            ArrayList<String> domains = new ArrayList<>();
            for (int domainVar = 0; domainVar < numOfDomainForCurrentVariable; domainVar++) {
                System.out.print("INPUT DOMAIN NO " + domainVar + " FOR VAR NO "+varIndex +":");
                domains.add(sc.next());
            }
            variablesDomains.add(domains);

        }
        System.out.println(variablesDomains);

        ArrayList<ArrayList<String>> combinations = new ArrayList<>();
        GeneratePermutations(variablesDomains,combinations,0,new ArrayList<String>());



        ArrayList<HashMap<String,BigDecimal>> marginalTables = new ArrayList<>();

        HashMap<ArrayList<String>,BigDecimal> jointDistributionTable = new HashMap<>();

        for(ArrayList<String> domainCombos: combinations){
            System.out.print("PLEASE INPUT JOINT PROBABILITY FOR "+domainCombos+" : ");
            BigDecimal value = new BigDecimal(sc.next());
            jointDistributionTable.put(domainCombos,value);
        }

        for (int varIndex = 0; varIndex < numOfRandomVariables; varIndex++) {
            HashMap<String,BigDecimal> tempHashMap = new HashMap<>();

            //for every domain
            for (String domain: variablesDomains.get(varIndex) ) {
                BigDecimal value = new BigDecimal("0.0000");

                //for each rows sa joint dist table
                for (Map.Entry<ArrayList<String>,BigDecimal> entry:jointDistributionTable.entrySet()){

                    //if kaparehas niya ang domain ng current var, add the values
                    if(entry.getKey().get(varIndex).equals(domain))value=value.add(entry.getValue());
                }

                tempHashMap.put(domain,value);

            }
            marginalTables.add(tempHashMap);
        }


        int counter =0;
        //for each table
        for(HashMap<String,BigDecimal> currentMarginalTable : marginalTables){
            System.out.println("\nMARGINAL TABLE FOR VAR "+(++counter));
            //for each entry sa current table
            for (Map.Entry<String,BigDecimal> marginalTableEntry: currentMarginalTable.entrySet()) {
                System.out.println(marginalTableEntry.getKey()+"="+marginalTableEntry.getValue());
            }
        }
        
        

    }


    public static void GeneratePermutations(ArrayList<ArrayList<String>> Lists, ArrayList<ArrayList<String>> result, int depth, ArrayList<String> current) {
        if (depth == Lists.size()) {
            result.add(current);
            return;
        }
            for (int i = 0; i < Lists.get(depth).size(); ++i) {
                ArrayList<String> bago = new ArrayList<>(current);
                bago.add(Lists.get(depth).get(i));
                GeneratePermutations(Lists, result, depth + 1, bago);
            }

    }
}
