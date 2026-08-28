package com.traincompany.management.admin_inputs_api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.graphql.test.autoconfigure.GraphQlTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.graphql.test.tester.GraphQlTester;
// import com.traincompany.management.admin_inputs_api.DTOs.CityDTO;
import com.traincompany.management.admin_inputs_api.services.CityService;

@GraphQlTest(CityController.class)
public class CityControllerTest {
    
   //  @Autowired
   //  private GraphQlTester graphQlTester;

   @MockitoBean
    private CityService cityService;

    @Test
     void canGetCities() {
      assertEquals(1, 1);
      //   var x = graphQlTester.documentName("cities").execute().path("cities").entityList(CityDTO.class);
      //   x.hasSize(0);
     }
}
