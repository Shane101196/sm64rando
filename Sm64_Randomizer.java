package rand;
import java.io.*;
import java.util.*;
import rand.*;
import java.lang.*;

public class Sm64_Randomizer {
   
    public static byte[] readFileFullyIntoBuffer(File fh) throws IOException {

        long fileSize = fh.length();
        if (fileSize > Integer.MAX_VALUE) {
            throw new IOException("too long to read in as a byte-array.");
        }
        FileInputStream fis = new FileInputStream(fh.getName());
        byte[] buf = readFullyIntoBuffer(fis, (int) fileSize);
        fis.close();
        return buf;
    }
    
        public static byte[] readFullyIntoBuffer(InputStream in, int bytes) throws IOException {
        byte[] buf = new byte[bytes];
        readFully(in, buf, 0, bytes);
        return buf;
    }

    public static void readFully(InputStream in, byte[] buf, int offset, int length) throws IOException {
        int offs = 0, read = 0;
        while (offs < length && (read = in.read(buf, offs + offset, length - offs)) != -1) {
            offs += read;
        }
    }
    public static String bytesToHex(byte[] bytes) {
      char[] hexChars = new char[bytes.length * 2];
      for ( int j = 0; j < bytes.length; j++ ) {
        int v = bytes[j] & 0xFF;
        hexChars[j * 2] = hexArray[v >>> 4];
        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
      }
    return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
       int len = s.length();
       byte[] data = new byte[len / 2];
       for (int i = 0; i < len; i += 2) {
          data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
       }
    return data;
    }

    public static StringBuilder disableCRC (StringBuilder sb) {
       sb.setCharAt(3288, '0');
       sb.setCharAt(3289, '0');
       sb.setCharAt(3290, '0');
       sb.setCharAt(3291, '0');
       sb.setCharAt(3292, '0');
       sb.setCharAt(3293, '0');
       sb.setCharAt(3294, '0');
       sb.setCharAt(3295, '0');

       sb.setCharAt(3312, '0');
       sb.setCharAt(3313, '0');
       sb.setCharAt(3314, '0');
       sb.setCharAt(3315, '0');
       sb.setCharAt(3316, '0');
       sb.setCharAt(3317, '0');
       sb.setCharAt(3318, '0');
       sb.setCharAt(3319, '0');
   return sb;
}

    public static StringBuilder disableOpening (StringBuilder sb) {
       sb.setCharAt(55208, '2');
       sb.setCharAt(55209, '4');
       sb.setCharAt(55210, '0');
       sb.setCharAt(55211, '0');

       sb.setCharAt(56096, '2');
       sb.setCharAt(56097, '4');
       sb.setCharAt(56098, '1');
       sb.setCharAt(56099, '0');
       sb.setCharAt(56100, '0');
       sb.setCharAt(56101, '0');
       sb.setCharAt(56102, '0');
       sb.setCharAt(56103, '0');
    return sb;
    }

    public static StringBuilder starSelectColor (StringBuilder sb, Random seed, Seed obj) {
       sb.setCharAt(5556884, obj.randomVal(seed));
       sb.setCharAt(5556885, obj.randomVal(seed));
       sb.setCharAt(5556886, obj.randomVal(seed));
       sb.setCharAt(5556887, obj.randomVal(seed));
    return sb;
    }

    public static StringBuilder marioColor (StringBuilder sb, Random seed, Seed obj) {
       int hx = 8534896 * 2;
       int max = hx + 280; 
       for (int count = 0; hx < max; hx++) {
          sb.setCharAt(hx, obj.randomVal(seed));
       }
    return sb;
    }

    public static String[] acceptedEnemies (String mapName) {
       String[] a = {};
       if (mapName == "bob") {
          String[] values = {"44", "8E", "B9", "3F", "46"};
       return values;
       }
    return a;
    }

    public static boolean checkValidSwap(String[] arr, String targetValue) {
       for (String s: arr) {
          if (s.equals(targetValue))
             return true;
       }
   return false;
   }

   public static StringBuilder enemySwap (StringBuilder sb, Random seed, Seed obj, String mapName, int offset) {
       char ax, bx;
       String testVal;
       String[] allowed = acceptedEnemies(mapName); 
       do {
          ax = obj.randomVal(seed);
          bx = obj.randomVal(seed);
          testVal = "";
          testVal += ax;
          testVal += bx;
      } while (checkValidSwap(allowed, testVal) == false);
          sb.setCharAt(offset, ax);
          sb.setCharAt(offset + 1, bx);
   return sb;
   }

