package rand;
import java.io.*;
import java.util.*;

public class Seed {
    static Random randnum = new Random();
    static long seedVal;
    public Seed() {
    }
    public Random getSeed(){
      return randnum;
    }
    public static long getSeedVal(){
      return seedVal;
    }
    public static void setSeeds(long x){
      randnum.setSeed(x);
      seedVal = x;
    }
    public char randomVal(Random seed) {
      String chars = "1234567890ABCDEF";
      char c = chars.charAt(seed.nextInt(chars.length()));
    return c;
}
}