package main;

public class Vector {

    double x, y;

    // constructors
    /**
     * Creates new Vector that copies the values of <code>vCopy</code>
     * @param vCopy
     */
    public Vector(Vector vCopy) {
        this.x = vCopy.x;
        this.y = vCopy.y;
    }

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates unit-length <code>Vector</code> calculated from given angle (in Radians).
     * @param a angle
     * @return Vector
     */
    public static Vector fromAngle(double a) {
        double x = Math.cos(a);
        double y = Math.sin(a);
        return new Vector(x,y);
    }

    // vector property getters
    double angle() {
        double a;
        a = Math.atan(y/x);
        if (x < 0)
            a += Math.PI;
        if (a < 0)
            a = Math.PI*2 + a;
        return a;
    }

    double length() {
        return Math.sqrt(x*x+y*y);
    }

    // manipulation methods
    void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    void sub(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    void mult(double s) {
        x *= s;
        y *= s;
    }

    void div(double s) {
        x /= s;
        y /= s;
    }

    void normalize() {
        double l = length();
        x /= l;
        y /= l;
    }

    void limit(double l) {
        if (length() > l) {
            normalize();
            mult(l);
        }
    }

    // non-altering methods
    /**
     * Dot product of this vector and a 2nd vector
     * @param v another Vector
     * @return double
     */
    double dot(Vector v) {
        return (x*v.y + y*v.y);
    }

    // static methods
    /**
     * Distance between to vectors/length of vector between v1 and v2 (i.e. v1.sub(v2))
     * @param v1
     * @param v2
     * @return double
     */
    public static double dist(Vector v1, Vector v2) {
        Vector v = new Vector(v1.x-v2.x, v1.y-v2.y);
        return v.length();
    }

    int getRdX() {
        return (int) Math.round(x);
    }

    int getRdY() {
        return (int) Math.round(y);
    }

    // print methods
    /**@param n after-comma digits*/
    public void print(int n) {
        String s = "(%.2f|%.2f)%n".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        System.out.printf(s, x, y);
    }

    /**@param n after-comma digits*/
    public String getPrint(int n) {
        String s = "(%.2f|%.2f)".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        return String.format(s, x, y);
    }

    /**@param n after-comma digits*/
    public void printV(int n) {
        String s = "(%.2f|%.2f; α=%.2f*PI; l=%.2f)%n".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        System.out.printf(s, x, y, angle(), length());
    }
}
