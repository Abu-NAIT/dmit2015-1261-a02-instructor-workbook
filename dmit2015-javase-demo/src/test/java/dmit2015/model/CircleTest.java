package dmit2015.model;


import org.junit.jupiter.api.Test;

import  static  org.assertj.core.api.AssertionsForClassTypes.*;


public class CircleTest {

@Test
    void area_whenRadiusIs5_shouldReturnCorrectArray(){

    Circle circle = new Circle();
    circle.setRadius(5);

    assertThat(circle.area()).isCloseTo(78.54, within(0.01));


}

    @Test
    void area_whenRadiusIs4_shouldReturnCorrectArray(){

        Circle circle = new Circle();
        circle.setRadius(4);

        assertThat(circle.area()).isCloseTo(10.10, within(0.01));


    }



}
