package reflection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

class Junit3TestRunner {

    @Test
    void run() throws Exception {
        Class<Junit3Test> clazz = Junit3Test.class;

        // TODO Junit3Test에서 test로 시작하는 메소드 실행
        Method[] methods = clazz.getMethods();
        Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("test"))
                .forEach(method -> {
                    try {
                        Assertions.assertThatNoException().isThrownBy(() ->
                                method.invoke(clazz.getDeclaredConstructor().newInstance()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
