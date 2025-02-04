/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.httpurlconnection;

import io.opentelemetry.instrumentation.api.instrumenter.net.NetClientAttributesGetter;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;
import java.net.HttpURLConnection;
import javax.annotation.Nullable;

class HttpUrlNetAttributesGetter implements NetClientAttributesGetter<HttpURLConnection, Integer> {

  @Override
  @Nullable
  public String getTransport(HttpURLConnection connection, @Nullable Integer status) {
    return SemanticAttributes.NetTransportValues.IP_TCP;
  }

  @Nullable
  @Override
  public String getProtocolName(HttpURLConnection connection, @Nullable Integer integer) {
    // HttpURLConnection hardcodes the protocol name&version
    return "http";
  }

  @Nullable
  @Override
  public String getProtocolVersion(HttpURLConnection connection, @Nullable Integer integer) {
    // HttpURLConnection hardcodes the protocol name&version
    return "1.1";
  }

  @Override
  public String getPeerName(HttpURLConnection connection) {
    return connection.getURL().getHost();
  }

  @Override
  public Integer getPeerPort(HttpURLConnection connection) {
    return connection.getURL().getPort();
  }
}
