package com.github.dockerjava.api.command;

import com.github.dockerjava.api.model.ContainerNetwork;
import com.github.dockerjava.core.RemoteApiVersion;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Connects a container to a network.
 *
 * @since {@link RemoteApiVersion#VERSION_1_21}
 */
public interface ConnectToNetworkCmd extends SyncDockerCmd<Void> {

    @CheckForNull
    public String getNetworkId();

    @CheckForNull
    public String getContainerId();

    @CheckForNull
    ContainerNetwork getEndpointConfig();

    public ConnectToNetworkCmd withNetworkId(@Nonnull String networkId);

    public ConnectToNetworkCmd withContainerId(@Nonnull String containerId);

    public ConnectToNetworkCmd withEndpointConfig(@Nonnull ContainerNetwork endpointConfig);

    public static interface Exec extends DockerCmdSyncExec<ConnectToNetworkCmd, Void> {
    }
}
