
package enxio.nio.channels;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.SelectorProvider;

public class NativeDeviceChannel extends AbstractSelectableChannel implements ByteChannel, NativeSelectableChannel {

    private final int fd;
    private final int validOps;

    public NativeDeviceChannel(int fd) {
        this(NativeSelectorProvider.getInstance(), fd, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }
    public NativeDeviceChannel(SelectorProvider provider, int fd, int ops) {
        super(provider);
        this.fd = fd;
        this.validOps = ops;
    }
    
    @Override
    protected void implCloseSelectableChannel() throws IOException {
       Native.close(fd);
    }

    @Override
    protected void implConfigureBlocking(boolean block) throws IOException {
        Native.setBlocking(fd, block);
    }

    @Override
    public final int validOps() {
        return validOps;
    }
    public final int getFD() {
        return fd;
    }
    public int read(ByteBuffer dst) throws IOException {
        return Native.read(fd, dst);
    }

    public int write(ByteBuffer src) throws IOException {
        return Native.write(fd, src);
    }
}