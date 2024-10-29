import java.util.ArrayList;

public class Bso {
int maxIter;
int flip;
int Nbees;
int maxChances;
int nbChances;
int locIter;
int lenght;  //the number of variables in the instance 
ArrayList<Bee> BeesTab;
ArrayList<Solution> searchArea;
ArrayList<Solution> danceTab;
Solution Sref;
ArrayList<Solution> tabooTab;

public Bso(int maxIter, int flip, int nbees, int maxChances, int locIter,int l, ArrayList<Bee> beesTab,
		ArrayList<Solution> danceTab, Solution sref, ArrayList<Solution> tabooTab) {
	super();
	this.maxIter = maxIter;
	this.flip = flip;
	Nbees = nbees;
	this.maxChances = maxChances;
	this.locIter = locIter;
	BeesTab = beesTab;
	this.lenght=l;
	this.danceTab = danceTab;
	this.Sref = sref;
	this.tabooTab = tabooTab;
	nbChances=maxChances;
}


public Bso(int maxIter, int flip, int nbees, int maxChances, int locIter,int l, Solution sref) {
	super();
	this.maxIter = maxIter;
	this.flip = flip;
	Nbees = nbees;
	this.maxChances = maxChances;
	this.locIter = locIter;
	this.lenght=l;
	BeesTab =new ArrayList<Bee>();
	this.creatBeeSwarm();
	this.danceTab = new ArrayList<Solution>();
	this.Sref = sref;
	this.tabooTab = new ArrayList<Solution>();
	this.searchArea = new ArrayList<Solution>();
	nbChances=maxChances;
}
public int getMaxIter() {
	return maxIter;
}
public void setMaxIter(int maxIter) {
	this.maxIter = maxIter;
}
public int getFlip() {
	return flip;
}
public void setFlip(int flip) {
	this.flip = flip;
}
public int getNbees() {
	return Nbees;
}

public void setNbees(int nbees) {
	Nbees = nbees;
}
public int getMaxChances() {
	return maxChances;
}
public void setMaxChances(int maxChances) {
	this.maxChances = maxChances;
}
public int getLocIter() {
	return locIter;
}
public void setLocIter(int locIter) {
	this.locIter = locIter;
}
public ArrayList<Bee> getBeesTab() {
	return BeesTab;
}
public void setBeesTab(ArrayList<Bee> beesTab) {
	BeesTab = beesTab;
}
public ArrayList<Solution> getDanceTab() {
	return danceTab;
}
public void setDanceTab(ArrayList<Solution> danceTab) {
	this.danceTab = danceTab;
}
public Solution getSref() {
	return Sref;
}
public void setSref(Solution sref) {
	this.Sref = sref;
}
public ArrayList<Solution> getTabooTab() {
	return tabooTab;
}
public void setTabooTab(ArrayList<Solution> tabooTab) {
	this.tabooTab = tabooTab;
}


public int getLenght() {
	return lenght;
}


public void setLenght(int lenght) {
	this.lenght = lenght;
}


public void creatBeeSwarm() {
	Bee b;
	for(int i=0;i<this.getNbees();i++) {
		b=new Bee(this.getLocIter(),this.getLenght());
		this.BeesTab.add(b);
	}
}


public void findSearchPoints(Solution ref) {
	System.out.print("sref");
	this.print(this.Sref);
	System.out.print("ref");
	this.print(ref);
	int h=0,p;
	Solution s=new Solution(ref.solution,ref.danceStrenght);
	ArrayList<Solution> list=new ArrayList<Solution>();
	while(list.size()<this.getNbees() & h<this.flip) {
		s.setSolution(ref.solution);
		s.setDanceSrenght(ref.danceStrenght);
		p=0;
		do {
			s.solution[this.flip*p+h]=!s.solution[this.flip*p+h];
			p++;
		}while(flip*p+h<this.lenght);
		list.add(s);
		h++;
	}
	this.searchArea=list;
	System.out.print("sref");
	this.print(this.Sref);
	System.out.print("ref");
	this.print(ref);
}

public void findSearchPoints() {
	//System.out.print("sref1");
	//this.print(Sref); 
	Solution cachSref=new Solution(Sref.solution,Sref.danceStrenght);
	int h=0,p;
	boolean[] s1=new boolean[75],s2=new boolean[75];
	s2=this.getSref().getSolution().clone();
	ArrayList<Solution> list=new ArrayList<Solution>();
	while(list.size()<this.getNbees() & h<this.flip) {
		s1=s2.clone();
		p=0;
		do {
			s1[this.flip*p+h]=!s2[this.flip*p+h];
			p++;
		}while(flip*p+h<this.lenght);
		Solution s=new Solution(s1);
		list.add(s);
		h++;
	}
	this.searchArea=list;
	Sref=cachSref;
	//System.out.print("sref2");
	//this.print(Sref);
}
public void assignSearchPoint() {
	int i;
	Interval inter=new Interval(0, searchArea.size());
	if(searchArea.size()==this.Nbees) {
		for(i=0;i<searchArea.size();i++)
		{
			BeesTab.get(i).setSearchLocal(searchArea.get(i));
		}
	}
	else {
		if(searchArea.size()<this.Nbees) {
			for(i=0;i<searchArea.size();i++)
			{
				BeesTab.get(i).setSearchLocal(searchArea.get(i));
			}
			for(int j=i;j<this.Nbees;j++)
			{
				BeesTab.get(j).setSearchLocal(searchArea.get((int)inter.getRandom()));
			}
		}
		else {
			for(int j=0;j<this.Nbees;j++)
			{
				BeesTab.get(j).setSearchLocal(searchArea.get((int)inter.getRandom()));
			}
		}
	}
}
public Solution selectSref(Instance ins) {
	double deltaF;
	Solution best=new Solution(danceTab.get(0).getSolution().clone(),danceTab.get(0).getDanceSrenght());
	//System.out.print("danctab 0");
	//this.print(this.danceTab.get(0));
	//System.out.print("best1");
	//this.print(best);
	if(danceTab.size()==1)
		return best;
	else {
	for(int i=1;i<danceTab.size();i++) {
		if(fitnessFonc(danceTab.get(i).solution,ins)>fitnessFonc(best.solution,ins))
		{
		best.setSolution(danceTab.get(i).getSolution().clone());
		best.setDanceSrenght(danceTab.get(i).getDanceSrenght());
		}
		//System.out.print("danceTab "+i);
		//this.print(this.danceTab.get(i));
	}
	//System.out.print("best2");
	//this.print(best);
	deltaF=fitnessFonc(best.solution,ins)-fitnessFonc(Sref.solution,ins);
	//System.out.print("deltaF="+deltaF);
	if(deltaF>0) {
		this.Sref.setSolution(best.solution.clone());
		this.Sref.setDanceSrenght(best.danceStrenght);
		if(this.nbChances<this.maxChances)
			nbChances=maxChances;
		return best;
	}
	else
	{
		nbChances--;
		if(nbChances>0) {return this.Sref;}
		else {
			if(this.diversity()>this.diversity(this.maxDiversityFromDanceTab()))
			{
				nbChances=maxChances;
				return this.Sref;
			}
			else {
				this.setSref(this.maxDiversityFromDanceTab());
				nbChances=maxChances;
				return this.maxDiversityFromDanceTab();
			}
		}
	}}
}
public Solution selectSref() {
	double deltaF;
	Solution best=new Solution(danceTab.get(0).getSolution().clone(),danceTab.get(0).getDanceSrenght());
	this.print(this.danceTab.get(0));
	if(danceTab.size()==1)
		return best;
	else {
	for(int i=1;i<this.getDanceTab().size();i++) {
		if(this.getDanceTab().get(i).getDanceSrenght()>best.getDanceSrenght())
		{
		best.setSolution(danceTab.get(i).getSolution().clone());
		best.setDanceSrenght(danceTab.get(i).getDanceSrenght());
		this.print(this.danceTab.get(i));
		}	                                      
		                                         }
	deltaF=best.getDanceSrenght()-Sref.getDanceSrenght();

	if(deltaF>0) {
		this.setSref(best);
		if(this.nbChances<this.maxChances)
			nbChances=maxChances;
		return best;
	}
	else
	{
		nbChances--;
		if(nbChances>0) {return this.Sref;}
		else {
			if(this.diversity()>this.diversity(this.maxDiversityFromDanceTab()))
			{
				nbChances=maxChances;
				return this.Sref;
			}
			else {
				this.setSref(this.maxDiversityFromDanceTab());
				nbChances=maxChances;
				return this.maxDiversityFromDanceTab();
			}
		}
	}}
}
public double fitnessFonc(Bee p,Instance inst) {
	return inst.evaluate(p);
}
public double fitnessFonc(boolean sref[],Instance inst) {
	return inst.evaluate(sref);
}

public int distance(boolean x1[],boolean x2[]) {
	int distance=0;
	for(int i=0;i<x1.length;i++) {
		if(x1[i]!=x2[i])
			distance++;
	}
	return distance;
}

public int diversity(Solution s) {
	int i=1,min;
	min=this.distance(s.getSolution(), this.tabooTab.get(0).getSolution());
	while(i<this.tabooTab.size())
    {
		if(min>this.distance(s.getSolution(), this.tabooTab.get(i).getSolution()))
			min=this.distance(s.getSolution(), this.tabooTab.get(i).getSolution());
		i++;
	}
	return min;
}
public int diversity() {
	int i=1,min;
	min=this.distance(this.Sref.getSolution(), this.tabooTab.get(0).getSolution());
	while(i<this.tabooTab.size())
    {
		if(min>this.distance(Sref.getSolution(), this.tabooTab.get(i).getSolution()))
			min=this.distance(Sref.getSolution(), this.tabooTab.get(i).getSolution());
		i++;
	}
	return min;
}
public Solution maxDiversityFromDanceTab() {
	int i=1,max;
	boolean[] bool;
	double fit;
	max=this.diversity(this.danceTab.get(0));
	bool=this.danceTab.get(0).getSolution().clone();
	fit=this.danceTab.get(0).danceStrenght;
	while(i<this.danceTab.size())
    {
		if(max<this.diversity(this.danceTab.get(i))) {
			max=this.diversity(this.danceTab.get(i));
		    bool=this.danceTab.get(i).getSolution().clone();
			fit=this.danceTab.get(i).danceStrenght;
		}
		    i++;
	}
	Solution s=new Solution(bool,fit);
	return s;
}
public void print(Solution ss) {
	  System.out.print("print solution[");
		for(int s=0;s<ss.solution.length;s++) {
			if(ss.solution[s]==true)
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println("]");
}
}
