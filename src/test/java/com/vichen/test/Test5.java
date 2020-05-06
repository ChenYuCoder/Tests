package com.vichen.test;

public class Test5 {
  public static void main(String[] args) {
    //待测百分比
    //P(T)
    //    int[] data={5,10,75,80};
    //P(A)
    for (int i = 0; i < 100; i++) {
      double p = (double) i / 100.0;
      double c = cFromeP(p);
      System.out.printf("PE=%f C=%f\n", i / 100.0, c);
    }
  }

  private static double cFromeP(double p) {
    if (p == 0.0) {
      return 0.0;
    }
    double dUp = p;
    double dLow = 0.0;
    double dMid = p;
    double dPLast = 1.0;
    while (true) {
      dMid = (dUp + dLow) / 2.0;
      double dPtestd = pFromC(dMid);
      double abs = Math.abs(dPtestd - dPLast);
      if (abs <= 0.0)
        break;
      if (dPtestd > p)
        dUp = dMid;
      else
        dLow = dMid;
      dPLast = dPtestd;
    }
    return dMid;
  }

  private static double pFromC(double c) {
    double dCurP = 0.0;
    double dPreSuessP = 0.0;
    double dPE = 0;
    int nMaxFail = (int) Math.ceil(1.0 / c);
    for (int i = 1; i <= nMaxFail; ++i) {
      dCurP = Math.min(1.0, i * c) * (1 - dPreSuessP);
      dPreSuessP += dCurP;
      dPE += i * dCurP;
    }
    return 1.0 / dPE;
  }

}
