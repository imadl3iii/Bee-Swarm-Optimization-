
public class Bee {
 Solution searchLocal;
 int iter;
 
 public Bee(Solution searchLocal, int iter) {
	super();
	this.searchLocal = searchLocal;
	this.iter = iter;
}

 public Bee(int iter,int lenght) {
		this.searchLocal = new Solution(lenght);
		this.iter = iter;
	}


public Solution getSearchLocal() {
	return searchLocal;
}




public void setSearchLocal(Solution searchLocal) {
	this.searchLocal = searchLocal;
}




public int getIter() {
	return iter;
}




public void setIter(int iter) {
	this.iter = iter;
}

public Solution startPoint(int x) {
	Solution s=new Solution(x);
	for(int i=0;i<x;i++)
		s.solution[i]=false;
	return s;
}


public void BeeInit(int lenght) {
	 this.setSearchLocal(this.startPoint(lenght));
 }
public void BeeInit(int lenght,Instance ins) {
	 //bee init at start point = 0
	 //this.setSearchLocal(this.startPoint(lenght));
	
	 //bee init random
	boolean tab[]=new boolean[lenght];
	Interval interval=new Interval(0,lenght);
	for(int i=0;i<lenght;i++) {
		if(interval.getRandom()<(interval.getStart()+interval.end)/2)
			tab[i]=true;
		else
			tab[i]=false;
	}
	this.searchLocal.setSolution(tab);
	this.updateDance(ins);
	 
}


 public void updateDance(Instance i) {
	 this.searchLocal.setDanceSrenght(this.dance(this.getSearchLocal().getSolution(), i));
 }
 
 
 public void exploration() {
	 Interval inter=new Interval(0, searchLocal.getSolution().length);
	 int index;
	 for(int i=0;i<iter;i++) {
		 index=(int)inter.getRandom();
		 searchLocal.getSolution()[index]=!searchLocal.getSolution()[index];
	 }
 }
 public void exploration(Instance ins) {
	 Interval inter=new Interval(0, searchLocal.getSolution().length);
	 int index;
	 for(int i=0;i<iter;i++) {
		 index=(int)inter.getRandom();
		 searchLocal.getSolution()[index]=!searchLocal.getSolution()[index];
	 }
	 this.searchLocal.setDanceSrenght(this.dance(this.getSearchLocal().getSolution(), ins));
 }
 public double dance(boolean[] t,Instance i) {
	  return i.evaluate(t);
 }
 
 public void print() {
	  System.out.print("search local solution from Bee[");
		for(int s=0;s<searchLocal.solution.length;s++) {
			if(searchLocal.solution[s]==true)
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println("]");
 }
 

}
