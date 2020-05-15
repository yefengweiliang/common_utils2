package com.utils.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.client.config.RequestConfig;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpSender {
    /**
     * socket超时时间
     */
    private Integer socketTimeOut;
    /**
     * http超时时间
     */
    private Integer httpTimeOut;

    public RequestConfig getConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(this.socketTimeOut)
                .setConnectTimeout(this.httpTimeOut)
                .build();
        return requestConfig;
    }
}
