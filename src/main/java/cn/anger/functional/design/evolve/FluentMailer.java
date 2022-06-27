package cn.anger.functional.design.evolve;

import java.util.function.Consumer;

/**
 * author : liuanglin
 * date : 2022/6/27 09:57
 * description : 更符合直觉的流畅的 mailer
 */
public class FluentMailer {
    /*
    private 构造函数
    不能直接 new 创建对象
    避免对象被随意创建
     */
    private FluentMailer() {}

    public FluentMailer from(final String address) {
        System.out.println("FluentMailer.from : " + address);
        return this;
    }

    public FluentMailer to(final String address) {
        System.out.println("FluentMailer.to : " + address);
        return this;
    }

    public FluentMailer subject(final String line) {
        System.out.println("FluentMailer.subject : " + line);
        return this;
    }

    public FluentMailer body(final String message) {
        System.out.println("FluentMailer.body : " + message);
        return this;
    }

    /**
     * 终止方法 send() 作为 static 方法
     * 接收 Consumer 作为参数
     * 调用该方法
     * 传入一段代码
     * send() 方法将会创建一个 FluentMailer 对象实例
     * 将其带入传入的代码中
     * 该实例的作用域仅存在于这段代码中
     * @param block 实现 Consumer 的代码块
     */
    public static void send(final Consumer<FluentMailer> block) {
        final FluentMailer mailer = new FluentMailer();
        block.accept(mailer);
        System.out.println("FluentMailer.send");
    }

    public static void main(String[] args) {
        FluentMailer.send(mailer ->
            mailer.from("1@anger.cn")
                .to("2@anger.cm")
                .subject("much better")
                .body("consumer"));
    }
}
