package de.sirarthur.math;

public interface Vecthur {

    double angle();
    double length();

    // manipulation methods
    void add(Vector v);
    void add(double x, double y);
    void sub(Vector v);
    void sub(double x, double y);
    void mult(double s);
    void div(double s);
    void normalize();
    void limit(double l);

    // non-altering methods
    double dot(Vector v);

    // static methods
    static double dist(Vector v1, Vector v2) {
        return 0;
    }

    // rounded getters
    int getRdX();
    int getRdY();

    // print methods
    public static int stdDigits = 2;
    /**@param n after-comma digits*/
    public void print(int n);
    public void print();

    /**@param n after-comma digits*/
    public String getPrint(int n);
    public String getPrint();

    /**@param n after-comma digits*/
    public void printV(int n);
    public void printV();
}
