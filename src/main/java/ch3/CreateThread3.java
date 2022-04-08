package ch3;

public class CreateThread3 {
    private int i = 0;
    private byte[] bytes = new byte[1024];
    private static int counter = 0;
    public static void main(String[] args) {
//        int j = 0;
//        int[] arr = new int[1024];
        try{
            add(0);
        }catch (Error e){
            e.printStackTrace();
            System.out.println(counter);
        }

    }

    private static void add(int i){
        ++counter;
        add(i + 1);
    }
}
