package com.tanya.maslova.vehiclehire;

import com.tanya.maslova.vehiclehire.service.VehicleHireService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class VehiclehireApplicationTests {
    private static final String CONTENT = "{\"carCategories\": [\"SMALL\"],\"dateFrom\": \"2020-09-17\",\"dateTo\": \"2020-09-20\"}";

    @Autowired
    private MockMvc mvc;
    @MockBean
    private VehicleHireService vehicleHireService;

    @Test
    @SneakyThrows
    void shouldReturnStatusOk_whenGetAvailableCars(){
        mvc.perform(get("/availableCars"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldReturnStatusOk_whenGetHiredCars() {
        mvc.perform(get("/hiredCars"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldReturnStatusOk_whenPostCalculateHireCost(){
        mvc.perform(post("/calculateHireCost")
                        .content(CONTENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldReturnStatusOk_whenPostCalculateFinalCost(){
        mvc.perform(post("/finalCost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CONTENT))
                .andExpect(status().isOk());
    }


}
