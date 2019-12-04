package engine;

public class Vector {
    public double v[];

    public Vector() {
        v = new double[3];
    }
    public Vector(int dim) {
        v = new double[dim];
    }
    public Vector(double vals[]) {
        v = vals;
    }

    public int dim() {
        return v.length;
    }

    public double dot(Vector vec) {
        if(this.dim() == vec.dim()) {
            double sum = 0;
            for(int i = 0; i < this.dim(); i++) {
                sum += this.v[i]*vec.v[i];
            }
            return sum;
        } else {
            return 0;
        }
    }

    public double mag() {
        double sum = 0;
        for(int i = 0; i < this.dim(); i++) {
            sum += v[i]*v[i];
        }
        return Math.sqrt(sum);
    }
}
