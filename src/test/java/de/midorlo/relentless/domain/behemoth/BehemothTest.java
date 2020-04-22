package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.Element;
import de.midorlo.relentless.domain.MockedRepository;
import lombok.extern.java.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Log
public class BehemothTest {

    Behemoth b1 = new Behemoth();
    Behemoth b2 = new Behemoth();

    @BeforeClass
    public void setup() {
        log.info("setup()");
        b1 = MockedRepository.mockBehemoth();
        b2 = MockedRepository.mockBehemoth();
        log.info("setup();");
    }

    @Test
    public void getPowerTest() {

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

    @Test
    public void equalsTest() {
        assertThat(b1, equalTo(b2));

        b1.setElement(Element.Neutral);
        b2.setElement(Element.Frost);
        assertThat(b1, not(equalTo(b2)));

        b1.setElement(b2.getElement());
        b1.setName(b2.getName() + ".");
        assertThat(b1, not(equalTo(b2)));

        b1.setName(b2.getName());
        assertThat(b1, equalTo(b2));

        b1.setThread(b2.getThread() + 1);
        assertThat(b1, not(equalTo(b2)));

        b1.setThread(b2.getThread());
        b1.setHealth(b2.getHealth() + 1);
        assertThat(b1, not(equalTo(b2)));

        b1.setHealth(b2.getHealth());
        b1.setStaggerHealth(b2.getStaggerHealth() + 1);
        assertThat(b1, not(equalTo(b2)));

        b1.setStaggerHealth(b2.getStaggerHealth());
        assertThat(b1, equalTo(b2));

        b1.setBehemothParts(MockedRepository.mockBehemothParts());
        assertThat(b1, equalTo(b2));

        b1.getBehemothParts().get(0).setHealth(1);
        assertThat(b1, not(equalTo(b2)));
    }
}
