package pattern.design;

/**
 * @Author: zouhongxue
 * @Date: 2019/4/22 11:56 PM
 */
abstract class Factory {
    public abstract Product manufactrue();
}

abstract class Product{
    public abstract void show();
}

class ProductA extends Product {

    @Override
    public void show() {
        System.out.println("生产了A");
    }
}

class ProductB extends Product {

    @Override
    public void show() {
        System.out.println("生产了B");
    }
}

class FactoryA extends Factory {

    @Override
    public Product manufactrue() {
        return new ProductA();
    }
}

class FactoryB extends Factory {

    @Override
    public Product manufactrue() {
        return new ProductB();
    }
}