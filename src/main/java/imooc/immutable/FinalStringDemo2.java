package imooc.immutable;

/**
 * @author chenlufeng
 * @date 2021/9/25
 */
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashiXiong();
        String c = b + 2;
        System.out.println(a==c);
    }

    private static String getDashiXiong() {
        return "wukong";
    }
}
