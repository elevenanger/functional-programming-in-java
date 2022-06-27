package cn.anger.functional.design.evolve;

/**
 * author : liuanglin
 * date : 2022/6/27 09:02
 * description : 邮件程序
 */
public class Mailer {

    public void from(final String address) {
        System.out.println("Mailer.from : " + address);
    }

    public void to(final String address) {
        System.out.println("Mailer.to : " + address);
    }

    public void subject(final String line) {
        System.out.println("Mailer.subject : " + line);
    }

    public void body(final String message) {
        System.out.println("Mailer.body : " + message);
    }

    public void send() {
        System.out.println("Mailer.send");
    }

    public static void main(String[] args) {
        /*
        在类中声明一系列 void 方法
        然后重复使用 mailer 对象调用这些方法
        这回导致很多代码重复
        而且在调用结束后如何处理当前的 mailer 对象实例
        实例的生命周期不清晰
        是可以复用还是一次性的，都无法从这一段代码中直观获取到这些信息
         */
        Mailer badMailer = new Mailer();
        badMailer.from("1@anger.cn");
        badMailer.to("2@anger.cn");
        badMailer.subject("bad implementation");
        badMailer.body("bad");
        badMailer.send();
    }
}
