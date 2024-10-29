import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

// CREATED BY IMADEDDIN DELL
public class ResolveSAT {
    //Impelmentation de l'algorithme BSO 
	//start
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//reservé une instance du probléme
		Instance instance=new Instance(325 , 75);
		//PATH to the dataset
		String nomF="C:\\Users/Dell/Desktop/meta hauristique/uuf75-325/UUF75.325.100/uuf75-0100.cnf";
		//String nomF="C:\\Users/Dell/Desktop/meta hauristique/uf75-325/ai/hoos/Shortcuts/UF75.325.100/uf75-01.cnf";
		//Dans tout les benchmarks uuf75 faut enlevé l'espace du prmier tuple pour permetre l input des données 
		try{
			//input the benchmark 
			InputStream ips=new FileInputStream(nomF); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String mot1="";
			String mot2="";
			String mot3="";
			Literal lit1=null;
			Literal lit2=null;
			Literal lit3=null;
			int i = 0;
			while ((ligne=br.readLine())!=null){
				if(ligne.startsWith("c")||ligne.startsWith("p")||ligne.startsWith("%")||ligne.startsWith("0")){
				}
				else{
					Clause clause=null;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot1=mot1 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot1)<0) 
						lit1=new Literal(Integer.parseInt(mot1)*-1, true);
					else 
						lit1=new Literal(Integer.parseInt(mot1), false);
					mot1="";
					i++;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot2=mot2 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot2)<0) 
						lit2=new Literal(Integer.parseInt(mot2)*-1, true);
					else 
						lit2=new Literal(Integer.parseInt(mot2), false);
					mot2="";
					i++;
					while((ligne.charAt(i)!=' ')&&(i<ligne.length())){
						mot3=mot3 + ligne.charAt(i);
						i++;
					}
					if(Integer.parseInt(mot3)<0) 
						lit3=new Literal(Integer.parseInt(mot3)*-1, true);
					else 
						lit3=new Literal(Integer.parseInt(mot3), false);
					mot3="";
					i++;
                  clause=new Clause(lit1, lit2, lit3);
                  instance.addClause(clause);
				}
				i=0;
			}
		br.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		//System.out.println(instance);
		//--------------start BSO processing-------------------
		//A bee initiates a search from a starting point "Sref"
		Bee beeInit=new Bee(15,instance.getNvar());
		beeInit.BeeInit(instance.getNvar(), instance);
		//beeInit.print();
		//put the parameters for BSO
        Bso bso=new Bso(35, 5, 10, 3, 15, instance.getNvar(), beeInit.searchLocal);
		//Bso bso=new Bso(25, 3, 6, 2, 10, instance.getNvar(), beeInit.searchLocal);
		//System.out.println("print bee init fitness "+ bso.fitnessFonc(beeInit, instance)); 
		//System.out.println("print bee init fitness "+ beeInit.searchLocal.danceStrenght); 
        int it=0;
        int i;
        // reiterate the process until maxIter 
        while(it<bso.maxIter) {
        	//The Sref is stored in a taboo list
        	bso.getTabooTab().add(bso.getSref());
        	// define search area from Sref
        	bso.findSearchPoints();
        	// Each point find from sref is assignes to a Bee
        	bso.assignSearchPoint();
        	//each bee strat exploration from start point then performs a dance and store it to the dance table
        	for(i=0;i<bso.Nbees;i++) {
        		bso.getBeesTab().get(i).exploration(instance);
        		bso.danceTab.add(bso.getBeesTab().get(i).getSearchLocal());
        	}
        	it++;
        	//the selection of the best value to become the new Sref to reiterate the process
        	//bso.selectSref();
        	bso.selectSref(instance);
        }
        //print results
        bso.print(bso.Sref);
		System.out.println("the fitness of the solution obtained is : "+ bso.fitnessFonc(bso.Sref.solution, instance));
		System.out.println("is it the solution ? "+ instance.ifSolution(bso.Sref.solution)); 
	}
	
	

}
