import java.util.ArrayList;

public class Instance {
int Nclause;
int Nvar;
ArrayList<Clause> instance;

public Instance(int nc,int nv) {
	this.instance=new ArrayList<Clause>();
	this.Nclause=nc;
	this.Nvar=nv;
}

public void addClause(Clause c) {
	instance.add(c);
}

public void addClause(Clause c,int i) {
	instance.add(i, c);
}

public Clause getClause(int i) {
	return this.instance.get(i);
}
public int getNclause() {
	return Nclause;
}

public int getNvar() {
	return Nvar;
}

public ArrayList<Clause> getInstance() {
	return instance;
}

public boolean ifSolution(boolean tab[]) {
	boolean solution=true;
	if(tab.length==this.Nvar) {
	solution=this.getClause(0).interpretation(tab);
	for(int i=1; i<Nclause;i++) {
		solution=solution&this.getClause(i).interpretation(tab);
	}
	return solution;
	}
	else 
		return false;
}

public double evaluate(Bee bee) {
	double fitness=0;
	if(bee.searchLocal.solution.length==this.Nvar) {
	for(int i=0;i<Nclause;i++){
	if(this.getClause(i).interpretation(bee.searchLocal.solution))
	fitness=fitness+0.0030769230769231;
	}
	return fitness*100;
	}
	else 
		return fitness*100;
}

public double evaluate(boolean x[]) {
	double fitness=0;
	if(x.length==this.Nvar) {
	for(int i=0;i<Nclause;i++){
	if(this.getClause(i).interpretation(x))
	fitness=fitness+0.0030769230769231;
	}
	return fitness*100;
	}
	else 
		return fitness*100;
}
@Override
public String toString() {
	return "Instance [Nclause=" + Nclause + ", Nvar=" + Nvar + ", instance=" + instance + "]";
}

}
