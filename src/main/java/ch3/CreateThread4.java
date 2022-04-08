package ch3;

public class CreateThread4 {
    private static int counter;

    public static void main(String[] args) {
        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try{
                    add(1);
                }catch ( Error e){
                    System.out.println(counter);
                }

            }
            private void add(int i){
                counter ++ ;
                add(i + 1);
            }
        }, "test", 1 << 24);
    }
}
