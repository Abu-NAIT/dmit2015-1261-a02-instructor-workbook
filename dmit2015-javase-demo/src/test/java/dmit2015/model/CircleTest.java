package dmit2015.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class CircleTest {
@Test new*
        void area_whenRadiusIs5_shouldReturnCorrectArray(){
        Cricle circle = new Cricle();
        circle.setRadius(5);

        assertThat(circle.area()).isEqualTo(78.54, within(0.01));
        }
}
