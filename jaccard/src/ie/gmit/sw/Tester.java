package ie.gmit.sw;

import com.sun.deploy.util.StringUtils;

public class Tester {

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "hello there";
        int hash1 = str1.hashCode();
        int hash2 = str2.hashCode();
        int hash3 = str2.hashCode();


//        int min = Integer.MAX_VALUE;
//        System.out.println((str1.hashCode()& 0x7fffffff) % 1000);
//        System.out.println(0x7fffffff);
//        System.out.println(min);

//        System.out.println(String.valueOf(hash1));
        System.out.println(hash2);
        System.out.println(hash3);

//        hash1 = hash1^hash2;
//        hash2 = hash1^hash2;
//        hash1 = hash1^hash2;
//
//
//        System.out.println(String.valueOf(hash1));
//        System.out.println(String.valueOf(hash2));


    }
}
