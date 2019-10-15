package rand;
public class Vector3 {
        public float x, y, z;

        public Vector3(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vector3 add(Vector3 other) {
            return new Vector3(x + other.x, y + other.y, z + other.z);
        }

        public Vector3 sub(Vector3 other) {
            return new Vector3(x - other.x, y - other.y, z - other.z);
        }

        public Vector3 scale(float f) {
            return new Vector3(x * f, y * f, z * f);
        }
    public final static Vector3 cross(Vector3 A, Vector3 B) {
        return new Vector3(A.y*B.z - A.z*B.y, A.z*B.x - A.x*B.z, A.x*B.y - A.y*B.x);
    }
        public Vector3 cross(Vector3 other) {
            return new Vector3(y * other.z - z * other.y,
                               z - other.x - x * other.z,
                               x - other.y - y * other.x);
        }

        public float dot(Vector3 other) {
            return x * other.x + y * other.y + z * other.z;
        }

        public final void normalize( ) {
        float t = x*x + y*y + z*z;
        if (t != 0 && t != 1) t = (float) (1 / Math.sqrt(t));
        x *= t;
        y *= t;
        z *= t;
    }
        static double area(int x1, int y1, int x2, int y2, 
                                        int x3, int y3) 
    { 
       return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ 
                                    x3*(y1-y2))/2.0); 
    } 
       
    public static boolean isInside(Vector3 v1, Vector3 v2, Vector3 v3, Vector3 p1) 
    {    
    int x1 =(int) v1.x;
    int x2 = (int)v2.x;
    int x3 = (int)v3.x;
    int x =(int) p1.x;
    int y1 =(int) v1.z;
    int y2 =(int) v2.z;
    int y3 = (int)v3.z;
    int y = (int)p1.z;
       /* Calculate area of triangle ABC */
        double A = area (x1, y1, x2, y2, x3, y3); 
       
       /* Calculate area of triangle PBC */  
        double A1 = area (x, y, x2, y2, x3, y3); 
       
       /* Calculate area of triangle PAC */  
        double A2 = area (x1, y1, x, y, x3, y3); 
       
       /* Calculate area of triangle PAB */   
        double A3 = area (x1, y1, x2, y2, x, y); 
         
       /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3); 
    } 
   public static float intersectRayTriangle(Vector3 Point1, 
            Vector3 Vec1, Vector3 Vec2, Vector3 Vec3,
            float epsilon) {
        float originX = Point1.x;
        float originY = Point1.y;
        float originZ = Point1.z;
        float dirX = originX;
        float dirY = -originY;
        float dirZ = originZ;
        float v0X = Vec1.x;
        float v0Y = Vec1.y;
        float v0Z = Vec1.z;
        float v1X = Vec2.x;
        float v1Y = Vec2.y;
        float v1Z = Vec2.z;
        float v2X = Vec3.x;
        float v2Y = Vec3.y;
        float v2Z = Vec3.z;
        float edge1X = v1X - v0X;
        float edge1Y = v1Y - v0Y;
        float edge1Z = v1Z - v0Z;
        float edge2X = v2X - v0X;
        float edge2Y = v2Y - v0Y;
        float edge2Z = v2Z - v0Z;
        float pvecX = dirY * edge2Z - dirZ * edge2Y;
        float pvecY = dirZ * edge2X - dirX * edge2Z;
        float pvecZ = dirX * edge2Y - dirY * edge2X;
        float det = edge1X * pvecX + edge1Y * pvecY + edge1Z * pvecZ;
        if (det < epsilon)
            return -1.0f;
/*        float tvecX = originX - v0X;
        float tvecY = originY - v0Y;
        float tvecZ = originZ - v0Z;
        float invDet = 1.0f / det;
        float u = (tvecX * pvecX + tvecY * pvecY + tvecZ * pvecZ) * invDet;
        if (u < 0.0f || u > 1.0f)
            return -1.0f;
        float qvecX = tvecY * edge1Z - tvecZ * edge1Y;
        float qvecY = tvecZ * edge1X - tvecX * edge1Z;
        float qvecZ = tvecX * edge1Y - tvecY * edge1X;
        float v = (dirX * qvecX + dirY * qvecY + dirZ * qvecZ) * invDet;
        if (v < 0.0f || u + v > 1.0f)
            return -1.0f;
        float t = (edge2X * qvecX + edge2Y * qvecY + edge2Z * qvecZ) * invDet;
        return t;*/
        return 1.0f;
    }
}