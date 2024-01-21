/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class GreetingControllerTests {

    @Value("${local.server.port}")
    private int serverPort;

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;

    @Given("the server is running")
    public void theServerIsRunning() {
        System.out.println("Server is running on port: " + serverPort);
        String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        String processId = processName.split("@")[0];
        System.out.println("Server process ID: " + processId);
    }

    @When("the client sends a GET request to {string}")
    public void theClientSendsAGETRequestTo(String url) throws Exception {
        this.resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(print());
    }

    @When("the client sends a GET request to {string} with the parameter {string} set to {string}")
    public void theClientSendsAGETRequestToWithTheParameterSetTo(String url, String paramName, String paramValue) throws Exception {
        this.resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get(url).param(paramName, paramValue)).andDo(print());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) throws Exception {
        this.resultActions.andExpect(status().is(expectedStatus));
    }

    @Then("the response content should be {string}")
    public void theResponseContentShouldBe(String expectedContent) throws Exception {
        String actualContent = this.resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Actual response content: " + actualContent);

        this.resultActions.andExpect(jsonPath("$.content").value(expectedContent));
    }
}