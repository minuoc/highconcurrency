package test;

public class Main {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMoney();
        }

        public void showMoney() {
            System.out.println("father: i have $" + money);
        }

    }

    static class Son extends Father {
        public int money = 3;
        public Son() {
            money = 4;
            showMoney();
        }

        public void showMoney(){
            System.out.println("son: i have $" + money);
        }
    }


    public static void main(String[] args) {
        Father guy = new Son();
        System.out.println("this guy have $" + guy.money);
    }
}
