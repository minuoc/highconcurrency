package imooc.threadlocal;

/**
 * 利用ThreadLocal 避免传递参数的麻烦
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ThreadLocalUsage06 {
    public static void main(String[] args) {
        new Service1().process("");
    }


}

class Service1 {
    public void process(String name){
        User user = new User("路费");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        ThreadSafeFormatter.dateFormatThreadLocal.get();
        System.out.println("service2拿到的用户名:" + user.name);
        new Service3().process();
    }
}

class Service3 {
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("service3拿到的用户名:" + user.name);
        UserContextHolder.holder.remove();
    }
}


class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}


class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}



