
public class Solution {
boolean[] solution;
double danceStrenght=0;
public Solution(boolean[] solution, double danceSrenght) {
	this.solution = solution;
	this.danceStrenght = danceSrenght;
}
public Solution(boolean[] solution) {
	this.solution = solution;
}
public Solution(int lenght) {
	this.solution = new boolean[lenght];
}
public boolean[] getSolution() {
	return solution;
}
public void setSolution(boolean[] solution) {
	this.solution = solution;
}
public double getDanceSrenght() {
	return danceStrenght;
}
public void setDanceSrenght(double danceSrenght) {
	this.danceStrenght = danceSrenght;
}

}
