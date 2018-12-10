package com.projects.callcenter.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.stream.Stream;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NumberUtilsTests {

    @Test
    public void when_getRandomNumberBetweenValues_then_shouldWorkOk() {
        int value = NumberUtils.getRandomBetween(5,10);
        Stream.iterate(0, i -> i).limit(100).forEach(  i -> {
            assertThat(NumberUtils.getRandomBetween(5,10), greaterThanOrEqualTo(5));
            assertThat(NumberUtils.getRandomBetween(5,10), lessThanOrEqualTo(10));
        });
    }
}
