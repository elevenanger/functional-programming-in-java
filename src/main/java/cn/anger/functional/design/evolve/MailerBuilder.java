package cn.anger.functional.design.evolve;

/**
 * author : liuanglin
 * date : 2022/6/27 09:38
 * description : 使用 builder 来构建对象实例
 */
public class MailerBuilder {

    public MailerBuilder from(final String address) {
        System.out.println("MailerBuilder.from : " +address);
        return this;
    }

    public MailerBuilder to(final String address) {
        System.out.println("MailerBuilder.to : " + address);
        return this;
    }

    public MailerBuilder subject(final String line) {
        System.out.println("MailerBuilder.subject : " + line);
        return this;
    }

    public MailerBuilder body(final String message) {
        System.out.println("MailerBuilder.body : " + message);
        return this;
    }

    public void send() {
        System.out.println("MailerBuilder.send");
    }

    public static void main(String[] args) {
        /*
        使用方法责任链或者级联的设计模式
        相对于使用 void 方法
        使每个方法都返回一个实例
        这个返回的对象一般是 this - 调用方法的对象
        使用这个对象来构建或者链接后续的调用
        这种方式将避免重复使用变量名
        并且将方法很好地连接到一起
        但是这个方法无法避免使用 new 将对象引用指向某个变量
        同样会存在对象生命周期的问题
         */
        new MailerBuilder()
            .from("1@anger.cn")
            .to("2@anger.cn")
            .subject("better implementation")
            .body("builder")
            .send();
    }
}
