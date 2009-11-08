/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kenai.jnr.enxio.channels;

import com.kenai.jaffl.Platform;
import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import com.kenai.jnr.enxio.channels.kqueue.KQSelector;
import com.kenai.jnr.enxio.channels.poll.PollSelector;

/**
 *
 * @author wayne
 */
public final class NativeSelectorProvider extends SelectorProvider {
    private static final class SingletonHolder {
        static NativeSelectorProvider INSTANCE = new NativeSelectorProvider();
    }
    public static final SelectorProvider getInstance() {
        return SingletonHolder.INSTANCE;
    }
    @Override
    public DatagramChannel openDatagramChannel() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pipe openPipe() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AbstractSelector openSelector() throws IOException {
        return Platform.getPlatform().isBSD() ? new KQSelector(this) : new PollSelector(this);
    }

    @Override
    public ServerSocketChannel openServerSocketChannel() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SocketChannel openSocketChannel() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}