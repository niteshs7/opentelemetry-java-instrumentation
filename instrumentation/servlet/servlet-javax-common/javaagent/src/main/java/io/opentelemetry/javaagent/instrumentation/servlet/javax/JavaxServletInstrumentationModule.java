/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.servlet.javax;

import com.google.auto.service.AutoService;
import io.opentelemetry.javaagent.instrumentation.servlet.common.dispatcher.RequestDispatcherInstrumentation;
import io.opentelemetry.javaagent.instrumentation.servlet.common.response.HttpServletResponseInstrumentation;
import io.opentelemetry.javaagent.tooling.InstrumentationModule;
import io.opentelemetry.javaagent.tooling.TypeInstrumentation;
import java.util.Arrays;
import java.util.List;

@AutoService(InstrumentationModule.class)
public class JavaxServletInstrumentationModule extends InstrumentationModule {
  private static final String BASE_PACKAGE = "javax.servlet";

  public JavaxServletInstrumentationModule() {
    super("servlet", "servlet-javax-common");
  }

  @Override
  public List<TypeInstrumentation> typeInstrumentations() {
    return Arrays.asList(
        new HttpServletResponseInstrumentation(
            BASE_PACKAGE, adviceClassName(".response.ResponseSendAdvice")),
        new RequestDispatcherInstrumentation(
            BASE_PACKAGE, adviceClassName(".dispatcher.RequestDispatcherAdvice")));
  }

  private static String adviceClassName(String suffix) {
    return JavaxServletInstrumentationModule.class.getPackage().getName() + suffix;
  }
}
