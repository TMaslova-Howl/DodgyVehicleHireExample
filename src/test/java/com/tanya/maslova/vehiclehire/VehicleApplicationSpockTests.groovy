package com.tanya.maslova.vehiclehire

import com.tanya.maslova.vehiclehire.controller.VehicleHireController
import com.tanya.maslova.vehiclehire.service.VehicleHireService
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@ActiveProfiles("test")
class VehicleApplicationSpockTests extends Specification {

    MockMvc mvc
    VehicleHireService vehicleHireService
    def CONTENT = "{\"carCategories\": [\"SMALL\"],\"dateFrom\": \"2020-09-17\",\"dateTo\": \"2020-09-20\"}";

    def setup() {
        vehicleHireService = Mock(VehicleHireService.class)
        mvc = standaloneSetup(new VehicleHireController(vehicleHireService)).build()
    }

    def "when get available cars is performed then response has status 200" () {
        expect: "status is 200"
        mvc.perform(get("/availableCars"))
                .andExpect(status().isOk())
    }

    def "when get hired cars is performed then response has status 200" () {
        expect: "status is 200"
        mvc.perform(get("/hiredCars"))
                .andExpect(status().isOk())
    }

    def "when post calculate hire cost is performed then response has status 200" () {
        expect: "status is 200"
        mvc.perform(post("/calculateHireCost")
                .content(CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    def "when post calculate final cost is performed then repose has satus 200" () {
        expect: "status is 200"
        mvc.perform(post("/finalCost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CONTENT))
                .andExpect(status().isOk())
    }
}
