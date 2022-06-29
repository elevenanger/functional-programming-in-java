package cn.anger.functional.compose;

import cn.anger.functional.design.AngerFinance;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/6/29 10:06
 * description : 股票列表
 */
public class Tickers {

    private static String financeInfoPath = "./src/main/java/resources/";

    private static final String[] LETTERS = "abcdefghijklmnopqrstuvwxyz".split("");

    private static final Random random = new Random();

    private static final Supplier<String> randomLetter =
        () -> LETTERS[random.nextInt(LETTERS.length - 1)];

    private static final Supplier<String> randomSymbol =
        () -> IntStream.rangeClosed(0,random.nextInt(5))
            .mapToObj(v -> randomLetter.get())
            .reduce("anger", String::concat);

    public static void writeSymbolPrice() {
        try (FileWriter writer =
            new FileWriter(financeInfoPath + randomSymbol.get())){
            String prices = IntStream.range(0,100)
                .mapToObj(v -> random.nextInt(200) + "\n")
                .reduce("prices:\n", String::concat);
            writer.write(prices);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> listSymbols() {
        try (Stream<Path> pathStream =
                Files.list(Paths.get(financeInfoPath))){
            return pathStream
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        Stream.iterate(1,v -> v + 1)
            .limit(20)
            .forEach(v -> writeSymbolPrice());

        listSymbols().stream()
            .map(AngerFinance::getPrice)
            .filter(price ->price.compareTo(new BigDecimal("100")) > 0)
            .sorted()
            .forEach(System.out::println);

    }
}
