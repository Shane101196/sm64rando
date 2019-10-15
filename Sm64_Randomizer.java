package rand;
import java.io.*;
import java.util.*;
import rand.*;

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
        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
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

public static StringBuilder modelRandomizer(StringBuilder sb, int offsetModel, Random seed, Seed obj, String mapName, int modelNum) {
offsetModel = offsetModel + 8;
String testCoordsX;
String testCoordsY;
String testCoordsZ;
char ax, bx, cx, dx, ay, by, cy, dy, az, bz, cz, dz; 
for(int count = 0; count < modelNum; count++) {
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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

public static StringBuilder objectRandomizer(StringBuilder sb, int offsetBox, Random seed, Seed obj, String mapName, int boxNum) {
offsetBox = offsetBox + 4;
String testCoordsX;
String testCoordsY;
String testCoordsZ;
char ax, bx, cx, dx, ay, by, cy, dy, az, bz, cz, dz; 
for (int count = 0; count < boxNum; count++) {
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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

public static StringBuilder redCoinsRandomizer(StringBuilder sb, int offset, Random seed, Seed obj, String mapName) {
offset = offset + 4;
String testCoordsX;
String testCoordsY;
String testCoordsZ;
char ax, bx, cx, dx, ay, by, cy, dy, az, bz, cz, dz;
if ((offset == bow1RedCoin) || (offset == bow2RedCoin)) {
for (int count = 0; count < 9; count++) {
if (count != 5) {
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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
if ((count != 2) || ( count >= 8 && count <= 11)) {
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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
} else if (offset == ttmRedCoin){
for (int count = 0; count < 30; count++) {
if (count <= 6 || count == 29) {
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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
do {
 ax = obj.randomVal(seed);
 bx = obj.randomVal(seed);
 cx = obj.randomVal(seed);
 dx = obj.randomVal(seed);
testCoordsX = "";
testCoordsX += ax;
testCoordsX += bx;
testCoordsX += cx;
testCoordsX += dx;
 ay = obj.randomVal(seed);
 by = obj.randomVal(seed);
 cy = obj.randomVal(seed);
 dy = obj.randomVal(seed);
testCoordsY = "";
testCoordsY += ay;
testCoordsY += by;
testCoordsY += cy;
testCoordsY += dy;
 az = obj.randomVal(seed);
 bz = obj.randomVal(seed);
 cz = obj.randomVal(seed);
 dz = obj.randomVal(seed);
testCoordsZ = "";
testCoordsZ += az;
testCoordsZ += bz;
testCoordsZ += cz;
testCoordsZ += dz;
} while (checkValidSpawn(sb, testCoordsX, testCoordsY, testCoordsZ, mapName) == false);
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

public static List<String> getCollisionMap(StringBuilder sb, String mapName) {
List<String> colList = new ArrayList<String>();
int offset;
int totalPolys;
String stringCol;
int temp;
String tempCoordsA;
String tempCoordsB;
String numOfPolys;
String typeOfPolys;
if (mapName == "bob") {
//offset = 15726479 * 2;
offset = 16608167 * 2;
} else {
offset = 0;
}
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
if (typeOfPolys.equals("0E")) {
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

public static boolean eoo(char a, char b, char c, char d) {
if ((a == '0')&&(b == '0')&&(c == '4')&&(d == '1'))
return true;
return false;
}
public static boolean checkValidSpawn(StringBuilder sb, String coordX, String coordY, String coordZ, String map) {
int currentCoordX = Integer.valueOf(coordX,16).shortValue();
int currentCoordY = Integer.valueOf(coordY,16).shortValue();
int currentCoordZ = Integer.valueOf(coordZ,16).shortValue();

List<String> colPolys = getCollisionMap(sb ,map);

for(int i = 0; i < colPolys.size(); i++) {
   String tempStr = colPolys.get(i);
   if (tempStr.length() == 12) {
   //x1
   String x1 = tempStr.substring(0, 4);
   int x1v = Integer.valueOf(x1,16).shortValue();
   //y1
   String y1 = tempStr.substring(4, 8);
   int y1v = Integer.valueOf(y1,16).shortValue();
   //z1
   String z1 = tempStr.substring(8, 12);
   int z1v = Integer.valueOf(z1,16).shortValue();
   } else if (tempStr.length() == 16){
   //x1
   String x1 = tempStr.substring(0, 4);
   int x1v = Integer.valueOf(x1,16).shortValue();
   //y1
   String y1 = tempStr.substring(4, 8);
   int y1v = Integer.valueOf(y1,16).shortValue();
   //z1
   String z1 = tempStr.substring(8, 12);
   int z1v = Integer.valueOf(z1,16).shortValue();
   //s1
   String s1 = tempStr.substring(12, 14);
   int s1v = Integer.valueOf(s1,16).shortValue();
   //s2
   String s2 = tempStr.substring(14, 16);
   int s2v = Integer.valueOf(s2,16).shortValue();
   }
   //x1,y1,z1 here
}
return false;
}

   public static void randomizer(File fh, boolean rc, boolean ss, boolean oc, boolean mc, boolean et, boolean sc, boolean ms, boolean wp) throws IOException{
        Seed obj = new Seed();
        byte[] byteArray = readFileFullyIntoBuffer(fh);
        String hexChars = bytesToHex(byteArray);
        StringBuilder sb = new StringBuilder(hexChars);
        sb = disableCRC(sb);
        if (rc == true) {
        sb = redCoinsRandomizer(sb, bobRedCoin, obj.randnum, obj, "bob");
        sb = redCoinsRandomizer(sb, bbhRedCoin, obj.randnum, obj, "bbh");
        sb = redCoinsRandomizer(sb, ccmRedCoin, obj.randnum, obj, "ccm");
        sb = redCoinsRandomizer(sb, hmcRedCoin, obj.randnum, obj, "hmc");
        sb = redCoinsRandomizer(sb, sslRedCoin, obj.randnum, obj, "ssl");
        sb = redCoinsRandomizer(sb, smlRedCoin, obj.randnum, obj, "sml");
        sb = redCoinsRandomizer(sb, wdwRedCoin, obj.randnum, obj, "wdw");
        sb = redCoinsRandomizer(sb, jrbRedCoin, obj.randnum, obj, "jrb");
        sb = redCoinsRandomizer(sb, thiRedCoin, obj.randnum, obj, "thi");
        sb = redCoinsRandomizer(sb, ttcRedCoin, obj.randnum, obj, "ttc");
        sb = redCoinsRandomizer(sb, rrRedCoin, obj.randnum, obj, "rr");
        sb = redCoinsRandomizer(sb, bow1RedCoin, obj.randnum, obj, "bow1");
        sb = redCoinsRandomizer(sb, vcRedCoin, obj.randnum, obj, "vc");
        sb = redCoinsRandomizer(sb, bow2RedCoin, obj.randnum, obj, "bow2");
        sb = redCoinsRandomizer(sb, saRedCoin, obj.randnum, obj, "sa");
        sb = redCoinsRandomizer(sb, bow3RedCoin, obj.randnum, obj, "bow3");
        sb = redCoinsRandomizer(sb, lllRedCoin, obj.randnum, obj, "lll");
        sb = redCoinsRandomizer(sb, dddRedCoin, obj.randnum, obj, "ddd");
        sb = redCoinsRandomizer(sb, wfRedCoin, obj.randnum, obj, "wf");
        sb = redCoinsRandomizer(sb, mcRedCoin, obj.randnum, obj, "mc");
        sb = redCoinsRandomizer(sb, wcRedCoin, obj.randnum, obj, "wc");
        sb = redCoinsRandomizer(sb, rcbRedCoin, obj.randnum, obj, "rcb");
        sb = redCoinsRandomizer(sb, ttmRedCoin, obj.randnum, obj, "ttm");
        }
        if (ss == true) {
        sb = modelRandomizer(sb, bobStarModel, obj.randnum, obj, "bob", 2);
        sb = objectRandomizer(sb, bobStarBox, obj.randnum, obj, "bob", 1);
         
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
        try (FileOutputStream fos = new FileOutputStream("SM64 TEST.ext.z64")) {
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