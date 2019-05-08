package pattern.design;

/**
 * @Author: zouhongxue
 * @Date: 2019/4/23 12:04 AM
 */
public interface Subject {
    public void buyMac();
}

class RealSubject implements Subject {

    @Override
    public void buyMac() {
        System.out.println("Buy a Mac");
    }
}

class Proxy implements Subject {

    @Override
    public void buyMac() {
        RealSubject realSubject = new RealSubject();
        realSubject.buyMac();
        this.wrapMac();
    }

    private void wrapMac() {
        System.out.println("Box Mac");
    }
}

class UseProxy {
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.buyMac();
    }
}