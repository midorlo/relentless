package de.midorlo.relentless.domain.behemoth;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BehemothTest {

    @Test
    public void getPower() {

        Behemoth behemoth = new Behemoth();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 50);
        map.put(2, 75);
        map.put(5, 200);
        map.put(6, 250);
        map.put(7, 275);
        map.put(8, 300);
        map.put(9, 325);
        map.put(10, 350);
        map.put(11, 375);
        map.put(12, 400);
        map.put(13, 425);
        map.put(14, 450);
        map.put(15, 475);
        map.put(16, 500);
        map.put(17, 550);
        map.put(18, 600);
        map.put(19, 625);
        map.put(22, 700);
        map.keySet().forEach(integer -> {
            behemoth.setThread(integer);
            assertThat(behemoth.getPower(), equalTo(map.get(integer)));
        });
    }
}
