import java.util.ArrayList;

public class Clause {
ArrayList<Literal> list;
public Clause() {
	list=new ArrayList<Literal>();
}
public Clause(Literal l1,Literal l2,Literal l3) {
	list=new ArrayList<Literal>();
	list.add(l1);
	list.add(l2);
	list.add(l3);
}
public Clause(ArrayList<Literal> tab) {
	this.list=tab;
}
public ArrayList<Literal> getList() {
	return list;
}
public void setList(ArrayList<Literal> list) {
	this.list = list;
}

public Literal getLit(int i) {
	return this.list.get(i);
}
public void setLit(int i,Literal lit) {
	list.add(i, lit);
}

public boolean interpretation(boolean tab[]) {
	boolean v1,v2,v3;
	v1=tab[list.get(0).var-1];
	if(list.get(0).isNeg())
		v1=!v1;
	v2=tab[list.get(1).var-1];
	if(list.get(1).isNeg())
		v2=!v2;
	v3=tab[list.get(2).var-1];
	if(list.get(2).isNeg())
		v3=!v3;
	
	return v1||v2||v3;
}
@Override
public String toString() {
	return "Clause [list=" + list + "]";
}


}
