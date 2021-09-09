public class Planet {
    /**
     * x position
     */
    public double xxPos;
    /**
     * y position
     */
    public double yyPos;
    /**
     * x velocity
     */
    public double xxVel;
    /**
     * y velocity
     */
    public double yyVel;
    /**
     * its mass
     */
    public double mass;
    /**
     * the name of the file that corresponds to the image
     */
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * calculate the distance between p and this planet
     * @param p target planet
     * @return distance
     */
    public double calcDistance(Planet p) {
        final double dx = p.xxPos - xxPos;
        final double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * calculate the force exerted by planet p
     * @param p planet
     * @return force
     */
    public double calcForceExertedBy(Planet p) {
        final double G = 6.67 * Math.pow(10, -11);
        final double squareR = Math.pow(calcDistance(p), 2);
        return (mass * p.mass * G) / squareR;
    }

    /**
     * calculate the force of x direction
     * @param p
     * @return
     */
    public double calcForceExertedByX(Planet p) {
        final double f = calcForceExertedBy(p);
        final double dx = p.xxPos - xxPos;
        return f * dx / calcDistance(p);
    }

    /**
     * calculate the force of Y direction
     * @param p
     * @return
     */
    public double calcForceExertedByY(Planet p) {
        final double f = calcForceExertedBy(p);
        final double dy = p.yyPos - yyPos;
        return f * dy / calcDistance(p);
    }

    /**
     * calculate net force exerted by x
     * @param allPlanets
     * @return
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double f = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                f += calcForceExertedByX(p);
            }
        }
        return f;
    }

    /**
     * calculate net force exerted by y
     * @param allPlanets
     * @return
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double f = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                f += calcForceExertedByY(p);
            }
        }
        return f;
    }

    /**
     * udpate the position of this planet in dt time
     * @param dt
     * @param forceX net force of x
     * @param forceY net force of y
     */
    public void update(double dt, double forceX, double forceY) {
        final double aX = forceX / mass;
        final double aY = forceY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
}
