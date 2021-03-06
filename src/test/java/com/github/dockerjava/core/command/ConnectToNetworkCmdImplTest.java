package com.github.dockerjava.core.command;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateNetworkResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.client.AbstractDockerClientTest;

@Test(groups = "integration")
public class ConnectToNetworkCmdImplTest extends AbstractDockerClientTest {

    @BeforeTest
    public void beforeTest() throws Exception {
        super.beforeTest();
    }

    @AfterTest
    public void afterTest() {
        super.afterTest();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        super.beforeMethod(method);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        super.afterMethod(result);
    }

    @Test
    public void connectToNetwork() throws InterruptedException {

        CreateContainerResponse container = dockerClient.createContainerCmd("busybox").withCmd("sleep", "9999").exec();
        dockerClient.startContainerCmd(container.getId()).exec();

        CreateNetworkResponse network = dockerClient.createNetworkCmd().withName("testNetwork").exec();

        dockerClient.connectToNetworkCmd().withNetworkId(network.getId()).withContainerId(container.getId()).exec();

        Network updatedNetwork = dockerClient.inspectNetworkCmd().withNetworkId(network.getId()).exec();

        assertTrue(updatedNetwork.getContainers().containsKey(container.getId()));

        InspectContainerResponse inspectContainerResponse = dockerClient.inspectContainerCmd(container.getId()).exec();

        assertNotNull(inspectContainerResponse.getNetworkSettings().getNetworks().get("testNetwork"));
    }
}
