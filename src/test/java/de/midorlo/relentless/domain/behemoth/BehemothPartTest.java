package de.midorlo.relentless.domain.behemoth;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class BehemothPartTest {

    BehemothPart p1, p2;

    @Test
    public void testTestEquals() {
        p1 = new BehemothPart(Hitzone.head, 1);
        p2 = new BehemothPart(Hitzone.head, 1);
        assertThat(p1, equalTo(p2));

        p1.setHealth(p1.getHealth() + 1);
        assertThat(p1, not(equalTo(p2)));

        p1.setHealth(p2.getHealth());
        p1.setHitzone(Hitzone.body);
        assertThat(p1, not(equalTo(p2)));

        p1.setHitzone(p2.getHitzone());
        p1.setMarginWounded(p2.getMarginWounded() + 1);
        assertThat(p1, not(equalTo(p2)));

        p1.setMarginWounded(p2.getMarginWounded());
        assertThat(p1, equalTo(p2));
    }
}