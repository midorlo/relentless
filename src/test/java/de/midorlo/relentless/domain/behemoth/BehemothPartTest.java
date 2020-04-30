package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.BehemothPart;
import de.midorlo.relentless.domain.Hitzone;
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
        p1.setWoundHealth(p2.getWoundHealth() + 1);
        assertThat(p1, not(equalTo(p2)));

        p1.setWoundHealth(p2.getWoundHealth());
        assertThat(p1, equalTo(p2));
    }
}