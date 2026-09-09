package dmit2015.model;

public class Cricle {

    private double radius;

    public double getRadius(){
        return  radius;
    }

    public void setRadius(double radius){
        this.radius=radius;
    }

    public Cricle(){
        radius = 1;
    }

    public  double area(){

        return Math.PI * Math.pow(radius,2);
    }

    public  static  void main(String[] args){

        Cricle currentCricle= new Cricle();
        currentCricle.setRadius(5);

        System.out.printf("Radius: %.2f, Area= %.2f\n",currentCricle.getRadius(), currentCricle.area());


    }

}
