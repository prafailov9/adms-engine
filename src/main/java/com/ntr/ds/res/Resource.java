package com.ntr.ds.res;

public class Resource {

  private String res;
  private static final Integer dicks = 1102301023;

  private Resource(String res) {
    this.res = res;
  }

  static class Holder {

    static Resource INSTANCE = new Resource("DICKS");

  }


  public static Resource get() {
    return Holder.INSTANCE;
  }

  @Override
  public String toString() {
    return "Resource{" +
        "res='" + res + '\'' +
        '}';
  }
}