   public static StringBuilder modelRandomizer(StringBuilder sb, int offsetModel, Random seed, Seed obj, String mapName, int modelNum, List<String> colTris1, List<String> colVerts1) {
       offsetModel = offsetModel + 8;
       String testVal;
       char a, b, c, d, ax,bx,cx,dx,ay,by,cy,dy,az,bz,cz,dz;
       boolean it = false;
       int val;
       for(int count = 0; count < modelNum; count++) {
          it = false;
          do {
             a = obj.randomVal(seed);
             b = obj.randomVal(seed);
             c = obj.randomVal(seed);
             d = obj.randomVal(seed);
             testVal = "";
             testVal += a;
             testVal += b;
             testVal += c;
             testVal += d;
             val = Integer.valueOf(testVal,16).shortValue();
             if (val < colTris1.size() && val >= 0)
                it = checkValidSpawn(sb, val, colVerts1, colTris1);
          } while (it == false);
          String triCoord = colTris1.get(val);
          String newPoint = newCoord(triCoord, colVerts1);
          ax = newPoint.charAt(0);
          bx = newPoint.charAt(1);
          cx = newPoint.charAt(2);
          dx = newPoint.charAt(3);
          ay = newPoint.charAt(4);
          by = newPoint.charAt(5);
          cy = newPoint.charAt(6);
          dy = newPoint.charAt(7);
          az = newPoint.charAt(8);
          bz = newPoint.charAt(9);
          cz = newPoint.charAt(10);
          dz = newPoint.charAt(11);
          sb.setCharAt(offsetModel, ax);
          sb.setCharAt(offsetModel + 1, bx);
          sb.setCharAt(offsetModel + 2, cx);
          sb.setCharAt(offsetModel + 3, dx);
          sb.setCharAt(offsetModel + 4, ay);
          sb.setCharAt(offsetModel + 5, by);
          sb.setCharAt(offsetModel + 6, cy);
          sb.setCharAt(offsetModel + 7, dy);
          sb.setCharAt(offsetModel + 8, az);
          sb.setCharAt(offsetModel + 9, bz);
          sb.setCharAt(offsetModel + 10, cz);
          sb.setCharAt(offsetModel + 11, dz);
          offsetModel = offsetModel + 96;
       }
    return sb;
    }
    
    public static boolean checkValidSpawn(StringBuilder sb, int val, List<String> colVerts1, List<String> colTris1) {
       String tempStr = colTris1.get(val);
       int v1 = 0;
       int v2 = 0;
       int v3 = 0;
       if (tempStr.length() == 12) {
          String vert1 = tempStr.substring(0, 4);
          v1 = Integer.valueOf(vert1,16).shortValue();
          String vert2 = tempStr.substring(4, 8);
          v2 = Integer.valueOf(vert2,16).shortValue();
          String vert3 = tempStr.substring(8, 12);
          v3 = Integer.valueOf(vert3,16).shortValue();
       } else if (tempStr.length() == 16){
          String vert1 = tempStr.substring(0, 4);
          v1 = Integer.valueOf(vert1,16).shortValue();
          String vert2 = tempStr.substring(4, 8);
          v2 = Integer.valueOf(vert2,16).shortValue();
          String vert3 = tempStr.substring(8, 12);
          v3 = Integer.valueOf(vert3,16).shortValue();
/* //s1
   String s11 = tempStr.substring(12, 14);
   int s11v = Integer.valueOf(s11,16).shortValue();
   //s2
   String s12 = tempStr.substring(14, 16);
   int s12v = Integer.valueOf(s12,16).shortValue(); */
       }
       String point1 = colVerts1.get(v1);
       String point2 = colVerts1.get(v2);
       String point3 = colVerts1.get(v3);
       //x1
       String x1 = point1.substring(0, 4);
       int x1v = Integer.valueOf(x1,16).shortValue();
       //y1
       String y1 = point1.substring(4, 8);
       int y1v = Integer.valueOf(y1,16).shortValue();
       //z1
       String z1 = point1.substring(8, 12);
       int z1v = Integer.valueOf(z1,16).shortValue(); 
       //x2
       String x2 = point2.substring(0, 4);
       int x2v = Integer.valueOf(x2,16).shortValue();
       //y2
       String y2 = point2.substring(4, 8);
       int y2v = Integer.valueOf(y2,16).shortValue();
       //z2
       String z2 = point2.substring(8, 12);
       int z2v = Integer.valueOf(z2,16).shortValue(); 
       //x3
       String x3 = point3.substring(0, 4);
       int x3v = Integer.valueOf(x3,16).shortValue();
       //y3
       String y3 = point3.substring(4, 8);
       int y3v = Integer.valueOf(y3,16).shortValue();
       //z3
       String z3 = point3.substring(8, 12);
       int z3v = Integer.valueOf(z3,16).shortValue(); 
       Vector3 Vec1 = new Vector3(x1v, y1v, z1v);
       Vector3 Vec2 = new Vector3(x2v, y2v, z2v);
       Vector3 Vec3 = new Vector3(x3v, y3v, z3v);
       if (((Vec1.x - Vec2.x >= 0 && Vec1.x - Vec2.x < 30) || (Vec1.x - Vec2.x <= 0 && Vec1.x - Vec2.x > -30))&&((Vec3.x - Vec2.x >= 0 && Vec3.x - Vec2.x < 30) || (Vec3.x - Vec2.x <= 0 && Vec3.x - Vec2.x > -30))&&((Vec3.x - Vec1.x >= 0 && Vec3.x - Vec1.x < 30) || (Vec3.x - Vec1.x <= 0 && Vec3.x - Vec1.x > -30)))
          return false;
       if (((Vec1.z - Vec2.z >= 0 && Vec1.z - Vec2.z < 30) || (Vec1.z - Vec2.z <= 0 && Vec1.z - Vec2.z > -30))&&((Vec3.z - Vec2.z >= 0 && Vec3.z - Vec2.z < 30) || (Vec3.z - Vec2.z <= 0 && Vec3.z - Vec2.z > -30))&&((Vec3.z - Vec1.z >= 0 && Vec3.z - Vec1.z < 30) || (Vec3.z - Vec1.z <= 0 && Vec3.z - Vec1.z > -30)))
          return false;
       if (((Vec1.y - Vec2.y > 30) || (Vec1.y - Vec2.y < -30))&&((Vec3.y - Vec2.y >= 30) || (Vec3.y - Vec2.y < -30))&&((Vec3.y - Vec1.y > 30) || (Vec3.y - Vec1.y < -30)))
          return false;
   //Vec1 = Vec1.cross(Vec2,Vec3);
   //Vec1.normalize();
   //float yComp =  Vec1.y;
   //if (yComp >= 0.75 && yComp <= 1)
       return true;
   //return false;
    }
    
