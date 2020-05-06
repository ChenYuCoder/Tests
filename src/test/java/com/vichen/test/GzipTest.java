package com.vichen.test;

import org.junit.Test;

public class GzipTest {
  @Test public void test(){
    String str="H4sIAAAAAAAAAO1YTWvjSBC9768QOjuiq79bt9kwCwO7MIcJe2w6UtsRI0tCasUTQv77Vtuxxx+a\n"
      + "jRl2wGFtjJLuKpVKr16Vn/Scli64NH9OXbALF3yaz109+Fla1NXy3ra9dV3Xt6542Fnaxi76dmzK\n"
      + "3U7n+q9+fzkMvll4PLdp0LHwS9+ENA/9iNZ+bn3j7mtvi5XrTnfDsi398jXYyywt/WNV+E8YPv2S\n"
      + "/34D+Eln6byuFg9hvWtuLQgpgCgm0ajQ2vbVomrQNjwNAYNhTrUL87bHuOmnPz7myZ9VM35L3LKU\n"
      + "POEZz5S4gczXKisw1XbIvmlpJccTw5Dm6/BKGSBacdx66hCnGLLxdggujEP68tvzdyjrUIWxjD5M\n"
      + "ipgPXvx1h0EGGjgThDDcb5vFqwEoZIwawzgYRfEiCIt98K6smkWaC5MZIxlTlOgLw4QDO8akaubt\n"
      + "ASIr12yBysll5U9Bxkq85r+X6EFFf31zvJrfcXcwxoW+dscBJlwQbd7qjuvofRtIAUaQK7kOMFHn\n"
      + "jN7bvz98hm2p44LuyBJX7GDFdyvXwRrfaghVMcRARds0vggVcrBAZiGpbmCWVoPtPVI08mdHKF9U\n"
      + "/tHbmIKd1+0KXdnEtu3XrI9lbYOrbY9Rq6XfBA69a4ZlFY6jnO5vw7zErOlR1of5bYntOnamHz/T\n"
      + "z07Dgx1sy3bV1K0r7dD52KokQzYTRqimmkiGNNn4jd2+F5cZkUhhCkhKI7lA6lXD1y8RKaw2aJ6J\n"
      + "dLN3N8QTUqozGneOLkcziZeKzAYmuQYkeDX39vbz3d3gFhG4DI2CobbZWP7yy51FYZJGCMyw66tH\n"
      + "xNlOFBeo1Iaq7z5TtWMoFKjBWnfjfV0Vk3EySZXEPOVHvfObimUyoiUVRkN0xAL1wW6YkyZgcoZf\n"
      + "loxdAknpnmZJwnPK4p9kHHw/4H8Rn8Q9+h5vM08QADTjkcYjMQjiYS0MZIpRBYQzyYgkWNgYyTbj\n"
      + "ck3flb8/qTLeDDapNpxpITQe+cbvqMoik1IC0wykIIj0pU0YzmE3YTaxr/rop8DU2PGMX58ejjA5\n"
      + "5yfsf4VIHAP0isgeIuYqc96rzMH+JoJpaXD2gZqSOZQj7SjV2hAmKFPy52UOxWctbB9mCEFlAPpU\n"
      + "58TXN8aYeJETocMAc3hT5xgSfw/f0Dkcb+cMnYOtdpbOMYRO6xwOpzpH/KvOIXKtc+AHOkeQDB/i\n"
      + "FWEo6KQCqs/SOYwQLiXBcQMyjvQpnUNFxjjlOJawp7kSQC9txmDVLkPovPNHdcRS4OEMnbOFcnNr\n"
      + "ZyG5cd0CuV39FzjG/YuCESInz4Bx/9XqZTUVAE4FMflq9R/iov6ndxgAAA==";
    System.out.println(DataHelper.unGzip(str));
  }
}
