
public class Literal {
int var;
boolean neg;
Literal(int v,boolean b){
	this.var=v;
	this.neg=b;
}
public int getVar() {
	return var;
}
public boolean isNeg() {
	return neg;
}
@Override
public String toString() {
	return "Literal [var=" + var + ", neg=" + neg + "]";
}

}
