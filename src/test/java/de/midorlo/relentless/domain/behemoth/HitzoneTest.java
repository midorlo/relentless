package de.midorlo.relentless.domain.behemoth;

import de.midorlo.relentless.domain.MockedRepository;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class HitzoneTest {

    Hitzone h1, h2;

    @BeforeMethod
    public void setUp() {
        h1 = MockedRepository.mockHitzone();
        h2 = MockedRepository.mockHitzone();
    }

    @Test
    public void testTestEquals() {
        assertThat(h1, equalTo(h2));

        h1.setName(h2.getName() + ".");
        assertThat(h1, not(equalTo(h2)));

        h1.setName(h2.getName());
        assertThat(h1, equalTo(h2));
    }
}