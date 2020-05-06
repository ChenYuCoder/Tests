package com.vichen.test.gps;

/**
 * 地图工具类
 * 火星坐标系(GCJ-02): google地图、高德地图、搜搜、阿里云等
 * 百度坐标系(BD-09): 百度地图
 * WGS84坐标系: 国际标准，谷歌国外地图、osm地图等国外的地图一般都是这个
 *
 * @author: gl
 * @date: Created in 9/10/18 10:21 AM
 * @modify by:
 */
public class GeographicUtil {
  /**
   * 一维度约等于111km
   */
  public static final int UNIT_LATITUDE = 111;

  /**
   * 赤道半径(单位: m)
   */
  private static final int EARTH_RADIUS = 6378245;

  private static final double X_PI = 3.14159265358979324 * 3000.0 / 180.0;
  private static final double EE = 0.00669342162296594323;

  private GeographicUtil() {}

  /**
   * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
   * 即谷歌、高德 转 百度
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 点对象
   */
  public static Point gcj02ToBd09(double lng, double lat) {
    double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * X_PI);
    double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * X_PI);
    double bd_lng = z * Math.cos(theta) + 0.0065, bd_lat = z * Math.sin(theta) + 0.006;

    return new Point(bd_lng, bd_lat);
  }

  /**
   * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
   * 即百度 转 谷歌、高德
   *
   * @param bd_lng 百度经度
   * @param bd_lat 百度纬度
   * @return 点对象
   */
  public static Point bd09ToGcj02(double bd_lng, double bd_lat) {
    double x = bd_lng - 0.0065, y = bd_lat - 0.006;
    double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * X_PI);
    double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * X_PI);
    double lng = z * Math.cos(theta), lat = z * Math.sin(theta);

    return new Point(lng, lat);
  }

  /**
   * WGS84转GCj02
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 点对象
   */
  public static Point wgs84ToGcj02(double lng, double lat) {
    if (inChina(lng, lat)) {
      double dlng = transformLng(lng - 105.0, lat - 35.0), dlat = transformLat(lng - 105.0, lat - 35.0);
      double radlat = rad(lat);
      double magic = Math.sin(radlat);
      magic = 1 - EE * magic * magic;
      double sqrtmagic = Math.sqrt(magic);
      dlng = (dlng * 180.0) / (EARTH_RADIUS / sqrtmagic * Math.cos(radlat) * Math.PI);
      dlat = (dlat * 180.0) / ((EARTH_RADIUS * (1 - EE)) / (magic * sqrtmagic) * Math.PI);

      return new Point(lng + dlng, lat + dlat);
    }

    return new Point(lng, lat);
  }

  /**
   * GCJ02转WGS84
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 点对象
   */
  public static Point gcj02ToWgs84(double lng, double lat) {
    if (inChina(lng, lat)) {
      double dlng = transformLng(lng - 105.0, lat - 35.0), dlat = transformLat(lng - 105.0, lat - 35.0);
      double radlat = rad(lat);
      double magic = Math.sin(radlat);
      magic = 1 - EE * magic * magic;
      double sqrtmagic = Math.sqrt(magic);
      dlng = (dlng * 180.0) / (EARTH_RADIUS / sqrtmagic * Math.cos(radlat) * Math.PI);
      dlat = (dlat * 180.0) / ((EARTH_RADIUS * (1 - EE)) / (magic * sqrtmagic) * Math.PI);
      double mglng = lng + dlng, mglat = lat + dlat;

      return new Point(lng * 2 - mglng, lat * 2 - mglat);
    }

    return new Point(lng, lat);
  }

  /**
   * WGS84转BD09
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 点对象
   */
  public static Point wgs84ToBd09(double lng, double lat) {
    Point point = wgs84ToGcj02(lng, lat);
    return gcj02ToBd09(point.getX(), point.getY());
  }

  /**
   * BD09转WGS84
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 点对象
   */
  public static Point bd09ToWgs84(double lng, double lat) {
    Point point = bd09ToGcj02(lng, lat);
    return gcj02ToWgs84(point.getX(), point.getY());
  }

  /**
   * 计算两点之间的实际距离
   *
   * @param lng1 第一点经度
   * @param lat1 第一点纬度
   * @param lng2 第二点经度
   * @param lat2 第二点纬度
   * @return 两点间距离(单位: km)
   */
  public static double realDistance(double lng1, double lat1, double lng2, double lat2) {
    double radLat1 = rad(lat1), radLat2 = rad(lat2);
    double a = radLat1 - radLat2, b = rad(lng1) - rad(lng2);
    double distance = 2 * Math.asin(Math.sqrt(
      Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math
        .pow(Math.sin(b / 2), 2)));
    distance *= EARTH_RADIUS;

    return distance / 1000;
  }

  /**
   * 计算两点之间的估计距离(减少计算量)
   * 算法:
   *   在赤道上经度差1度对应的实际距离是111千米
   *   在经线上纬度差1度对应的实际距离是111千米
   *   在除赤道外的其他纬线上，经度差1度对应的实际距离是111*cos纬度
   *
   * @param lng1 第一点经度
   * @param lat1 第一点纬度
   * @param lng2 第二点经度
   * @param lat2 第二点纬度
   * @return 两点间距离(单位: km)
   */
  public static double estimateDistance(double lng1, double lat1, double lng2, double lat2) {
    return Math.abs(lng2 - lng1) * Math.cos(rad(lat2)) * UNIT_LATITUDE + Math.abs(lat2 - lat1) * UNIT_LATITUDE;
  }

  /**
   * 转换经度
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 转换后经度
   */
  private static double transformLng(double lng, double lat) {
    double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
    ret += (20.0 * Math.sin(6.0 * lng * Math.PI) + 20.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lng * Math.PI) + 40.0 * Math.sin(lng / 3.0 * Math.PI)) * 2.0 / 3.0;
    ret += (150.0 * Math.sin(lng / 12.0 * Math.PI) + 300.0 * Math.sin(lng / 30.0 * Math.PI)) * 2.0 / 3.0;

    return ret;
  }

  /**
   * 转换纬度
   *
   * @param lng 经度
   * @param lat 纬度
   * @return 转换后纬度
   */
  private static double transformLat(double lng, double lat) {
    double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
    ret += (20.0 * Math.sin(6.0 * lng * Math.PI) + 20.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(lat * Math.PI) + 40.0 * Math.sin(lat / 3.0 * Math.PI)) * 2.0 / 3.0;
    ret += (160.0 * Math.sin(lat / 12.0 * Math.PI) + 320 * Math.sin(lat * Math.PI / 30.0)) * 2.0 / 3.0;

    return ret;
  }

  /**
   * 是否在中国
   *
   * @param lng 经度
   * @param lat 纬度
   * @return true:在中国
   */
  private static boolean inChina(double lng, double lat) {
    return 73.66 < lng && 135.05 > lng && 3.86 < lat && 53.55 > lat;
  }

  /**
   * 经纬度转换成弧度
   *
   * @param d 经度/纬度
   * @return 弧度
   */
  private static double rad(double d) {
    return d * Math.PI / 180.0;
  }
}
