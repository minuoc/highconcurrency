package imooc.immutable;

/**
 * @author chenlufeng
 * @date 2021/9/25
 */
public class FinalStringDemo {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        System.out.println(a==c);
        System.out.println(a==e);
    }
}
