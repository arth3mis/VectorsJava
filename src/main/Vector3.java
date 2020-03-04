package main;

public class Vector3 {

    double x, y, z;

    // contstructors
    /**
     * Creates new Vector that copies the values of <code>vCopy</code>
     * @param vCopy
     */
    public Vector3(Vector3 vCopy) {
        this.x = vCopy.x;
        this.y = vCopy.y;
        this.z = vCopy.z;
    }

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a Vector3 with values set to 0.
     *
     * If 1-3 booleans are passed, values will be randomized:
     *  (true -> all=random;
     *   false, true -> x=0, y=random, z=0)
     * @param b up to 3 boolean values
     */
    public Vector3(boolean ... b) {
        x = 0;
        y = 0;
        z = 0;

        if (b.length > 0) {
            if (b[0])
                x = Math.random()-0.5;
            if (b.length > 1 && b[1])
                y = Math.random()-0.5;
            if (b.length > 2 && b[2])
                z = Math.random()-0.5;
            normalize();
        }
    }

    // vector property getters
    double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    // manipulation methods
    void add(Vector3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    void sub(Vector3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    void mult(double s) {
        x *= s;
        y *= s;
        z *= s;
    }

    void div(double s) {
        x /= s;
        y /= s;
        z /= s;
    }

    void normalize() {
        x /= length();
        y /= length();
        z /= length();
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
     * @param v another Vector3
     * @return double
     */
    double dot(Vector3 v) {
        return (x*v.x + y*v.y + z*v.z);
    }

    /**
     * Angle between this vector and a 2nd vector
     * @param v another Vector3
     * @return double
     */
    double angle(Vector3 v) {
        return Math.acos(dot(v) / (length() * v.length()));
    }

    /**
     * NOT IMPLEMENTED YET
     * Creates cross product Vector
     * @param v another Vector3
     * @return Vector3
     */
    Vector3 cross(Vector3 v) {
        return null;
    }

    // static methods
    /**
     * Distance between to vectors/length of vector between v1 and v2 (i.e. v1.sub(v2))
     * @param v1
     * @param v2
     * @return double
     */
    static double dist(Vector3 v1, Vector3 v2) {
        Vector3 v = new Vector3(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z);
        return v.length();
    }


    int getRdX() {
        return (int) Math.round(x);
    }

    int getRdY() {
        return (int) Math.round(y);
    }

    int getRdZ() {
        return (int) Math.round(z);
    }

}
