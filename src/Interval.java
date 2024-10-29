
public class Interval {
double start;
double end;
public Interval(double s,double e){
	this.start=s;
	this.end=e;
}
public double getStart() {
	return start;
}
public double getEnd() {
	return end;
}

public double getRandom() {
	double rand=Math.random();
	return(this.start+(this.end-this.start)*rand);
}
@Override
public String toString() {
	return "Interval [start=" + start + ", end=" + end + "]";
}

}
