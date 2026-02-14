package com.ntr.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

public final class Singleton {

  AtomicBoolean running = new AtomicBoolean(true);
  private final Object lock = new Object();

  public static void main(String[] args) {

  }


}