    public static byte[] intToByteArray ( final int i ) throws IOException { 
       try {     
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          DataOutputStream dos = new DataOutputStream(bos);
          dos.writeInt(i);
          dos.flush();
          return bos.toByteArray();
       }catch(IOException e){return null;}
    }
    
    public static String newCoord(String tempStr, List<String> colVerts1) {
       int v1 = 0;
       int v2 = 0;
       int v3 = 0;
       if (tempStr.length() == 12) {
          String vert1 = tempStr.substring(0, 4);
          v1 = Integer.valueOf(vert1,16).shortValue();
          String vert2 = tempStr.substring(4, 8);
          v2 = Integer.valueOf(vert2,16).shortValue();
          String vert3 = tempStr.substring(8, 12);
          v3 = Integer.valueOf(vert3,16).shortValue();
       } else if (tempStr.length() == 16){
          String vert1 = tempStr.substring(0, 4);
          v1 = Integer.valueOf(vert1,16).shortValue();
          String vert2 = tempStr.substring(4, 8);
          v2 = Integer.valueOf(vert2,16).shortValue();
          String vert3 = tempStr.substring(8, 12);
          v3 = Integer.valueOf(vert3,16).shortValue();
/* //s1
   String s11 = tempStr.substring(12, 14);
   int s11v = Integer.valueOf(s11,16).shortValue();
   //s2
   String s12 = tempStr.substring(14, 16);
   int s12v = Integer.valueOf(s12,16).shortValue(); */
       }
       String point1 = colVerts1.get(v1);
       String point2 = colVerts1.get(v2);
       String point3 = colVerts1.get(v3);
       //x1
       String x1 = point1.substring(0, 4);
       int x1v = Integer.valueOf(x1,16).shortValue();
       //y1
       String y1 = point1.substring(4, 8);
       int y1v = Integer.valueOf(y1,16).shortValue();
       //z1
       String z1 = point1.substring(8, 12);
       int z1v = Integer.valueOf(z1,16).shortValue(); 
       //x2
       String x2 = point2.substring(0, 4);
       int x2v = Integer.valueOf(x2,16).shortValue();
       //y2
       String y2 = point2.substring(4, 8);
       int y2v = Integer.valueOf(y2,16).shortValue();
       //z2
       String z2 = point2.substring(8, 12);
       int z2v = Integer.valueOf(z2,16).shortValue(); 
       //x3
       String x3 = point3.substring(0, 4);
       int x3v = Integer.valueOf(x3,16).shortValue();
       //y3
       String y3 = point3.substring(4, 8);
       int y3v = Integer.valueOf(y3,16).shortValue();
       //z3
       String z3 = point3.substring(8, 12);
       int z3v = Integer.valueOf(z3,16).shortValue(); 
       int xNew = (x1v + x2v + x3v)/3;
       int yNew = (y1v + y2v + y3v)/3 + 100;
       int zNew = (z1v + z2v + z3v)/3;
       try {
          byte[] xby = intToByteArray(xNew);
          byte[] yby = intToByteArray(yNew);
          byte[] zby = intToByteArray(zNew);
          String xHex = bytesToHex(xby);
          String yHex = bytesToHex(yby);
          String zHex = bytesToHex(zby);
          xHex = xHex.substring(4);
          yHex = yHex.substring(4);
          zHex = zHex.substring(4);
          String newP = xHex + yHex + zHex;
          return newP;
       }catch(IOException e){}
       return null;
    }
    
