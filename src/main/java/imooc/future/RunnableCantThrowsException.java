package imooc.future;

/**
 * @author chenlufeng
 * @date 2021/10/6
 */
public class RunnableCantThrowsException {
    public void ddd() throws Exception {

    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }
}
