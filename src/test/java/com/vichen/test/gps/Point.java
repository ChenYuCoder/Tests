package com.vichen.test.gps;

import org.springframework.util.Assert;

import java.util.Locale;

/**
 * 坐标类
 */
public class Point {
  private static final long serialVersionUID = 8487155588359019666L;

  /** x轴坐标 */
  private final double x;

  /** y轴坐标 */
  private final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Point(Point point) {
    Assert.notNull(point, "Source point must not be null!");

    this.x = point.x;
    this.y = point.y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  /**
   * 转换数组对象
   *
   * @return 数组对象 [x, y]
   */
  public double[] toArray() {
    return new double[] {x, y};
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Point)) {
      return false;
    }
    Point point = (Point) o;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(point.x)) {
      return false;
    }

    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(point.y)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;

    long temp = Double.doubleToLongBits(x);
    result = 31 * result + (int) (temp ^ temp >>> 32);

    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ temp >>> 32);

    return result;
  }

  @Override
  public String toString() {
    return String.format(Locale.ENGLISH, "Point [x=%f, y=%f]", x, y);
  }
}
