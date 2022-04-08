package core.impatient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * @author chenlufeng
 * @date 2021/9/23
 */
public class Task1 {
    public static long occurences(String word, Path path){
        try {
            String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return Pattern.compile("\\PL+")
                    .splitAsStream(contents)
                    .filter(Predicate.isEqual(word))
                    .count();
        } catch (IOException e) {
            return 0;
        }

    }

    public static Set<Path> descendants(Path path) throws IOException {
        try (Stream<Path> entries = Files.walk(path)){
            return entries.collect(toSet());
        }
    }


    public static void main(String[] args) throws IOException {
        String word = "String";
        Set<Path> paths = descendants(Paths.get("user.dir"));
        List<Callable<Long>> tasks = new ArrayList<>();
        for (Path p :
                paths) {
//            tasks.add(()->{return occurences(word,)})

        }

    }
}
