package main;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class PVector {

    private DoubleProperty X, Y;
    private boolean isBoundX, isBoundY;

    // constructors
    /**
     * Creates new PVector that copies the values of <code>vCopy</code>.
     * @param vCopy vector to copy
     */
    public PVector(Vector vCopy) {
        X = new SimpleDoubleProperty(vCopy.x);
        Y = new SimpleDoubleProperty(vCopy.y);
    }

    public PVector() {
        X = new SimpleDoubleProperty();
        Y = new SimpleDoubleProperty();
    }

    public PVector(double x, double y) {
        X = new SimpleDoubleProperty(x);
        Y = new SimpleDoubleProperty(y);
    }

    /**
     * Creates unit-length <code>PVector</code> calculated from given angle (in Radians).
     * @param a angle
     * @return PVector
     */
    public static PVector fromAngle(double a) {
        double x = Math.cos(a);
        double y = Math.sin(a);
        return new PVector(x,y);
    }

    // both coordinates bound

    /**
     * Binds this PVector's coordinates to the given PVector's coordinates.
     * @param v PVector to bind to
     */
    public PVector(PVector v) {
        this(v.X, v.Y);
    }
    /**
     * Binds this PVector's coordinates to the given PVector's coordinates with specified manipulations (for both x and y).
     * @param v PVector to bind to
     * @param calcOperation 0=add 1=sub 2=mult 3=div
     * @param calcValue addend/subtrahend/factor/divisor
     */
    public PVector(PVector v, int calcOperation, double calcValue) {
        this(v.X, v.Y);
        if (calcOperation == 0) {
            bindX(v.X.add(calcValue));
            bindY(v.Y.add(calcValue));
        } else if (calcOperation == 1) {
            bindX(v.X.subtract(calcValue));
            bindY(v.Y.subtract(calcValue));
        } else if (calcOperation == 2) {
            bindX(v.X.multiply(calcValue));
            bindY(v.Y.multiply(calcValue));
        } else if (calcOperation == 3) {
            bindX(v.X.divide(calcValue));
            bindY(v.Y.divide(calcValue));
        }
    }
    public PVector(ReadOnlyDoubleProperty x, ReadOnlyDoubleProperty y) {
        this(x.add(0), y.add(0));
    }
    public PVector(DoubleBinding x, ReadOnlyDoubleProperty y) {
        this(x, y.add(0));
    }
    public PVector(ReadOnlyDoubleProperty x, DoubleBinding y) {
        this(x.add(0), y);
    }
    public PVector(DoubleBinding x, DoubleBinding y) {
        isBoundX = true;
        isBoundY = true;
        X = new SimpleDoubleProperty();
        Y = new SimpleDoubleProperty();
        X.bind(x);
        Y.bind(y);
    }

    // x bound to y or vice versa

    /**
     * Binds y to x with specified manipulations.
     * @param x initial value for x
     * @param bindYToX value doesn't matter
     * @param calcOperation 0=add 1=sub 2=mult 3=div
     * @param calcValue addend/subtrahend/factor/divisor
     */
    public PVector(double x, boolean bindYToX, int calcOperation, double calcValue) {
        isBoundY = true;
        X = new SimpleDoubleProperty(x);
        Y = new SimpleDoubleProperty();
        if (calcOperation == 0)
            Y.bind(X.add(calcValue));
        else if (calcOperation == 1)
            Y.bind(X.subtract(calcValue));
        else if (calcOperation == 2)
            Y.bind(X.multiply(calcValue));
        else if (calcOperation == 3)
            Y.bind(X.divide(calcValue));
    }
    public PVector(double x, boolean bindYToX) {
        isBoundY = true;
        X = new SimpleDoubleProperty(x);
        Y = new SimpleDoubleProperty();
        Y.bind(X);
    }

    /**
     * Binds x to y with specified manipulations.
     * @param y initial value for y
     * @param bindXToY value doesn't matter
     * @param calcOperation 0=add 1=sub 2=mult 3=div
     * @param calcValue addend/subtrahend/factor/divisor
     */
    public PVector(boolean bindXToY, double y, int calcOperation, double calcValue) {
        isBoundX = true;
        Y = new SimpleDoubleProperty(y);
        X = new SimpleDoubleProperty();
        if (calcOperation == 0)
            X.bind(Y.add(calcValue));
        else if (calcOperation == 1)
            X.bind(Y.subtract(calcValue));
        else if (calcOperation == 2)
            X.bind(Y.multiply(calcValue));
        else if (calcOperation == 3)
            X.bind(Y.divide(calcValue));
    }
    public PVector(boolean bindXToY, double y) {
        isBoundX = true;
        Y = new SimpleDoubleProperty(y);
        X = new SimpleDoubleProperty();
        X.bind(Y);
    }

    // vector property getters
    double angle() {
        double a;
        a = Math.atan(Y()/X());
        if (X() < 0)
            a += Math.PI;
        if (a < 0)
            a = Math.PI*2 + a;
        return a;
    }

    double length() {
        return Math.sqrt(X()*X() + Y()*Y());
    }

    // manipulation methods
    void add(Vector v) {
        if (!isBoundX)
            X.set(X() + v.x);
        if (!isBoundY)
            Y.set(Y() + v.y);
    }
    void add(double x, double y) {
        if (!isBoundX)
            X.set(X() + x);
        if (!isBoundY)
            Y.set(Y() + y);
    }

    void sub(Vector v) {
        if (!isBoundX)
            X.set(X() - v.x);
        if (!isBoundY)
            Y.set(Y() - v.y);
    }
    void sub(double x, double y) {
        if (!isBoundX)
            X.set(X() - x);
        if (!isBoundY)
            Y.set(Y() - y);
    }

    void mult(double s) {
        if (!isBoundX)
            X.set(X() * s);
        if (!isBoundY)
            Y.set(Y() * s);
    }

    void div(double s) {
        if (!isBoundX)
            X.set(X() / s);
        if (!isBoundY)
            Y.set(Y() / s);
    }

    void normalize() {
        if (!isBoundX && !isBoundY) {
            double l = length();
            X.set(X() / l);
            Y.set(Y() / l);
        }
    }

    void limit(double l) {
        if (length() > l && !isBoundX && !isBoundY) {
            normalize();
            mult(l);
        }
    }

    // non-altering methods
    /**
     * Dot product of this PVector and a 2nd PVector.
     * @param v another PVector
     * @return double
     */
    double dot(PVector v) {
        return (X()*v.getX() + Y.get()*v.getY());
    }
    /**
     * Dot product of this PVector and a 2nd (normal) vector.
     * @param v another vector
     * @return double
     */
    double dot(Vector v) {
        return (X()*v.y + Y()*v.y);
    }

    // static methods
    /**
     * @see Vector
     */
    public static double dist(PVector v1, PVector v2) {
        return new Vector(v1.X()-v2.X(), v1.Y()-v2.Y()).length();
    }

    // property actions
    void bind(PVector v) {
        bindX(v.X);
        bindY(v.Y);
    }

    void bindX(DoubleBinding x) {
        if (isBoundX)
            X.unbind();
        X.bind(x);
    }
    void bindX(ReadOnlyDoubleProperty x) {
        bindX(x.add(0));
    }

    void unbindX() {
        if (isBoundX) {
            double x = X();
            X.unbind();
            X.set(x);
        }
    }

    void bindY(DoubleBinding y) {
        if (isBoundY)
            Y.unbind();
        Y.bind(y);
    }
    void bindY(ReadOnlyDoubleProperty y) {
        bindX(y.add(0));
    }

    void unbindY() {
        if (isBoundY) {
            double y = Y();
            Y.unbind();
            Y.set(y);
        }
    }

    public DoubleProperty getPX() {
        return X;
    }

    public DoubleProperty getPY() {
        return Y;
    }

    private double X() {
        return X.get();
    }

    private double Y() {
        return Y.get();
    }

    double getX() {
        return X.get();
    }

    double getY() {
        return Y.get();
    }

    void setX(double x) {
        if (!isBoundX)
            X.set(x);
    }

    void setY(double y) {
        if (!isBoundY)
            Y.set(y);
    }

    // rounded getters
    int getRdX() {
        return (int) Math.round(X());
    }

    int getRdY() {
        return (int) Math.round(Y());
    }

    // print methods
    public static int stdDigits = 2;
    /**@param n after-comma digits*/
    public void print(int n) {
        String s = "(%.2f|%.2f)%n".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        System.out.printf(s, X(), Y());
    }
    public void print() {
        if (stdDigits < 0)
            stdDigits = 0;
        print(stdDigits);
    }

    /**@param n after-comma digits*/
    public String getPrint(int n) {
        String s = "(%.2f|%.2f)".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        return String.format(s, X(), Y());
    }
    public String getPrint() {
        if (stdDigits < 0)
            stdDigits = 0;
        return getPrint(stdDigits);
    }

    /**@param n after-comma digits*/
    public void printV(int n) {
        String s = "(%.2f|%.2f; α=%.2f*PI; l=%.2f)%n".replaceAll("2", (n>=0?Integer.toString(n):"2"));
        System.out.printf(s, X(), Y(), angle(), length());
    }
    public void printV() {
        if (stdDigits < 0)
            stdDigits = 0;
        printV(stdDigits);
    }
}
