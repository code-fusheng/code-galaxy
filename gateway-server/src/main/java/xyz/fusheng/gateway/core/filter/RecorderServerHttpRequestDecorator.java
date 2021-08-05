package xyz.fusheng.gateway.core.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import xyz.fusheng.gateway.core.filter.util.DataBufferUtilFix;
import xyz.fusheng.gateway.core.filter.util.DataBufferWrapper;

/**
 * @author code-fusheng
 */

public class RecorderServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private DataBufferWrapper data = null;

    public RecorderServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        synchronized (this) {
            Mono<DataBuffer> mono = null;
            if (data == null) {
                mono = DataBufferUtilFix.join(super.getBody())
                        .doOnNext(d -> this.data = d)
                        .filter(d -> d.getFactory() != null)
                        .map(DataBufferWrapper::newDataBuffer);
            } else {
                mono = Mono.justOrEmpty(data.newDataBuffer());
            }
            return Flux.from(mono);
        }
    }
}
