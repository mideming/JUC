package com.mideming.juc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class JucApplicationTests {

    @Test
    void contextLoads() {
        Long n = 1L;
        Long b = 2L;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYYMMdd");
        String A = localDate.format(dateTimeFormatter);
        long l = Long.parseLong(A) * n % b;
        String format = String.format("%08d", l);
        System.out.println(format);
    }
    private static final ThreadLocal<Map<Integer, List<Integer>>> issueCouponMap = ThreadLocal.withInitial(HashMap::new);
    @Test
    void test() {
        for (int cardTemplateId = 11; cardTemplateId < 20; cardTemplateId++) {
            for (int wid = 0; wid < 10; wid++) {
                List<Integer> cardTemplateIds = issueCouponMap.get().computeIfAbsent(wid, k -> new ArrayList<>());
                cardTemplateIds.add(cardTemplateId);
            }
        }
        for (int wid = 0; wid < 10; wid++) {
            System.out.println(issueCouponMap.get().get(wid));
        }
    }

}