    public static StringBuilder objectRandomizer(StringBuilder sb, int offsetBox, Random seed, Seed obj, String mapName, int boxNum, List<String> colVerts1, List<String> colTris1) {
       offsetBox = offsetBox + 4;
       String testVal;
       int val;
       char a,b,c,d,ax, bx, cx, dx, ay, by, cy, dy, az, bz, cz, dz; 
       boolean it = false; 
       for (int count = 0; count < boxNum; count++) {
          it = false;
          do {
             a = obj.randomVal(seed);
             b = obj.randomVal(seed);
             c = obj.randomVal(seed);
             d = obj.randomVal(seed);
             testVal = "";
             testVal += a;
             testVal += b;
             testVal += c;
             testVal += d;
             val = Integer.valueOf(testVal,16).shortValue();
             if (val < colTris1.size() && val >= 0)
                it = checkValidSpawn(sb, val, colVerts1, colTris1);
          } while (it == false);
          String triCoord = colTris1.get(val);
          String newPoint = newCoord(triCoord, colVerts1);
          ax = newPoint.charAt(0);
          bx = newPoint.charAt(1);
          cx = newPoint.charAt(2);
          dx = newPoint.charAt(3);
          ay = newPoint.charAt(4);
          by = newPoint.charAt(5);
          cy = newPoint.charAt(6);
          dy = newPoint.charAt(7);
          az = newPoint.charAt(8);
          bz = newPoint.charAt(9);
          cz = newPoint.charAt(10);
          dz = newPoint.charAt(11);
          sb.setCharAt(offsetBox, ax);
          sb.setCharAt(offsetBox + 1, bx);
          sb.setCharAt(offsetBox + 2, cx);
          sb.setCharAt(offsetBox + 3, dx);
          sb.setCharAt(offsetBox + 4, ay);
          sb.setCharAt(offsetBox + 5, by);
          sb.setCharAt(offsetBox + 6, cy);
          sb.setCharAt(offsetBox + 7, dy);
          sb.setCharAt(offsetBox + 8, az);
          sb.setCharAt(offsetBox + 9, bz);
          sb.setCharAt(offsetBox + 10, cz);
          sb.setCharAt(offsetBox + 11, dz);
          offsetBox = offsetBox + 20;
}
return sb;
}

public static StringBuilder redCoinsRandomizer(StringBuilder sb, int offset, Random seed, Seed obj, String mapName, List<String> colVerts1, List<String> colTris1) {
offset = offset + 4;
String testVal;
int val = 0;
boolean it = false; 
char a,b,c,d,ax, bx, cx, dx, ay, by, cy, dy, az, bz, cz, dz;
if ((offset == bow1RedCoin) || (offset == bow2RedCoin)) {
for (int count = 0; count < 9; count++) {
it = false;
if (count != 5) {
do {
a = obj.randomVal(seed);
b = obj.randomVal(seed);
c = obj.randomVal(seed);
d = obj.randomVal(seed);
testVal = "";
testVal += a;
testVal += b;
testVal += c;
testVal += d;
val = Integer.valueOf(testVal,16).shortValue();
if (val < colTris1.size() && val >= 0)
it = checkValidSpawn(sb, val, colVerts1, colTris1);
} while (it == false);
String triCoord = colTris1.get(val);
String newPoint = newCoord(triCoord, colVerts1);
ax = newPoint.charAt(0);
bx = newPoint.charAt(1);
cx = newPoint.charAt(2);
dx = newPoint.charAt(3);
ay = newPoint.charAt(4);
by = newPoint.charAt(5);
cy = newPoint.charAt(6);
dy = newPoint.charAt(7);
az = newPoint.charAt(8);
bz = newPoint.charAt(9);
cz = newPoint.charAt(10);
dz = newPoint.charAt(11);
sb.setCharAt(offset, ax);
sb.setCharAt(offset + 1, bx);
sb.setCharAt(offset + 2, cx);
sb.setCharAt(offset + 3, dx);
sb.setCharAt(offset + 4, ay);
sb.setCharAt(offset + 5, by);
sb.setCharAt(offset + 6, cy);
sb.setCharAt(offset + 7, dy);
sb.setCharAt(offset + 8, az);
sb.setCharAt(offset + 9, bz);
sb.setCharAt(offset + 10, cz);
sb.setCharAt(offset + 11, dz);
offset = offset + 20;
}
offset = offset + 20;
}
} else if (offset == ccmRedCoin){
for (int count = 0; count < 13; count++) {
it = false;
if ((count != 2) || ( count >= 8 && count <= 11)) {
do {
a = obj.randomVal(seed);
b = obj.randomVal(seed);
c = obj.randomVal(seed);
d = obj.randomVal(seed);
testVal = "";
testVal += a;
testVal += b;
testVal += c;
testVal += d;
val = Integer.valueOf(testVal,16).shortValue();
if (val < colTris1.size() && val >= 0)
it = checkValidSpawn(sb, val, colVerts1, colTris1);
} while (it == false);
String triCoord = colTris1.get(val);
String newPoint = newCoord(triCoord, colVerts1);
ax = newPoint.charAt(0);
bx = newPoint.charAt(1);
cx = newPoint.charAt(2);
dx = newPoint.charAt(3);
ay = newPoint.charAt(4);
by = newPoint.charAt(5);
cy = newPoint.charAt(6);
dy = newPoint.charAt(7);
az = newPoint.charAt(8);
bz = newPoint.charAt(9);
cz = newPoint.charAt(10);
dz = newPoint.charAt(11);
sb.setCharAt(offset, ax);
sb.setCharAt(offset + 1, bx);
sb.setCharAt(offset + 2, cx);
sb.setCharAt(offset + 3, dx);
sb.setCharAt(offset + 4, ay);
sb.setCharAt(offset + 5, by);
sb.setCharAt(offset + 6, cy);
sb.setCharAt(offset + 7, dy);
sb.setCharAt(offset + 8, az);
sb.setCharAt(offset + 9, bz);
sb.setCharAt(offset + 10, cz);
sb.setCharAt(offset + 11, dz);offset = offset + 20;
}
offset = offset + 20;
}
} else if (offset == ttmRedCoin){
for (int count = 0; count < 30; count++) {
it = false;
if (count <= 6 || count == 29) {
do {
a = obj.randomVal(seed);
b = obj.randomVal(seed);
c = obj.randomVal(seed);
d = obj.randomVal(seed);
testVal = "";
testVal += a;
testVal += b;
testVal += c;
testVal += d;
val = Integer.valueOf(testVal,16).shortValue();
if (val < colTris1.size() && val >= 0)
it = checkValidSpawn(sb, val, colVerts1, colTris1);
} while (it == false);
String triCoord = colTris1.get(val);
String newPoint = newCoord(triCoord, colVerts1);
ax = newPoint.charAt(0);
bx = newPoint.charAt(1);
cx = newPoint.charAt(2);
dx = newPoint.charAt(3);
ay = newPoint.charAt(4);
by = newPoint.charAt(5);
cy = newPoint.charAt(6);
dy = newPoint.charAt(7);
az = newPoint.charAt(8);
bz = newPoint.charAt(9);
cz = newPoint.charAt(10);
dz = newPoint.charAt(11);
sb.setCharAt(offset, ax);
sb.setCharAt(offset + 1, bx);
sb.setCharAt(offset + 2, cx);
sb.setCharAt(offset + 3, dx);
sb.setCharAt(offset + 4, ay);
sb.setCharAt(offset + 5, by);
sb.setCharAt(offset + 6, cy);
sb.setCharAt(offset + 7, dy);
sb.setCharAt(offset + 8, az);
sb.setCharAt(offset + 9, bz);
sb.setCharAt(offset + 10, cz);
sb.setCharAt(offset + 11, dz);
offset = offset + 20;
}
offset = offset + 20;
}
} else {
for (int count = 0; count < 8; count++) {
it = false;
do {
a = obj.randomVal(seed);
b = obj.randomVal(seed);
c = obj.randomVal(seed);
d = obj.randomVal(seed);
testVal = "";
testVal += a;
testVal += b;
testVal += c;
testVal += d;
val = Integer.valueOf(testVal,16).shortValue();
if (val < (colTris1.size()) && (val >= 0))
it = checkValidSpawn(sb, val, colVerts1, colTris1);
} while (it == false);
String triCoord = colTris1.get(val);
String newPoint = newCoord(triCoord, colVerts1);
ax = newPoint.charAt(0);
bx = newPoint.charAt(1);
cx = newPoint.charAt(2);
dx = newPoint.charAt(3);
ay = newPoint.charAt(4);
by = newPoint.charAt(5);
cy = newPoint.charAt(6);
dy = newPoint.charAt(7);
az = newPoint.charAt(8);
bz = newPoint.charAt(9);
cz = newPoint.charAt(10);
dz = newPoint.charAt(11);
sb.setCharAt(offset, ax);
sb.setCharAt(offset + 1, bx);
sb.setCharAt(offset + 2, cx);
sb.setCharAt(offset + 3, dx);
sb.setCharAt(offset + 4, ay);
sb.setCharAt(offset + 5, by);
sb.setCharAt(offset + 6, cy);
sb.setCharAt(offset + 7, dy);
sb.setCharAt(offset + 8, az);
sb.setCharAt(offset + 9, bz);
sb.setCharAt(offset + 10, cz);
sb.setCharAt(offset + 11, dz);
offset = offset + 20;
}
}
return sb;
}

public static List<String> getCollisionTris(StringBuilder sb, String mapName) {
List<String> colList = new ArrayList<String>();
int offset = 0;
int totalPolys;
String stringCol;
int temp;
String tempCoordsA;
String tempCoordsB;
String numOfPolys;
String typeOfPolys;
if (mapName == "bob")
offset = 15726479 * 2;
if (mapName == "jrb")
offset = 16046177 * 2;
if (mapName == "ccm")
offset = 14629961 * 2;
if (mapName == "wf")
offset = 17501019 * 2;
if (mapName == "ssa")
offset = 16959359 * 2;
if (mapName == "bow1")
offset = 16704735 * 2;
if (mapName == "bbh")
offset = 14490813 * 2;
if (mapName == "wdwrc")
offset = 15958973 * 2;
if (mapName == "ttm")
offset = 18377047 * 2;
if (mapName == "thirc")
offset = 16158713 * 2;
if (mapName == "sml")
offset = 15823313 * 2;
if (mapName == "ttc")
offset = 16269985 * 2;
if (mapName == "rr")
offset = 16494929 * 2;
if (mapName == "rcb")
offset = 18158529 * 2;
if (mapName == "vc")
offset = 16786943 * 2;
if (mapName == "bow3")
offset = 17086663 * 2;
if (mapName == "bow2")
offset = 16898575 * 2;
if (mapName == "dddrc")
offset = 17394147 * 2;
if (mapName == "ssl")
offset = 15532807 * 2;
if (mapName == "wc")
offset = 4989450 * 2;
if (mapName == "lll")
offset = 17248395 * 2;
if (mapName == "hmc")
offset = 15409587 * 2;
offset = offset + 4;
numOfPolys = "";
numOfPolys += sb.charAt(offset);
numOfPolys += sb.charAt(offset + 1);
numOfPolys += sb.charAt(offset + 2);
numOfPolys += sb.charAt(offset + 3);
totalPolys = Integer.valueOf(numOfPolys,16).shortValue();
offset = offset + 4;
offset = offset + (totalPolys * 12);
System.out.println(mapName);
do {
offset = offset + 4;
typeOfPolys = "";
typeOfPolys += sb.charAt(offset - 2);
typeOfPolys += sb.charAt(offset - 1);
numOfPolys = "";
numOfPolys += sb.charAt(offset);
numOfPolys += sb.charAt(offset + 1);
numOfPolys += sb.charAt(offset + 2);
numOfPolys += sb.charAt(offset + 3);
totalPolys = Integer.valueOf(numOfPolys,16).shortValue();
offset = offset + 4;
temp = offset;
if (typeOfPolys.equals("0E") || typeOfPolys.equals("24") || typeOfPolys.equals("25") || typeOfPolys.equals("27")|| typeOfPolys.equals("2C") || typeOfPolys.equals("2D")) {
offset = offset + (totalPolys  * 16);
for (int i = 0; i < totalPolys; i++) {
tempCoordsA = "";
tempCoordsA += sb.charAt(temp);
tempCoordsA += sb.charAt(temp + 1);
tempCoordsA += sb.charAt(temp + 2);
tempCoordsA += sb.charAt(temp + 3);
tempCoordsA += sb.charAt(temp + 4);
tempCoordsA += sb.charAt(temp + 5);
tempCoordsA += sb.charAt(temp + 6);
tempCoordsA += sb.charAt(temp + 7);
tempCoordsA += sb.charAt(temp + 8);
tempCoordsA += sb.charAt(temp + 9);
tempCoordsA += sb.charAt(temp + 10);
tempCoordsA += sb.charAt(temp + 11);
tempCoordsA += sb.charAt(temp + 12);
tempCoordsA += sb.charAt(temp + 13);
tempCoordsA += sb.charAt(temp + 14);
tempCoordsA += sb.charAt(temp + 15);
stringCol = tempCoordsA;
colList.add(stringCol);
temp = temp + 16;
}
} else {
offset = offset + (totalPolys * 12);
for (int i = 0; i < totalPolys; i++) {
tempCoordsA = "";
tempCoordsA += sb.charAt(temp);
tempCoordsA += sb.charAt(temp + 1);
tempCoordsA += sb.charAt(temp + 2);
tempCoordsA += sb.charAt(temp + 3);
tempCoordsA += sb.charAt(temp + 4);
tempCoordsA += sb.charAt(temp + 5);
tempCoordsA += sb.charAt(temp + 6);
tempCoordsA += sb.charAt(temp + 7);
tempCoordsA += sb.charAt(temp + 8);
tempCoordsA += sb.charAt(temp + 9);
tempCoordsA += sb.charAt(temp + 10);
tempCoordsA += sb.charAt(temp + 11);
stringCol = tempCoordsA;
colList.add(stringCol);
temp = temp + 12;
}
}
}while(eoo(sb.charAt(offset), sb.charAt(offset + 1), sb.charAt(offset + 2), sb.charAt(offset + 3)) == false);
return colList;
}

public static List<String> getCollisionVerts(StringBuilder sb, String mapName) {
List<String> colList = new ArrayList<String>();
int offset = 0;
int totalPolys;
String stringCol;
int temp;
String tempCoordsA;
String tempCoordsB;
String numOfPolys;
String typeOfPolys;
if (mapName == "bob")
offset = 15726479 * 2;
if (mapName == "jrb")
offset = 16046177 * 2;
if (mapName == "ccm")
offset = 14629961 * 2;
if (mapName == "wf")
offset = 17501019 * 2;
if (mapName == "ssa")
offset = 16959359 * 2;
if (mapName == "bow1")
offset = 16704735 * 2;
if (mapName == "bbh")
offset = 14490813 * 2;
if (mapName == "wdwrc")
offset = 15958973 * 2;
if (mapName == "ttm")
offset = 18377047 * 2;
if (mapName == "thirc")
offset = 16158713 * 2;
if (mapName == "sml")
offset = 15823313 * 2;
if (mapName == "ttc")
offset = 16269985 * 2;
if (mapName == "rr")
offset = 16494929 * 2;
if (mapName == "rcb")
offset = 18158529 * 2;
if (mapName == "vc")
offset = 16786943 * 2;
if (mapName == "bow3")
offset = 17086663 * 2;
if (mapName == "bow2")
offset = 16898575 * 2;
if (mapName == "dddrc")
offset = 17394147 * 2;
if (mapName == "ssl")
offset = 15532807 * 2;
if (mapName == "wc")
offset = 4989450 * 2;
if (mapName == "lll")
offset = 17248395 * 2;
if (mapName == "hmc")
offset = 15409587 * 2;
offset = offset + 4;
numOfPolys = "";
numOfPolys += sb.charAt(offset);
numOfPolys += sb.charAt(offset + 1);
numOfPolys += sb.charAt(offset + 2);
numOfPolys += sb.charAt(offset + 3);
totalPolys = Integer.valueOf(numOfPolys,16).shortValue();
offset = offset + 4;
temp = offset;
for (int i = 0; i < totalPolys; i++) {
tempCoordsA = "";
tempCoordsA += sb.charAt(temp);
tempCoordsA += sb.charAt(temp + 1);
tempCoordsA += sb.charAt(temp + 2);
tempCoordsA += sb.charAt(temp + 3);
tempCoordsA += sb.charAt(temp + 4);
tempCoordsA += sb.charAt(temp + 5);
tempCoordsA += sb.charAt(temp + 6);
tempCoordsA += sb.charAt(temp + 7);
tempCoordsA += sb.charAt(temp + 8);
tempCoordsA += sb.charAt(temp + 9);
tempCoordsA += sb.charAt(temp + 10);
tempCoordsA += sb.charAt(temp + 11);
stringCol = tempCoordsA;
colList.add(stringCol);
temp = temp + 12;
}
return colList;
}

public static boolean eoo(char a, char b, char c, char d) {
if ((a == '0')&&(b == '0')&&(c == '4')&&(d == '1'))
return true;
return false;
}


   
   public static void randomizer(File fh, boolean rc, boolean ss, boolean oc, boolean mc, boolean et, boolean sc, boolean ms, boolean wp) throws IOException{
        Seed obj = new Seed();
        byte[] byteArray = readFileFullyIntoBuffer(fh);
        String hexChars = bytesToHex(byteArray);
        StringBuilder sb = new StringBuilder(hexChars);
        sb = disableCRC(sb);
        List<String> colTris = new ArrayList<String>();
        List<String> colVerts = new ArrayList<String>();
        if (rc == true) {
        colVerts = getCollisionVerts(sb ,"bob");
        colTris = getCollisionTris(sb ,"bob");
        sb = redCoinsRandomizer(sb, bobRedCoin, obj.randnum, obj, "bob", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"bbh");
        colTris = getCollisionTris(sb ,"bbh");
        sb = redCoinsRandomizer(sb, bbhRedCoin, obj.randnum, obj, "bbh", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"ccm");
        colTris = getCollisionTris(sb ,"ccm");
        sb = redCoinsRandomizer(sb, ccmRedCoin, obj.randnum, obj, "ccm", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"hmc");
        colTris = getCollisionTris(sb ,"hmc");
        sb = redCoinsRandomizer(sb, hmcRedCoin, obj.randnum, obj, "hmc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"ssl");
        colTris = getCollisionTris(sb ,"ssl");
        sb = redCoinsRandomizer(sb, sslRedCoin, obj.randnum, obj, "ssl", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"sml");
        colTris = getCollisionTris(sb ,"sml");
        sb = redCoinsRandomizer(sb, smlRedCoin, obj.randnum, obj, "sml", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"wdwrc");
        colTris = getCollisionTris(sb ,"wdwrc");
        sb = redCoinsRandomizer(sb, wdwRedCoin, obj.randnum, obj, "wdwrc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"jrb");
        colTris = getCollisionTris(sb ,"jrb");
        sb = redCoinsRandomizer(sb, jrbRedCoin, obj.randnum, obj, "jrb", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"thirc");
        colTris = getCollisionTris(sb ,"thirc");
        sb = redCoinsRandomizer(sb, thiRedCoin, obj.randnum, obj, "thirc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"ttc");
        colTris = getCollisionTris(sb ,"ttc");
        sb = redCoinsRandomizer(sb, ttcRedCoin, obj.randnum, obj, "ttc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"rr");
        colTris = getCollisionTris(sb ,"rr");
        sb = redCoinsRandomizer(sb, rrRedCoin, obj.randnum, obj, "rr", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"bow1");
        colTris = getCollisionTris(sb ,"bow1");
        sb = redCoinsRandomizer(sb, bow1RedCoin, obj.randnum, obj, "bow1", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"vc");
        colTris = getCollisionTris(sb ,"vc");
        sb = redCoinsRandomizer(sb, vcRedCoin, obj.randnum, obj, "vc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"bow2");
        colTris = getCollisionTris(sb ,"bow2");
        sb = redCoinsRandomizer(sb, bow2RedCoin, obj.randnum, obj, "bow2", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"ssa");
        colTris = getCollisionTris(sb ,"ssa");
        sb = redCoinsRandomizer(sb, saRedCoin, obj.randnum, obj, "ssa", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"bow3");
        colTris = getCollisionTris(sb ,"bow3");
        sb = redCoinsRandomizer(sb, bow3RedCoin, obj.randnum, obj, "bow3", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"lll");
        colTris = getCollisionTris(sb ,"lll");
        sb = redCoinsRandomizer(sb, lllRedCoin, obj.randnum, obj, "lll", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"dddrc");
        colTris = getCollisionTris(sb ,"dddrc");
        sb = redCoinsRandomizer(sb, dddRedCoin, obj.randnum, obj, "dddrc", colVerts, colTris);
        colVerts = getCollisionVerts(sb ,"wf");
        colTris = getCollisionTris(sb ,"wf");
        sb = redCoinsRandomizer(sb, wfRedCoin, obj.randnum, obj, "wf", colVerts, colTris);
        //List<String> colVerts = getCollisionVerts(sb ,"mc");
        //List<String> colTris = getCollisionTris(sb ,"mc");
        //sb = redCoinsRandomizer(sb, mcRedCoin, obj.randnum, obj, "mc");
        //List<String> colVerts = getCollisionVerts(sb ,"wc");
        //List<String> colTris = getCollisionTris(sb ,"wc");
        //sb = redCoinsRandomizer(sb, wcRedCoin, obj.randnum, obj, "wc");
        //List<String> colVerts = getCollisionVerts(sb ,"rcb");
        //List<String> colTris = getCollisionTris(sb ,"rcb");
        //sb = redCoinsRandomizer(sb, rcbRedCoin, obj.randnum, obj, "rcb");
        colVerts = getCollisionVerts(sb ,"ttm");
        colTris = getCollisionTris(sb ,"ttm");
        sb = redCoinsRandomizer(sb, ttmRedCoin, obj.randnum, obj, "ttm", colVerts, colTris);
        }
        if (ss == true) {
        colVerts = getCollisionVerts(sb ,"bob");
        colTris = getCollisionTris(sb ,"bob");
        sb = modelRandomizer(sb, bobStarModel, obj.randnum, obj, "bob", 2, colVerts, colTris);
        sb = objectRandomizer(sb, bobStarBox, obj.randnum, obj, "bob", 1, colVerts, colTris);
         
        }
        if (oc == true) {
        sb = disableOpening(sb);
        }
        if (mc == true) {
        sb = marioColor(sb, obj.randnum, obj);
        }
        if (et == true) {
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31472944);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31472964);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473284);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473304);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473324);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473344);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473364);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473384);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473404);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473824);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473844);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473864);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473884);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473904);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473964);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31473984);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31474004);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31474564);
        sb = enemySwap(sb, obj.randnum, obj, "bob", 31474644);
        }
        if (sc == true) {
        sb = starSelectColor(sb, obj.randnum, obj);
        }
        if (ms == true) {
        //marioSpawn
        }
        if (wp == true) {
        //Warps
        }
        String output = sb.toString();
        byte[] out = hexStringToByteArray(output);
        try (FileOutputStream fos = new FileOutputStream("SM64 Random.ext.z64")) {
         fos.write(out);
        }
        try {
    BufferedWriter writer = new BufferedWriter(new FileWriter("SM64 Randomizer Info.txt"));
    writer.write("Seed: " + Long.toString(Seed.getSeedVal()));
    writer.newLine();
    writer.write("Red Coin Locations Changed: " + rc);
    writer.newLine();
    writer.write("Stars Moved: " + ss);
    writer.newLine();
    writer.write("Disable Opening Cutscenes: " + oc);
    writer.newLine();
    writer.write("Mario Colors Changed: " + mc);
    writer.newLine();
    writer.write("Enemies Changed: " + et);
    writer.newLine();
    writer.write("Level Select Color Changed: " + sc);
    writer.newLine();
    writer.write("Mario Spawns Changed: " + ms);
    writer.newLine();
    writer.write("Warps Changed: " + wp);
    writer.close();
   } catch (IOException e){}
   }
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
   public static int bobRedCoin = 15736741 * 2;
   public static int bobStarModel = 4217976 * 2;
   public static int bobStarBox = 15737291 * 2;
   public static int bobBoss = 4217928 * 2;
   public static int bbhRedCoin = 14515731 * 2;
   public static int ccmRedCoin = 14640833 * 2;
   public static int hmcRedCoin = 15431429 * 2;
   public static int sslRedCoin = 15542879 * 2;
   public static int smlRedCoin = 15832059 * 2;
   public static int wdwRedCoin = 15966885 * 2;
   public static int jrbRedCoin = 16051631 * 2;
   public static int thiRedCoin = 16162117 * 2;
   public static int ttcRedCoin = 16284899 * 2;
   public static int rrRedCoin = 16511405 * 2;
   public static int bow1RedCoin = 16713257 * 2;
   public static int vcRedCoin = 16792135 * 2;
   public static int bow2RedCoin = 16911381 * 2;
   public static int saRedCoin = 16960003 * 2;
   public static int bow3RedCoin = 17101729 * 2;
   public static int lllRedCoin = 17256491 * 2;
   public static int dddRedCoin = 17398679 * 2;
   public static int wfRedCoin = 17507755 * 2;
   public static int mcRedCoin = 17946291 * 2;
   public static int wcRedCoin = 18014601 * 2;
   public static int rcbRedCoin = 18170909 * 2;
   public static int ttmRedCoin = 18391171 * 2;
   public static void main(String[] args)
   {	
        new JavaWindow();
   }
}