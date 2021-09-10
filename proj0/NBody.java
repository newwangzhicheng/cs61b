public class NBody {

  public static void main(String[] args) {
    try {
      /** Read arguments */
      double T = Double.parseDouble(args[0]);
      double dt = Double.parseDouble(args[1]);
      String filename = args[2];

      /** Read file content */
      double radius = readRadius(filename);
      Planet[] allPlanets = readPlanets(filename);

      /** Initiate canvas Draw the first frame */
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(-radius, radius);
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (Planet planet : allPlanets) {
        planet.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);

      /** Animate */
      for (double time = 0; time < T; time += dt) {
        StdDraw.clear();
        final int quantity = allPlanets.length;
        final double[] xForces = new double[quantity];
        final double[] yForces = new double[quantity];
        for (int i = 0; i < quantity; i++) {
          xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
          yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
        }
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < quantity; i++) {
          allPlanets[i].update(dt, xForces[i], yForces[i]);
          allPlanets[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
      }
      /** Output the final position */
      StdOut.printf("%d\n", allPlanets.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < allPlanets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", allPlanets[i].xxPos, allPlanets[i].yyPos,
            allPlanets[i].xxVel, allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Read the radius of the university
   * 
   * @param filename file path
   * @return
   */
  public static double readRadius(String filename) {
    final In in = new In(filename);
    in.readLine();
    return in.readDouble();
  }

  /**
   * Read all planets parameters
   * 
   * @param filename
   * @return planets
   */
  public static Planet[] readPlanets(String filename) {
    final In in = new In(filename);
    final int quantity = in.readInt();
    final Planet[] planets = new Planet[quantity];
    in.readDouble();
    for (int i = 0; i < quantity; i++) {
      final double xxPos = in.readDouble();
      final double yyPos = in.readDouble();
      final double xxVel = in.readDouble();
      final double yyVel = in.readDouble();
      final double mass = in.readDouble();
      final String imgFileName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }
    return planets;

  }
}
